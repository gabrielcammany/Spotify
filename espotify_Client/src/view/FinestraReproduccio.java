package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.xswingx.PromptSupport;

import controller.CloseWindowController;
import controller.ControladorBotons;
import controller.ControladorFinestres;
import controller.ControladorLlistar;
import controller.ControladorLlistesFollowing;
import controller.ControladorLlistesMusica;
import controller.ControladorReproductor;
import controller.DeleteController;
import controller.VotarController;
import model.Canco;
import model.Llistes;
import model.User;
import model.sUser;
import net.miginfocom.swing.MigLayout;
import network.InfoServidor;


/**
 * Gestio de la finestra de reproduccio, permet Llistar la musica disponible, les llistes propies,
 * fer follow i reproduir musica
 *
 */


@SuppressWarnings({ "unused", "serial" })
public class FinestraReproduccio extends JFrame{
	
	private JFrame jfReproduccio;
	private JPanel jpReproduccio;
	private JScrollPane jspLlistatDisponible;
	private JScrollPane jspLlistatPropia;
	private JScrollPane jspUsuarisFollowing;
	private JScrollPane jspLlistesFollowing;
	private JTable taulaMusica;
	private JTable taulaUsuaris;
	private JTable taulaMusicaLlista;
	private ArrayList<User> alUsers;
	private JPanel jpVisualitzarLlistes;
	private JPanel jpLlistesFollowing;
	private ArrayList<Canco> alMusica;
	private JScrollPane jspCrearLlista;
	private JPanel jpLlistatFollowing;
	
	private JTable jtLlistatFollowing;
	private DefaultTableModel tableModel;
	
	
	public FinestraReproduccio(){
		
		
		
		//inicialitzem
		if(jspLlistatDisponible == null){
			jspLlistatDisponible = new JScrollPane();
			this.jspLlistatDisponible.setBackground(new Color(50,50,50));
			
		}
		if(jspUsuarisFollowing == null){
			jspUsuarisFollowing = new JScrollPane();
			this.jspUsuarisFollowing.setBackground(new Color(50,50,50));

		}
		if(jspLlistatPropia == null){
			jspLlistatPropia = new JScrollPane();
			this.jspLlistatPropia.setBackground(new Color(50,50,50));

		}
		if(jspLlistesFollowing == null){
			jspLlistesFollowing = new JScrollPane();
			this.jspLlistesFollowing.setBackground(new Color(50,50,50));

		}
		
		jfReproduccio = new JFrame();
		
		jfReproduccio.setVisible(true);
		jfReproduccio.setSize(800, 700);
		jfReproduccio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfReproduccio.setLocationRelativeTo(null);
		jfReproduccio.setBackground(new Color(24,24,24));
		jfReproduccio.addWindowListener(new CloseWindowController());
		
		
		//panell general
		this.jpReproduccio = new JPanel(new BorderLayout());
		this.jpReproduccio.setBackground(new Color(24,24,24));
		
		//barra del nort
		JPanel jpUsuari = new JPanel(new BorderLayout());
		jpUsuari.setBackground(new Color(40,40,40));
		jpUsuari.setBorder(BorderFactory.createLineBorder(Color.black));
		//adalt a la dreta
		/*//nom usuari
		JLabel jlUsuari = new JLabel("");
		jlUsuari.setForeground(new Color(164,164,164));
		jlUsuari.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); 
		//introduir-lo
		jpUsuari.add(jlUsuari,  BorderLayout.LINE_END);*/
		
		
		/*
		//logo
		JPanel jpBuscarFollow = new JPanel(new MigLayout("wrap"));
		jpBuscarFollow.setBackground(new Color(40,40,40));
		//logo adalt a l'esquerra
		ImageIcon imLogo = new ImageIcon("Images/logo.png");
		JLabel jlLogo = new JLabel();
		jlLogo.setForeground(new Color(164,164,164));
		ImageIcon icLogo = new ImageIcon(imLogo.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		jlLogo.setIcon(icLogo);
		jlLogo.setBorder(new EmptyBorder(1, 5, 0, 10));
		jpBuscarFollow.add(jlLogo,"dock west");
		
		//buscar (no fa res) 
		ImageIcon imBuscar = new ImageIcon("Images/buscar.png");
		JLabel jlBuscar = new JLabel();
		jlBuscar.setForeground(new Color(164,164,164));
		ImageIcon icBuscar = new ImageIcon(imBuscar.getImage().getScaledInstance(200, 30, Image.SCALE_DEFAULT));
		jlBuscar.setIcon(icBuscar);
		
		
		//text i boto de seguir usuari
		JPanel jpBuscarUsuari = new JPanel(new MigLayout("wrap"));
		jpBuscarUsuari.setBackground(new Color(40,40,40));
		JTextField jtfBuscar = new JTextField(15);
		jtfBuscar.setBackground(new Color(251,251,251));
		jtfBuscar.setForeground(new Color(50,50,50));
		jtfBuscar.setText("Buscar");
		jtfBuscar.setFocusable(false);
		
		jpBuscarUsuari.add(jtfBuscar,"dock west");

		jpUsuari.add(jpBuscarUsuari, BorderLayout.LINE_START);
		jpUsuari.add(jpBuscarFollow, BorderLayout.LINE_END);
		*/
		
		//afegim la barra d'adalt al panell
		jpReproduccio.add(bucarUsuari(), BorderLayout.NORTH);
		
		//afegim la columna de l'esquerra, faig un mig layout per fer us dels wrap
		//Musica disponible, â€œLlistes de musica propies i Llistes de musica following
		
		JPanel jpGestionar = new JPanel(new MigLayout("wrap"));
		jpGestionar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		jpGestionar.setBackground(new Color(40,40,40));
		jpGestionar.setBorder(BorderFactory.createLineBorder(Color.black));

		String disponible = "Música disponible";
		String propies = "Música propia";
		String llistesfollowing = "Música following";
		String usuarisfollowing = "Gestio usuaris";
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

			jlDisponible.addMouseListener(new ControladorLlistar("disponible"));
			jlPropies.addMouseListener(new ControladorLlistar("propia"));
			jlUsuarisfollowing.addMouseListener(new ControladorLlistar("usuarisfollowing"));
			jlLlistesfollowing.addMouseListener(new ControladorLlistar("llistesfollowing"));
			
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
		ImageIcon imNext = new ImageIcon("Images/next-button1.png");
		JLabel jlNext = new JLabel();
		jlNext.setForeground(new Color(164,164,164));
		ImageIcon icNext = new ImageIcon(imNext.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		jlNext.setIcon(icNext);
		
		//back
		ImageIcon imBack = new ImageIcon("Images/back-button1.png");
		JLabel jlBack = new JLabel();
		jlBack.setForeground(new Color(164,164,164));
		ImageIcon icBack = new ImageIcon(imBack.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
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

		jlPlay.addMouseListener(new ControladorReproductor("play"));
		jlBack.addMouseListener(new ControladorReproductor("back"));
		jlNext.addMouseListener(new ControladorReproductor("next"));
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
		this.alMusica = alMusica;
		//Tabla musica disponible 
		Vector<String> columnas = new Vector();
		
		columnas.add("Nom canco");
		columnas.add("Genere");
		columnas.add("Album");
		columnas.add("Artistes");
		columnas.add("Estrelles");

   
		Vector filas = new Vector();
		for (int i = 0; i <alMusica.size(); i ++) {
			Vector<String> fila = new Vector();
			
			fila.add(alMusica.get(i).getNom());
			fila.add(alMusica.get(i).getGenere());
			fila.add(alMusica.get(i).getAlbum());
			fila.add(alMusica.get(i).getArtista());
			int estrelles = Integer.parseInt(alMusica.get(i).getEstrelles());
			int nVotacio = Integer.parseInt(alMusica.get(i).getnVotacio());
			if(nVotacio != 0) {
				fila.add( Integer.toString (estrelles/nVotacio));
			} else {
				fila.add("0");
			}
			
			filas.add(fila);
		}
	
		taulaMusica = new JTable(filas, columnas){
		
		public boolean isCellEditable (int rowIndex, int vColIndex) {
			return false;
		}};		
		
		for(int i=0;i<columnas.size();i++){
			taulaMusica.getColumnModel().getColumn(i).setCellRenderer(new TableRenderer());
		}
		//JScrollPane jspAux = new JScrollPane(taulaMusica);	
		this.jspLlistatDisponible = new JScrollPane(taulaMusica);	
		this.jspLlistatDisponible.setBackground(new Color(50,50,50));
		this.jspLlistatDisponible.getViewport().setBackground(new Color(50,50,50));
		this.jspLlistatDisponible.setFocusable(false);

	}
	
	public void setUsuaris(ArrayList<User> alUsers) {
		this.alUsers = alUsers;	
	}
	
	/**
	 * Aquesta funcio rep l'array de totes del cancos propies i actualitza la taula on es mostraran totes.
	 */
	
	public void setMusicaPropia(){
		JTabbedPane jtpLlistatPropia = new JTabbedPane();
		jtpLlistatPropia.setBackground(new Color(50,50,50));
		//jtpLlistatPropia.setFocusable(false);
		
		
		jtpLlistatPropia.addTab("Crear llista",new JScrollPane(CrearlLlista()));
		jtpLlistatPropia.addTab("Visualitzar llistes", new JScrollPane(setVisualitzarLlistes()));	
		jtpLlistatPropia.addTab("Eliminar llista", EliminarLlista());
		
		this.jspLlistatPropia = new JScrollPane(jtpLlistatPropia);
		this.jspLlistatPropia.setBackground(new Color(50,50,50));
		this.jspLlistatPropia.getViewport().setBackground(new Color(50,50,50));
		//this.jspLlistatPropia.setFocusable(false);
		
	}
	
	/**
	 * Aquesta funcio permetra crear una llista i dir si es privada i retornara un scrollPane
	 */
	public JPanel CrearlLlista(){
		jspCrearLlista = new JScrollPane();
		jspCrearLlista.setBackground(new Color(50,50,50));
		jspCrearLlista.getViewport().setBackground(new Color(50,50,50));
		
		JPanel jpCrearLlista = new JPanel(new MigLayout("al center center, wrap, gapy 10"));
		jpCrearLlista.setBackground(new Color(50,50,50));
		
		JTextField jtfNomLlista = new JTextField(15);
		//jtfNomLlista.setText("Nom llista");
		PromptSupport.setPrompt("Nom Llista", jtfNomLlista);


		JTextField jtfPrivadaSN = new JTextField(5);
		//jtfPrivadaSN.setText("Privada (S/N)");
		PromptSupport.setPrompt("Privada (S/N)", jtfPrivadaSN);

		
		JButton jbCrearLlista = new JButton();		
		jbCrearLlista.setText("Crear Llista");
		
		jbCrearLlista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//si premen crear
				if(!jtfNomLlista.getText().isEmpty() && !jtfPrivadaSN.getText().isEmpty()){
					System.out.println(jtfPrivadaSN.getText());
					if(jtfPrivadaSN.getText().equals("s") || jtfPrivadaSN.getText().equals("S")){
						//si es privada es 0
						System.out.println("he entrat aquiii");	
						if(ControladorFinestres.crearLlista(jtfNomLlista.getText(), 1) == 1){
							JOptionPane.showMessageDialog(jpCrearLlista, "La llista s'ha inserit correctament ", "Informacio", JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(jpCrearLlista, "Hi ha hagut un error al inserir. ", "Error", JOptionPane.INFORMATION_MESSAGE);
						}
						
					}
					if(jtfPrivadaSN.getText().equals("n") || jtfPrivadaSN.getText().equals("N")){
						System.out.println("he entrat aquiii");	
						if(ControladorFinestres.crearLlista(jtfNomLlista.getText(), 0) == 1){
							JOptionPane.showMessageDialog(jpCrearLlista, "La llista s'ha inserit correctament ", "Informacio", JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(jpCrearLlista, "Hi ha hagut un error al inserir. ", "Error", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					jtfPrivadaSN.setText("");
					jtfNomLlista.setText("");
				}				
			}
		});
		
		jpCrearLlista.add(jtfNomLlista, "span 2, grow, wrap");
		jpCrearLlista.add(jtfPrivadaSN, "span 2, grow, wrap");
		jpCrearLlista.add(jbCrearLlista, "span 2, grow, wrap");
		
		return jpCrearLlista;
	}
	
	/**
	 * Aquesta funcio permetra esborrar llistes
	 */
	public JScrollPane EliminarLlista(){
		JScrollPane jspEliminarLlista = new JScrollPane();
		jspEliminarLlista.setBackground(new Color(50,50,50));
		jspEliminarLlista.getViewport().setBackground(new Color(50,50,50));
		
		return jspEliminarLlista;
	}
	
	
	public JPanel setVisualitzarLlistes(){
		
		JList jlLlistes = new JList(ControladorFinestres.getlistesPropies().toArray());
		jlLlistes.setBorder(BorderFactory.createEmptyBorder(5,5,5,30));
		jlLlistes.setCellRenderer(new ListRenderer());
		jlLlistes.setBackground(new Color(70,70,70));
		//jlLlistes.setFocusable(false);
		
		jpVisualitzarLlistes = new JPanel(new BorderLayout());
		jpVisualitzarLlistes.add(jlLlistes, BorderLayout.WEST);
		jpVisualitzarLlistes.setBackground(new Color(50,50,50));
		
		//jpVisualitzarLlistes.setFocusable(false);
		
		jlLlistes.addMouseListener(new ControladorLlistesMusica(jlLlistes));
			
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
		taulaMusicaLlista = new JTable(filas, columnas){
		
		public boolean isCellEditable (int rowIndex, int vColIndex) {
			return false;
		}};
		
		for(int i=0;i<columnas.size();i++){
			taulaMusicaLlista.getColumnModel().getColumn(i).setCellRenderer(new TableRenderer());
		}
		
		
		//sense aixo es quedara el primer jpanel(border.center)
		BorderLayout layout =  (BorderLayout) jpVisualitzarLlistes.getLayout();
		if (layout.getLayoutComponent(BorderLayout.CENTER) != null){
			jpVisualitzarLlistes.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		}	
		
		taulaMusicaLlista.addMouseListener(new VotarController(this));
		
		jpVisualitzarLlistes.add(new JScrollPane(taulaMusicaLlista), BorderLayout.CENTER);
		jpVisualitzarLlistes.setBackground(new Color(50,50,50));
		//jpVisualitzarLlistes.repaint();
		jpVisualitzarLlistes.validate();
	}
	
	
	public void actualitzaLlistaFollowingSeleccionada (ArrayList<Canco> musicaLlista) {

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
		taulaMusicaLlista = new JTable(filas, columnas){
		
		public boolean isCellEditable (int rowIndex, int vColIndex) {
			return false;
		}};
		
		for(int i=0;i<columnas.size();i++){
			taulaMusicaLlista.getColumnModel().getColumn(i).setCellRenderer(new TableRenderer());
		}
		
		
		//sense aixo es quedara el primer jpanel(border.center)
		BorderLayout layout =  (BorderLayout) jpLlistesFollowing.getLayout();
		if (layout.getLayoutComponent(BorderLayout.CENTER) != null){
			jpLlistesFollowing.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		}	
		
		
		jpLlistesFollowing.add(new JScrollPane(taulaMusicaLlista), BorderLayout.CENTER);
		jpLlistesFollowing.setBackground(new Color(50,50,50));
		jpLlistesFollowing.validate();
		
	}
		
	
	
	/**
	 * Aquesta funcio rep l'array de totes llistes dels usuaris que segueix i els mostra en una taula
	 */
	
	public void setLlistesFollowing(ArrayList<Llistes> llFollowing){
		jpLlistesFollowing = new JPanel(new BorderLayout());
		
		ArrayList<String> nomLlistes = new ArrayList <String>();
		
		for (int i = 0; i < llFollowing.size(); i ++) {
			nomLlistes.add(llFollowing.get(i).getNom_llista());
		}
		
		JList jlLlistesFollowing = new JList(nomLlistes.toArray());
		jlLlistesFollowing.setBorder(BorderFactory.createEmptyBorder(5,5,5,30));
		jlLlistesFollowing.setCellRenderer(new ListRenderer());
		jlLlistesFollowing.setBackground(new Color(70,70,70));
		
		jlLlistesFollowing.addMouseListener(new ControladorLlistesFollowing(jlLlistesFollowing));
		

		
		jpLlistesFollowing.add(jlLlistesFollowing, BorderLayout.WEST);
		jpLlistesFollowing.setBackground(new Color(50,50,50));
		
		this.jspLlistesFollowing = new JScrollPane(jpLlistesFollowing);
		this.jspLlistesFollowing.setBackground(new Color(50,50,50));
		this.jspLlistesFollowing.getViewport().setBackground(new Color(50,50,50));
		//this.jspLlistesFollowing.setFocusable(false);
	}
	
	/**
	 * Aquesta funcio rep l'array de tots els usuaris que segueix i els mostra en una taua
	 */
	
	public void setUsuarisFollowing(ArrayList<sUser> alUsuari){
		//podem escollir entre llistar els usuaris que seguim o buscar un usuari per seguir-lo/seixar-lo de seguir
		JTabbedPane jtpFollowing = new JTabbedPane();
		//buscar un nou usuari
		JPanel jpNouFollow = new JPanel(new MigLayout("al center center, wrap, gapy 10"));
		JTextField jtfUsuari = new JTextField(15);
		PromptSupport.setPrompt("Nom usuari", jtfUsuari);
		jpNouFollow.add(jtfUsuari);
		//JPanel jpLlistat = new JPanel(new BorderLayout());

		
		//jtpFollowing.add("Llistat following", llistatFollowing());
		//jtpFollowing.add("Buscar Usuari", bucarUsuari());
		

		//this.jspUsuarisFollowing = new JScrollPane(taulaUsuariFollowing);	
		this.jspUsuarisFollowing = new JScrollPane(llistatFollowing(alUsuari));
		this.jspUsuarisFollowing.setBackground(new Color(50,50,50));
		this.jspUsuarisFollowing.getViewport().setBackground(new Color(50,50,50));
		//jspUsuarisFollowing.setFocusable(false);

		
	}
	
	public JPanel bucarUsuari() {
		
		JPanel jpLogo = new JPanel(new MigLayout("wrap"));
		jpLogo.setBackground(new Color(40,40,40));
		//logo adalt a l'esquerra
		ImageIcon imLogo = new ImageIcon("Images/logo.png");
		JLabel jlLogo = new JLabel();
		jlLogo.setForeground(new Color(40,40,40));
		ImageIcon icLogo = new ImageIcon(imLogo.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		jlLogo.setIcon(icLogo);
		jlLogo.setBorder(new EmptyBorder(1, 5, 0, 10));
		jpLogo.add(jlLogo,"dock west");
		
		JPanel jpBuscaUsuari = new JPanel(new BorderLayout());
		jpBuscaUsuari.setBackground(new Color(40,40,40));
		
		JTextField jtBusca = new JTextField(20);
		//jtBusca.setForeground(Color.WHITE);
		//jtBusca.setBackground(new Color(40,40,40));
		
		JButton jbBusca = new JButton();
		jbBusca.setText("Buscar");
		
		//Afegim el controlador al boto de buscar
		ControladorBotons controladorBusca = new ControladorBotons(jtBusca);
		jbBusca.addMouseListener(controladorBusca);
		
		JPanel jpBuscar = new JPanel (new BorderLayout());
		jpBuscar.setBackground(new Color(40,40,40));
		jpBuscar.add(jtBusca, BorderLayout.LINE_START);
		jpBuscar.add(jbBusca, BorderLayout.LINE_END);
		
		JPanel jpSuperior = new JPanel (new BorderLayout());
		JPanel auxColor = new JPanel();
		auxColor.setBackground(new Color(40,40,40));
		jpSuperior.add(jpLogo, BorderLayout.LINE_START);
		jpSuperior.add(auxColor, BorderLayout.CENTER);
		jpSuperior.add(jpBuscar, BorderLayout.LINE_END);
		jpBuscaUsuari.add(jpSuperior, BorderLayout.NORTH);
		return jpBuscaUsuari;
	}
	
	public JTable getTaulaFollowing() {
		return jtLlistatFollowing;
	}
	
	public DefaultTableModel getModel() {
		return tableModel;
	}
	
	public JPanel llistatFollowing(ArrayList <sUser> alUser) {
		jpLlistatFollowing = new JPanel(new BorderLayout());

		
		Vector<String> columnas = new Vector<String>();
		columnas.add ("Nom usuari");
		
		
		Vector<String> filas = new Vector<String>();
		jtLlistatFollowing = new JTable(filas, columnas){
		
		public boolean isCellEditable (int rowIndex, int vColIndex) {
			return false;
		}};
		
		
		tableModel = (DefaultTableModel)jtLlistatFollowing.getModel();
		tableModel.setRowCount(0);
		tableModel.setRowCount(alUser.size());
		System.out.println(alUser.size());
		
		String[]data = new String[1];
		for (int i = 0; i < alUser.size(); i ++) {

			//String[]data = new String[4];
			if (!alUser.get(i).getNickname().isEmpty()){
				data[0] = alUser.get(i).getNickname();
				tableModel.insertRow(i, data);
				
			}
		}
		if(alUser != null){
			for(int i = alUser.size(); i < tableModel.getRowCount(); i++) {
				
				tableModel.removeRow(i);
				
			}
		}
		
		
		//data[0] = alUser.get(i).getNickname();
		//data[0] = "aaaaa";
		//tableModel.addRow(data);
		
		
		jtLlistatFollowing.setModel(tableModel);
		jtLlistatFollowing.addMouseListener(new DeleteController(this));

		
		
		JLabel jtTitol = new JLabel ("Usuaris que segueixes");
		jtTitol.setFont(new Font("Arial", Font.PLAIN, 16));
		jtTitol.setBackground(new Color(184,184,184));
		
		//jtLlistatFollowing.setBackground(new Color(184,184,184));
		
		JScrollPane jspLlistat = new JScrollPane(jtLlistatFollowing);
		tableModel.fireTableDataChanged();
		
		jpLlistatFollowing.add(jspLlistat, BorderLayout.CENTER);
		jpLlistatFollowing.add(jtTitol, BorderLayout.PAGE_START );
		
		return jpLlistatFollowing;
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
				setMusicaDisponible(alMusica);
				//this.jspLlistatDisponible.setFocusable(false);
				this.jpReproduccio.add(this.jspLlistatDisponible, BorderLayout.CENTER);
				this.jpReproduccio.setBackground(new Color(50,50,50));
				this.jpReproduccio.validate();
				break;
			 
			case "propia":
				setMusicaDisponible(alMusica);//le de posar per desclicar la canco
				/*provisional: */ setMusicaPropia();
				
				this.jpReproduccio.add(this.jspLlistatPropia, BorderLayout.CENTER);
				this.jpReproduccio.setBackground(new Color(50,50,50));
				this.jpReproduccio.validate();
				
				break;
				
			case "llistesfollowing":
				setMusicaDisponible(alMusica);//le de posar per desclicar la canco
				
				
				
				this.jpReproduccio.add(this.jspLlistesFollowing, BorderLayout.CENTER);
				this.jpReproduccio.setBackground(new Color(50,50,50));
				this.jpReproduccio.validate();
				break;
				
				
			case "usuarisfollowing":
				setMusicaDisponible(alMusica);//le de posar per desclicar la canco
				
				//setUsuarisFollowing(new ArrayList<>());
				this.jpReproduccio.add(this.jspUsuarisFollowing, BorderLayout.CENTER);
				this.jpReproduccio.setBackground(new Color(50,50,50));
				this.jpReproduccio.validate();
				break;
				
		
			default: break;
			
		}
	}
	
	
	
	public JTable getTaulaMusica() {
		return taulaMusica;
	}
	
	public JTable getTaulaLlistaMusicaFollowing() {
		return taulaMusicaLlista;
	}
	
}
