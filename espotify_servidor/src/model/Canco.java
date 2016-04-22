package model;

import java.util.ArrayList;

import controller.ButtonsController;

public class Canco {
	private ButtonsController controller;
	String nom;
	String genere;
	String album;
	String artista;
	String path;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	String estrelles;
	int reproduccions;
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public String getEstrelles() {
		return estrelles;
	}
	public void setEstrelles(String string) {
		this.estrelles = string;
	}
	public int getReproduccions() {
		return reproduccions;
	}
	public void setReproduccions(int reproduccions) {
		this.reproduccions = reproduccions;
	}
	
	public Canco(){}
	
	public ArrayList<Canco> consultaTotesCancons(){
		Query q =new Query();
		String select = q.queryList(4,null);
		controller = new ButtonsController();
		return controller.selectSongs(select);
	}

}
