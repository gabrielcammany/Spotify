package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import model.Canco;
import model.Data;
import model.Query;
import model.User;
import network.ConectorDB;
import network.JsonConfig;
import view.FinestraServidor;

public class DeleteController implements MouseListener {
	// VISTA
	private JFrame jf;
	private static FinestraServidor fs;
	private JPopupMenu popupMenu;
	private JMenuItem deleteItem;
	private String nom;
	private ConectorDB conn = new ConectorDB(JsonConfig.creaJson());
	
	public DeleteController(JFrame jf, FinestraServidor fs){
		this.jf = jf;
		this.setFs(fs);
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
			        	 nom = (String) getFs().getTaulaMusica().getValueAt(getFs().getTaulaMusica().getSelectedRow(), 0);
			        	 deleteCanco(nom);
					 }catch(Exception e1){
			        	 System.out.println("catch taula musica not select");
					 }
		        	 if (nom == null){
		        		try{
		        			nom = (String) getFs().getTaulaUsuari().getValueAt(getFs().getTaulaUsuari().getSelectedRow(), 0);
		        			deleteUser(nom);
		        		}catch(Exception e2){
				        	System.out.println("catch taula usuari not select");
				        }
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
	
	
	/*
	 * borra la canco de tot arreu on esta
	 * 
	 * @param nomCanco 
	 * 
	 */
	public void deleteCanco (String nomCanco){
		try{
        	int id = 0;
        	ArrayList<Canco> llcanco = Data.getAlMusica();
        	
        	for(Canco c  : llcanco)if(c.getNom().equals(nom))id =c.getIdCanco();
        	
	        	conn.deleteQuery(new Query().queryList(12,id));
	        	ResultSet res = conn.selectQuery(new Query().queryList(16, id));
	        	try {
	        		ArrayList<Integer> ll = new ArrayList<Integer>();
	        		int i= 0;
					while (res.next()){
						ll.add(res.getInt("id_canco_llista"));				
						System.out.println("[Servidor] Response server: '"+ll.get(i)+"'." );
						i++;
					}
					int j = 0;
					while(j<ll.size()){
						conn.deleteQuery(new Query().queryList(17, ll.get(j)));
						j++;
					}
				} catch (SQLException e99) {
					e99.printStackTrace();
			}
        	System.out.println("-------------------------------------------------------------------");
        	System.out.println("[Servidor]Canco '"+nom+"' deleted.");
        	Data.esborraCanco(id);
        	getFs().tableModel.removeRow(getFs().getTaulaMusica().getSelectedRow());
        	getFs().tableModel.fireTableDataChanged();
        }catch(Exception e1){
        	System.out.println("Catch borrar canco");
        }
	}
	
	/*
	 * borra lusuari de tot arreu on esta
	 * 
	 * @param nomCanco 
	 * 
	 */
	public void deleteUser(String nom){
		
		try{
        	int id = 0;
        	
        	for(User u : Data.getUsers()) if (u.getNickname().equals(nom)) id = u.getId_usuari();
        	
        	conn.deleteQuery(new Query().queryList(18,id));
        	System.out.println("[Servidor] usuari eliminat -> " + nom);
        	ResultSet res = conn.selectQuery(new Query().queryList(19, id));
        	try {
        		//esborrem llistes del usuari a eliminar
        		ArrayList<Integer> ll = new ArrayList<Integer>();
        		int i= 0;
				while (res.next()){
					ll.add(res.getInt("id_usuari_llista"));				
					System.out.println("[Servidor] Response server user: '"+ll.get(i)+"'." );
					i++;
				}
				int j = 0;
				while(j<ll.size()){
					conn.deleteQuery(new Query().queryList(20, ll.get(j)));
					j++;
				}
				
				//esborrem followers
				ResultSet res2 = conn.selectQuery(new Query().queryList(21, id));
				ArrayList<Integer> ll2 = new ArrayList<Integer>();
        		int k= 0;
				while (res2.next()){
					ll2.add(res2.getInt("id_user_follower"));				
					System.out.println("[Servidor] Response server follower: '"+ll2.get(k)+"'." );
					k++;
				}
				int z = 0;
				while(z<ll2.size()){
					conn.deleteQuery(new Query().queryList(22, ll2.get(z)));
					z++;
				}
				
			} catch (SQLException e99) {
				e99.printStackTrace();
			}
        	System.out.println("-------------------------------------------------------------------");
        	System.out.println("[Servidor]Uusari '"+nom+"' deleted.");
        	Data.esborraUser(id);
        	
        	/*
        	  ACTUALITZA TAULA USER	
        	 
        	fs.tableModel.removeRow(fs.getTaulaMusica().getSelectedRow());
        	fs.tableModel.fireTableDataChanged();
        	*/
        	
        }catch(Exception e1){
        	System.out.println("Catch borrar user");
        }
		
	}

	public static FinestraServidor getFs() {
		return fs;
	}

	public static void setFs(FinestraServidor fs) {
		DeleteController.fs = fs;
	}
}