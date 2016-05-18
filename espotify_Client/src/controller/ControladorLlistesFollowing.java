package controller;

import java.awt.event.MouseListener;

import javax.swing.JList;

import com.sun.glass.events.MouseEvent;

public class ControladorLlistesFollowing implements MouseListener  {
	

		private String seleccio;
		private JList lista;
		
		public ControladorLlistesFollowing(JList lista) {
			
			this.lista = lista;			
		}
		
		
		
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Han premut: " + lista.getSelectedIndex());
			ControladorFinestres.actualitzaTablaLlistesFollowing(lista.getSelectedIndex());
			
		}
		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

}


