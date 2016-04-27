package model;

import java.io.Serializable;
import java.util.ArrayList;


public class Canco implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String genere;
	private String album;
	private String artista;
	private String path;
	
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

}
