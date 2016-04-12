package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import model.Messages;
import model.User;
import network.MessageService;
import view.MainWindow;


public class ButtonsController implements ActionListener {
	// VISTA
	private MainWindow view;
	// NETWORK
	private MessageService mService;
	
	private User user;
	
	private Messages cadenas;
	
	network.ConectorDB conn = new network.ConectorDB("dpo_root", "sinminus", "bd_espotifi", 3306);
	
	public ButtonsController(){}
	public ButtonsController(User user, MainWindow view) {
		this.user = user;
		this.view = view;
		// Instanciem la classe per rebre missatges.
		// Passem per parametre una referencia al propi objecte
		// per tal que notifiqui larribada de nous missatges.
		// Aquest tambe podria ser creat des del principal.
		this.mService = new MessageService(this);
		this.cadenas = new Messages();
		conn.connect();
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("START")) {
			// Iniciem el servei
			mService.startService();
			view.changeButtonsStateStarted();
		} else if (event.getActionCommand().equals("STOP")) {
			// Aturem el servei
			mService.stopService();
			view.changeButtonsStateStopped();
		}
	}
	
	public void showMessage(String message) {
		view.addText(message);
		cadenas.tableMessages(message);
	}
	public boolean inserirUser(String cad){
		conn.insertQuery(cad);
		return true;
	}
	// Aquest metode es invocat pel servei de missatges quan arriba
	// un nou missatge
	public void showInformation(String info) {
		//cotxe.creaCotxe(info);
		// Mostrem el missatge rebut a la vista
		view.addText(info);
	}

}
