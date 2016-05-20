package controller;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import view.FinestraReproduccio;

public class AddCancoLlistaController extends MouseAdapter {
	private FinestraReproduccio finestraReproduccio;
	JMenuItem afegir;
	JPopupMenu popupMenu;
	
	public AddCancoLlistaController(FinestraReproduccio finestraReproduccio) {
		this.finestraReproduccio = finestraReproduccio;
		
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			// han apretat el boto dret

			popupMenu = new JPopupMenu();
			afegir = new JMenuItem("Afegir a una llista");
			//afegim al menu
			popupMenu.add(afegir);	

			// agafem l'event source
			Component b=(Component)e.getSource();

			// afafem la localitzacio del punt en la pantalla
			Point p=b.getLocationOnScreen();

			//el mostrem
			popupMenu.show(b,e.getX(),e.getY());
			
			afegir.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//Recuperem el nom de la canco que l'usuari vol afegir
					String nomCanco = (String) finestraReproduccio.getTaulaMusica().getValueAt(finestraReproduccio.getTaulaMusica().getSelectedRow(), 0);
					System.out.println("AFEGIR LA CANÇO: " + nomCanco);
					
					//Obrem dialog i recuperem el nom de la llista
					String nomLlista = JOptionPane.showInputDialog(finestraReproduccio,"Introdueix el nom de la llista");
				}
			});
		}
	}
	
	
}
