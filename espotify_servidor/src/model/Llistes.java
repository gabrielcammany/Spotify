package model;

import java.util.ArrayList;

public class Llistes {
	private int id_llistes;
	private ArrayList<Integer> allIdCanco;
	public int getId_llistes() {
		return id_llistes;
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
	public Llistes(){}
}
