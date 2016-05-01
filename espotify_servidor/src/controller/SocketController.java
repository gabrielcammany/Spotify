package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ConnectionPropertiesTransform;

import model.Canco;
import model.Query;
import model.User;
import network.ConectorDB;
import network.MessageServiceWorker;


public class SocketController {
	
	public ConectorDB conn = new ConectorDB("dpo_root", "sinminus", "bd_espotifi", 3306);
	
	public SocketController() {}
	
	public String verifyUser(User user){
		String response;
		Query q =new Query();
		
		String select = q.queryList(2, user);
		System.out.println("## "+select+" ##");
		response= selectUser(select);
		return response;
	}
	

	public void registroUsuario(String usuario, String password){
		User user =new User(usuario,password);
		
		//Comprovamos el nombre de usuario pero no validamos la contraseña
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
	
	public void loginUser(String usuario, String password){
		User user =new User(usuario,password);

		String result = verifyUser(user);
		if(result.equals("null"))System.out.println("[Servidor] L'usuari no es troba registrat");
		//si user no es troba registrat cal notificar al client.

	}
	
	
	public String selectUser(String query){
		ResultSet responseServer = conn.selectQuery(query);
		String nickname= null;
		int i = 0 ;
		try {
			while (responseServer.next()) {
				i++;
				nickname = responseServer.getString("nickname");
				System.out.println("[Servidor] Response server: '"+nickname+"'." );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i>0)return nickname;
		return "error";
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
public ArrayList<User> selectUsers(){
		Query q = new Query();
		
		ResultSet responseServer = conn.selectQuery(q.queryList(5, null));
		
		ArrayList<User> alUser = new ArrayList<User>();
		try {
			
			while (responseServer.next()) {
				User u = new User();
				
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
	/*User user =new User(usuario,password);
	
	//Comprovamos el nombre de usuario pero no validamos la contraseña
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
*/
	ArrayList<Canco> allMusic = selectSongs();
	int size = allMusic.size();
	int i = 0;
	boolean trobat =  false;
	while( i <size && !trobat){
		if(allMusic.get(i).getNom().equals(nCanco) && allMusic.get(i).getArtista().equals(nArtista))trobat=true;
		i++;
	}
	if(!trobat){
		return -1;
	}else{
		return i-1;
	}
	
	}
	
}
