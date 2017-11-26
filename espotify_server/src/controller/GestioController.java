package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import network.ConectorDB;
import java.sql.DriverManager;

import model.Query;
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
