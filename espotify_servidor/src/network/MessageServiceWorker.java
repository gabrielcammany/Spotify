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

				int response = cadenas.songRequest(data[2],aux[1]);
				if(response== -1){

					objectOutput.writeObject(null);

				}else{
					try{ 		
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

			/* (data[1].equals("UserRequest")) {

				System.out.println("request usuaris");
				int idTrobat= 0 ;
				for(User u : Data.getUsers())if(u.getNickname().toLowerCase().equals(data[2].toLowerCase()))idTrobat =u.getId_usuari();
				System.out.println("Hola tete "+idTrobat);
				DataOutputStream d = new DataOutputStream(this.sClient.getOutputStream());
				d.writeInt(idTrobat);
				//ObjectOutputStream objectOutput  = new ObjectOutputStream(sClient.getOutputStream());
				//objectOutput.writeObject(usuaris);
			}*/
			
			if(data[1].equals("addCancoLlista")){
				
				if(cadenas.afegeixCanco(data[2], data[3], data[0])){
					DataOutputStream d = new DataOutputStream(this.sClient.getOutputStream());
					d.writeInt(1);
				}else{
					DataOutputStream d = new DataOutputStream(this.sClient.getOutputStream());
					d.writeInt(0);
				}
				
				
			}
			
			if (data[1].equals("creaLlista")) {
				int realitzat = 0;
				int i=0;
				for(Sessio u : Data.getaSessio()){
					if(u.getIdSessio() == Integer.parseInt(data[0])){
						for(Llistes l: u.getLPropies()){
							if(l.getNom_llista().equals(data[2])){
								break;
							}else{
								Llistes llista = new Llistes();
								llista.setNom_llista(data[2]);
								llista.setPrivacitat(Integer.parseInt(data[3]));
								llista.setId_llistes(0);
								realitzat = cadenas.crearLlistes(llista,data[0]);
								break;
							}
						}
						if(u.getLPropies().isEmpty()){
							Llistes llista = new Llistes();
							llista.setNom_llista(data[2]);
							llista.setPrivacitat(Integer.parseInt(data[3]));
							llista.setId_llistes(0);
							realitzat = cadenas.crearLlistes(llista,data[0]);
						}
						if(realitzat!=0)Data.getaSessio().get(i).setLPropies(cadenas.omplirLlistes(u.getIdSessio()));
						break;
					}
					i++;
					
				}
				
				DataOutputStream d = new DataOutputStream(this.sClient.getOutputStream());
				d.writeInt(realitzat);
				//ObjectOutputStream objectOutput  = new ObjectOutputStream(sClient.getOutputStream());
				//objectOutput.writeObject(usuaris);
			}
			
			if (data[1].equals("requestFollow")) {
				
				int id = 0;
				for(User u : Data.getUsers()){
					if(u.getNickname().toLowerCase().equals(data[2].toLowerCase())){
						id = u.getId_usuari();
						break;
					}
				}
				for(Sessio s:Data.getaSessio()){
					if(s.getIdSessio() == Integer.parseInt(data[0])){
						for(sUser u:s.getlUserFollow()){
							if(u.getNickname().toLowerCase().equals(data[2].toLowerCase())){
								id = -1;
								break;
							}
						}
						break;
					}
				}
				DataOutputStream d = new DataOutputStream(this.sClient.getOutputStream());
				System.out.println(id);
				if(id > 0){
					d.writeInt(cadenas.hacerFollow(Integer.parseInt(data[0]),id));
					for(Sessio s:Data.getaSessio()){
						if(s.getIdSessio() == Integer.parseInt(data[0])){
							sUser auxiliar = new sUser(data[2],id);
							s.getlUserFollow().add(auxiliar);
							s.setlFollowing(cadenas.omplirLlistesFollowing(s.getlUserFollow()));
							ObjectOutputStream objectOutput;
							try {
								objectOutput = new ObjectOutputStream(sClient.getOutputStream());
								objectOutput.writeObject(s.getlFollowing());
								objectOutput.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						}
					}
				}else{
					d.writeInt(id);
				}
				
				
			}
			
			if(data[1].equals("requestLlistesFollow")){
				for(Sessio s:Data.getaSessio()){
					if(Integer.parseInt(data[0]) == s.getIdSessio()){
						System.out.println("he entrat");
						s.setlFollowing(cadenas.omplirLlistesFollowing(s.getlUserFollow()));
						ObjectOutputStream objectOutput;
						try {
							objectOutput = new ObjectOutputStream(sClient.getOutputStream());
							objectOutput.writeObject(s.getlFollowing());
							objectOutput.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			
			if(data[1].equals("eliminaLlista")){
				cadenas.eliminaLlista(Integer.parseInt(data[2]),Integer.parseInt(data[0]));
			}

			if(data[1].equals("actualitzaMusicaLlista")){
				ArrayList<Integer> result =  cadenas.actualitzaMusicaLlista(Integer.parseInt(data[2]));
				ObjectOutputStream objectOutput  = new ObjectOutputStream(sClient.getOutputStream());
				objectOutput.writeObject(result);
			}
			
			if (data[1].equals("requestUsuarisFollower")) {
				ArrayList<sUser> usuaris = new ArrayList<sUser>();
				int i = 0;
				for(i = 0; i<Data.getaSessio().size(); i++){
					if(Integer.parseInt(data[0]) == Data.getaSessio().get(i).getIdSessio()){
						Data.getaSessio().get(i).setlUserFollow(cadenas.selectSUsers(Integer.parseInt(data[0])));
						ObjectOutputStream objectOutput  = new ObjectOutputStream(sClient.getOutputStream());
						objectOutput.writeObject(Data.getaSessio().get(i).getlUserFollow());
						//objectOutput.close();
						break;
					}
				}
				
				
			}

			if(data[0].equals("user")){
				user = data[1];
				//password = desencripta(aux[1].getBytes());
				password = aux[1];
				DataOutputStream d = new DataOutputStream(this.sClient.getOutputStream());
				//d.writeInt(cadenas.registroUsuario(user,password));
				int i = cadenas.registroUsuario(user,password);
				d.writeInt(i);
				Sessio s =new Sessio(i,cadenas.omplirLlistes(i));
				Data.addSessio(s);
				
				d.close();

				// Tanquem el socket del client
				//sClient.close();
			}
			if(data[0].equals("userLog")){
				user = data[1];
				password = aux[1];
				DataOutputStream d = new DataOutputStream(this.sClient.getOutputStream());
				int i = cadenas.verifyUser(user,password);
				d.writeInt(i);
				if(i > 0){
					
					Sessio s =new Sessio(i,cadenas.omplirLlistes(i));

					s.setlUserFollow(cadenas.selectSUsers(i));
					s.setlFollowing(cadenas.omplirLlistesFollowing(s.getlUserFollow()));
					Data.addSessio(s);
					//System.out.println(s.getLl().get(0).getNom_llista());
					ObjectOutputStream objectOutput  = new ObjectOutputStream(this.sClient.getOutputStream());
					List<Llistes> list = s.getLPropies();
					Llistes[] llistes = new Llistes[list.size()];
					llistes = list.toArray(llistes);
					
					objectOutput.writeObject(s.getLPropies());
					//Llista[] llista = 
					//objectOutput.writeObject("hello world");
					//objectOutput.writeObject(s.getLl());
				}
			}
			if(data[1].equals("requestMusic")){

				ObjectOutputStream objectOutput  = new ObjectOutputStream(sClient.getOutputStream());
				objectOutput.writeObject(Data.getAlMusica());

			}
			if(data[1].equals("unFollow")){
				cadenas.unfollow(data[0],data[2]);
				for(Sessio s:Data.getaSessio()){
					if(Integer.parseInt(data[0]) == s.getIdSessio()){
						Thread t = new Thread(){
							public void run(){
								s.setlFollowing(cadenas.omplirLlistesFollowing(s.getlUserFollow()));
							}
						};
						t.start();
					}
				}

			}
			if(data[1].equals("requestNomLlistesFollow")){
				
			}
			if(data[1].equals("deleteSessio")){
				for(Sessio s : Data.getaSessio()){
					if(Integer.parseInt(data[0]) == s.getIdSessio())Data.getaSessio().remove(s);
					break;
				}
				System.out.println("[Servidor] Sessio amb id '"+data[0]+"' ha set eliminada.");
			}
			
			if(data[1].equals("votaCanco")) {
				cadenas.votaCanco(data[2], data[3]);
			}
			
			if(data[1].equals("eliminaCancoLlista")) {
				cadenas.eliminaCancoLlista(data[0], data[2], data[3]);
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
