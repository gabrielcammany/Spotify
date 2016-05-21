package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import model.User;

/**
 * Controlador encarregat de gestionar quina musica llistar 
 * (disponible, propia, following)
 * 
 *
 */

public class ControladorLlistar implements MouseListener {
	
	private String opcio;
	
	
	public ControladorLlistar(String Opcio) {
		this.opcio =Opcio;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (opcio) {
		case "disponible":
			System.out.println("click disponible");
			ControladorFinestres.novaOpcio(opcio);
			
			break;
		case "propia":
			System.out.println("click propia");
			ControladorFinestres.novaOpcio(opcio);
			break;
		case "llistesfollowing":
			System.out.println("click llistesfollowing");
			if(User.getlFollowing() == null)ControladorFinestres.getInfoServidor().demanarLlistesFollowing();
			ControladorFinestres.novaOpcio(opcio);
			break;
		case "usuarisfollowing":
			System.out.println("click usuarisfollowing");
			ControladorFinestres.novaOpcio(opcio);
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