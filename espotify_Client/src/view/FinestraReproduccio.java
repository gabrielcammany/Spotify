package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.UnsupportedEncodingException;

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
		 * Â¡
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
		//â€œMuÌ�sica disponibleâ€�, â€œLlistes de muÌ�sica proÌ€piesâ€� i â€œLlistes de muÌ�sica followingâ€�
		
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
		

		
	
		
		
	
		
		//ho afegim tot al frame general
		jfReproduccio.add(jpReproduccio);
	}
	
}
