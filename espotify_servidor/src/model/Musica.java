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
	private static ArrayList<Canco> alMusica;

	

	SocketController message;
	
	public Musica () {
		SocketController message = new SocketController();
		alMusica = message.selectSongs();
		
	}
	public static void setMusica(ArrayList<Canco> alMusica) {
		Musica.alMusica = alMusica;
	}
	public ArrayList<Canco> getMusica() {
		return alMusica;
	}

}
