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
	private String song;
	private boolean isPlaying;
	BasicPlayer player;
	
	public Reproductor(String s) {
		this.path = s;
		this.song = "";
		setPlaying(false);
	}
	
	public Reproductor(){
		this.path = "";
		this.song = "";
		setPlaying(false);
	}
	
	@Override
	public void run()  {
		
		try {
            FileInputStream fis;
            
            fis = new FileInputStream(this.path);
            setPlaying(true);
            player = new BasicPlayer();
            
            player.open(AudioSystem.getAudioInputStream(fis));
            while (true) {
            	//System.out.println("hola");
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
			if (isPlaying()) {
				player.pause();
				setPlaying(false);
			}
			else {
				setPlaying(true);
				player.resume();
			}
			
			
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String nom, String artista) {
		
		this.song = (nom + "_" + artista);
		nom.replaceAll("\\s", "");
		artista.replaceAll("\\s", "");
		//System.out.println(nom.replaceAll("\\s", "") + "_" + artista.replaceAll("\\s", ""));
		this.path = "temp/" + nom + "_" + artista + ".mp3";
		//System.out.println(this.path);
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	
	public void endSong() {
		setPlaying(false);
		try {
			player.stop();
			stop();
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getSong() {
		return this.song;
	}
}

