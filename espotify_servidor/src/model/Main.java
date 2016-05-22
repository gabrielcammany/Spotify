package model;

import javax.swing.SwingUtilities;

import controller.ButtonsController;
import controller.GestioController;
import network.ConectorDB;
import network.JsonConfig;
import view.FinestraServidor;
import view.MainWindow;
/**
 * 
 * Es el main del servidor
 *
 */
public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				
				ConectorDB conn = new ConectorDB(JsonConfig.creaJson());
				GestioController gestioController = new GestioController(conn);
				// Creem la VISTA
				MainWindow view = new MainWindow();
				//User user = new User();
				Data.inicialitza();
				FinestraServidor viewF = new FinestraServidor(gestioController);
				// Creem el CONTROLADOR
				// Establim la relacio CONTROLADOR->VISTA
				ButtonsController controller = new ButtonsController(view, viewF,conn);
				// Establim la "relacio" VISTA->CONTROLADOR
				view.registerController(controller);
				
				
				
				// Mostrem la VISTA
				view.setVisible(true);

				
			}
		});
	}
	
}