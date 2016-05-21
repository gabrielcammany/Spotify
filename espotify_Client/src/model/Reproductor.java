package model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.sun.media.sound.JavaSoundAudioClip;

import controller.ControladorFinestres;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
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

	private boolean repeat;
	public boolean fi;
	
	public Reproductor(String s) {
		Reproductor.path = s;
		Reproductor.song = "";
		setPlaying(false);
		setStart(false);
		setRepeat(false);
	}
	
	public Reproductor(){
		setPlaying(false);
		setStart(false);
		setRepeat(false);
	}
	
	@Override
	public void run()  {
		boolean first = true;
			//if (isRepeat()) {
				while (isRepeat() || first) {
					first = false;
					fi = false;
		            while (!fi) {
		            	//System.out.println("hola");
		            	try {
		            		
		            		file = new File("./temp/" + Reproductor.song + ".mp3");
		            		//player.setGain(50);
		            		start = true;
		            		player.play();
		            	}
		            	catch (BasicPlayerException e){
		            		fi = true;
		            	}
		            	  
		            	try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		            }
				}
			//}
			/*else {
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
			}*/
		setStart(false);
        setPlaying(false);
        File f = new File("./temp/" + Reproductor.song + ".mp3");
        f.delete();
    	System.out.println("Final de canço");
	}
	
	public void pause(){
		try {
			if (isPlaying()) {
				System.out.println("He pausaaaaaaaaaaaaaaaaaaaaaaat");
				comptar = false;
				player.pause();
				setPlaying(false);
			}
			else {
				setPlaying(true);
				comptar = true;
				getThread().start();
				player.resume();
			}
			
			
		} catch (BasicPlayerException  e) {
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
		
		
		//nom.replaceAll("\\s", "");
		//artista.replaceAll("\\s", "");
		Reproductor.song = (nom + "_" + artista);
		//System.out.println(nom.replaceAll("\\s", "") + "_" + artista.replaceAll("\\s", ""));
		Reproductor.path = "temp/" + nom + "_" + artista + ".mp3";
		//System.out.println(this.path);
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
	
	public void endSong2(){
		setPlaying(false);
		setStart(false);
		try {
			player.stop();
			stop();
		} catch (BasicPlayerException e) {
			System.out.println("##############################");// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void endSong() {
		setPlaying(false);
		setStart(false);
		//String name = this.song.replaceAll(" ", "");
		File f = new File("./temp/" + Reproductor.song + ".mp3");
		System.out.println("PATH: " + Reproductor.song);
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
		return Reproductor.song;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		System.out.println(repeat);
		this.repeat = repeat;
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

