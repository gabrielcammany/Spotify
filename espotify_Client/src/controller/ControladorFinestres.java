package controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import network.InfoServidor;
import view.Finestra_Registre;
import view.Finestra_login;
/**
 * Controlador encarregat de gestionar la relacion entre les diferents finestres 
 * (Login, Registre, finestra principal)
 * @author carlaurrea
 *
 */
public class ControladorFinestres {
	public Finestra_login jfLogin;
	public Finestra_Registre fRegistre;

	
	public ControladorFinestres () {
		this.jfLogin = new Finestra_login(this);	
		
	}
	
	/**
	 * Metode encarregat de gestionar un nou registre a l'aplicacio
	 */
	
	public void nouRegistre() {
		System.out.println("asd");
		//tanquem finestra de login
		jfLogin.tancaFinestraLogin();
		fRegistre = new Finestra_Registre();
		simulaTAB();
		fRegistre.getjbRegistre().addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                InfoServidor info = new InfoServidor();
                info.enviarUsuari(1, fRegistre.getjtfUsuari().getText(),fRegistre.getjtfPassword().getPassword());
            }
        }); 
		
	}
	
	/**
	 * Proces de login
	 */
	public void Login() {
		jfLogin.getjbAccedeix().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				InfoServidor info = new InfoServidor();
				info.enviarUsuari(2, jfLogin.getjtfUsuari().getText(), jfLogin.getjtfPassword().getPassword());
				
			}
			
			
		});
		
	}
	
	public void simulaTAB(){
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);		

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
}
