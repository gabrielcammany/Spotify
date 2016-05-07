package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdesktop.xswingx.PromptSupport;

import controller.ButtonsController;
import controller.DeleteController;
import net.miginfocom.swing.MigLayout;
import model.Canco;
import model.Musica;
import model.Usuaris;


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
	private Musica musica;
	private JPanel jpMusica;
	private Estadistica estadistica;
	private ButtonsController controlador;
	private JFrame jfServidor;
	JTextField jtfcanco;
	JButton jbAddicio;
	JTextField jtfGenere;
	JTextField jtfAlbum;
	JTextField jtfArtista;
	JTextField jtfUbicacio;

	
	
	public FinestraServidor() {}
	
	public void setControlador(ButtonsController controlador) {
		this.controlador = controlador;
	}
	
	
	
	
	
	
	public void creaFinestra(Musica musica, Usuaris allUsers){
		this.musica = musica;
		
		
		System.out.println(musica.getMusica().get(0).getNom());
		System.out.println(musica.getMusica().get(1).getNom());
		
		jfServidor = new JFrame("SPOTYFAI - Servidor");
		
		jfServidor.setSize(900,500);
		jfServidor.setResizable(true);
		jfServidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfServidor.setLocationRelativeTo(null);;
		jfServidor.setVisible(true);
		
		
		JTabbedPane jtpServidor = new JTabbedPane();
		//tab user
		jpUsuari = new JPanel();
		jpUsuari = FinestraUsuari(allUsers);
		//tab music
		jpMusica = new JPanel();
		jpMusica = FinestraMusica();
		
		jtpServidor.addTab("Usuari", jpUsuari);
		jtpServidor.addTab("Musica", jpMusica);	
		
		jfServidor.add(jtpServidor);
	}
	/**
	 * La finestra de l'usuari et mostra els usuaris de la BBDD
	 * 
	 * @author jorgemelguizo
	 * 
	 **/
	
	public JPanel FinestraUsuari(Usuaris allUsers){
		jpUsuari = new JPanel(new BorderLayout());
		//connectar amb base de dades 
		
		Vector columnas = new Vector();
		columnas.add ("Nom usuari");
		columnas.add("Data registre");
		columnas.add("Ultim acces");
		columnas.add("Número de llistes");
		columnas.add("Numero de llistes");
		columnas.add("Numero de cancos");
		columnas.add("Numero follower");
		columnas.add("Numero following");
		
		Vector filas = new Vector();
		
		for (int i = 0; i < allUsers.getUsuaris().size(); i ++) {
			Vector fila = new Vector();
			fila.add(allUsers.getUsuaris().get(i).getNickname());
			fila.add(allUsers.getUsuaris().get(i).getData_reg());
			fila.add(allUsers.getUsuaris().get(i).getData_ult());
			fila.add("no esta hecho");
			fila.add("no esta hecho");
			fila.add("no esta hecho");
			fila.add("no esta hecho");
			fila.add("no esta hecho");
			
			filas.add(fila);
		}
		
		JTable taulaUsuari = new JTable(filas, columnas){
			
		public boolean isCellEditable (int rowIndex, int vColIndex) {
			return false;
		}};
		taulaUsuari.addMouseListener(new DeleteController(jfServidor, this));
			
		
	


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
		

		
	
		//Tabla musica disponible 
		
		Vector columnas = new Vector();
		
		columnas.add("Nom canco");
		columnas.add("Genere");
		columnas.add("Album");
		columnas.add("Artistes");

   
		Vector filas = new Vector();
		for (int i = 0; i < musica.getMusica().size(); i ++) {
			Vector fila = new Vector();
			
			fila.add(musica.getMusica().get(i).getNom());
			fila.add(musica.getMusica().get(i).getGenere());
			fila.add(musica.getMusica().get(i).getAlbum());
			fila.add(musica.getMusica().get(i).getArtista());
			
			filas.add(fila);
		}

		
		JTable taulaMusica = new JTable(filas, columnas){
			
		public boolean isCellEditable (int rowIndex, int vColIndex) {
			return false;
		}};
	
		taulaMusica.addMouseListener(new DeleteController(jfServidor, this));

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

		jtfcanco = new JTextField(15);
		PromptSupport.setPrompt("Nom canco", jtfcanco);
		jtfGenere = new JTextField(15);
		PromptSupport.setPrompt("Gènere", jtfGenere);

		jtfAlbum = new JTextField( 15);
		PromptSupport.setPrompt("Nom album", jtfAlbum);

		jtfArtista = new JTextField( 15);
		PromptSupport.setPrompt("Nom artista", jtfArtista);

		jtfUbicacio = new JTextField( 15);
		PromptSupport.setPrompt("Ubicació o path", jtfUbicacio);

		jbAddicio = new JButton();
		jbAddicio.setText("Afegir canço");
		jbAddicio.addActionListener(controlador);
		jbAddicio.setActionCommand("Addicio");
		
		

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
		
		/*
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
		
		
		for (int i = 0; i < 10; i++) {
			jpNoms.add(new JLabel(nomsCancons.get(i)));
		}
		
		//Procedim a dibuixar les barres
		for (int i = 0; i < 10; i++) {
			
		}*/
		estadistica = new Estadistica(musica);
		
		//Agafem el valor mes gran (CUIDADO HARA FALTA ORDENAR CUANDO RECIBAMOS DE LA BDD)
		//mesGran = Integer.parseInt(valors.get(0)); //i = sort de la array (nombre + reproducciones) ordenada de mayor a menos
		
		//jpGrafica.add(jpNoms, BorderLayout.LINE_START);
		//jpGrafica.add(jpBarres, BorderLayout.CENTER);
		jpGrafica.add(estadistica, BorderLayout.CENTER);
		
		return jpGrafica;
	}
	
	public String getAddCanco() {
		return jtfcanco.getText();
	}
	
	public String getAddGenere() {
		return jtfGenere.getText();
	}
	
	public String getAddAlbum() {
		return jtfAlbum.getText();
	}
	
	public String getAddArtista() {
		return jtfArtista.getText();
	}
	
	public String getAddUbicacio() {
		return jtfUbicacio.getText();
	}
	
	public void netejaFormulari() {
		jtfcanco.setText(null);
		jtfGenere.setText(null);
		jtfAlbum.setText(null);
		jtfArtista.setText(null);
		jtfUbicacio.setText(null);
	}

}

