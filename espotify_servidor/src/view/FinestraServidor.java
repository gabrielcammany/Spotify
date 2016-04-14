package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private Estadistica estadistica;

	
	
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
		jpUsuari = new JPanel(new BorderLayout());
		//connectar amb base de dades 
		
		/*
		 * temporal, vinda de la bbdd
		 */
		Object info[][] = { { "Carla", "28/03/1996", "29/06/2015", "2", "1", "0", "9", "0" },
				{ "Jorge dientes", "14/06/1995", "29/06/2015", "2", "1", "0", "356", "0" },
				{ "Problem manager", "05/08/95", "05/08/95", "4", "7", "3", "89", "4"}
		};
		
		/*
		 * fiTemporal
		 */
		
		Object titol[] = { "Nom usuari", "Data registre", "Ultim accès", "Numero de llistes", "Numero de cançons",
				"Numero followers", "Numero following"};
	    JTable taulaUsuari = new JTable(info, titol);

		JScrollPane jspUsuari = new JScrollPane(taulaUsuari);
		
		jpUsuari.add(jspUsuari, BorderLayout.CENTER);
		
		return jpUsuari;
	}
	
	public JPanel FinestraMusica(){
		
		jpMusica = new JPanel(new BorderLayout());
		JTabbedPane jtpMusica = new JTabbedPane();
		
		JPanel jpLlistat = new JPanel();
		JPanel jpAddicio = new JPanel();
		JPanel jpEstadistiques = new JPanel(new BorderLayout());
		
		
		
		//****** Opcio Llistat ******
		
		//****** Opcio Addicio ******
		
		//****** Opcio Estadistiques ******
		jpEstadistiques.add(generaEstadistica(), BorderLayout.CENTER);
		
		
		
		//Afegim al JTabbedPane i finalment al JPanel
		jtpMusica.add("Llistat", jpLlistat);
		jtpMusica.add("Addicio", jpAddicio);
		jtpMusica.add("Estadistiques", jpEstadistiques);
		
		
		jpMusica.add(jtpMusica, BorderLayout.CENTER);
		
		
			
		return jpMusica;
	}
	
	/**
	 * genera les estadistiques de les 10 cancons mes escoltades
	 * @return JPanel 
	 */
	
	public JPanel generaEstadistica() {
		
		
		
		
		JPanel jpGrafica = new JPanel(new BorderLayout() );
		JPanel jpNoms = new JPanel (new GridLayout(10, 1));
		JPanel jpBarres = new JPanel (new GridLayout(10,1));
		
		//!!!!!Hardcodeo nombres y valores para hacer pruebas
		ArrayList<String> nomsCancons = new ArrayList<String>();
		nomsCancons.add("pepe");nomsCancons.add("jeje");
		nomsCancons.add("fulanito");nomsCancons.add("menganito");
		nomsCancons.add("juan");nomsCancons.add("prova");
		nomsCancons.add("salle");nomsCancons.add("silla");
		nomsCancons.add("adeu");nomsCancons.add("sdfdsf");
		
		//!!!!!Los introduzco ordenados, faltara un SORT
		ArrayList <String>valors = new ArrayList<String>();
		valors.add("133");valors.add("96");
		valors.add("78");valors.add("75");
		valors.add("66");valors.add("60");
		valors.add("45");valors.add("34");
		valors.add("23");valors.add("8");
		
		
		
		/*for (int i = 0; i < 10; i++) {
			jpNoms.add(new JLabel(nomsCancons.get(i)));
		}
		
		//Procedim a dibuixar les barres
		for (int i = 0; i < 10; i++) {
			
		}*/
		estadistica = new Estadistica(valors, nomsCancons);
		
		
		//Agafem el valor mes gran (CUIDADO HARA FALTA ORDENAR CUANDO RECIBAMOS DE LA BDD)
		//mesGran = Integer.parseInt(valors.get(0)); //i = sort de la array (nombre + reproducciones) ordenada de mayor a menos
		
		//jpGrafica.add(jpNoms, BorderLayout.LINE_START);
		//jpGrafica.add(jpBarres, BorderLayout.CENTER);
		jpGrafica.add(estadistica, BorderLayout.CENTER);
		
		return jpGrafica;
	}
	
	

}

