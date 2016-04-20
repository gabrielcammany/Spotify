package controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import view.FinestraReproduccio;

/**
 * Controlador encarregat de gestionar quina musica llistar 
 * (disponible, propia, following)
 * 
 *
 */

public class ControladorLlistar implements MouseListener {
	
	private String opcio;
	
	
	
	public ControladorLlistar(String opcio) {
		this.opcio = opcio;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (opcio) {
		case "disponible":
			System.out.println("click disponible");
			break;
		case "propia":
			System.out.println("click propia");
			break;
		case "following":
			System.out.println("click following");
			break;

		default:
			break;
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}