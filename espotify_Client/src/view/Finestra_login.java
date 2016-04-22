package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class Finestra_login extends JFrame {
	private JTextField jtfUsuari;
	private JPasswordField jtfPassword;
	private JButton jbAccedeix;
	//private JButton jbRegistre;
	//private ControladorFinestres controladorf;
	private JFrame jfLog;
	
	

	
	public Finestra_login(ControladorFinestres controladorf) {
		
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
		ImageIcon logo = new ImageIcon("Images/logoSpotyfai1.png");
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
		ImageIcon bk = new ImageIcon("Images/backGround1.jpg");
		JLabel jlBk = new JLabel(new ImageIcon(bk.getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT)));
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
				controladorf.nouRegistre();
				
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
	                JOptionPane.showMessageDialog(jtfUsuari, "Camps buits", "Error a iniciar sessi�", 0);
				}else if(String.copyValueOf(getjtfPassword().getPassword()).isEmpty()){
	                JOptionPane.showMessageDialog(jtfUsuari, "Has d'inserir una contrasenya","Error a iniciar sessi�", 0);
					PromptSupport.setForeground(new Color(255, 77, 77), getjtfPassword());
				}else if(getjtfUsuari().getText().isEmpty()){
	                JOptionPane.showMessageDialog(jtfUsuari, "Nom d'usuari obligatori","Error a iniciar sessi�", 0);
					PromptSupport.setForeground(new Color(255, 77, 77), jtfUsuari);		
				}else{
					controladorf.Login();
				}
				
				
			}
		});
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
