package model;

import javax.swing.SwingUtilities;

import controller.ButtonsController;
import view.FinestraServidor;
import view.MainWindow;


public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				
				// Creem la VISTA
				MainWindow view = new MainWindow();
				User user = new User();
				FinestraServidor viewF = new FinestraServidor();
				// Creem el CONTROLADOR
				// Establim la relacio CONTROLADOR->VISTA
				ButtonsController controller = new ButtonsController(user,view, viewF);
				// Establim la "relacio" VISTA->CONTROLADOR
				view.registerController(controller);
				
				
				
				
				// Mostrem la VISTA
				view.setVisible(true);

				
			}
		});
	}
	
}