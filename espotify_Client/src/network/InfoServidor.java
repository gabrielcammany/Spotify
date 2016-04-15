package network;

import java.awt.Image;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class InfoServidor {
	
	/**
	 * 
	 *
	 * @param  url  an absolute URL giving the base location of the image
	 * @param  name the location of the image, relative to the url argument
	 * @return      the image at the specified URL
	 * @see         Image
	 */
	public void enviarUsuari(int option, String nom, char[] contrasenya){
		try {
			System.out.println("[CLIENT] - Petici� de connexi�..."); 
			
			Socket sServidor = new Socket("localhost", 34567);

			DataOutputStream doStream = new DataOutputStream(sServidor.getOutputStream());
			
			algo(String.valueOf(contrasenya));
			//COMENTARIO DE GABRI PARA EL FINALIZAR EL PASSWORD!!
			switch(option) {
			case 1: doStream.writeUTF("user:" + nom + "/" + String.valueOf(contrasenya));
				break;
			case 2: doStream.writeUTF("userLog:" + nom + "/" + String.valueOf(contrasenya));
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
	
	public void algo(String s){
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
	        System.out.println("encrypted: " + new String(encrypted));
	        
	        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
	        byte[] decrypted = cipher.doFinal(encrypted);
	        System.out.println("decrypted: " + new String(decrypted, "UTF-8"));
	        
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


		
		
	}
	
	
	
}
