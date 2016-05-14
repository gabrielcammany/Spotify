package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import controller.SocketController;
import model.Data;
import model.Llistes;
import model.Sessio;
import model.User;
import model.sUser;

public class MessageServiceWorker implements Runnable {

	private Socket sClient;
	private DataInputStream diStream;
	private boolean active;
	private SocketController cadenas;
	
	public MessageServiceWorker(){
	}
	
	public MessageServiceWorker(Socket sclient) {
		active = true;
		this.sClient = sclient;
		this.cadenas = new SocketController();
		
	}

	// Escolta peticions de connexio i llegeix els missatjges dels clients
	public void run() {

		String[] aux;
		String password;
		String[] data;
		String user;
		try {
			// Atenem les connexions
			diStream = new DataInputStream(this.sClient.getInputStream());

			String newMessage = diStream.readUTF();
			System.out.println(newMessage);
			aux = newMessage.split("/");
			data = aux[0].split(":");
			if (data[1].equals("requestCanco")){
				ObjectOutputStream objectOutput  = new ObjectOutputStream(this.sClient.getOutputStream());
				System.out.println("He recibido la peticion de cancion: "+ data[2] + " " + aux[1]);

				int response = cadenas.songRequest(data[1],aux[1]);
				if(response== -1){

					objectOutput.writeObject(null);

				}else{
					try{ 
						System.out.println("debug##10##");		
						PrintStream envio = new PrintStream(this.sClient.getOutputStream());
						FileInputStream fitxer = new FileInputStream("./Musica/" + data[2] + "_" + aux[1] + ".mp3");
						byte[] buffer = new byte[1024];
						int len = 0;
						while((len = fitxer.read(buffer))>=0){
							envio.write(buffer,0,len);
						}
						envio.flush();
						fitxer.close();

					}catch(IOException e){
						e.printStackTrace();
					}

				}
			}

			if (data[1].equals("UserRequest")) {

				System.out.println("request usuaris");
				int idTrobat= 0 ;
				for(User u : Data.getUsers())if(u.getNickname().toLowerCase().equals(data[2].toLowerCase()))idTrobat =u.getId_usuari();
				System.out.println("Hola tete "+idTrobat);
				DataOutputStream d = new DataOutputStream(this.sClient.getOutputStream());
				d.writeInt(idTrobat);
				//ObjectOutputStream objectOutput  = new ObjectOutputStream(sClient.getOutputStream());
				//objectOutput.writeObject(usuaris);
			}
			
			
			if (data[1].equals("requestFollow")) {
				int id = 0;
				for(User u : Data.getUsers())if(u.getNickname().toLowerCase().equals(data[2].toLowerCase()))id = u.getId_usuari();
				cadenas.hacerFollow(Integer.parseInt(data[0]),id);
				
			}
			

			if (data[1].equals("requestUsuarisFollower")) {

				ArrayList<sUser> usuaris = new ArrayList<sUser>();
				usuaris=cadenas.selectSUsers(Integer.parseInt(data[0]));
				ObjectOutputStream objectOutput  = new ObjectOutputStream(sClient.getOutputStream());
				System.out.println("[Server]id follow-->"+usuaris.get(0).getId_usuari()+" - "+usuaris.get(1).getId_usuari());
				objectOutput.writeObject(usuaris);
			}

			if(data[0].equals("user")){
				user = data[1];
				System.out.println(aux[1]);
				//password = desencripta(aux[1].getBytes());
				password = aux[1];
				DataOutputStream d = new DataOutputStream(this.sClient.getOutputStream());
				d.writeInt(cadenas.registroUsuario(user,password));


				// Tanquem el socket del client
				//sClient.close();
			}
			if(data[0].equals("userLog")){
				user = data[1];
				password = aux[1];
				System.out.println("----------------------------------------------------------");
				DataOutputStream d = new DataOutputStream(this.sClient.getOutputStream());
				int i = cadenas.verifyUser(user,password);
				d.writeInt(i);
				if(i != 0){
					Sessio s =new Sessio(i,cadenas.omplirLlistes(i),new ArrayList<Integer>());
					Data.addSessio(s);
					System.out.println(s.getLl().get(0).getNom_llista());
					ObjectOutputStream objectOutput  = new ObjectOutputStream(this.sClient.getOutputStream());
					List<Llistes> list = s.getLl();
					Llistes[] llistes = new Llistes[list.size()];
					llistes = list.toArray(llistes);
					
					for(Llistes l : s.getLl()) System.out.println(l.getAllIdCanco().get(0));
				
					objectOutput.writeObject(s.getLl());
					//Llista[] llista = 
					//objectOutput.writeObject("hello world");
					//objectOutput.writeObject(s.getLl());
				}
			}
			if(data[1].equals("requestMusic")){

				ObjectOutputStream objectOutput  = new ObjectOutputStream(sClient.getOutputStream());
				objectOutput.writeObject(Data.getAlMusica());

			}
		} catch (IOException e) { 
			e.printStackTrace();
		}

		try {
			sClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	// Operacio privada per generar la data de recepcio dels missatges
	public String getCurrentTime() {
		return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
	}

	public void stopListening() {
		active = false;
	}
	
/*	public void sendMusic(){
		
            Musica m =new Musica();
            ArrayList<Canco> al = new ArrayList<Canco>();
            al = m.getMusica();
            System.out.println();
            System.out.println("@@ hola tete 2 @@");
            try{
                ObjectOutputStream objectOutput = new ObjectOutputStream(sClient.getOutputStream());
               
                objectOutput.writeObject(al);               
            } 
            catch (IOException e){
                e.printStackTrace();
            }
	}*/
	
	//
	/*
	public String desencripta(byte[] s){
		String keyString = "KA839KJsdDa4sdJSNsdjasid!@$@#$#@$#*&(*&}{234hjuk32432432dsfsdf";
		String desencriptat = null;
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
	        
	        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
	        byte[] decrypted = cipher.doFinal(s);
	        desencriptat = new String(decrypted, "UTF-8");
	        System.out.println("decrypted: " + s);
	        
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
		return desencriptat;
	}*/

}
