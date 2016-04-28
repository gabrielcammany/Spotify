package model;

import javax.swing.SwingUtilities;

import controller.ControladorFinestres;
import network.InfoServidor;


public class  Main {
	/*public static void main(String[] args) {
	    launch(args);
	    System.out.println("hola tete");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		@SuppressWarnings("unused")
		Login l = new Login(primaryStage);
	}*/

	 public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				//Creem la finestra de login i la mostrem
				InfoServidor infoServidor = new InfoServidor();
				@SuppressWarnings("unused")
				ControladorFinestres controladorf = new ControladorFinestres(infoServidor);
				//finestra_log.setVisible(true);
				
			}
		});
		
	}



}

