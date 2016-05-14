package model;

import java.util.ArrayList;

public class Sessio {
	private ArrayList<Llistes> lPropies;
	private ArrayList<Integer> lFollowing;
	private ArrayList<sUser> lUserFollow;
	private int idSessio;
	private String desconexio;
	private boolean update;
	
	public Sessio(int id,ArrayList<Llistes> llistes,ArrayList<Integer> lFollowing){
		this.lFollowing = lFollowing;
		this.lPropies = llistes;
		this.idSessio = id;
		this.setUpdate(false);
	}
	
	public int getIdSessio() {
		return idSessio;
	}
	public void setIdSessio(int idSessio) {
		this.idSessio = idSessio;
	}

	public ArrayList<Llistes> getLl() {
		return lPropies;
	}
	public void setLl(ArrayList<Llistes> ll) {
		this.lPropies = ll;
	}
	public String getDesconexio() {
		return desconexio;
	}
	public void setDesconexio(String desconexio) {
		this.desconexio = desconexio;
	}

	public ArrayList<Integer> getlFollowing() {
		return lFollowing;
	}

	public void setlFollowing(ArrayList<Integer> lFollowing) {
		this.lFollowing = lFollowing;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public ArrayList<sUser> getlUserFollow() {
		return lUserFollow;
	}

	public void setlUserFollow(ArrayList<sUser> lUserFollow) {
		this.lUserFollow = lUserFollow;
	}
	
	
}
