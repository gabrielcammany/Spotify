package model;

import java.util.ArrayList;

import controller.SocketController;

/**
 * classe encarregada de la gestio de les estructures d'emmagatzematge de la
 * musica disponible al sistema
 * @author carlaurrea
 *
 */

public class Musica {
	public static ArrayList<Canco> alMusica;
	SocketController message;
	
	public Musica () {
		SocketController message = new SocketController();
		alMusica = message.selectSongs();
		
	}
	
	public ArrayList<Canco> getMusica() {
		return alMusica;
	}

}
