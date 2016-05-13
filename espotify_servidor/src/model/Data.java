package model;

import java.util.ArrayList;

public class Data {

	private static ArrayList<Sessio> aSessio;
	private static ArrayList<Object> Users;
	private static ArrayList<Canco> alMusica;

	public static ArrayList<Sessio> getaSessio() {
		return aSessio;
	}

	public static void setaSessio(ArrayList<Sessio> aSessio) {
		Data.aSessio = aSessio;
	}

	public static ArrayList<Object> getUsers() {
		return Users;
	}

	public static void setUsers(ArrayList<Object> users) {
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
	

}
