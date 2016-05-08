package model;

import java.util.ArrayList;

import controller.SocketController;

public class Usuaris {
	private static ArrayList<User> alUser;
	SocketController message;
	
	public Usuaris () {
		SocketController message = new SocketController();
		alUser = message.selectUsers(true, null);
	}
	
	public ArrayList<User> getMusica() {
		return alUser;
	}
	
	public ArrayList<User> getUsuaris() {
		return alUser;
	}
}
