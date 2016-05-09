package network;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import controller.SocketController;
import model.Canco;
import model.Musica;
import model.User;
import model.Usuaris;

public class MessageServiceWorker implements Runnable {

	private Socket sClient;
	private DataInputStream diStream;
	private boolean active;
	private SocketController cadenas;
	private ArrayList<Canco> alcanco;
	private Musica musica;
	
	public MessageServiceWorker(){
	}
	
	public MessageServiceWorker(Socket sclient,Musica musica) {
		active = true;
		cadenas = new SocketController();
		this.musica = musica;
		this.sClient = sclient;
		
	}

	// Escolta peticions de connexio i llegeix els missatjges dels clients
	public void run() {
	    
		String[] aux;
		String password;
		String[] data;
		String user;
		while (active) {
			try {
				// Atenem les connexions
				diStream = new DataInputStream(this.sClient.getInputStream());

				String newMessage = diStream.readUTF();
				System.out.println(newMessage);
				aux = newMessage.split("/");
				data = aux[0].split(":");
				
				if (data[0].equals("requestCanco")){
					ObjectOutputStream objectOutput  = new ObjectOutputStream(this.sClient.getOutputStream());
					System.out.println("He recibido la peticion de cancion: "+ data[1] + " " + aux[1]);

					int response = cadenas.songRequest(data[1],aux[1]);
					if(response== -1){

						objectOutput.writeObject(null);

					}else{
						try{ 
							System.out.println("debug##10##");
							alcanco = musica.getMusica();						
							String path = alcanco.get(response).getPath();
							System.out.println("####");
							PrintStream envio = new PrintStream(this.sClient.getOutputStream());
							FileInputStream fitxer = new FileInputStream("./Musica/" + data[1] + "_" + aux[1] + ".mp3");
							byte[] buffer = new byte[1024];
							int len = 0;
							while((len = fitxer.read(buffer))>0){
								envio.write(buffer,0,len);
							}
							envio.flush();
							sClient.close();	
						}catch(IOException e){
							e.printStackTrace();
						}
						
					}
				}
				
				if (data[0].equals("requestUsuaris")) {
					ArrayList<User> usuaris = new ArrayList<User>();
					usuaris = cadenas.selectUsers(true,null);
					ObjectOutputStream objectOutput  = new ObjectOutputStream(sClient.getOutputStream());
					objectOutput.writeObject(usuaris);
				}
				
				if (data[0].equals("requestUsuarisFollower")) {
					ArrayList<User> usuaris = new ArrayList<User>();
					usuaris = cadenas.selectUsers(false,data[1]);
					ObjectOutputStream objectOutput  = new ObjectOutputStream(sClient.getOutputStream());
					objectOutput.writeObject(usuaris);
				}
				
				
				if(data[0].equals("user")){
					user = data[1];
					System.out.println(aux[1]);
					//password = desencripta(aux[1].getBytes());
					password = aux[1];

					cadenas.registroUsuario(user,password);
					//Tornem a generar la llista d'usuaris
					Usuaris allUsers = new Usuaris();
					
					// Tanquem el socket del client
					//sClient.close();
				}else{
					if(data[0].equals("userLog")){
						user = data[1];
						
						password = aux[1];
						
						cadenas.loginUser(user,password);
						
						// Tanquem el socket del client
						//sClient.close();
					}else{
						if(data[0].equals("requestMusic")){
							alcanco = new ArrayList<Canco>();
							alcanco = musica.getMusica();
							
							ObjectOutputStream objectOutput  = new ObjectOutputStream(sClient.getOutputStream());
							objectOutput.writeObject(alcanco);
							
						}
				
					}
				}
				
			} catch (IOException e) { 
				e.printStackTrace();
			}
		}
	}

	// Operacio privada per generar la data de recepcio dels missatges
	private String getCurrentTime() {
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
