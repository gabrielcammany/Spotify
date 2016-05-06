package model;

import java.util.ArrayList;

public class Llistes {
	private int id_llistes;
	private ArrayList<Canco> allMusic;
	public int getId_llistes() {
		return id_llistes;
	}
	public void setId_llistes(int id_llistes) {
		this.id_llistes = id_llistes;
	}
	public ArrayList<Canco> getAllMusic() {
		return allMusic;
	}
	public void setAllMusic(ArrayList<Canco> allMusic) {
		this.allMusic = allMusic;
	}
	public Llistes(){}
}
