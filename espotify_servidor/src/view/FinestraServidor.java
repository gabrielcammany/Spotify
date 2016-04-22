package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdesktop.xswingx.PromptSupport;

import net.miginfocom.swing.MigLayout;


/**
 * Creem la interficie del seervido composada per la "Gestio dels usuaris" i 
 * la "GestiÃ³ de mÃºsica"
 * @author jorgemelguizo
 *
 *
 * 
 **/
@SuppressWarnings("serial")
public class FinestraServidor extends JFrame {
	
	private JPanel jpUsuari;
	
	private JPanel jpMusica;
	private Estadistica estadistica;
	
	
	public FinestraServidor() {
		JFrame jfServidor = new JFrame("SPOTYFAI - Servidor");
		
		jfServidor.setSize(900,500);
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
		jtpServidor.addTab("Música", jpMusica);	
		
		jfServidor.add(jtpServidor);
	}
	/**
	 * La finestra de l'usuari et mostra els usuaris de la BBDD
	 * 
	 * @author jorgemelguizo
	 * 
	 **/
	
	public JPanel FinestraUsuari(){
		jpUsuari = new JPanel(new BorderLayout());
		//connectar amb base de dades 
		
		/*
		 * temporal, vinda de la bbdd
		 */
		Object info[][] = { { "Carla medio metro", "28/03/1998", "29/06/2015", "0", "1", "0", "9", "0" },
				{ "Jorge te queremos", "14/06/1994", "29/06/2015", "2", "1", "200983248", "6", "0" },
				{ "Problem manager", "05/08/95", "05/08/95", "4", "7", "3", "89", "4"},
				{ "Gabri hombre socket", "05/00/95", "05/08/95", "4", "7", "2", "69", "4"},
				{ "Ivan casper", "05/00000/95", "05/08/95", "4", "7", "2", "69", "4"}
		};
		
		
		/*
		 * fiTemporal
		 */

		Object titol []={ "Nom usuari", "Data registre", "Últim accés", "Número de llistes", "Numero de cançons",
				"Numero followers", "Numero following"};
		JTable taulaUsuari = new JTable(info, titol);
		taulaUsuari.setEnabled(false);



		JScrollPane jspUsuari = new JScrollPane(taulaUsuari);
		
		jpUsuari.add(jspUsuari, BorderLayout.CENTER);
		
		return jpUsuari;
	}
	
	public JPanel FinestraMusica(){
		
		jpMusica = new JPanel(new BorderLayout());
		JTabbedPane jtpMusica = new JTabbedPane();
		
		JPanel jpLlistat = new JPanel(new BorderLayout());
		JPanel jpAddicio = new JPanel();
		JPanel jpEstadistiques = new JPanel(new BorderLayout());
		
		
		
		//****** Opcio Llistat ******
		jpLlistat.add(LlistarMusica(), BorderLayout.CENTER);
		
	
		//****** Opcio Addicio ******
		jpAddicio = AddicioMusica();
		
		//****** Opcio Estadistiques ******
		jpEstadistiques.add(generaEstadistica(), BorderLayout.CENTER);
		
		
		
		//Afegim al JTabbedPane i finalment al JPanel
		jtpMusica.add("Llistat", jpLlistat);
		jtpMusica.add("Addicio", jpAddicio);
		jtpMusica.add("Estadístiques", jpEstadistiques);


		jpMusica.add(jtpMusica, BorderLayout.CENTER);
		
		
			
		return jpMusica;
	}
	
	/**
	 * Genera una taula de tota la musica al sistema i la seva informaciÃ³
	 * @return JPanel 
	 */
	
	public JPanel LlistarMusica(){
		JPanel jpLlistat = new JPanel(new BorderLayout());
		
		/*
		 * temporal, vinda de la bbdd
		 */
		Object info[][] = { {"Cargol", "Infantil", "Party", "Nuse", "Jorge el puto", "a ti que te importa"},
				{ "Sol solet", "Infantil", "JorgePrivateParty", "Ves tu a saber", "Jorge", "es mia"}
		};
		/*
		 * fiTemporal  FALTA POSAR UN BOTO D'ESBORRAR I ESCOLTAR A LA DRETA DE CADA FILA
		 */
		Object titol[] = { "Nom canço","Gènere", "Àlbum","Artistes", "Ubicació"};

		JTable taulaMusica = new JTable(info, titol);

		JScrollPane jspLlistat = new JScrollPane(taulaMusica);

		jpLlistat.add(jspLlistat, BorderLayout.CENTER);



		return jpLlistat;
	}
	
	/**
	 * Permet l'addiciÃ³ de mÃºsica en la base de dades
	 * @return JPanel 
	 */
	
	public JPanel AddicioMusica(){
		
		/*
		 * 
		 * FALTA MIGLAYOUT
		 */
		JPanel jpAddicio = new JPanel(new MigLayout("al center center, wrap, gapy 10"));

		JTextField jtfcanco = new JTextField(15);
		PromptSupport.setPrompt("Nom canço", jtfcanco);
		JTextField jtfGenere = new JTextField(15);
		PromptSupport.setPrompt("Gènere", jtfGenere);

		JTextField jtfAlbum = new JTextField( 15);
		PromptSupport.setPrompt("Nom album", jtfAlbum);

		JTextField jtfArtista = new JTextField( 15);
		PromptSupport.setPrompt("Nom artista", jtfArtista);

		JTextField jtfUbicacio = new JTextField( 15);
		PromptSupport.setPrompt("Ubicació o path", jtfUbicacio);

		JButton jbAddicio = new JButton();
		jbAddicio.setText("Afegir canço");

		jpAddicio.add(jtfcanco);
		jpAddicio.add(jtfGenere);
		jpAddicio.add(jtfAlbum);
		jpAddicio.add(jtfArtista);
		jpAddicio.add(jtfUbicacio);
		jpAddicio.add(jbAddicio, "span , grow");


		return jpAddicio;
	}


	/**
	 * Genera les estadistiques de les 10 canÃ§ons mes escoltades del sistema
	 * @return JPanel 
	 */
	
	public JPanel generaEstadistica() {

		JPanel jpGrafica = new JPanel(new BorderLayout() );
		//JPanel jpNoms = new JPanel (new GridLayout(10, 1));
		//JPanel jpBarres = new JPanel (new GridLayout(10,1));
		
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

