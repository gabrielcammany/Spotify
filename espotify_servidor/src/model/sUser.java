package model;

import java.io.Serializable;


/**
 * 
 * Clase de los usuarios enviados al cliente, solo tienen informacion visual (sin pswd)
 *
 */
public class sUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nickname;
	private int idUsuari;
	


	public int getId_usuari() {
		return idUsuari;
	}
	
	


	public void setId_usuari(int id_usuari) {
		this.idUsuari = id_usuari;
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
		this.idUsuari = id;
	}
	
	
}
