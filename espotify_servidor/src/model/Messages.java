package model;

import java.util.ArrayList;


public class Messages {
	
	public Messages() {}

	public boolean tableMessages(String m){
		boolean ok = true;
		User user =new User();
		Canco can = new Canco();
		ArrayList<Canco> alMusica;
		String[] s = m.split(" ");
		String[] st = s[1].split(":");
		
		if(st[0].equals("user")){
			String[] up = st[1].split("/");
			user.setNickname(up[0]);
			user.setPassword(up[1]);
			user.insertUser(user);
			System.out.println("User: "+user.getNickname()+"Inserit correctament.");
			
			//System.out.println(user.verifyUser(user));
		}
		if(st[0].equals("userLog")){
			System.out.println("User: "+user.getNickname()+".");
			//System.out.println("Comprovacio usuari:");
			user.verifyUser(user);
		}
		if(st[0].equals("Request")){
			System.out.println("Samu a estado aki");
			alMusica = new ArrayList<Canco>();
			alMusica = can.consultaTotesCancons();
			System.out.println();
			int size = alMusica.size();
			for(int i = 0;i<size;i++)System.out.println("Canco numero "+i+" -->"+alMusica.get(i).getNom());
			
			
		}
		return ok;
	}
	
	public ArrayList<Canco> obteMusicaDisponible() {
		Canco can = new Canco();
		ArrayList <Canco> alMusica = new ArrayList<Canco>();
		alMusica = can.consultaTotesCancons();
		System.out.println();
		int size = alMusica.size();
		for(int i = 0;i<size;i++)System.out.println("Canco numero "+i+" -->"+alMusica.get(i).getNom());
		return alMusica;
	}
}
