package network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class InfoServidor {
	
	/**
	 * 
	 *
	 * @param  url  an absolute URL giving the base location of the image
	 * @param  name the location of the image, relative to the url argument
	 * @return      the image at the specified URL
	 * @see         Image
	 */
	public void extreureDades(){
		try {
			System.out.println("[CLIENT] - Petici— de connexi—..."); 
			
			Socket sServidor = new Socket("localhost", 34567);

			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());

			
			doStream.writeUTF("user:gabriel/gabriel");;
			
			System.out.println("Enviat");
			sServidor.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
