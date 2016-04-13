package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
/**
 * Creem la interficie del seervido composada per la "Gestio dels usuaris" i 
 * la "Gestio de 
 * @author jorgemelguizo
 *
 *
 * 
 **/
public class FinestraServidor extends JFrame {
	
	private JPanel jpUsuari;
	
	private JPanel jpMusica;
	
	
	public FinestraServidor() {
		JFrame jfServidor = new JFrame("SPOTYFAI - Servidor");
		
		jfServidor.setSize(800,500);
		jfServidor.setResizable(true);
		jfServidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfServidor.setLocationRelativeTo(null);;
		jfServidor.setVisible(true);
		
		
		JTabbedPane jtpServidor = new JTabbedPane();
		//tab user
		jpUsuari = new JPanel();
		jpUsuari = FinestraUsuari();
		//tab music
		jpMusica = new JPanel();
		jpMusica = FinestraMusica();
		
		jtpServidor.addTab("Usuari", jpUsuari);
		jtpServidor.addTab("Musica", jpMusica);	
		
		jfServidor.add(jtpServidor);
	}
	/**
	 * Creem la interficie del seervido composada per la "Gestio dels usuaris" i 
	 * la "Gestio de 
	 * @author jorgemelguizo
	 * 
	 **/
	
	public JPanel FinestraUsuari(){
		jpUsuari = new JPanel();
		//connectar amb base de dades 
		
		/*
		 * temporal, vinda de la bbdd
		 */
		Object info[][] = { { "Carla", "28/03/1996", "29/06/2015", "2", "1", "0", "9", "0" },
				{ "Jorge puto amo", "14/06/2010", "29/06/2015", "2", "1", "0", "356", "0" }
		};
		
		/*
		 * fiTemporal
		 */
		
		Object titol[] = { "Nom usuari", "Data registre", "Ultim accès", "Numero de llistes", "Numero de cançons",
				"Numero followers", "Numero following"};
	    JTable taulaUsuari = new JTable(info, titol);

		JScrollPane jspUsuari = new JScrollPane(taulaUsuari);
		
		jpUsuari.add(jspUsuari);
		
		return jpUsuari;
	}
	
	public JPanel FinestraMusica(){
		
		jpMusica = new JPanel();
		
		JTabbedPane jtpMusica = new JTabbedPane();
		
		jtpMusica.addTab("Llistar i eliminar", new JPanel());
		jtpMusica.addTab("Addicio Musica", new JPanel());
		jtpMusica.addTab("Estadistiques", new JPanel());
		
		jpMusica.add(jtpMusica);
			
		return jpMusica;
	}

}
