package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

public class ControladorLlistesMusica implements MouseListener{
	

	private String seleccio;
	private JList lista;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Han premut: " + lista.getSelectedIndex());
		ControladorFinestres.actualitzaTablaLlistaPropia(lista.getSelectedIndex());
	}
	public ControladorLlistesMusica(JList lista) {


		this.lista = lista;
		
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
