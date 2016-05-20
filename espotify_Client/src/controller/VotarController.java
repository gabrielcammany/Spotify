package controller;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import model.Canco;
import network.InfoServidor;
import view.FinestraReproduccio;

public class VotarController extends MouseAdapter {
	
	
	private JPopupMenu popupMenu;
	private JMenuItem estrella1;
	private JMenuItem estrella2;
	private JMenuItem estrella3;
	private JMenuItem estrella4;
	private JMenuItem estrella5;
	private JMenuItem eliminar;
	private FinestraReproduccio finestraReproduccio;
	
	public VotarController(FinestraReproduccio finestraReproduccio) {
		this.finestraReproduccio = finestraReproduccio;
	}
	
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (SwingUtilities.isRightMouseButton(e))
        {
            // han apretat el boto dret
			
			popupMenu = new JPopupMenu();
			eliminar = new JMenuItem("Eliminar");
	        estrella1 = new JMenuItem("1 estrella");
	        estrella2 = new JMenuItem("2 estrella");
	        estrella3 = new JMenuItem("3 estrella");
	        estrella4 = new JMenuItem("4 estrella");
	        estrella5 = new JMenuItem("5 estrella");
	        
	       //afegim al menu
	        popupMenu.add(eliminar);
	        popupMenu.add(estrella1);
	        popupMenu.add(estrella2);
	        popupMenu.add(estrella3);
	        popupMenu.add(estrella4);
	        popupMenu.add(estrella5);
			
			// agafem l'event source
	        Component b=(Component)e.getSource();
	        
	        // afafem la localitzacio del punt en la pantalla
	        Point p=b.getLocationOnScreen();
	        
	        //el mostrem
	        popupMenu.show(b,e.getX(),e.getY());
	        
	        String nomCanco = (String) finestraReproduccio.getTaulaLlistaMusicaFollowing().getValueAt(finestraReproduccio.getTaulaLlistaMusicaFollowing().getSelectedRow(), 0);
	        
	        eliminar.addMouseListener(new DeleteController(finestraReproduccio, "llistaPropia"));
	        estrella1.addMouseListener(new EstrellesController(finestraReproduccio,1, nomCanco));
	        estrella2.addMouseListener(new EstrellesController(finestraReproduccio,2, nomCanco));
	        estrella3.addMouseListener(new EstrellesController(finestraReproduccio,3, nomCanco));
	        estrella4.addMouseListener(new EstrellesController(finestraReproduccio,4, nomCanco));
	        estrella5.addMouseListener(new EstrellesController(finestraReproduccio,5, nomCanco));
        }
	}

}
