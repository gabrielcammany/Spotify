package model;

import java.util.ArrayList;

public class Llistes {
	private static int id_llistes;
	private	static String nom_llista;
	private static ArrayList<Integer> allIdCanco;
	
	public int getId_llistes() {
		return id_llistes;
	}
	public String getNom_llista() {
		return nom_llista;
	}
	public void setNom_llista(String nom_llista) {
		Llistes.nom_llista = nom_llista;
	}
	public void setId_llistes(int id_llistes) {
		Llistes.id_llistes = id_llistes;
	}
	public ArrayList<Integer> getAllIdCanco() {
		return allIdCanco;
	}
	public void setAllIdCanco(ArrayList<Integer> allIdCanco) {
		Llistes.allIdCanco = allIdCanco;
	}
	public Llistes(){
		if(allIdCanco==null)allIdCanco=new ArrayList<Integer>();
	}
}
