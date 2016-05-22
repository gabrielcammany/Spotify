package controller;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import model.Canco;
import model.Llistes;
import model.User;
import view.FinestraReproduccio;

/**
 * controlador de llistar cancons
 *
 */
public class CancoLlistaController extends MouseAdapter {
	private FinestraReproduccio finestraReproduccio;
	JMenuItem afegir;
	JMenu submenu;
	JPopupMenu popupMenu;
	ArrayList<Canco> al;
	
	public CancoLlistaController(FinestraReproduccio finestraReproduccio, ArrayList<Canco> a) {
		this.finestraReproduccio = finestraReproduccio;
		this.al = a;
		
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			// han apretat el boto dret

			popupMenu = new JPopupMenu();
			submenu = new JMenu();
			afegir = new JMenuItem("Afegir a una llista");
			//afegim al menu
			popupMenu.add(afegir);
			popupMenu.add(submenu);	
			for(Llistes l: User.getlPropies()){
				JMenuItem aux = new JMenuItem(l.getNom_llista());
				submenu.add(aux);
				aux.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						//Recuperem el nom de la canco que l'usuari vol afegir
						if(finestraReproduccio.getTaulaMusica().getSelectedRow() != -1){
							String nomCanco = (String) finestraReproduccio.getTaulaMusica().getValueAt(finestraReproduccio.getTaulaMusica().getSelectedRow(), 0);
							String artista = (String) finestraReproduccio.getTaulaMusica().getValueAt(finestraReproduccio.getTaulaMusica().getSelectedRow(),3);
							for(Canco c: al){
								if(nomCanco.equals(c.getNom()) && artista.equals(c.getArtista())){
									if(ControladorFinestres.getInfoServidor().afegeixCancoLlista(c.getidCanco(), l.getId_llistes())){
										JOptionPane.showMessageDialog(finestraReproduccio,"S'ha inserit la llista correctament" , "Informacio", JOptionPane.INFORMATION_MESSAGE);
										l.getAllIdCanco().add(c.getidCanco());
										finestraReproduccio.getTableModel().fireTableDataChanged();
									}else{
										JOptionPane.showMessageDialog(finestraReproduccio, "Hi ha hagut un error a inserir", "Error", JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						}
					}
				});
			}
			// agafem l'event source
			Component b=(Component)e.getSource();


			//el mostrem
			popupMenu.show(b,e.getX(),e.getY());
			
			
		}
	}
	
	
}
