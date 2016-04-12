package controller;

import view.Finestra_Registre;
import view.Finestra_login;
/**
 * Controlador encarregat de gestionar la relacion entre les diferents finestres 
 * (Login, Registre, finestra principal)
 * @author carlaurrea
 *
 */
public class ControladorFinestres {
	Finestra_login fLogin;
	Finestra_Registre fRegistre;

	
	public ControladorFinestres () {
		this.fLogin = new Finestra_login(this);		
	}
	
	/**
	 * Metode encarregat de gestionar un nou registre a l'aplicacio
	 */
	
	public void nouRegistre() {
		//tanquem finestra de login
		fLogin.dispose();
		fRegistre = new Finestra_Registre();
		
	}
	
	
}
