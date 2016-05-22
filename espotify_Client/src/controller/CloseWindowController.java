package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.User;
import network.InfoServidor;

public class CloseWindowController extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent e) {
		
		//Demanem que el servidor es carregui la sessio
		//ControladorFinestres.getR().getThread().stop();
		ControladorFinestres.getInfoServidor().closeSessio();
	}
	

}
