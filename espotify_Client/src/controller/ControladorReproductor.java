package controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import view.FinestraReproduccio;


/**
 * Controlador encarregat de gestionar els botons encarregats de reproduir la musica
 * (play/pausa, next/back)
 * 
 *
 */

public class ControladorReproductor implements MouseListener {
	
	private String opcio;
	
	
	
	public ControladorReproductor(String opcio) {
		this.opcio = opcio;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (opcio) {
		case "play":
			System.out.println("click play");
			break;
		case "next":
			System.out.println("click next");
			break;
		case "back":
			System.out.println("click back");
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
