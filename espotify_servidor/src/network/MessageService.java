package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controller.ButtonsController;


public class MessageService {
	private ServerSocket sServer;
	private static final int PORT = 34567;
	// Relacio amb el fil dexecucio que escolta les peticions de connexio
	private MessageServiceWorker msWorker;
	// Relacio amb el controlador per notificar les recepcions de missatges
	private ButtonsController controller;
	int i = 0;
	
	public MessageService(ButtonsController controller) {
		this.controller = controller;
		try {
			sServer = new ServerSocket(PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Inicia el servei per la recepcio de missatges
	public void startService() {
		Thread t = new Thread(){
			@Override
			public void run(){
				while(true){
					try {
						System.out.println("Esperant..."+(i));
						Socket sClient = sServer.accept();
						msWorker = new MessageServiceWorker(sClient,controller.getMusica());
						new Thread(msWorker).start();
						System.out.println("Esperant..."+(i++));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
			t.start();
			// Creem el ServerSocket
			// Creem i iniciem un nou fil d execucio per tal descoltar
			// els clients i rebre els missatges per part dels clients
			// Informem al CONTROLADOR que informi que el servidor ha
			// estat iniciat, ell informara a la vista.
			//controller.showInformation("SERVER started. \nAwaiting messages...");
			controller.creaFinestra();
	
	}
	
	public void stopService() {
		try {
			msWorker.stopListening();
			sServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
