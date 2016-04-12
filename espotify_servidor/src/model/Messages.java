package model;

import java.util.Arrays;

public class Messages {
	
	public Messages() {}

	public boolean tableMessages(String m){
		boolean ok = true;
		User user =new User();
		String[] s = m.split(" ");
		String[] st = s[1].split(":");
		String[] up = st[1].split("/");
		if(st[0].equals("user")){
			user.setNickname(up[0]);
			user.setPassword(up[1]);
			user.comprovaUser(user);
			System.out.println("USER OK!");
		}else{
			System.out.println("USER KO!");
		}
		return ok;
	}
}
