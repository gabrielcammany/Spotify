package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.xswingx.PromptSupport;

import controller.ControladorFinestres;
import controller.ControladorLlistar;
import controller.ControladorLlistesMusica;
import controller.ControladorReproductor;
import model.Canco;
import model.Llistes;
import model.User;
import net.miginfocom.swing.MigLayout;


/**
 * Gestio de la finestra de reproduccio, permet Llistar la musica disponible, les llistes propies,
 * fer follow i reproduir musica
 *
 */


@SuppressWarnings({ "unused", "serial" })
public class FinestraReproduccio extends JFrame {
	
	private JFrame jfReproduccio;
	private JPanel jpReproduccio;
	private ControladorFinestres cf;
	private JScrollPane jspLlistatDisponible;
	private JScrollPane jspLlistatPropia;
	private JScrollPane jspUsuarisFollowing;
	private JScrollPane jspLlistesFollowing;
	private JTable taulaMusica;
	private JTable taulaUsuaris;
	private JTable jtMusicaLlista;
	ArrayList<User> alUsers;
	JPanel jpVisualitzarLlistes;
	
	
	
	public FinestraReproduccio(ControladorFinestres cf) throws ClassNotFoundException{
		this.cf = cf;
		//inicialitzem
		if(jspLlistatDisponible == null){
			jspLlistatDisponible = new JScrollPane();
			
		}
		if(jspUsuarisFollowing == null){
			jspUsuarisFollowing = new JScrollPane();
		}
		if(jspLlistatPropia == null){
			jspLlistatPropia = new JScrollPane();
		}
		if(jspLlistesFollowing == null){
			jspLlistesFollowing = new JScrollPane();
		}
		
		jfReproduccio = new JFrame();
		jfReproduccio.setVisible(true);
		jfReproduccio.setSize(800, 700);
		jfReproduccio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfReproduccio.setLocationRelativeTo(null);
		jfReproduccio.setBackground(new Color(24,24,24));
		
		
		//panell general
		this.jpReproduccio = new JPanel(new BorderLayout());
		this.jpReproduccio.setBackground(new Color(24,24,24));
		
		/*
		 * 
		 * GET NOM USUARI!!!
		 * 
		 */	
		
		//barra del nort
		JPanel jpUsuari = new JPanel(new BorderLayout());
		jpUsuari.setBackground(new Color(40,40,40));
		jpUsuari.setBorder(BorderFactory.createLineBorder(Color.black));
		//adalt a la dreta
		//nom usuari
		JLabel jlUsuari = new JLabel("Jorge el mejor  ");
		jlUsuari.setForeground(new Color(164,164,164));
		jlUsuari.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); 
		//introduir-lo
		jpUsuari.add(jlUsuari,  BorderLayout.LINE_END);
		
		//adalt a l'esquera
		JPanel jpAdaltEsquerra = new JPanel(new MigLayout("wrap"));
		jpAdaltEsquerra.setBackground(new Color(40,40,40));
		
		//logo adalt a l'esquerra
		ImageIcon imLogo = new ImageIcon("Images/logo.png");
		JLabel jlLogo = new JLabel();
		jlLogo.setForeground(new Color(164,164,164));
		ImageIcon icLogo = new ImageIcon(imLogo.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		jlLogo.setIcon(icLogo);
		jlLogo.setBorder(new EmptyBorder(1, 5, 0, 10));
		jpAdaltEsquerra.add(jlLogo,"dock west");
		
		//buscar (no fa res)
		/*ImageIcon imBuscar = new ImageIcon("Images/buscar.png");
		JLabel jlBuscar = new JLabel();
		jlBuscar.setForeground(new Color(164,164,164));
		ImageIcon icBuscar = new ImageIcon(imBuscar.getImage().getScaledInstance(200, 30, Image.SCALE_DEFAULT));
		jlBuscar.setIcon(icBuscar);*/
		JTextField jtfBuscar = new JTextField(15);
		jtfBuscar.setBackground(new Color(251,251,251));
		jtfBuscar.setForeground(new Color(50,50,50));
		jtfBuscar.setText("Buscar");
		jtfBuscar.setFocusable(false);
		
		jpAdaltEsquerra.add(jtfBuscar,"dock west");

		
		jpUsuari.add(jpAdaltEsquerra, BorderLayout.LINE_START);

		//afegim la barra d'adalt al panell
		jpReproduccio.add(jpUsuari, BorderLayout.NORTH);
		
		//afegim la columna de l'esquerra, faig un mig layout per fer us dels wrap
		//Musica disponible, â€œLlistes de musica propies i Llistes de musica following
		
		JPanel jpGestionar = new JPanel(new MigLayout("wrap"));
		jpGestionar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		jpGestionar.setBackground(new Color(40,40,40));
		jpGestionar.setBorder(BorderFactory.createLineBorder(Color.black));

		String disponible = "Música disponible";
		String propies = "Música propia";
		String llistesfollowing = "Música following";
		String usuarisfollowing = "Follow usuaris";
		try {
			JLabel jlDisponible = new JLabel(new String(disponible.getBytes("UTF-8"),"UTF-8"));
			jlDisponible.setForeground(new Color(164,164,164));
			jlDisponible.setBorder(BorderFactory.createEmptyBorder(10,4,15,10));
			
			JLabel jlPropies = new JLabel(new String(propies.getBytes("UTF-8"),"UTF-8"));
			jlPropies.setForeground(new Color(164,164,164));
			jlPropies.setBorder(BorderFactory.createEmptyBorder(10,4,15,10));
			
			JLabel jlLlistesfollowing = new JLabel(new String(llistesfollowing.getBytes("UTF-8"),"UTF-8"));
			jlLlistesfollowing.setForeground(new Color(164,164,164));
			jlLlistesfollowing.setBorder(BorderFactory.createEmptyBorder(10,4,15,10));
			
			JLabel jlUsuarisfollowing = new JLabel(new String(usuarisfollowing.getBytes("UTF-8"),"UTF-8"));
			jlUsuarisfollowing.setForeground(new Color(164,164,164));
			jlUsuarisfollowing.setBorder(BorderFactory.createEmptyBorder(10,4,15,10));

			jlDisponible.addMouseListener(new ControladorLlistar("disponible",cf));
			jlPropies.addMouseListener(new ControladorLlistar("propia",cf));
			jlUsuarisfollowing.addMouseListener(new ControladorLlistar("usuarisfollowing",cf));
			jlLlistesfollowing.addMouseListener(new ControladorLlistar("llistesfollowing",cf));
			
			jpGestionar.add(jlDisponible, "span 2, grow, wrap ");
			jpGestionar.add(jlPropies, "span 2, grow, wrap");
			jpGestionar.add(jlLlistesfollowing, "span 2, grow, wrap");
			jpGestionar.add(jlUsuarisfollowing, "span 2, grow, wrap");
		}  catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		jpReproduccio.add(jpGestionar, BorderLayout.WEST);		
		
		JPanel jpBarraReproduccio = new JPanel(new BorderLayout());
		jpBarraReproduccio.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		jpBarraReproduccio.setBackground(new Color(40,40,40));
		jpBarraReproduccio.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//conte play, pause, next i back
		JPanel jpBarraRepro = new JPanel(new MigLayout());
		jpBarraRepro.setBackground(new Color(40,40,40));
		
		//next
		ImageIcon imNext = new ImageIcon("Images/next.png");
		JLabel jlNext = new JLabel();
		jlNext.setForeground(new Color(164,164,164));
		ImageIcon icNext = new ImageIcon(imNext.getImage().getScaledInstance(45, 40, Image.SCALE_DEFAULT));
		jlNext.setIcon(icNext);
		
		//back
		ImageIcon imBack = new ImageIcon("Images/back.png");
		JLabel jlBack = new JLabel();
		jlBack.setForeground(new Color(164,164,164));
		ImageIcon icBack = new ImageIcon(imBack.getImage().getScaledInstance(45, 40, Image.SCALE_DEFAULT));
		jlBack.setIcon(icBack);
				
		//play
		ImageIcon imPlay = new ImageIcon("Images/play.png");
		JLabel jlPlay = new JLabel();
		jlPlay.setForeground(new Color(164,164,164));
		ImageIcon icPlay = new ImageIcon(imPlay.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		jlPlay.setFocusable(true);
		jlPlay.setFocusTraversalKeysEnabled(true);
		jlPlay.setIcon(icPlay);
		
		/*//pause, canvia de play a pause
		ImageIcon imPausa = new ImageIcon("Images/pausa.png");
		JLabel jlPausa = new JLabel();
		jlPlay.setForeground(new Color(164,164,164));
		ImageIcon icPausa = new ImageIcon(imPausa.getImage().getScaledInstance(60, 50, Image.SCALE_DEFAULT));
		jlPlay.setIcon(icPausa);*/
		
		//imatges de play, next i back clickables

		jlPlay.addMouseListener(new ControladorReproductor("play",cf));
		jlBack.addMouseListener(new ControladorReproductor("back",cf));
		jlNext.addMouseListener(new ControladorReproductor("next",cf));
		//sonido
		ImageIcon imSonido = new ImageIcon("Images/sonido.png");
		JLabel jlSonido = new JLabel();
		jlSonido.setForeground(new Color(164,164,164));
		ImageIcon icSonido = new ImageIcon(imSonido.getImage().getScaledInstance(150, 40, Image.SCALE_DEFAULT));
		jlSonido.setFocusable(true);
		jlSonido.setFocusTraversalKeysEnabled(true);
		jlSonido.setIcon(icSonido);
		
		jpBarraRepro.add(jlBack,"dock west");
		jpBarraRepro.add(jlPlay,"dock west");
		jpBarraRepro.add(jlNext,"dock west");
		
		jpBarraReproduccio.add(jpBarraRepro,BorderLayout.WEST);
		jpBarraReproduccio.add(jlSonido,BorderLayout.EAST);
				
		jpReproduccio.add(jpBarraReproduccio, BorderLayout.SOUTH);		
		
		//ho afegim tot al frame general
		jfReproduccio.add(jpReproduccio);
		
	}
	
	
	
	/**
	 * Aquesta funcio rep l'array de totes del cancos disponibles i actualitza la taula on es mostraran totes.
	 */
	
	public void setMusicaDisponible(ArrayList<Canco> alMusica){
		//JPanel jpLlistat = new JPanel(new BorderLayout());
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * AQUI SET 0
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		//Tabla musica disponible 
		Vector<String> columnas = new Vector();
		
		columnas.add("Nom canco");
		columnas.add("Genere");
		columnas.add("Album");
		columnas.add("Artistes");

   
		Vector filas = new Vector();
		for (int i = 0; i <alMusica.size(); i ++) {
			Vector<String> fila = new Vector();
			
			fila.add(alMusica.get(i).getNom());
			fila.add(alMusica.get(i).getGenere());
			fila.add(alMusica.get(i).getAlbum());
			fila.add(alMusica.get(i).getArtista());
			
			filas.add(fila);
		}
	
		taulaMusica = new JTable(filas, columnas){
		
		public boolean isCellEditable (int rowIndex, int vColIndex) {
			return false;
		}};		
		
		this.jspLlistatDisponible = new JScrollPane(taulaMusica);		
	}
	
	public void setUsuaris(ArrayList<User> alUsers) {
		this.alUsers = alUsers;	
	}
	
	/**
	 * Aquesta funcio rep l'array de totes del cancos propies i actualitza la taula on es mostraran totes.
	 */
	
	public void setMusicaPropia(/*passar llistes*/){
		JTabbedPane jtpLlistatPropia = new JTabbedPane();
		
		
		jtpLlistatPropia.addTab("Crear llista", new JScrollPane());
		jtpLlistatPropia.addTab("Visualitzar llistes", new JScrollPane(setVisualitzarLlistes()));	
		jtpLlistatPropia.addTab("Eliminar llista", new JScrollPane());
		jtpLlistatPropia.addTab("Votar cancons", new JScrollPane());
		
		this.jspLlistatPropia = new JScrollPane(jtpLlistatPropia);
	}
	
	public JPanel setVisualitzarLlistes(){
		jpVisualitzarLlistes = new JPanel(new BorderLayout());
		JList jlLlistes = new JList(cf.getlistesPropies().toArray());
		jlLlistes.setBorder(BorderFactory.createEmptyBorder(5,5,5,60));
		jpVisualitzarLlistes.add(jlLlistes, BorderLayout.WEST);
		
		
		jlLlistes.addMouseListener(new ControladorLlistesMusica(jlLlistes, cf));
			
		return jpVisualitzarLlistes;
	}
	
	/**
	 * Aquesta funcio rep l'array de totes les cançons de la llista seleccionada
	 * 
	 */
	public void actualitzaLlistaSeleccionada(ArrayList<Canco> musicaLlista ){
		
		Vector<String> columnas = new Vector();

		columnas.add("Nom canco");
		columnas.add("Genere");
		columnas.add("Album");
		columnas.add("Artistes");


		Vector filas = new Vector();
		for (int i = 0; i <musicaLlista.size(); i ++) {
			Vector<String> fila = new Vector();

			fila.add(musicaLlista.get(i).getNom());
			fila.add(musicaLlista.get(i).getGenere());
			fila.add(musicaLlista.get(i).getAlbum());
			fila.add(musicaLlista.get(i).getArtista());

			filas.add(fila);
		}
		System.out.println(columnas);
		jtMusicaLlista = new JTable(filas, columnas){
		
		public boolean isCellEditable (int rowIndex, int vColIndex) {
			return false;
		}};
		
		
		//sense aixo es quedara el primer jpanel(border.center)
		BorderLayout layout =  (BorderLayout) jpVisualitzarLlistes.getLayout();
		if (layout.getLayoutComponent(BorderLayout.CENTER) != null){
			jpVisualitzarLlistes.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		}	
		
		jpVisualitzarLlistes.add(new JScrollPane(jtMusicaLlista), BorderLayout.CENTER);
		//jpVisualitzarLlistes.repaint();
		jpVisualitzarLlistes.validate();
	}
		
	
	
	/**
	 * Aquesta funcio rep l'array de totes llistes dels usuaris que segueix i els mostra en una taula
	 */
	
	public void setLlistesFollowing(/*array de llistes*/ArrayList<String> alLlistes){
		JPanel jpLlistat = new JPanel(new BorderLayout());

		//Tabla musica disponible 
		Vector<String> columnas = new Vector();
		
		columnas.add("Nom llista");
		columnas.add("Nom usuari");
   
		Vector filas = new Vector();
		 
		  for (int i = 0; i <alLlistes.size(); i ++) {

			Vector<String> fila = new Vector();
			fila.add(alLlistes.get(i).toString());
			filas.add(fila);
		}

		JTable taulaUsuariFollowing = new JTable(filas, columnas){
		
		public boolean isCellEditable (int rowIndex, int vColIndex) {
			return false;
		}};
		
		
		this.jspLlistesFollowing = new JScrollPane(taulaUsuariFollowing);	
	}
	
	/**
	 * Aquesta funcio rep l'array de tots els usuaris que segueix i els mostra en una taua
	 */
	
	public void setUsuarisFollowing(ArrayList<User> alUsuari){
		//podem escollir entre llistar els usuaris que seguim o buscar un usuari per seguir-lo/seixar-lo de seguir
		JTabbedPane jtpFollowing = new JTabbedPane();
		//buscar un nou usuari
		JPanel jpNouFollow = new JPanel(new MigLayout("al center center, wrap, gapy 10"));
		JTextField jtfUsuari = new JTextField(15);
		PromptSupport.setPrompt("Nom usuari", jtfUsuari);
		jpNouFollow.add(jtfUsuari);
		//JPanel jpLlistat = new JPanel(new BorderLayout());

		//Tabla musica disponible 
		Vector<String> columnas = new Vector();
		
		columnas.add("Nom usuari");
		columnas.add("Numero llistes");


   
		Vector filas = new Vector();

		  System.out.println("%%%%%%");
		  for (int i = 0; i <alUsuari.size(); i ++) {
			  System.out.println("%%%%%%"+i);
			Vector<String> fila = new Vector();
			
			fila.add(alUsuari.get(i).toString());
			
			filas.add(fila);
		}
		
		
	
		JTable taulaUsuariFollowing = new JTable(filas, columnas){
		
		public boolean isCellEditable (int rowIndex, int vColIndex) {
			return false;
		}};
		
		jtpFollowing.add("Llistat following", taulaUsuariFollowing);
		jtpFollowing.add("Buscar Usuari", jpNouFollow);
		

		//this.jspUsuarisFollowing = new JScrollPane(taulaUsuariFollowing);	
		this.jspUsuarisFollowing = new JScrollPane(jtpFollowing);

		
	}
	
	
	/**
	 * Aquesta funcio rep l'string de la opcio que es vol fer i 
	 * actualitza el que es mostra en la pantalla de reproduccio
	 * 
	 */
	public void actualitzaOpcio(String opcio) {
		
		//eborrem el que hi havia per pintar de nou
		BorderLayout layout =  (BorderLayout) jpReproduccio.getLayout();
		if (layout.getLayoutComponent(BorderLayout.CENTER) != null){
			jpReproduccio.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		}
		
		//segons l'opcio que esolli l'usuari mostrem en el centre del panell
		switch (opcio){
		
			case "disponible":
			
				this.jpReproduccio.add(this.jspLlistatDisponible, BorderLayout.CENTER);
				this.jpReproduccio.validate();
				break;
			 
			case "propia":
				
				/*provisional: */ setMusicaPropia();
				
				this.jpReproduccio.add(this.jspLlistatPropia, BorderLayout.CENTER);
				this.jpReproduccio.validate();
				
				break;
				
			case "llistesfollowing":
				/*provisional: */
				ArrayList<String> llistes = new ArrayList<String>();
				llistes.add("Party loca");
				llistes.add("Portugues");
				llistes.add("Brasilenherias");
				setLlistesFollowing(llistes);
				/*fi provisional: */ 
				
				this.jpReproduccio.add(this.jspLlistesFollowing, BorderLayout.CENTER);
				this.jpReproduccio.validate();
				break;
				
				
			case "usuarisfollowing":
				/*provisional: */ 
				this.jpReproduccio.add(this.jspUsuarisFollowing, BorderLayout.CENTER);
				this.jpReproduccio.validate();
				break;
				
		
			default: break;
			
		}
	}
	
	
	
	public JTable getTaulaMusica() {
		return taulaMusica;
	}
	
	public JTable getTaulaLlistaMusicaFollowing() {
		return jtMusicaLlista;
	}
	
	
}
