package network;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import controller.ControladorFinestres;
import controller.ControladorLlistar;
import model.Canco;
import model.User;

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
		private Socket sServidor ;
		BufferedOutputStream bos;
		
		public InfoServidor(){}
		
		public void newSocket(){
			try {
				sServidor = new Socket("localhost", 34567);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public InfoServidor(ServerSocket sServer) {
			this.sServer = sServer;
			active = true;
		}
		
		public void setControladorFinestra(ControladorFinestres controladorFinestres){
			this.controladorFinestres = controladorFinestres;
			System.out.println(controladorFinestres);
		}
		
	public void demanaSessio(){
		
		try {
			newSocket();
			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
			doStream.writeUTF("Sessio:");
			System.out.println("Envio sesion");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public boolean enviarUsuari(int option, String nom, char[] contrasenya){
		
		try {
			newSocket();
			String algo;
			System.out.println("[CLIENT] - Peticio de connexio..."); 
			

			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
			
			//algo = algo(String.valueOf(contrasenya));
			algo = String.valueOf(contrasenya);
			//COMENTARIO DE GABRI PARA EL FINALIZAR EL PASSWORD!!
			switch(option) {
			case 1: doStream.writeUTF("user:" + nom + "/" + algo);
				User user = new User(nom,algo);
				this.controladorFinestres.setUser(user);
				
				break;
			case 2:
				doStream.writeUTF("userLog:" + nom + "/" + algo);
				DataInputStream input = new DataInputStream(sServidor.getInputStream());
				int i = input.readInt();
				if(i == 1)return true;
				if (i == 0) return false;
				break;
			
			}
			System.out.println("Enviat");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*try {
			sServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void peticioMusica() throws ClassNotFoundException{
		System.out.println("[CLIENT] - Peticio de musica..."); 
		newSocket();
		try {
			
			ArrayList <Canco> alMusica = new ArrayList<Canco>();
			
			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
			doStream.writeUTF("requestMusic:");
			ObjectInputStream objectInput = new ObjectInputStream(sServidor.getInputStream());
			alMusica = (ArrayList<Canco>) objectInput.readObject();
			
			
			if (controladorFinestres != null)  controladorFinestres.actualitzaMusicaDisponible(alMusica);

		} catch (IOException e) {
			System.out.println("exc1");
		}
		
	}
	
	public void peticioUsuaris() throws UnknownHostException, IOException, ClassNotFoundException {
		newSocket();
		ArrayList <User> alUsers = new ArrayList<User>();
		
		DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
		doStream.writeUTF("requestUsuaris:");
		ObjectInputStream objectInput = new ObjectInputStream(sServidor.getInputStream());
		alUsers = (ArrayList<User>) objectInput.readObject();

		//controladorFinestres.actualitzaUsuarisFollowing(alUsers);
		//sServer.close();
	}
	
	/**
	 * Funcio encarregada de enviar al servidor les diferens peticions del client, cal observar que el segon parametre 
	 * es de la clase objecte ja que, segons la peticio, haurem de rebre diferents objectes.
	 * @param request
	 */
	
	public void peticio(String request, String obj) throws IOException {
		try {
			
			newSocket();
			
			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
			doStream.writeUTF("requestCanco:"+obj.replace(" ", ""));
			String[] s = obj.split("/");
			switch(request){
			case "requestCanco":   
				System.out.println("Path    ./espotify_Client/temp/" + s[0] + "_" + s[1] + ".mp3");
				InputStream llegada = sServidor.getInputStream();
				System.out.println(s[0] + " " + s[1] + " ES ESTE");
				File f = new File("./temp/" + s[0] + "_" + s[1] + ".mp3");
				
				FileOutputStream desti = new FileOutputStream(f);
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len=llegada.read(buffer))>=0){
					System.out.println("len-> "+ len);
					desti.write(buffer, 0, len);

				}
				//desti.write(buffer, 0, len);
				desti.close();
				
				
				
				System.out.println("fi while");
			}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("->exception");
			}
		sServidor.close();
		}

	
	
	public void peticioFollowers() throws UnknownHostException, IOException, ClassNotFoundException {
		newSocket();
		ArrayList <User> alUsers = new ArrayList<User>();
		
		DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
		doStream.writeUTF("requestUsuarisFollower:"+this.controladorFinestres.getUser().getNickname()+"");
		ObjectInputStream objectInput = new ObjectInputStream(sServidor.getInputStream());
		alUsers = (ArrayList<User>) objectInput.readObject();
		
		controladorFinestres.actualitzaUsuarisFollowing(alUsers);
		//sServidor.close();
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

