package model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;



public class Reproductor extends Thread{
	
	private String path;
	BasicPlayer player;
	
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
            
            player = new BasicPlayer();
            
            player.open(AudioSystem.getAudioInputStream(fis));
            while (true) {
            	System.out.println("hola");
            	player.play();  
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        } catch (BasicPlayerException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
	}
	
	public void pause(){
		try {
			player.pause();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String nom, String artista) {
		System.out.println(nom + "_" + artista);
		this.path = "temp/" + nom.replace(" ", "") + "_" + artista.replace(" ", "") + ".mp3";
	}
}

