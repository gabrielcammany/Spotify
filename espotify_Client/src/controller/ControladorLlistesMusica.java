package controller;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import model.Llistes;
import model.User;
import view.FinestraReproduccio;

public class ControladorLlistesMusica implements MouseListener{
	

	private String seleccio;
	private JList lista;
	private FinestraReproduccio finestrareproduccio;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		ControladorFinestres.actualitzaTablaLlistaPropia(lista.getSelectedIndex());
		
		if (SwingUtilities.isRightMouseButton(e))
		{
			// han apretat el boto dret

			JPopupMenu popupMenu = new JPopupMenu();
			JMenuItem deleteItem = new JMenuItem("Delete");
			//afegim al menu
			popupMenu.add(deleteItem);	

			// agafem l'event source
			Component b=(Component)e.getSource();

			// afafem la localitzacio del punt en la pantalla
			Point p=b.getLocationOnScreen();

			//el mostrem
			popupMenu.show(b,e.getX(),e.getY());	


			deleteItem.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					Iterator<Llistes> iter = User.getlPropies().iterator();
					while (iter.hasNext()) {
					    Llistes str = iter.next();
					    if (str.getNom_llista().equals(lista.getSelectedValue())){
					    	System.out.println("ELIMINAR LA LLISTA " + str.getNom_llista());
							iter.remove();
							ControladorFinestres.getInfoServidor().eliminaLlista(String.valueOf(str.getId_llistes()));
						}
					
					}
					
					finestrareproduccio.getModel().fireTableDataChanged();


				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}
	}
	public ControladorLlistesMusica(JList lista,FinestraReproduccio finestrareproduccio) {
		this.lista = lista;
		this.finestrareproduccio = finestrareproduccio; 
		
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
