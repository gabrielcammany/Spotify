package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

public class ControladorLlistesMusica implements MouseListener{
	
	ControladorFinestres cFinestra;
	String seleccio;
	JList lista;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Han premut: " + lista.getSelectedIndex());
		cFinestra.actualitzaTablaLlistaPropia(lista.getSelectedIndex());
	}
	public ControladorLlistesMusica(JList lista,ControladorFinestres cFinestra) {

		this.cFinestra = cFinestra;
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
