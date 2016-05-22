package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.FinestraReproduccio;

/**
 * controlador de les estrelles
 *
 */
public class EstrellesController extends MouseAdapter {
	private int estrelles;
	private FinestraReproduccio fReproduccio;
	private String nomCanco;
	
	
	public EstrellesController(FinestraReproduccio fReproduccio, int estrelles, String nomCanco) {
		this.estrelles = estrelles;
		this.fReproduccio = fReproduccio;
		this.nomCanco = nomCanco;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("ESTRELLES: " + estrelles);
		ControladorFinestres.votacio(estrelles, nomCanco);
	}
		
	
}
