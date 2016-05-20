package model;

import java.util.ArrayList;

public class User{
	private static String nickname;
	private static int id_usuari;
	private static ArrayList<Llistes> lPropies;
	private static ArrayList<Llistes> lFollowing;
	private static ArrayList<sUser> lUsersFollowing;

	public static ArrayList<Llistes> getlFollowing() {
		return lFollowing;
	}
	
	public static void setlFollowing(ArrayList<Llistes> lFollowing) {
		User.lFollowing = lFollowing;
	}
	
	
	public static int getId_usuari() {
		return id_usuari;
	}


	public static void setId_usuari(int id_usuari) {
		User.id_usuari = id_usuari;
	}


	public static String getNickname() {
		return nickname;
	}


	public static void setNickname(String nickname) {
		User.nickname = nickname;
	}



	public User(String usuario,int id){
		//this.alUser =new ArrayList<User>();
		User.nickname = usuario;
		User.id_usuari = id;
	}


	public static ArrayList<Llistes> getlPropies() {
		if (lPropies == null) {
			lPropies = new ArrayList<Llistes>();
		}
		return lPropies;
	}


	public static void setlPropies(ArrayList<Llistes> lPropies) {
		User.lPropies = lPropies;
	}

	public static ArrayList<sUser> getlUsersFollowing() {
		return lUsersFollowing;
	}

	public static void setlUsersFollowing(ArrayList<sUser> lUsersFollowing) {
		User.lUsersFollowing = lUsersFollowing;
	}
	
	
}
