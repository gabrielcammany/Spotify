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
		if(st[0].equals("Usuari")){
			String[] up = st[1].split("/");
			user.setNickname(up[0]);
			user.setPassword(up[1]);
			//Comprovamos el nombre de usuario pero no validamos la contraseña
			String result = user.verifyUser(user);
			System.out.println("'"+result+"'");
			if(result.equals("error")){
				user.insertUser(user);
				System.out.println("User: '"+user.getNickname()+"' Inserit correctament.");
			}else{
				System.out.println("[Servidor] L'usari '"+user.getNickname()+"' ja es troba registrat.");
			}
			
			//System.out.println(user.verifyUser(user));
		}
		if(st[0].equals("Loguin_Usuari")){
			System.out.println("User: "+user.getNickname()+".");
			//System.out.println("Comprovacio usuari:");
			String result = user.verifyUser(user);
			if(result.equals("null"))System.out.println("[Servidor] L'usuari no es troba registrat");
			//si user no es troba registrat cal notificar al client.
		}
		if(st[0].equals("Request")){
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
