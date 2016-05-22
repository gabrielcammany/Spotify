package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.Canco;
import model.Data;
import model.Llistes;
import model.Query;
import model.Sessio;
import model.User;
import model.sUser;
import network.ConectorDB;
import network.JsonConfig;

/**
 * 
 * Clase per controlar tots els sockets
 *
 */
public class SocketController {

	private static ConectorDB conn;
	private User user;

	
	public SocketController() {
		conn = new ConectorDB(JsonConfig.creaJson());
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
		ConectorDB.insertQuery(cad);
	}

	/**
	 * Funcio per registrar un usuari
	 * @param usuario
	 * @param password
	 * @return idUser
	 */
	public int registroUsuario(String usuario, String password){
		user =new User(usuario,password);

		//Comprovamos el nombre de usuario pero no validamos la contrase�a
		int result = verifyUser(usuario,password);
		if(result == 0){
			Query q = new Query();
			String response;
			response = q.queryList(1, user);
			
			ConectorDB.insertQuery(response);

			ArrayList<User> users = selectUsers();
			for(User u : users) if(u.getNickname().equals(usuario)) user.setId_usuari(u.getId_usuari());
			
			return user.getId_usuari();
			//return verifyUser(usuario, password);
		}else{
			return result;
		}

	}

	/**
	 * Funcio per verificar un usuari
	 * @param usuario
	 * @param password
	 * @return idUsuari o incorrecte
	 */
	public int verifyUser(String usuario, String password){
		int id = 0;
		for(Object u : Data.getUsers()){
			if(((User)u).getNickname().toLowerCase().equals(usuario.toLowerCase())){
				if(((User)u).getPassword().equals(password)) {
					id = ((User)u).getId_usuari();
					if(!Data.getaSessio().isEmpty()){
						for(Sessio s:Data.getaSessio()){
							if(s.getIdSessio() == id){
								return -1;
							}
						}
					}
					return id;
				}
			}
		}
		return id;
	}


	/**
	 * Funcio que demana un usuari
	 * @param query
	 * @return l'usuari demanat
	 */
	public String selectUser(String query){
		ResultSet responseServer = conn.selectQuery(query);
		String nickname= null;
		int i = 0 ;
		if(responseServer != null){
			try {
				while (responseServer.next()) {
					i++;
					nickname = responseServer.getString("nickname");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(i>0)return nickname;
		return "-1";
	}

/**
 * Funcio per demanar cancons
 * @return les cancons demanades
 */
	public ArrayList<Canco> selectSongs(){

		Query q = new Query();
		
		
		
		ResultSet responseServer = conn.selectQuery(q.queryList(4, null));
		ArrayList<Canco> alMusica = new ArrayList<Canco>();
		try {

			while (responseServer.next()) {
				Canco c = new Canco();
				c.setIdCanco(responseServer.getInt("id_canco"));
				c.setNom(responseServer.getString("nom"));
				c.setGenere(responseServer.getString("genere"));
				c.setAlbum(responseServer.getString("album"));
				c.setArtista(responseServer.getString("artista"));
				c.setPath(responseServer.getString("ubicacio"));
				c.setEstrelles(responseServer.getString("num_estrelles"));
				c.setnReproduccio(responseServer.getString("num_reproduccio"));
				c.setnVotacio(responseServer.getString("nVotacio"));
				
				alMusica.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return alMusica;
	}
	/**
	 * Funcio per demanar usuaris
	 * @param id
	 * @return usuaris demanats per id
	 */
	public ArrayList<sUser> selectSUsers (int id){
		ArrayList<sUser> aux = new ArrayList<sUser>();
		Query q = new Query();
		ResultSet responseServer = conn.selectQuery(q.queryList(11, id));
		
		if(responseServer == null) return aux;
		
		try {
			while (responseServer.next()) {
				sUser u = new sUser();
				u.setId_usuari(responseServer.getInt("id_follower"));
				for(User user : Data.getUsers())if(u.getId_usuari()==user.getId_usuari())u.setNickname(user.getNickname());
				aux.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		return aux;
	}
	
	/**
	 * Funcio per demanar tots els usuaris
	 * @param id
	 * @return usuaris
	 */
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

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aUsers;
	}
	
	/**
	 * peticio de canco
	 * @param nCanco
	 * @param nArtista
	 * @return id canco
	 */
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
		Data.setAlMusica(allMusic);
		String response = q.queryList(6,c);
		conn.updateQuery(response);
		
		return i;
	}
	/**
	 * Funcio per fer follow
	 * @param idUser
	 * @param idFollow
	 * @return ok o ko
	 */
	public int hacerFollow(Integer idUser,Integer idFollow){
		String aux = idUser.toString().concat("/"+idFollow.toString());
		ConectorDB.insertQuery(new Query().queryList(13, aux));
		return 1;
	}
	
	
	/**
	 * Funcio per fer unfollow
	 * @param idSesio
	 * @param nom
	 */
	public void unfollow(String idSesio,String nom){
		int id = 0;
		for(User u : Data.getUsers())if(u.getNickname().toLowerCase().equals(nom.toLowerCase()))id = u.getId_usuari();
		String[] data= new String[2];
		data[0] = idSesio;
		data[1] = String.valueOf(id);
		for(Sessio s:Data.getaSessio()){
			if(s.getIdSessio() == Integer.parseInt(idSesio)){
				for(int i = 0;i<s.getlUserFollow().size();i++){
					if(s.getlUserFollow().get(i).getNickname().toLowerCase().equals(nom.toLowerCase())){
						s.getlUserFollow().remove(i);
					}
				}
				
			}
		}
		
		conn.deleteQuery(new Query().queryList(14, data));
		
		
	}
	
	/**
	 * Funcio per omplir les llistes dels usuaris seguits
	 * @param lUserFollow
	 * @return arrau de llistes 
	 */
	public ArrayList<Llistes> omplirLlistesFollowing(ArrayList<sUser> lUserFollow ){
		ArrayList<Llistes> llFollowers = new ArrayList<Llistes>();
		if(!lUserFollow.isEmpty()){
			int size = lUserFollow.size();
			for(int i = 0;i<size;i++){
				ArrayList<Llistes> ll  = omplirLlistes(lUserFollow.get(i).getId_usuari());
				if(ll != null){
					Iterator<Llistes> iter = ll.iterator();
					while (iter.hasNext()) {
					    Llistes str = iter.next();
					    if(str.getPrivacitat() != 1){
							iter.remove();
						}
					}
					for(Llistes l : ll)llFollowers.add(l);
				}
			}
		}
		return llFollowers;
	}

	/**
	 * Funcio per omplir totes llistes 
	 * @param lUserFollow
	 * @return arrau de llistes 
	 */
	public ArrayList<Llistes> omplirLlistes(int id_user){
		ArrayList<Llistes> ll = new ArrayList<Llistes>();
		Query q =new Query();
		ResultSet responseServer = conn.selectQuery(q.queryList(7,id_user));

		if(responseServer == null) return null;
		
		try {

			while (responseServer.next()) {
				if(responseServer != null){
					Llistes al = new Llistes();
					ArrayList<Integer> aCancons = new ArrayList<Integer>();
					int idACanco = 0 ;
	
					al.setId_llistes(responseServer.getInt("id_llista"));
					
	
					ResultSet responseServer2 = conn.selectQuery(q.queryList(8,al.getId_llistes()));
					while (responseServer2.next()) {
						al.setNom_llista(responseServer2.getString("nom_llista"));
						
						al.setPrivacitat(responseServer2.getInt("privacitat"));
					
					}
					
					ResultSet responseServer3 = conn.selectQuery(q.queryList(9,al.getId_llistes()));
					while (responseServer3.next()) {
						
						aCancons.add(responseServer3.getInt("id_canco"));
						idACanco++;
					}
					al.setAllIdCanco(aCancons);
					ll.add(al);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}
	
	/**
	 * funcio per crear llista
	 * @param l
	 * @param id_usuari
	 * @return idLlista
	 */
	public int crearLlistes(Llistes l,String id_usuari){
		String[] info = new String[2];
		ConectorDB.insertQuery(new Query().queryList(23, l));
		ResultSet rs1 = conn.selectQuery(new Query().queryList(24,l));
		int idLlista = 0;
		try {
			while(rs1.next()){
				idLlista = rs1.getInt("id_llista");
			}
			info[0] = String.valueOf(idLlista);
			info[1] = id_usuari;
			ConectorDB.insertQuery(new Query().queryList(28,info));
			//faltara que se actualizen las listas
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idLlista;
		
	}
	/**
	 * funcio per afegir canco
	 * @param idCanco
	 * @param idLlista
	 * @param idUsuari
	 * @return boolean de comprovacio
	 */
	public boolean afegeixCanco(String idCanco, String idLlista,String idUsuari){
		int CancoiLlista[] = new int[2];
		CancoiLlista[0] = Integer.parseInt(idCanco);
		CancoiLlista[1] = Integer.parseInt(idLlista);
		boolean trobada = true;
		for(Sessio s : Data.getaSessio()){
			if(String.valueOf(s.getIdSessio()).equals(idUsuari)){
				for(Llistes l: s.getLPropies()){
					if(String.valueOf((l.getId_llistes())).equals(idLlista)){
						for(Integer i:l.getAllIdCanco()){
							if(i == Integer.parseInt(idCanco)){
								trobada = false;
								break;
							}
						}
						break;
					}
				}
				break;
			}
		}
		if(trobada){ConectorDB.insertQuery(new Query().queryList(32, CancoiLlista));return true;}
		return false;
		
		
	}
	public void votaCanco (String nomCanco , String estrelles) {
		Canco c = null;
		for (int i = 0; i < Data.getAlMusica().size(); i ++) {
			if(Data.getAlMusica().get(i).getNom().equals(nomCanco)) {
				c = Data.getAlMusica().get(i);
			}
		}
		Query q= new Query();
		int estrellesVotacio = Integer.parseInt(estrelles); 
		int nEstrelles = Integer.parseInt(c.getEstrelles())+estrellesVotacio;
		int nVotacio = Integer.parseInt(c.getnVotacio())+1;
		c.setEstrelles(Integer.toString(nEstrelles));
		c.setnVotacio(Integer.toString(nVotacio));
		String response = q.queryList(29,c);
		
		conn.updateQuery(response);
	
	}
	
	public ArrayList<Integer> actualitzaMusicaLlista(int id){
		ArrayList<Integer> songs = new ArrayList<Integer>();
		ResultSet responseServer = conn.selectQuery(new Query().queryList(9,id));
		if(responseServer != null){
			try {
				while (responseServer.next()) {
					songs.add(responseServer.getInt("id_canco"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			return null;
		}
		return songs;
	}
	
	public void eliminaLlista(int id, int idUsuari){
		conn.deleteQuery(new Query().queryList(31, id));
		conn.deleteQuery(new Query().queryList(33, id));
		conn.deleteQuery(new Query().queryList(34, id));
		for(Sessio s:Data.getaSessio()){
			if(s.getIdSessio() == idUsuari ){
				for(Llistes l:s.getLPropies()){
					if(l.getId_llistes() == id){
						s.getLPropies().remove(l);
						break;
					}
				}
			}else{
				for(Llistes l:s.getlFollowing()){
					if(l.getId_llistes() == id){
						s.getlFollowing().remove(l);
						break;
					}
				}
			}
		}
	}
	
	public void eliminaCancoLlista(String idUsuari, String nom, String nomLlista) {
		Canco c = null;
		int idLlista = 0;
		int idSessio = 0;
		for (int i = 0; i < Data.getAlMusica().size(); i ++) {
			if(Data.getAlMusica().get(i).getNom().equals(nom)) {
				c = Data.getAlMusica().get(i);
			}
		}

		for(int i = 0; i<Data.getaSessio().size();i++){
			if(Data.getaSessio().get(i).getIdSessio()==Integer.parseInt(idUsuari))idSessio = i;		
			break;
		}
		for (int i = 0; i < Data.getaSessio().get(idSessio).getLPropies().size(); i ++) {
			if(Data.getaSessio().get(idSessio).getLPropies().get(i).getNom_llista().equals(nomLlista)) {
				idLlista = Data.getaSessio().get(idSessio).getLPropies().get(i).getId_llistes();
				break;
			}
		}

		ResultSet rs3 = conn.selectQuery(new Query().queryList(30, Integer.toString(c.getIdCanco()).concat("/"+idLlista)));
		try {
			ArrayList<Integer> ids = new ArrayList<Integer>();
			while (rs3.next()) {
				idLlista = rs3.getInt("id_canco_llista");
				ids.add(idLlista);			
			}
			for (int i = 0; i<ids.size(); i++ ) {
				conn.deleteQuery(new Query().queryList(31, ids.get(i)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void updateData(String id, String dada){
		conn.updateQuery(new Query().queryList(39, dada.concat("/"+id)));
	}
}
