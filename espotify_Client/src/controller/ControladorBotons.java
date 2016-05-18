package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import network.InfoServidor;


/**
 * control del buscar usuari a fer follow
 *
 */
public class ControladorBotons implements MouseListener{
	
	JTextField jtNomUsuariBusca;
	
	public ControladorBotons (JTextField jtNomUsuariBusca) {
		this.jtNomUsuariBusca = jtNomUsuariBusca;
	}
	
	/**
	 * control del buscar usuari a fer follow
	 *@param rep un mouse event del boto buscar usuari
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		//si el text field de buscar usuari a fer follow no esta buit enviarem una pericio de busqueda
		if(!jtNomUsuariBusca.getText().isEmpty()){
			new InfoServidor().demanaUser(jtNomUsuariBusca.getText());
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
