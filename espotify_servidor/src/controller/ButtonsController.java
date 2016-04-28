package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Canco;
import model.Musica;
import model.User;
import model.Usuaris;
import network.MessageService;
import view.ErrorLog;
import view.FinestraServidor;
import view.MainWindow;


public class ButtonsController implements ActionListener {
	// VISTA
	private MainWindow view;
	private FinestraServidor viewF;
	//ERRORS
	public	ErrorLog vError =new ErrorLog();
	// NETWORK
	private MessageService mService;
	
	private User user;
	
	
	public ButtonsController(){}
	public ButtonsController(/*User user,*/ MainWindow view, FinestraServidor viewF) {
		//this.user = user;
		this.view = view;
		this.viewF = viewF;
		this.mService = new MessageService(this);
		
		
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
	
	public void creaFinestra() {
		Musica musica = new Musica();
		System.out.println("########################################################");
		Usuaris allUsers = new Usuaris();
		viewF.creaFinestra(musica);
	}
	
	
	
	public void incorrectUser(){
		vError.errorInsertUser();
	}
	

}
