package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Messages;
import model.User;
import network.MessageService;
import view.ErrorLog;
import view.MainWindow;


public class ButtonsController implements ActionListener {
	// VISTA
	private MainWindow view;
	//ERRORS
	public	ErrorLog vError =new ErrorLog();
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
	
	public void incorrectUser(){
		vError.errorInsertUser();
	}
	public String selectUser(String cad){
		ResultSet responseServer = conn.selectQuery(cad);
		String nickname= null;

		try {
			while (responseServer.next()) {
			  nickname = responseServer.getString("nickname");
			  System.out.println("[Servidor] "+nickname );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return nickname;
	}
	
	// Aquest metode es invocat pel servei de missatges quan arriba
	// un nou missatge
	public void showInformation(String info) {
		//cotxe.creaCotxe(info);
		// Mostrem el missatge rebut a la vista
		view.addText(info);
	}

}
