package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * 
 * Clase per controlar quan tanquen la finestra del client
 *
 */
public class CloseWindowController extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent e) {
		
		//Demanem que el servidor es carregui la sessio
		//ControladorFinestres.getR().getThread().stop();
		ControladorFinestres.getInfoServidor().closeSessio();
		
	}
	

}
