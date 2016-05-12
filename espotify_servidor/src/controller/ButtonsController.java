package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Data;
import model.Musica;
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
	private Musica musica;
	private User user;
	
	
	public ButtonsController(){}
	public ButtonsController( MainWindow view, FinestraServidor viewF) {

		this.view = view;
		this.viewF = viewF;
		this.viewF.setControlador(this);
		this.mService = new MessageService(this);
		
		
		
	}
	
	public void insertSongUser(){
		String add = ("'"+viewF.getAddCanco()+"','"+viewF.getAddAlbum()+"','"+viewF.getAddArtista()+"','"+viewF.getAddUbicacio()+"','0','0','0'");
		
		ConectorDB conn = new ConectorDB() ;
		Query q =new Query();
		conn.insertQuery(q.queryList(10, add));
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
			insertSongUser();
			viewF.netejaFormulari();
		}
	}
	
	public void creaFinestra() {
		System.out.println("########################################################");
		Data.setUsers(new SocketController().selectUsers(true, null));
		viewF.creaFinestra(getMusica(),Data.getUsers());
	}
	
	
	
	public void incorrectUser(){
		vError.errorInsertUser();
	}
	public void setMusica(Musica musica) {
		this.musica = musica;
	}
	public Musica getMusica() {
		return musica;
	}
	

}
