package network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MessageServiceWorker implements Runnable{

	private MessageService mService;
	private ServerSocket sServer;
	private Socket sClient;
	private DataInputStream diStream;
	private boolean active;

	public MessageServiceWorker(MessageService mService, ServerSocket sServer) {
		this.mService = mService;
		this.sServer = sServer;
		active = true;
	}

	// Escolta peticions de connexio i llegeix els missatjges dels clients
	public void run() {
		while (active) {
			try {
				// Esperem peticions de connexio
				sClient = sServer.accept();
				// Atenem les connexions
				diStream = new DataInputStream(sClient.getInputStream());
				String newMessage = diStream.readUTF();
				// Informem a MessageService que sha rebut un nou missatge
				// ell informara al controlador i el controlador actualitzara la vista.
				mService.messageReceived("[" + getCurrentTime()+ "] " + newMessage);
				// Tanquem el socket del client
				sClient.close();
			} catch (IOException e) { }
		}
	}

	// Operacio privada per generar la data de recepcio dels missatges
	private String getCurrentTime() {
		return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
	}

	public void stopListening() {
		active = false;
	}

}
