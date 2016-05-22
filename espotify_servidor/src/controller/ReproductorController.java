package controller;

import model.Reproductor;

/**
 * 
 * Clase que posa a reproduir les cancons que li pasis
 *
 */
public class ReproductorController {

	private Reproductor reproductor;
	public ReproductorController() {
		
		this.reproductor = new Reproductor();
	}
	
	
	public void reprodueix(String nom, String artista) {
		nom = nom.replace(" ", "");
		artista = artista.replace(" ", "");
		reproductor.setPath(nom, artista);
		reproductor.play();
	}
	
	public void pausa() {
	reproductor.pause();
	}
}
