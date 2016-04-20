package controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import network.InfoServidor;
import view.FinestraReproduccio;
import view.Finestra_Registre;
import view.Finestra_login;
/**
 * Controlador encarregat de gestionar la relacion entre les diferents finestres 
 * (Login, Registre, finestra principal)
 * @author carlaurrea
 *
 */
public class ControladorFinestres {
	public Finestra_login fLogin;
	public Finestra_Registre fRegistre;
	public FinestraReproduccio fReproduccio;

	
	public ControladorFinestres () {
		this.fLogin = new Finestra_login(this);	
		
	}
	
	/**
	 * Metode encarregat de gestionar un nou registre a l'aplicacio
	 */
	
	public void nouRegistre() {
		//tanquem finestra de login
		fLogin.tancaFinestraLogin();
		fRegistre = new Finestra_Registre();
		
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
		InfoServidor info = new InfoServidor();
		info.enviarUsuari(2, fLogin.getjtfUsuari().getText(), fLogin.getjtfPassword().getPassword());
		Reproduccio();	
		
	}
	
	/**
	 * Reproduir la musica
	 */
	
	public void Reproduccio(){
		/*
		 * 
		 * Comprovar dades correctes
		 * 
		 * depen de si ve per login o registre tancar la finestra correcte per no fer pataPUM
		 * 
		 */
		
		fReproduccio = new FinestraReproduccio();
		
		
		
		
	}
	
		
	
}
