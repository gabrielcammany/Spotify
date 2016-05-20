package model;

import java.util.ArrayList;

public class Data {

	private static ArrayList<Sessio> aSessio;
	private static ArrayList<User> user;
	private static ArrayList<Canco> alMusica;
	private static ArrayList<sLlista> update;

	public static ArrayList<Sessio> getaSessio() {
		return aSessio;
	}

	public static void setaSessio(ArrayList<Sessio> aSessio) {
		Data.aSessio = aSessio;
	}

	public static ArrayList<User> getUsers() {
		return user;
	}

	public static void setUsers(ArrayList<User> users) {
		user = users;
	}
	
	public static void addSessio(Sessio s){
		if(aSessio == null)aSessio = new ArrayList<Sessio> ();
		Data.aSessio.add(s);
	}
	
	public static void removeSessio(Sessio s){
		Data.aSessio.remove(s);
	}

	public static ArrayList<Canco> getAlMusica() {
		return alMusica;
	}

	public static void setAlMusica(ArrayList<Canco> alMusica) {
		Data.alMusica = alMusica;
	}
	
	public static void esborraCanco (int idCanco) {
		for (int i = 0; i < alMusica.size(); i++) {
			if (alMusica.get(i).getIdCanco() == idCanco) {
				alMusica.remove(i);
			}
		}
		
	}
	public static void esborraUser (int idUsuari) {
		for (int i = 0; i < user.size(); i++) {
			if (user.get(i).getId_usuari() == idUsuari) {
				user.remove(i);
			}
		}
		
	}
	

}
