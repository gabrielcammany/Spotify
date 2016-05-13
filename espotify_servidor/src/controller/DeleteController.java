package controller;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import model.Canco;
import model.Data;
import model.Query;
import network.ConectorDB;
import view.FinestraServidor;

public class DeleteController implements MouseListener {
	// VISTA
	private JFrame jf;
	private FinestraServidor fs;
	private JPopupMenu popupMenu;
	private JMenuItem deleteItem;
	private String nom;
	private ConectorDB conn = new ConectorDB("dpo_root", "sinminus", "bd_espotifi", 3306);
	
	public DeleteController(JFrame jf, FinestraServidor fs){
		this.jf = jf;
		this.fs = fs;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e))
        {
            // han apretat el boto dret
			
			popupMenu = new JPopupMenu();
	        deleteItem = new JMenuItem("Delete");
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
					// TODO Auto-generated method stub
					 try{
				        	nom = (String) fs.getTaulaMusica().getValueAt(fs.getTaulaMusica().getSelectedRow(), 0);
				        	
				        }catch(Exception e1){
				        	System.out.println("asdfasdf");
				        }
				        try{
				        	int id = 0;
				        	ArrayList<Canco> llcanco = Data.getAlMusica();
				        	
				        	for(Canco c  : llcanco)if(c.getNom().equals(nom))id =c.getIdCanco();
				        	
				        	conn.deleteQuery(new Query().queryList(12, id));
				        	System.out.println("-------------------------------------------------------------------");
				        	System.out.println("[Servidor]Canco '"+nom+"' deleted.");
				        	Data.esborraCanco(id);
				        }catch(Exception e1){
				        	e1.printStackTrace();
				        	System.out.println("444444");
				        }
						
					
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
	public void mousePressed(MouseEvent e) {
		
		
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