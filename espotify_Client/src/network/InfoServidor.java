package network;

import java.awt.List;
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
import java.util.Arrays;

import controller.ControladorFinestres;
import controller.ControladorLlistar;
import model.Canco;
import model.Llistes;
import model.User;
import model.sUser;

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

		private ControladorLlistar controladorLlistar;
		private FileInputStream fSongServ;
		private Socket sServidor ;
		private BufferedOutputStream bos;
		
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
	
	public void demanaUser(String nickname){
		
		try {
			System.out.println("[Client]Request user '"+nickname+"'.");
			newSocket();
			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
			doStream.writeUTF(User.getId_usuari()+":UserRequest:"+nickname);
			
			DataInputStream input3 = new DataInputStream(sServidor.getInputStream());
			int trobat = input3.readInt();
			doStream.close();
			if(trobat!= 0) ControladorFinestres.mostraPopUp(1,nickname);
			if(trobat ==0) ControladorFinestres.mostraPopUp(0,nickname);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void unfollow(String nickname){
		
		try {
			newSocket();
			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
			doStream.writeUTF(User.getId_usuari()+":unFollow:"+nickname);
			
			doStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void demanarLlistesFollowing(){
		try{
			try {
			newSocket();
			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
			doStream.writeUTF(User.getId_usuari()+":requestLlistesFollow:");
			
			ObjectInputStream objectInput = new ObjectInputStream(sServidor.getInputStream());
		
				ArrayList<Llistes> llFollowing = (ArrayList<Llistes>) objectInput.readObject();
				objectInput.close();
				doStream.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public boolean enviarUsuari(int option, String nom, char[] contrasenya){
		
		try {
			newSocket();
			String algo;
			System.out.println("[CLIENT] - Peticio de connexio...");
			System.out.println("NOM -> " + nom + " CONTRA: " +String.valueOf(contrasenya) + " OPCION: " + option);
			

			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
			int id = 0;
			//algo = algo(String.valueOf(contrasenya));
			algo = String.valueOf(contrasenya);
			switch(option) {
			case 1: doStream.writeUTF("user:" + nom + "/" + algo);
				DataInputStream input2 = new DataInputStream(sServidor.getInputStream());
				id = input2.readInt();
				break;
			case 2:
				doStream.writeUTF("userLog:" + nom + "/" + algo);
				DataInputStream input = new DataInputStream(sServidor.getInputStream());
				id = input.readInt();

				break;
			}
			System.out.println("ID --> " + id);
			if(id!=0){
				User.setId_usuari(id);
				User.setNickname(nom);
				ObjectInputStream o = new ObjectInputStream(sServidor.getInputStream());
				try {
					ArrayList<Llistes> all = (ArrayList<Llistes>)o.readObject();
					
					for(Llistes a:all)System.out.println("[llistes]--> "+a.getAllIdCanco().get(1));
					User.setlPropies(all);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
			return false;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void peticioMusica() throws ClassNotFoundException{
		System.out.println("[CLIENT] - Peticio de musica..."); 
		newSocket();
		try {
			
			ArrayList <Canco> alMusica = new ArrayList<Canco>();
			
			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
			System.out.println(User.getId_usuari()+":requestMusic");
			doStream.writeUTF(User.getId_usuari()+":requestMusic");
			ObjectInputStream objectInput = new ObjectInputStream(sServidor.getInputStream());
			alMusica = (ArrayList<Canco>) objectInput.readObject();
			
			
			ControladorFinestres.actualitzaMusicaDisponible(alMusica);

		} catch (IOException e) {
			System.out.println("exc1");
		}
		
	}
	
	public void peticioUsuaris() throws UnknownHostException, IOException, ClassNotFoundException {
		newSocket();
		ArrayList <User> alUsers = new ArrayList<User>();
		
		DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
		doStream.writeUTF(User.getId_usuari()+":requestUsuaris");
		//ObjectInputStream objectInput = new ObjectInputStream(sServidor.getInputStream());

		//alUsers = (ArrayList<User>) objectInput.readObject();
		//Object o = objectInput.readObject();
		//for(User c : alUsers)System.out.println(c.getNickname());

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
			doStream.writeUTF(User.getId_usuari()+":requestCanco:"+obj.replace(" ", ""));
			String[] s = obj.split("/");
			System.out.println("Path    ./espotify_Client/temp/" + s[0] + "_" + s[1] + ".mp3");
			InputStream llegada = sServidor.getInputStream();
			System.out.println(s[0] + " " + s[1] + " ES ESTE");
			File f = new File("./temp/" + s[0] + "_" + s[1] + ".mp3");

			FileOutputStream desti = new FileOutputStream(f);
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len=llegada.read(buffer))>=0){
				desti.write(buffer, 0, len);
			}
			desti.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		sServidor.close();
	}

	public void peticioFollow(String nickname){
		newSocket();
		int result = 0;
		
		try {
			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
			doStream.writeUTF(User.getId_usuari()+":requestFollow:"+nickname);
			
			System.out.println("estas seguin a '"+nickname+"'.");	
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//sServidor.close();
	}
	
	@SuppressWarnings("unchecked")
	public void peticioFollowers() throws UnknownHostException, IOException, ClassNotFoundException {
		newSocket();
		ArrayList <sUser> alUsers = new ArrayList<sUser>();
		
		DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
		
		doStream.writeUTF(User.getId_usuari()+":requestUsuarisFollower");
		
		ObjectInputStream objectInput = new ObjectInputStream(sServidor.getInputStream());
		alUsers = (ArrayList<sUser>) objectInput.readObject();
		doStream.close();

		
		ControladorFinestres.actualitzaUsuarisFollowing(alUsers);
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

