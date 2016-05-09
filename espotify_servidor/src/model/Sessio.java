package model;

import java.util.ArrayList;

public class Sessio {
	private ArrayList<Llistes> llistes = new ArrayList<Llistes>();
	private User user = new User();
	private int idSessio;
	
	public int getIdSessio() {
		return idSessio;
	}
	public void setIdSessio(int idSessio) {
		this.idSessio = idSessio;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ArrayList<Llistes> getLl() {
		return llistes;
	}
	public void setLl(ArrayList<Llistes> ll) {
		this.llistes = ll;
	}
	
	
}
