package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import network.InfoServidor;

public class ControladorBotons implements MouseListener{
	
	JTextField jtNomUsuariBusca;
	
	public ControladorBotons (JTextField jtNomUsuariBusca) {
		this.jtNomUsuariBusca = jtNomUsuariBusca;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		new InfoServidor().demanaUser(jtNomUsuariBusca.getText());

		
		
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
