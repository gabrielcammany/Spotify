package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdesktop.xswingx.PromptSupport;

import controller.ControladorFinestres;
import net.miginfocom.swing.MigLayout;
/**
 * Gestio de la finestra encarregada de la introduccio de dades referents al registre. 
 * 
 *
 */



@SuppressWarnings("serial")
public class Finestra_Registre extends JPanel{
	
	private JFrame jfRegistre;
	private JButton jbRegistre;
	private JPasswordField jtfPassword;
	private JTextField jtfUsuari;
	private ImageIcon imagen;
	
	

	
	
	
	public Finestra_Registre() {
		
		

		
		jfRegistre = new JFrame("eSpotyfai - Registre");
		jfRegistre.setVisible(true);
		jfRegistre.setSize(350, 350);
		jfRegistre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfRegistre.setLocationRelativeTo(null);
		
		
		
		
		JPanel jpRegistre = new CustomPanel(){
	       

	    };	
		//centrar a la pantalla
		jpRegistre.setLayout(new MigLayout("al center center, wrap, gapy 10"));
		//color negre del fons
		//jpRegistre.setOpaque(false);
		
				
		jtfUsuari = new JTextField(15);
		jtfUsuari.setForeground(Color.WHITE);
		jtfUsuari.setBackground(new Color(51,51,51));
		PromptSupport.setPrompt("Nom d'Usuari", jtfUsuari);
		

		
		
		jtfPassword = new JPasswordField(15);
		jtfPassword.setBackground(new Color(51,51,51));
		jtfPassword.setForeground(Color.WHITE);
		PromptSupport.setPrompt("Contrasenya", jtfPassword);
		
		//Logo
		ImageIcon logo = new ImageIcon("espotify_Client/Images/logoSpotyfai.png");
		JLabel jlLogo = new JLabel();
		ImageIcon icono = new ImageIcon(logo.getImage()/*.getScaledInstance(800, 800, Image.SCALE_DEFAULT)*/);
		jlLogo.setIcon(icono);
		
		//afegim els jtarea al jpanel log
		jpRegistre.add(jlLogo, "span 2, grow, wrap ");
		jpRegistre.add(jtfUsuari, "span 2, grow, wrap");
		jpRegistre.add(jtfPassword,  "span 2, grow, wrap");
		
		//boto
		jbRegistre = new JButton ();
		
		jpRegistre.add(jbRegistre, "span 2, grow, wrap");
		jbRegistre.setText("Completar registre");
		
		
		
		ImageIcon bk = new ImageIcon("Images/PantallaRegistre.jpg");
		JLabel jlBk = new JLabel(new ImageIcon(bk.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT)));
		jlBk.setLayout(new MigLayout("al center center, wrap, gapy 10"));
		jfRegistre.setContentPane(jlBk);
		


		jfRegistre.add(jpRegistre);
		
		jfRegistre.requestFocus();
		
		jbRegistre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Finestra_Registre fReg = new FinestraRegistre();
				
				try {
					ControladorFinestres.Reproduccio();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	}
	
	
	public void tancaFinestraRegistre() {
		jfRegistre.dispose();
	}
	
	public JButton getjbRegistre() { 
		return jbRegistre;
	}
	
	public JPasswordField getjtfPassword() {
		return jtfPassword;
	}
	
	public JTextField getjtfUsuari() {
		return jtfUsuari;
	}

}
