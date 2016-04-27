package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.UnsupportedEncodingException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.FinestraReproduccio;

/**
 * Controlador encarregat de gestionar quina musica llistar 
 * (disponible, propia, following)
 * 
 *
 */

public class ControladorLlistar implements MouseListener {
	
	private String opcio;
	

	private FinestraReproduccio fReproduccio;
	private ControladorFinestres cFinestra;
	
	
	public ControladorLlistar(String Opcio,ControladorFinestres cFinestra) {
		this.opcio =Opcio;
		this.cFinestra = cFinestra;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (opcio) {
		case "disponible":
			System.out.println("click disponible");
			cFinestra.novaOpcio(opcio);
			break;
		case "propia":
			System.out.println("click propia");
			cFinestra.novaOpcio(opcio);
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
		if (e.getSource() instanceof JLabel) {
			JLabel aux = (JLabel)e.getSource();
			aux.setForeground(new Color(254,254,254));
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof JLabel) {
			JLabel aux = (JLabel)e.getSource();
			aux.setForeground(new Color(164,164,164));
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public String getOpcio() {
		return opcio;
	}

	public void setOpcio(String opcio) {
		this.opcio = opcio;
	}

	public void llistarCanco(){
		
	}
	
	
	

}