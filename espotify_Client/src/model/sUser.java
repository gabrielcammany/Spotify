package model;

import java.io.Serializable;

public class sUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nickname;
	private int id_usuari;
	


	public int getId_usuari() {
		return id_usuari;
	}
	
	public void setId_usuari(int id_usuari) {
		this.id_usuari = id_usuari;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public sUser(){}
	public sUser(String usuario,int id){
		this.nickname = usuario;
		this.id_usuari = id;
	}
	
	
}
