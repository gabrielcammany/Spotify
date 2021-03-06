package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdesktop.xswingx.PromptSupport;

import controller.ControladorFinestres;
import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class FinestraLogin extends JFrame {
	private JTextField jtfUsuari;
	private JPasswordField jtfPassword;
	private JButton jbAccedeix;
	//private JButton jbRegistre;
	private JFrame jfLog;
	
	

	
	public FinestraLogin() {
		
		jfLog = new JFrame("SPOTYFAI - Login");
		jfLog.setUndecorated(false);
		JPanel jpLog = new JPanel();
		
		//color negre del fons
		//jpLog.setBackground(new Color(29,29,23));
		//per alinear al mig
		jpLog.setLayout(new MigLayout("al center center, wrap, gapy 10"));
		//per  fer-lo transparent i que es vegi el fons
		jpLog.setOpaque(false);
		
			
		jtfUsuari = new JTextField(15);
		jtfUsuari.setForeground(Color.WHITE);
		jtfUsuari.setBackground(new Color(51,51,51));
		PromptSupport.setPrompt("Nom d'Usuari", jtfUsuari);

		jtfPassword = new JPasswordField(15);
		jtfPassword.setBackground(new Color(51,51,51));
		jtfPassword.setForeground(Color.WHITE);
		PromptSupport.setPrompt("Contrasenya", jtfPassword);
		
		//creacio botons
		JButton jbInicia = new JButton ();
		JButton jbRegistre = new JButton();
		//JButton tanca = new JButton ();
		
		jbInicia.setText("Inicia Sessio");
		
		
		jbRegistre.setText("Registra't");
		
		//Logo
		/*ImageIcon logo = new ImageIcon("Images/logoSpotyfai1.png");
		JLabel jlLogo = new JLabel();
		ImageIcon icono = new ImageIcon(logo.getImage().getScaledInstance(200, 60, Image.SCALE_DEFAULT));
		jlLogo.setIcon(icono);
		
		
	
		//afegim els jtarea al jpanel log
		jpLog.add(jlLogo, "span 2, grow, wrap");*/
		jpLog.add(jtfUsuari, "span 2, grow, wrap");
		jpLog.add(jtfPassword,  "span 2, grow, wrap");
		//afegim els botons al jpanel log
		jpLog.add(jbInicia, "span 2, grow, wrap");
		jpLog.add(jbRegistre, "span 2, grow, wrap");
		
		
		//background
		ImageIcon bk = new ImageIcon("Images/PantallaLogin.jpg");
		JLabel jlBk = new JLabel(new ImageIcon(bk.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT)));
		jlBk.setLayout(new MigLayout("al center center, wrap, gapy 10"));
		jfLog.setContentPane(jlBk);

		//afegim el jpanel log al jFrame
		jfLog.add(jpLog);
		
		jfLog.pack();
		
		
		jfLog.setVisible(true);
		jfLog.setSize(500, 500);
		
		jfLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(300,200);
		jfLog.setLocationRelativeTo(null);
		
		
		//Al polsar el boto de "Registra't" obrirem una segona finestra
		jbRegistre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Finestra_Registre fReg = new FinestraRegistre();
				ControladorFinestres.nouRegistre();
				
			}
		});
		jbInicia.requestFocus();
		jbInicia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Finestra_Registre fReg = new FinestraRegistre();
				
				if(!getjtfUsuari().getText().isEmpty()){	
					PromptSupport.setForeground(Color.LIGHT_GRAY, jtfUsuari);
				}else if(!String.copyValueOf(getjtfPassword().getPassword()).isEmpty()){
					PromptSupport.setForeground(Color.LIGHT_GRAY, jtfPassword);
				}
				
				if(getjtfUsuari().getText().isEmpty() && String.copyValueOf(getjtfPassword().getPassword()).isEmpty()){
					//Font font = new Font("Verdana", Font.BOLD, 12);	
					PromptSupport.setForeground(new Color(255, 77, 77), jtfUsuari);
					PromptSupport.setForeground(new Color(255, 77, 77), getjtfPassword());
	                mostraMissatgeError("Camps buits\n Error a iniciar sessio");
				}else if(String.copyValueOf(getjtfPassword().getPassword()).isEmpty()){
	                mostraMissatgeError("Has d'inserir una contrasenya\n Error a iniciar sessio");
					PromptSupport.setForeground(new Color(255, 77, 77), getjtfPassword());
				}else if(getjtfUsuari().getText().isEmpty()){
	                mostraMissatgeError("Nom d'usuari obligatori\n Error a iniciar sessio");
					PromptSupport.setForeground(new Color(255, 77, 77), jtfUsuari);		
				}else{
					try {
						ControladorFinestres.Login();
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
				
				
			}
		});
	}
	public void mostraMissatgeError(String s) {
		JOptionPane.showMessageDialog(jtfUsuari, s, "Error", JOptionPane.ERROR_MESSAGE);
	}
	public void tancaFinestraLogin() {
		jfLog.dispose();
	}
	
	public JTextField getjtfUsuari() {
		return jtfUsuari;
	}
	
	public JPasswordField getjtfPassword() {
		return jtfPassword;
	}
	
	public JButton getjbAccedeix() {
		return jbAccedeix;
	}

}
