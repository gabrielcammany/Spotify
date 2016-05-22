package controller;

import java.awt.event.MouseListener;

import javax.swing.JList;

public class ControladorLlistesFollowing implements MouseListener  {
	

		private JList<String> lista;
		
		public ControladorLlistesFollowing(JList<String> lista) {
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


