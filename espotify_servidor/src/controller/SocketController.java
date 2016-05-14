package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Canco;
import model.Data;
import model.Llistes;
import model.Query;
import model.User;
import model.sUser;
import network.ConectorDB;


public class SocketController {

	private ConectorDB conn;
	private User user;

	
	public SocketController() {
		//json donde estas!!!
		conn = new ConectorDB("dpo_root", "sinminus", "bd_espotifi", 3306);
	}
	
	public User getUsuariActual() {
		return user;
	}
	
	/**
	 * Metode que s'encarrega d'afegir a la bbdd la nova canco que s'ha afegit
	 * des del servidor
	 * @param descripcio
	 */
	public void registroAddCanco(String descripcio) {
		Query q = new Query();
		String cad = q.queryList(10, descripcio);
		conn.insertQuery(cad);
	}


	public int registroUsuario(String usuario, String password){
		user =new User(usuario,password);

		//Comprovamos el nombre de usuario pero no validamos la contrase�a
		int result = verifyUser(usuario,password);
		if(result == 0){
			Query q = new Query();
			String response;
			response = q.queryList(1, user);
			
			ConectorDB.insertQuery(response);

			System.out.println("User: '"+user.getNickname()+"' Inserit correctament.");
			selectUsers();
			return verifyUser(usuario, password);
		}else{
			System.out.println("[Servidor] L'usari '"+user.getNickname()+"' ja es troba registrat.");
			return result;
		}
		//System.out.println(user.verifyUser(user));

	}

	public int verifyUser(String usuario, String password){
		int id = 0;
		for(Object u : Data.getUsers()){
			
			if(((User)u).getNickname().toLowerCase().equals(usuario.toLowerCase())){
				if(((User)u).getPassword().equals(password))return id = ((User)u).getId_usuari();
			}
		}
		return id;
	}


	public String selectUser(String query){
		ResultSet responseServer = conn.selectQuery(query);
		String nickname= null;
		int i = 0 ;
		if(responseServer != null){
			try {
				while (responseServer.next()) {
					i++;
					nickname = responseServer.getString("nickname");
					System.out.println("[Servidor] Response server: '"+nickname+"'." );
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(i>0)return nickname;
		return "-1";
	}


	public ArrayList<Canco> selectSongs(){

		Query q = new Query();
		
		
		
		ResultSet responseServer = conn.selectQuery(q.queryList(4, null));
		ArrayList<Canco> alMusica = new ArrayList<Canco>();
		try {

			while (responseServer.next()) {
				Canco c = new Canco();
				c.setIdCanco(responseServer.getInt("id_canco"));
				c.setNom(responseServer.getString("nom"));
				c.setAlbum(responseServer.getString("album"));
				c.setArtista(responseServer.getString("artista"));
				c.setPath(responseServer.getString("ubicacio"));
				c.setEstrelles(responseServer.getString("num_estrelles"));
				c.setnReproduccio(responseServer.getString("num_reproduccio"));
				System.out.println("[Servidor]id_canco '"+c.getIdCanco()+"'.");
				//System.out.println("[Servidor] "+c.getNom()+" amb path: "+c.getPath()+" num reproduccions: "+c.getnReproduccio());
				alMusica.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size = alMusica.size();
		for(int i = 0;i<size;i++)System.out.println("Canco numero "+i+" -->"+alMusica.get(i).getNom());

		return alMusica;
	}
	
	public ArrayList<sUser> selectSUsers (int id){
		ArrayList<sUser> aux = new ArrayList<sUser>();
		Query q = new Query();
		ResultSet responseServer = conn.selectQuery(q.queryList(11, id));
		
		
		try {
			while (responseServer.next()) {
				sUser u = new sUser();
				u.setId_usuari(responseServer.getInt("id_follower"));
				for(User user : Data.getUsers())if(u.getId_usuari()==user.getId_usuari())u.setNickname(user.getNickname());
				aux.add(u);

				System.out.println("[Servidor] Usuari ' "+u.getId_usuari()+" '");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return aux;
	}
	
	public ArrayList<User> selectUsers(){
		Query q = new Query();
		ResultSet responseServer;
		ArrayList<User> aUsers = new ArrayList<User>();

		responseServer = conn.selectQuery(q.queryList(5, null));

		try {

			while (responseServer.next()) {
				User u = new User();
				u.setId_usuari(responseServer.getInt("id_usuaris"));
				u.setNickname(responseServer.getString("nickname"));
				u.setPassword(responseServer.getString("password"));
				u.setData_reg(responseServer.getString("data_reg"));
				u.setData_ult(responseServer.getString("data_ult"));
				aUsers.add(u);

				System.out.println("[Servidor] Usuari ' "+u.getNickname()+" '");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aUsers;
	}

	public int songRequest(String nCanco, String nArtista){
		ArrayList<Canco> allMusic = Data.getAlMusica();
		int size = Data.getAlMusica().size();
		int i = 0;
		boolean trobat =  false;
		
		while( i <size && !trobat){
			String comparaCanco = new String (Data.getAlMusica().get(i).getNom());
			String comparaArtista = new String(Data.getAlMusica().get(i).getArtista());
			
			comparaCanco = comparaCanco.replace(" ", "");
			comparaArtista = comparaArtista.replace(" ", "");
			
			System.out.println("COMPARACION: pasan -> " + nCanco+ "  comparo -> "+ comparaCanco);
			System.out.println("COMPARACION: pasan -> " + nArtista+ "  comparo -> "+ comparaArtista);
			
			//if(allMusic.get(i).getNom().equals(nCanco) && allMusic.get(i).getArtista().replace(" ", "").equals(nArtista))trobat=true;
			if(comparaCanco.equals(nCanco) && comparaArtista.equals(nArtista))trobat=true;
			i++;
		}
		i--;
		
		Query q= new Query();
		Canco c = allMusic.get(i);
		Integer nRep = Integer.parseInt(c.getnReproduccio())+1;
		c.setnReproduccio(nRep.toString());
		System.out.println("QUE LE LLEGA  "+ c.getNom() + " " +c.getArtista());
		allMusic.set(i,c);
		Data.setAlMusica(allMusic);
		String response = q.queryList(6,c);
		conn.updateQuery(response);
		
		return i;
	}
	
	public void hacerFollow(Integer idUser,Integer idFollow){
		String aux = idUser.toString().concat("/"+idFollow.toString());
		conn.insertQuery(new Query().queryList(13, aux));
	}
	
	public void unfollow(Integer idSesio,String nom){
		int id = 0;
		for(User u : Data.getUsers())if(u.getNickname().toLowerCase().equals(nom.toLowerCase()))id = u.getId_usuari();
		ResultSet rs = conn.selectQuery(new Query().queryList(15, idSesio.toString().concat("/"+id)));
		try {
			int id2 =  0;
			while (rs.next())id2 = rs.getInt("id_user_follower");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.deleteQuery(new Query().queryList(14, id));
		
		
	}

	public ArrayList<Llistes> omplirLlistes(int id_user){
		ArrayList<Llistes> ll = new ArrayList<Llistes>();
		Query q =new Query();

		ResultSet responseServer = conn.selectQuery(q.queryList(7,id_user));

		try {

			while (responseServer.next()) {
				Llistes al = new Llistes();
				ArrayList<Integer> aCancons = new ArrayList<Integer>();
				int idACanco = 0 ;

				al.setId_llistes(responseServer.getInt("id_llista"));

				

				ResultSet responseServer2 = conn.selectQuery(q.queryList(8,al.getId_llistes()));
				while (responseServer2.next()) {
					al.setNom_llista(responseServer2.getString("nom_llista"));
				}
				System.out.println("[omplirLlistes]# "+al.getNom_llista());
				
				ResultSet responseServer3 = conn.selectQuery(q.queryList(9,al.getId_llistes()));
				while (responseServer3.next()) {
					
					aCancons.add(responseServer3.getInt("id_canco"));
					System.out.println("[SERVIDOR][Llista]id_canco '"+aCancons.get(idACanco));
					idACanco++;
				}
				al.setAllIdCanco(aCancons);
				ll.add(al);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}

}
