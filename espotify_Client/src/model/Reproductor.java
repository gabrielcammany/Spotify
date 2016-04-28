package model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;



public class Reproductor extends Thread{
	
	private String path;
	Player player;
	
	public Reproductor(String s) {
		this.path = s;
	}
	
	public Reproductor(){
		this.path = "";
	}
	
	@Override
	public void run()  {
		
		try {
            FileInputStream fis;
            
            fis = new FileInputStream(this.path);
            BufferedInputStream bis = new BufferedInputStream(fis);

            player = new Player(bis); 
            
            while (true) {
            	System.out.println("hola");
            	player.play();  
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	public void pause(){
		try {
			player.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String nom, String artista) {
		System.out.println(nom + "_" + artista);
		this.path = "C:/Users/gabriel53/git/eSpotyfai/espotify_servidor/Musica/" + nom + "_" + artista.replace(" ", "") + ".mp3";
	}
}

