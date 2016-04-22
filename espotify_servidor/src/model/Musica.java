package model;

import java.util.ArrayList;

/**
 * classe encarregada de la gestio de les estructures d'emmagatzematge de la
 * musica disponible al sistema
 * @author carlaurrea
 *
 */

public class Musica {
	private static ArrayList<Canco> alMusica;
	Messages message;
	
	public Musica () {
		Messages message = new Messages();
		alMusica = message.obteMusicaDisponible(); 
		
	}
	
	public ArrayList<Canco> getMusica() {
		return alMusica;
	}

}
