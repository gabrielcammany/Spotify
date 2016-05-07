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
	public Musica musica;
	private User user;
	
	
	public ButtonsController(){}
	public ButtonsController(/*User user,*/ MainWindow view, FinestraServidor viewF) {
		//this.user = user;
		this.view = view;
		this.viewF = viewF;
		this.viewF.setControlador(this);
		this.mService = new MessageService(this);
		
		
		
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("START")) {
			this.musica = new Musica();
			// Iniciem el servei
			mService.startService();
			view.changeButtonsStateStarted();
		} else if (event.getActionCommand().equals("STOP")) {
			// Aturem el servei
			mService.stopService();
			view.changeButtonsStateStopped();
		} else if (event.getActionCommand().equals("Addicio")) {
			System.out.println(viewF.getAddCanco());
			System.out.println(viewF.getAddAlbum());
			System.out.println(viewF.getAddArtista());
			System.out.println(viewF.getAddGenere());
			System.out.println(viewF.getAddUbicacio());
			viewF.netejaFormulari();
		}
	}
	
	public void creaFinestra() {
		System.out.println("########################################################");
		Usuaris allUsers = new Usuaris();
		viewF.creaFinestra(musica, allUsers);
	}
	
	
	
	public void incorrectUser(){
		vError.errorInsertUser();
	}
	

}
