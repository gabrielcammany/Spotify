package network;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import controller.ControladorFinestres;
import controller.ControladorLlistar;
import model.Canco;

@SuppressWarnings("unused")
public class InfoServidor {
		public final static int FILE_SIZE = 6022386;
		public final static String FILE_TO_RECEIVED = "espotify_Client/Musica/";
	
		//private MessageService mService;
		private ServerSocket sServer;
		private Socket sClient;
		private DataInputStream diStream;
		private ObjectInputStream objectInput;
		
		private ArrayList<Canco> alMusica;
		private boolean active;
		private ControladorFinestres controladorFinestres;
		private ControladorLlistar controladorLlistar;
		private FileInputStream fSongServ;
		
		public InfoServidor(){}
		
		public InfoServidor(ServerSocket sServer) {
			this.sServer = sServer;
			active = true;
		}
		
		public void setControladorFinestra(ControladorFinestres controladorFinestres){
			this.controladorFinestres = controladorFinestres;
			System.out.println(controladorFinestres);
		}
	
	
	public void enviarUsuari(int option, String nom, char[] contrasenya){
		
		try {
			String algo;
			System.out.println("[CLIENT] - Peticio de connexio..."); 
			
			Socket sServidor = new Socket("localhost", 34567);

			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
			
			//algo = algo(String.valueOf(contrasenya));
			algo = String.valueOf(contrasenya);
			//COMENTARIO DE GABRI PARA EL FINALIZAR EL PASSWORD!!
			switch(option) {
			case 1: doStream.writeUTF("user:" + nom + "/" + algo);
				break;
			case 2:
				doStream.writeUTF("userLog:" + nom + "/" + algo);
				break;
			
			}
			System.out.println("Enviat");
			sServidor.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void peticioMusica() throws ClassNotFoundException{
		System.out.println("[CLIENT] - Peticio de connexio..."); 
		try {
			Socket sServidor = new Socket("localhost", 34567);
			
			ArrayList <Canco> alMusica = new ArrayList<Canco>();
			
			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
			doStream.writeUTF("requestMusic:");
			ObjectInputStream objectInput = new ObjectInputStream(sServidor.getInputStream());
			alMusica = (ArrayList<Canco>) objectInput.readObject();
			System.out.println(alMusica.get(0).getNom()); 
			/////
			
			if (controladorFinestres != null)  controladorFinestres.actualitzaMusicaDisponible(alMusica);

			
			sServidor.close();
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("exc1");
		}
		
	}
	
	/**
	 * Funcio encarregada de enviar al servidor les diferens peticions del client, cal observar que el segon parametre 
	 * es de la clase objecte ja que, segons la peticio, haurem de rebre diferents objectes.
	 * @param request
	 */
	
	public void peticio(String request, Object obj) {
		int bytesRead;
	    int current = 0;
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
		try {
			Socket sServidor = new Socket ("localhost", 34567);
			
			switch(request){
				case "requestCanco": 
						//Envia: requestCanco:canco/nomArtista
						DataOutputStream doStream  = new DataOutputStream(sServidor.getOutputStream());
						doStream.writeUTF("requestCanco:" + (String)obj);
						//ObjectInputStream objectInput = new ObjectInputStream(sServidor.getInputStream());
						/*try {
							fSongServ = (FileInputStream) objectInput.readObject();
							if(fSongServ == null)System.out.println("es nulo!");
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						// receive file
					      byte [] mybytearray  = new byte [FILE_SIZE];
					      InputStream is = sServidor.getInputStream();
					      String auxPath = obj.toString();
					      String[] concat=auxPath.split("/");
					      fos = new FileOutputStream(FILE_TO_RECEIVED.concat(concat[0])+"_".concat(concat[1]));
					      bos = new BufferedOutputStream(fos);
					      bytesRead = is.read(mybytearray,0,mybytearray.length);
					      current = bytesRead;

					      do {
					         bytesRead =
					            is.read(mybytearray, current, (mybytearray.length-current));
					         if(bytesRead >= 0) current += bytesRead;
					      } while(bytesRead > -1);

					      bos.write(mybytearray, 0 , current);
					      bos.flush();
					      System.out.println("File " + FILE_TO_RECEIVED
					          + " downloaded (" + current + " bytes read)");
						break;
			}
			sServidor.close();
		} catch (IOException e) {
			System.out.println("No s'ha pogut conectar amb el servidor");
		}
	}
	/*
	public String algo(String s){
		String keyString = "KA839KJsdDa4sdJSNsdjasid!@$@#$#@$#*&(*&}{234hjuk32432432dsfsdf";

        MessageDigest digest;
		try {
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        byte[] iv = new byte[cipher.getBlockSize()];
	        new SecureRandom().nextBytes(iv);
	        IvParameterSpec ivSpec = new IvParameterSpec(iv);
			digest = MessageDigest.getInstance("SHA-256");
	        digest.update(keyString.getBytes());
	        byte[] key = new byte[16];
	        System.arraycopy(digest.digest(), 0, key, 0, key.length);
	        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
	        byte[] encrypted = cipher.doFinal(s.getBytes("UTF-8"));
	        return new String(encrypted);
	        
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
		return null;

		
		
	}*/
	
}
