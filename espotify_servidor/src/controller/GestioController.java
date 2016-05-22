package controller;

import java.sql.ResultSet;


import java.sql.SQLException;

import model.Query;
import network.ConectorDB;

/**
 * 
 * 
 * Clase que contavilitza els resultats de l'usuari (seguidors, cancons, etc)
 *
 */
public class GestioController {
	
	ConectorDB con;
	
	public GestioController (ConectorDB con) {
		this.con = con;
	}
	
	public int quantesLlistes(int idUsuari) {
		int quantes = 0;
		Query q = new Query();

		ResultSet result = con.selectQuery(q.queryList(35, idUsuari));
		try {
			
			quantes = result.last() ? result.getRow() : 0;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return quantes;
	}
	
	/**
	 * Funcio per comptar el numero de followers
	 * @param idUsuari
	 * @return num de followers de l'usuari
	 */
	public int quantsFollowers(int idUsuari) {
		int quants = 0;
		
		ResultSet result = con.selectQuery(new Query().queryList(36, idUsuari));
		
		try {
			quants = result.last() ? result.getRow() : 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quants;
	}
	
	/**
	 * Funcio per comptar a quants seguidors segueix
	 * @param idUsuari
	 * @return num de gent que segueix
	 */
	public int quantsFollowing (int idUsuari) {
		int quants = 0;
		
		ResultSet result = con.selectQuery(new Query().queryList(37, idUsuari));
		
		try {
			quants = result.last() ? result.getRow() : 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quants;
	}
	
	/**
	 * Funcio per comptar les cancons que te
	 * @param idUsuari
	 * @return numero de cancons que te
	 */
	public int quantesCancons (int idUsuari) {
		int quantes = 0;
		ResultSet result = con.selectQuery(new Query().queryList(38, idUsuari));
		
		try {
			quantes = result.last() ? result.getRow() : 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quantes;
	}
}
