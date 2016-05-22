package model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * 
 * Clase llista
 *
 */
public class Llistes implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id_llistes;
	private String nom_llista;
	private int privacitat;
	private ArrayList<Integer> allIdCanco;
	
	public int getId_llistes() {
		return id_llistes;
	}
	public String getNom_llista() {
		return nom_llista;
	}
	public void setNom_llista(String nom_llista) {
		this.nom_llista = nom_llista;
	}
	public void setId_llistes(int id_llistes) {
		this.id_llistes = id_llistes;
	}
	public ArrayList<Integer> getAllIdCanco() {
		return allIdCanco;
	}
	public void setAllIdCanco(ArrayList<Integer> allIdCanco) {
		this.allIdCanco = allIdCanco;
	}
	public Llistes(){
		if(allIdCanco==null)allIdCanco=new ArrayList<Integer>();
	}
	public int getPrivacitat() {
		return privacitat;
	}
	public void setPrivacitat(int privacitat) {
		this.privacitat = privacitat;
	}
}
