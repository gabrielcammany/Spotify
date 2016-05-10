package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ConnectionPropertiesTransform;

import model.Canco;
import model.Llistes;
import model.Musica;
import model.Query;
import model.User;
import network.ConectorDB;
import network.MessageServiceWorker;


public class SocketController {

	private ConectorDB conn;
	private Musica m;

	public SocketController() {
		conn = new ConectorDB("dpo_root", "sinminus", "bd_espotifi", 3306);
	}

	public String verifyUser(User user){
		String response;
		Query q =new Query();

		String select = q.queryList(2, user);
		System.out.println("## "+select+" ##");
		response= selectUser(select);
		return response;
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


	public void registroUsuario(String usuario, String password){
		User user =new User(usuario,password);

		//Comprovamos el nombre de usuario pero no validamos la contrase�a
		String result = verifyUser(user);
		System.out.println("## "+result+" ##");
		if(result.equals("error")){
			Query q = new Query();
			String response;
			String cad = q.queryList(0, user);
			response = q.queryList(1, user);

			conn.insertQuery(response);

			System.out.println("User: '"+user.getNickname()+"' Inserit correctament.");
		}else{
			System.out.println("[Servidor] L'usari '"+user.getNickname()+"' ja es troba registrat.");
		}

		//System.out.println(user.verifyUser(user));

	}

	public Boolean loginUser(String usuario, String password){
		User user =new User(usuario,password);
System.out.println("hola bro ");
		String result = verifyUser(user);
		System.out.println("adios bro"+ result);
		if(result.equals("-1"))return true;
		return false;
		//si user no es troba registrat cal notificar al client.

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
				c.setNom(responseServer.getString("nom"));
				c.setAlbum(responseServer.getString("album"));
				c.setArtista(responseServer.getString("artista"));
				c.setPath(responseServer.getString("ubicacio"));
				c.setEstrelles(responseServer.getString("num_estrelles"));
				c.setnReproduccio(responseServer.getString("num_reproduccio"));

				System.out.println("[Servidor] "+c.getNom()+" amb path: "+c.getPath()+" num reproduccions: "+c.getnReproduccio());
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
	public ArrayList<User> selectUsers(boolean i, String nom){
		Query q = new Query();
		ResultSet responseServer;
		if(i){
			responseServer = conn.selectQuery(q.queryList(5, null));
		}else{
			responseServer = conn.selectQuery(q.queryList(11, nom));
		}

		ArrayList<User> alUser = new ArrayList<User>();
		try {

			while (responseServer.next()) {
				User u = new User();
				u.setId_usuari(responseServer.getInt("id_usuaris"));
				u.setNickname(responseServer.getString("nickname"));
				u.setPassword(responseServer.getString("password"));
				u.setData_reg(responseServer.getString("data_reg"));
				u.setData_ult(responseServer.getString("data_ult"));

				System.out.println("[Servidor] Usuari ' "+u.getNickname()+" '");
				alUser.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return alUser;
	}

	public int songRequest(String nCanco, String nArtista){
		m = new Musica();
		ArrayList<Canco> allMusic = m.getMusica();
		int size = allMusic.size();
		int i = 0;
		boolean trobat =  false;
		
		while( i <size && !trobat){
			String comparaCanco = new String (allMusic.get(i).getNom());
			String comparaArtista = new String(allMusic.get(i).getArtista());
			
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
		allMusic.set(i,c);
		m.setMusica(allMusic);
		String response = q.queryList(6,c);
		conn.updateQuery(response);
		
		return i;
	}

	public ArrayList<Llistes> omplirLlistes(int id_user){
		ArrayList<Llistes> ll = new ArrayList<Llistes>();
		Query q =new Query();
		ArrayList<Canco> allCanco = new ArrayList<Canco>();
		m = new Musica();
		allCanco = m.getMusica();

		ResultSet responseServer = conn.selectQuery(q.queryList(7,id_user));

		try {

			while (responseServer.next()) {
				Llistes al = new Llistes();
				ArrayList<Integer> aCancons = new ArrayList<Integer>();

				al.setId_llistes(responseServer.getInt("id_llista"));

				

				ResultSet responseServer2 = conn.selectQuery(q.queryList(8,al.getId_llistes()));
				while (responseServer2.next()) {
					al.setNom_llista(responseServer2.getString("nom_llista"));
				}
				System.out.println("[omplirLlistes]# "+al.getNom_llista());
				
				ResultSet responseServer3 = conn.selectQuery(q.queryList(9,al.getId_llistes()));
				while (responseServer3.next()) {
					aCancons.add(responseServer3.getInt("id_canco"));
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
