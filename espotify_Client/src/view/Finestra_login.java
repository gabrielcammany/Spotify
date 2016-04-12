package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControladorFinestres;
import javafx.scene.shape.Box;
import net.miginfocom.swing.MigLayout;


public class Finestra_login extends JFrame {
	JTextField jtfUsuari;
	JTextField jtfPassword;
	JButton jbAccedeix;
	JButton jbRegistre;
	ControladorFinestres controladorf;
	
	public Finestra_login(ControladorFinestres controladorf) {
		
		JFrame jfLog = new JFrame("SPOTYFAI - Login");
		JPanel jpLog = new JPanel();
		
		//color negre del fons
		//jpLog.setBackground(new Color(29,29,23));
		//per alinear al mig
		jpLog.setLayout(new MigLayout("al center center, wrap, gapy 10"));
		//per  fer-lo transparent i que es vegi el fons
		jpLog.setOpaque(false);
		
		
		
			
		jtfUsuari = new JTextField(15);
		jtfUsuari.setForeground(Color.WHITE);
		jtfPassword = new JTextField(15);
		//color gris dins del text area
		jtfUsuari.setBackground(new Color(51,51,51));
		jtfPassword.setBackground(new Color(51,51,51));
		jtfPassword.setForeground(Color.WHITE);
		
		
		//el que surt dins del text area
		jtfUsuari.setText("Usuari");
		jtfPassword.setText("Contrasenya");
		
		//creacio botons
		JButton jbInicia = new JButton ();
		JButton jbRegistre = new JButton();
		
		jbInicia.setText("Inicia Sessio");
		jbRegistre.setText("Registra't");
		
		//Logo
		ImageIcon logo = new ImageIcon("/espotify_Client/Images/logoSpotyfai.png");
		JLabel jlLogo = new JLabel();
		ImageIcon icono = new ImageIcon(logo.getImage().getScaledInstance(200, 60, Image.SCALE_DEFAULT));
		jlLogo.setIcon(icono);
		
		
	
		//afegim els jtarea al jpanel log
		jpLog.add(jlLogo, "span 2, grow, wrap");
		jpLog.add(jtfUsuari, "span 2, grow, wrap");
		jpLog.add(jtfPassword,  "span 2, grow, wrap");
		//afegim els botons al jpanel log
		jpLog.add(jbInicia, "span 2, grow, wrap");
		jpLog.add(jbRegistre, "span 2, grow, wrap");
		
		
		//background
		ImageIcon bk = new ImageIcon("espotify_Client/Images/backGround.jpg");
		JLabel jlBk = new JLabel(new ImageIcon(bk.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT)));
		jlBk.setLayout(new MigLayout("al center center, wrap, gapy 10"));
		jfLog.setContentPane(jlBk);
		
		//afegim el jpanel log al jFrame
		jfLog.add(jpLog);
		
		jfLog.pack();
		
		
		jfLog.setVisible(true);
		jfLog.setSize(350, 350);
		
		jfLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(300,200);
		jfLog.setLocationRelativeTo(null);
		this.setTitle("eSpotyfai - Login");
		
		
		//Al polsar el boto de "Registra't" obrirem una segona finestra
		jbRegistre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Finestra_Registre fReg = new FinestraRegistre();
				controladorf.nouRegistre();
				
			}
		});
	}

}
