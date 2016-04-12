package model;

import java.util.Date;
import java.util.LinkedList;

import javax.xml.ws.Response;

import controller.ButtonsController;

public class User {
	private String nickname;
	private String password;
	private String data_reg;
	private String data_ult;
	
	
	private LinkedList<User> lluser;
	
	public LinkedList<User> getLluser() {
		return lluser;
	}


	public void setLluser(LinkedList<User> lluser) {
		this.lluser = lluser;
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

	
	public User(){
		this.lluser =new LinkedList<User>();
	}
	
	
	public void creaCotxe(String cad){
		
	}
	
	boolean comprovaUser(User user){
		boolean ok = true;
		Query q = new Query();
		String response;
		ButtonsController controller = new ButtonsController();
		q.queryList(0, user);
		response = q.queryList(1, user);
		ok = controller.inserirUser(response);
		return ok;
	}
	
}
