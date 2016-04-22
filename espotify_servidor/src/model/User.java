package model;

import java.util.LinkedList;

import controller.ButtonsController;

public class User {
	private String nickname;
	private String password;
	private String data_reg;
	private String data_ult;
	private ButtonsController controller;
	
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
	
	public boolean insertUser(User user){
		boolean ok = true;
		Query q = new Query();
		String response;
		controller = new ButtonsController();
		q.queryList(0, user);
		response = q.queryList(1, user);
		ok = controller.inserirUser(response);
		return ok;
	}
	public String verifyUser(User user){
		String response;
		Query q =new Query();
		String select = q.queryList(2, user);
		System.out.println("## "+select+" ##");
		controller = new ButtonsController();
		response= controller.selectUser(select);
		return response;
	}
	
}
