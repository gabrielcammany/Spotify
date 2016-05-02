package network;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		public final static String FILE_TO_RECEIVED = "./Musica/7Year.mp3";
	
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
		BufferedOutputStream bos;
		
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
	
	public void peticio(String request, String obj) throws IOException {
		Socket sServidor = new Socket ("localhost", 34567);
		DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
		doStream.writeUTF("requestCanco:"+obj.replace(" ", ""));
		sServidor.close();
		String[] s = obj.split("/");
		Socket sServidor2 = new Socket ("localhost", 34567);
		switch(request){
		case "requestCanco":   
			byte[] mybytearray = new byte[600000];
			InputStream is = sServidor2.getInputStream();
			FileOutputStream fos = new FileOutputStream(s[0] + "_" + s[1] + ".mp3");
			bos = new BufferedOutputStream(fos);int bytesRead;
			System.out.println("askudhahjsdjhuiashd");
				while((bytesRead = is.read(mybytearray, 0, mybytearray.length)) > -1){
					
					bos.write(mybytearray, 0, bytesRead);
				}
			bos.close();
			sServidor2.close();

		}

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

		
		
	*/

