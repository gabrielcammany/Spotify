package network;

import java.io.IOException;
import java.net.ServerSocket;
import controller.ButtonsController;


public class MessageService {
	private ServerSocket sServer;
	private static final int PORT = 34567;
	// Relacio amb el fil dexecucio que escolta les peticions de connexio
	private MessageServiceWorker msWorker;
	// Relacio amb el controlador per notificar les recepcions de missatges
	private ButtonsController controller;
	
	public MessageService(ButtonsController controller) {
		this.controller = controller;
	}
	
	// Inicia el servei per la recepcio de missatges
	public void startService() {
		try {
			// Creem el ServerSocket
			sServer = new ServerSocket(PORT);
			// Creem i iniciem un nou fil d execucio per tal descoltar
			// els clients i rebre els missatges per part dels clients
			msWorker = new MessageServiceWorker(this, sServer);
			new Thread(msWorker).start();
			// Informem al CONTROLADOR que informi que el servidor ha
			// estat iniciat, ell informara a la vista.
			controller.showInformation("SERVER started. \nAwaiting messages...");
			controller.creaFinestra();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopService() {
		try {
			// Aturem el fil d execucio
			msWorker.stopListening();
			// Tanquem el ServerSpcket
			sServer.close();
			// Informem al CONTROLADOR que informi que el servidor ha
			// estat aturat
			controller.showInformation("SERVER stopped.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void messageReceived(String message) {
		// Informem al controlador de la recepcio dun missatge,
		// ell actualitzara la vista.
		controller.showMessage(message);
		
	}
	
}
