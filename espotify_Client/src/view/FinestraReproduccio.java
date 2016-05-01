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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.ControladorFinestres;
import controller.ControladorLlistar;
import controller.ControladorReproductor;
import model.Canco;
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
	private JScrollPane jspLlistat;
	public JTable taulaMusica;
	
	
	
	public FinestraReproduccio(ControladorFinestres cf) throws ClassNotFoundException{
		this.cf = cf;
		
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

		JLabel jlBlanc0 = new JLabel("  ");
		JLabel jlBlanc1 = new JLabel("  ");
		JLabel jlBlanc2 = new JLabel("  ");
		String disponible = "Música disponible";
		String propies = "Música propia";
		String following = "Música following";
		JLabel jldisponible;
		try {
			jldisponible = new JLabel(new String(disponible.getBytes("UTF-8"),"UTF-8"));
			jldisponible.setForeground(new Color(164,164,164));
			JLabel jlpropies = new JLabel(new String(propies.getBytes("UTF-8"),"UTF-8"));
			jlpropies.setForeground(new Color(164,164,164));
			JLabel jlfollowing = new JLabel(new String(following.getBytes("UTF-8"),"UTF-8"));
			jlfollowing.setForeground(new Color(164,164,164));

			jldisponible.addMouseListener(new ControladorLlistar("disponible",cf));
			
			jlpropies.addMouseListener(new ControladorLlistar("propia",cf));
			jlfollowing.addMouseListener(new ControladorLlistar("following",cf));
			
			jpGestionar.add(jlBlanc0, "span 2, grow, wrap ");
			jpGestionar.add(jldisponible, "span 2, grow, wrap ");
			jpGestionar.add(jlBlanc1, "span 2, grow, wrap ");
			jpGestionar.add(jlpropies, "span 2, grow, wrap");
			jpGestionar.add(jlBlanc2, "span 2, grow, wrap ");
			jpGestionar.add(jlfollowing, "span 2, grow, wrap");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
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
		JPanel jpLlistat = new JPanel(new BorderLayout());
		
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
			System.out.println("entra");
			
			filas.add(fila);
		}
	
		taulaMusica = new JTable(filas, columnas){
		
		public boolean isCellEditable (int rowIndex, int vColIndex) {
			return false;
		}};
		
		
		this.jspLlistat = new JScrollPane(taulaMusica);
		
		
		
		//jpReproduccio.add(jspLlistat, BorderLayout.CENTER);

		
	}
	
	public void actualitzaOpcio(String opcio) {
		switch (opcio){
		
			case "disponible":
				this.jpReproduccio.add(this.jspLlistat, BorderLayout.CENTER);
				this.jpReproduccio.invalidate();
				this.jpReproduccio.validate();
				
				
			case "propia":
				this.jpReproduccio.add(new JScrollPane(), BorderLayout.CENTER);
				System.out.println(opcio + "jeje");
		}
	}
	
	public JTable getTaulaMusica() {
		return taulaMusica;
	
	}
	
	
}
