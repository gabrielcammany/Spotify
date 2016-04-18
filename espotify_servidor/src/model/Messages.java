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
		user.setNickname(up[0]);
		user.setPassword(up[1]);
		if(st[0].equals("user")){
			user.insertUser(user);
			System.out.println("User: "+user.getNickname()+"Inserit correctament.");
			
			//System.out.println(user.verifyUser(user));
		}else{
			System.out.println("User: "+user.getNickname()+".");
			//System.out.println("Comprovacio usuari:");
			user.verifyUser(user);
		}
		return ok;
	}
}
