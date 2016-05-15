package model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
	private boolean start;
	private BasicPlayer player;
	
	public Reproductor(String s) {
		this.path = s;
		this.song = "";
		setPlaying(false);
		setStart(false);
		
	}
	
	public Reproductor(){
		this.path = "";
		this.song = "";
		setPlaying(false);
		setStart(false);
	}
	
	@Override
	public void run()  {
		
		try {
            FileInputStream fis;
            
            fis = new FileInputStream(this.path);
            setPlaying(true);
            setStart(true);
            player = new BasicPlayer();
            
            InputStream bufferedIn = new BufferedInputStream(fis);
            player.open(AudioSystem.getAudioInputStream(bufferedIn));
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
		
		
		//nom.replaceAll("\\s", "");
		//artista.replaceAll("\\s", "");
		this.song = (nom + "_" + artista);
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
		setStart(false);
		//String name = this.song.replaceAll(" ", "");
		File f = new File("./temp/" + this.song + ".mp3");
		System.out.println("PATH: " + this.song);
		try {
			
			player.stop();
			f.delete();
			stop();
			
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getSong() {
		return this.song;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}
}

