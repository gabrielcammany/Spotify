package model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class Reproductor extends Thread{
	private static String path;
	private static String song;
	private boolean isPlaying;
	private boolean start;
	private BasicPlayer player;
	private static File file;
	private double volum;
	private long bytes = 0;
	private Thread t;
	private long temp = 0 ;
	private boolean comptar = true;
	
	public BasicPlayer getPlayer() {
		return player;
	}

	public void setPlayer(BasicPlayer player) {
		this.player = player;
	}

	public void play() {
		try {
			player.play();
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public Reproductor(String s) {
		Reproductor.path = s;
		Reproductor.song = "";
		setPlaying(false);
		setStart(false);
		setRepeat(false);
		
		try {
			player.play();
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Reproductor(){
		setPlaying(false);
		setStart(false);
		setRepeat(false);
	}
	
	@Override
	public void run()  {
		boolean first = true;

		setStart(false);
        setPlaying(false);
	}
	
	public void pause(){
		try {
			player.pause();
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPath() {
		return path;
	}

	public void restart(){
		try {
    		FileInputStream fis = new FileInputStream(Reproductor.path);
            fis.skip(bytes);
            setPlaying(true);
            setStart(true);
            player = new BasicPlayer();
            BufferedInputStream bufferedIn = new BufferedInputStream(fis);
			player.open(AudioSystem.getAudioInputStream(bufferedIn));
		} catch (BasicPlayerException | UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPath(String nom, String artista) {
		
		Reproductor.song = (nom + "_" + artista);

		Reproductor.path = "Musica/" + nom + "_" + artista + ".mp3";
		System.out.println(path);

		try {
    		FileInputStream fis;
            fis = new FileInputStream(Reproductor.path);
            fis.skip(bytes);
            setPlaying(true);
            setStart(true);
            player = new BasicPlayer();
            BufferedInputStream bufferedIn = new BufferedInputStream(fis);
			player.open(AudioSystem.getAudioInputStream(bufferedIn));
		} catch (BasicPlayerException | UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	

	public String getSong() {
		return Reproductor.song;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}


	public void setRepeat(boolean repeat) {

	}

	public File getFile() {
		return file;
	}


	public long getBytes() {
		return bytes;
	}

	public void setBytes(long bytes) {
		this.bytes = bytes;
	}

	public double getVolum() {
		return volum;
	}

	public void setVolum(double volum) {
		this.volum = volum;
	}

	public Thread getThread() {
		return t;
	}

	public void setThread(Thread t) {
		this.t = t;
	}

}