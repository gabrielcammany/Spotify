package controller;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import model.Canco;
import model.User;
import network.InfoServidor;
import view.FinestraReproduccio;

public class DeleteController implements MouseListener {
	
	private String ubicacio;
	private JPopupMenu popupMenu;
	private JMenuItem unfollowItem;
	private FinestraReproduccio finestraReproduccio;
	
	public DeleteController(FinestraReproduccio finestraReproduccio, String ubicacio) {
		this.finestraReproduccio = finestraReproduccio;
		this.ubicacio = ubicacio;
		System.out.println("UBICACIO: ");
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (ubicacio.equals("llistaPropia")) {
			String nom = (String) finestraReproduccio.getTaulaLlistaMusicaFollowing().getValueAt(finestraReproduccio.getTaulaLlistaMusicaFollowing().getSelectedRow(), 0);	
			String nomLlista = (String) User.getlPropies().get(finestraReproduccio.getLlistesPropies().getSelectedIndex()).getNom_llista();
			System.out.println("QUIERES BORRAR UNA CANCION DE UNA LISTA " + nom + nomLlista);
			ControladorFinestres.getInfoServidor().eliminarCancoLlista(nom, nomLlista);
		
		}else {
		
			if (SwingUtilities.isRightMouseButton(e))
			{
				// han apretat el boto dret

				popupMenu = new JPopupMenu();
				unfollowItem = new JMenuItem("Unfollow");
				//afegim al menu
				popupMenu.add(unfollowItem);	

				// agafem l'event source
				Component b=(Component)e.getSource();

				// afafem la localitzacio del punt en la pantalla
				Point p=b.getLocationOnScreen();

				//el mostrem
				popupMenu.show(b,e.getX(),e.getY());	


				unfollowItem.addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mousePressed(MouseEvent e) {


						String nom = (String) finestraReproduccio.getTaulaFollowing().getValueAt(finestraReproduccio.getTaulaFollowing().getSelectedRow(), 0);
						ControladorFinestres.getInfoServidor().unfollow(nom);
						System.out.println("JODER");			

						finestraReproduccio.getModel().removeRow(finestraReproduccio.getTaulaFollowing().getSelectedRow());
						finestraReproduccio.getModel().fireTableDataChanged();


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
