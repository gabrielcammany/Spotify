package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;


public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nickname;
	private String password;
	private String data_reg;
	private String data_ult;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getData_reg() {
		return data_reg;
	}


	public void setData_reg(String data) {
		this.data_reg = data;
	}


	public String getData_ult() {
		return data_ult;
	}


	public void setData_ult(String data_ult) {
		this.data_ult = data_ult;
	}

	public User(String usuario,String password){
		//this.alUser =new ArrayList<User>();
		this.nickname = usuario;
		this.password = password;
	}
	
	
}
