package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Data;
import model.Query;
import model.User;
import network.ConectorDB;
import network.MessageService;
import view.ErrorLog;
import view.FinestraServidor;
import view.MainWindow;


public class ButtonsController implements ActionListener {
	// VISTA
	private MainWindow view;
	private FinestraServidor viewF;
	//ERRORS
	private	ErrorLog vError =new ErrorLog();
	// NETWORK
	private MessageService mService;
	private User user;
	
	
	public ButtonsController(){}
	public ButtonsController( MainWindow view, FinestraServidor viewF) {

		this.view = view;
		this.viewF = viewF;
		this.viewF.setControlador(this);
		this.mService = new MessageService(this);
		
		
		
	}
	
	public void insertSongUser(){
		String add = ("'"+viewF.getAddCanco()+"','"+viewF.getAddAlbum()+"','"+viewF.getAddArtista()+"','"+viewF.getAddGenere()+"','"+viewF.getAddUbicacio()+"','a','a','a'");
		Query q =new Query();
		ConectorDB.insertQuery(q.queryList(10, add));
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("START")) {
			Data.setAlMusica((new SocketController().selectSongs()));
			// Iniciem el servei
			mService.startService();
			view.changeButtonsStateStarted();
		} else if (event.getActionCommand().equals("STOP")) {
			// Aturem el servei
			mService.stopService();
			view.changeButtonsStateStopped();
		} else if (event.getActionCommand().equals("Addicio")) {			
			insertSongUser();
			viewF.netejaFormulari();
		}
	}
	
	public void creaFinestra() {
		System.out.println("########################################################");
		Data.setUsers(new SocketController().selectUsers(true, null));
		viewF.creaFinestra(Data.getAlMusica(),Data.getUsers());
	}
	
	
	
	public void incorrectUser(){
		vError.errorInsertUser();
	}
	

}
