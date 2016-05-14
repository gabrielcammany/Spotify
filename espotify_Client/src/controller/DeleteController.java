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
import network.InfoServidor;
import view.FinestraReproduccio;

public class DeleteController implements MouseListener {
	
	
	private JPopupMenu popupMenu;
	private JMenuItem unfollowItem;
	private FinestraReproduccio finestraReproduccio;
	
	public DeleteController(FinestraReproduccio finestraReproduccio) {
		this.finestraReproduccio = finestraReproduccio;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
					// TODO Auto-generated method stub
					
					String nom = (String) finestraReproduccio.getTaulaFollowing().getValueAt(finestraReproduccio.getTaulaFollowing().getSelectedRow(), 0);
					
					InfoServidor inf = new InfoServidor();
					inf.unfollow(nom);
					
					
		        	finestraReproduccio.getModel().removeRow(finestraReproduccio.getTaulaMusica().getSelectedRow());
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
