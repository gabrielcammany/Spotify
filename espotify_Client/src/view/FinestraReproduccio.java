package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;





import controller.ControladorFinestres;
import net.miginfocom.swing.MigLayout;


/**
 * Gestio de la finestra de reproduccio, permet Llistar la musica disponible, les llistes propies,
 * fer follow i reproduir musica
 *
 */


public class FinestraReproduccio extends JFrame {
	
	private JFrame jfReproduccio;
	
	
	
	public FinestraReproduccio(){
		
		jfReproduccio = new JFrame();
		jfReproduccio.setVisible(true);
		jfReproduccio.setSize(800, 700);
		jfReproduccio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfReproduccio.setLocationRelativeTo(null);
		jfReproduccio.setBackground(new Color(24,24,24));
		
		
		//panell general
		JPanel jpReproduccio = new JPanel(new BorderLayout());
		jpReproduccio.setBackground(new Color(24,24,24));
		/*
		//background
		ImageIcon bk = new ImageIcon("Images/backGround1.jpg");
		JLabel jlBk = new JLabel(new ImageIcon(bk.getImage().getScaledInstance(800, 700, Image.SCALE_DEFAULT)));
		jfReproduccio.setContentPane(jlBk);*/
		
		
		/*
		 * ¡
		 * GET NOM USUARI!!!
		 * 
		 */	
		
		//barra del nort
		JPanel jpUsuari = new JPanel(new BorderLayout());
		jpUsuari.setBackground(new Color(40,40,40));
		jpUsuari.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel jlUsuari = new JLabel("Jorge el mejor  ");
		jlUsuari.setForeground(new Color(164,164,164));
		jlUsuari.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); 
		
		jpUsuari.add(jlUsuari,  BorderLayout.LINE_END);
		//afegim la barra d'adalt al panell
		jpReproduccio.add(jpUsuari, BorderLayout.NORTH);
		
		
		//afegim la columna de l'esquerra, faig un mig layout per fer us dels wrap
		//“Música disponible”, “Llistes de música pròpies” i “Llistes de música following”
		
		JPanel jpGestionar = new JPanel(new MigLayout("wrap"));
		jpGestionar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		jpGestionar.setBackground(new Color(40,40,40));
		jpGestionar.setBorder(BorderFactory.createLineBorder(Color.black));

		JLabel jlBlanc0 = new JLabel("  ");
		JLabel jlBlanc1 = new JLabel("  ");
		JLabel jlBlanc2 = new JLabel("  ");
		JLabel jldisponible = new JLabel("Música disponible");
		jldisponible.setForeground(new Color(164,164,164));
		JLabel jlpropies = new JLabel("Ḿsica propia  ");
		jlpropies.setForeground(new Color(164,164,164));
		JLabel jlfollowing = new JLabel("Música following");
		jlfollowing.setForeground(new Color(164,164,164));
		
		jpGestionar.add(jlBlanc0, "span 2, grow, wrap ");
		jpGestionar.add(jldisponible, "span 2, grow, wrap ");
		jpGestionar.add(jlBlanc1, "span 2, grow, wrap ");
		jpGestionar.add(jlpropies, "span 2, grow, wrap");
		jpGestionar.add(jlBlanc2, "span 2, grow, wrap ");
		jpGestionar.add(jlfollowing, "span 2, grow, wrap");
		
		jpReproduccio.add(jpGestionar, BorderLayout.WEST);
		

		
	
		
		
	
		
		//ho afegim tot al frame general
		jfReproduccio.add(jpReproduccio);
	}
	
}
