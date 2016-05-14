package model;

import java.util.ArrayList;

public class Data {

	private static ArrayList<Sessio> aSessio;
	private static ArrayList<User> Users;
	private static ArrayList<Canco> alMusica;

	public static ArrayList<Sessio> getaSessio() {
		return aSessio;
	}

	public static void setaSessio(ArrayList<Sessio> aSessio) {
		Data.aSessio = aSessio;
	}

	public static ArrayList<User> getUsers() {
		return Users;
	}

	public static void setUsers(ArrayList<User> users) {
		Users = users;
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
	

}
