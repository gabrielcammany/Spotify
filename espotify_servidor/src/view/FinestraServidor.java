package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdesktop.xswingx.PromptSupport;

import controller.ButtonsController;
import controller.DeleteController;
import controller.GestioController;
import model.Canco;
import model.Data;
import model.User;
import net.miginfocom.swing.MigLayout;


/**
 * Creem la interficie del seervido composada per la "Gestio dels usuaris" i 
 * la gestio de musica
 * @author jorgemelguizo
 *
 *
 * 
 **/
@SuppressWarnings("serial")
public class FinestraServidor extends JFrame {
	
	private GestioController gestioController;
	private JPanel jpUsuari;
	public JPanel jpMusica;
	private Estadistica estadistica;
	private ButtonsController controlador;
	private JFrame jfServidor;
	private JTextField jtfcanco;
	private JButton jbAddicio;
	private JTextField jtfGenere;
	private JTextField jtfAlbum;
	private JTextField jtfArtista;
	private JTextField jtfUbicacio;
	private JTable taulaMusica;
	private JTable taulaUsuari;
	public DefaultTableModel tableModel;

	
	
	public JTable getTaulaMusica() {
		return taulaMusica;
	}
	
	public JTable getTaulaUsuari() {
		return taulaUsuari;
	}

	public FinestraServidor(GestioController gestioController) {
		this.gestioController = gestioController;
	}
	
	public void setControlador(ButtonsController controlador) {
		this.controlador = controlador;
	}

	public void creaFinestra(ArrayList<Canco> musica, ArrayList<User> alUsuaris){
		
		jfServidor = new JFrame("SPOTYFAI - Servidor");
		
		jfServidor.setSize(900,500);
		jfServidor.setResizable(true);
		jfServidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfServidor.setLocationRelativeTo(null);;
		jfServidor.setVisible(true);
		
		
		JTabbedPane jtpServidor = new JTabbedPane();
		//tab user
		jpUsuari = new JPanel();
		jpUsuari = FinestraUsuari(alUsuaris);
		//tab music
		jpMusica = new JPanel();
		jpMusica = FinestraMusica();
		
		jtpServidor.addTab("Usuari", jpUsuari);
		jtpServidor.addTab("Musica", jpMusica);	
		
		jtpServidor.setIconAt(0, new ImageIcon("Images/profile.png"));
		jtpServidor.setIconAt(1, new ImageIcon("Images/musical-note.png"));
		
		UIManager.put(jtpServidor.getSelectedComponent(), new Color(123,45,67));
		
		jfServidor.add(jtpServidor);
	}
	/**
	 * La finestra de l'usuari et mostra els usuaris de la BBDD
	 * 
	 * @author jorgemelguizo
	 * 
	 **/
	
	public JPanel FinestraUsuari(ArrayList<User> allUsers){
		jpUsuari = new JPanel(new BorderLayout());
		//connectar amb base de dades 
		
		Vector<String> columnas = new Vector<String>();
		columnas.add ("Nom usuari");
		columnas.add("Data registre");
		columnas.add("Ultim acces");
		columnas.add("Numero de llistes");
		columnas.add("Numero de cancos");
		columnas.add("Numero follower");
		columnas.add("Numero following");
		
		Vector<Vector<String>> filas = new Vector<Vector<String>>();
		
		DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
		Alinear.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int i = 0; i < allUsers.size(); i ++) {
			Vector<String> fila = new Vector<String>();
			fila.add(((User)(allUsers.get(i))).getNickname());
			fila.add(((User)(allUsers.get(i))).getData_reg());
			fila.add(((User)(allUsers.get(i))).getData_ult());
			int numLlistes = gestioController.quantesLlistes(allUsers.get(i).getId_usuari());
			fila.add(Integer.toString(numLlistes));
			int numCancons = gestioController.quantesCancons(allUsers.get(i).getId_usuari());
			fila.add(Integer.toString(numCancons));
			int numFollowers = gestioController.quantsFollowers(allUsers.get(i).getId_usuari());
			fila.add(Integer.toString(numFollowers));
			int numFollowing = gestioController.quantsFollowing(allUsers.get(i).getId_usuari());
			fila.add(Integer.toString(numFollowing));
			
			filas.add(fila);
		}
		
		taulaUsuari = new JTable(filas, columnas){
			
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
		jpEstadistiques.add(generaEstadistica(Data.getAlMusica()), BorderLayout.CENTER);
		
		
		
		//Afegim al JTabbedPane i finalment al JPanel
		jtpMusica.add("Llistat", jpLlistat);
		jtpMusica.add("Addicio", jpAddicio);
		jtpMusica.add("Estadistiques", jpEstadistiques);


		jpMusica.add(jtpMusica, BorderLayout.CENTER);
		
		
			
		return jpMusica;
	}
	
	/**
	 * Genera una taula de tota la musica al sistema i la seva informaciÃ³
	 * @param musica 
	 * @return JPanel 
	 */
	
	public JPanel LlistarMusica(){
		JPanel jpLlistat = new JPanel(new BorderLayout());

		//Tabla musica disponible 
		
		Vector<String> columnas = new Vector<String>();
		
		columnas.add("Nom canco");
		columnas.add("Genere");
		columnas.add("Album");
		columnas.add("Artistes");

 
	    Vector<Vector<String>> filas = new Vector<Vector<String>>();
		taulaMusica = new JTable(filas, columnas){
		
		public boolean isCellEditable (int rowIndex, int vColIndex) {
			return false;
		}};
	
	    tableModel = (DefaultTableModel) taulaMusica.getModel();
	    tableModel.setRowCount(0);
		
		ArrayList<Canco>musica = Data.getAlMusica();
		for (int i = 0; i < musica.size(); i ++) {

			String[]data = new String[4];
			
			data[0] = musica.get(i).getNom();
			data[1] = musica.get(i).getGenere();
			data[2] = musica.get(i).getAlbum();
			data[3] = musica.get(i).getArtista();
			
			tableModel.addRow(data);
	
			
		}
	    taulaMusica.setModel(tableModel);
	    
		taulaMusica.addMouseListener(new DeleteController(jfServidor, this));

		JScrollPane jspLlistat = new JScrollPane(taulaMusica);
		tableModel.fireTableDataChanged();

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
	 * @param musica 
	 * @return JPanel 
	 */
	
	public JPanel generaEstadistica(ArrayList<Canco> musica) {

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

