package model;

import java.util.ArrayList;

public class Data {

	private static ArrayList<Sessio> aSessio;
	private static ArrayList<User> Users;

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


}
