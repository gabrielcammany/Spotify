package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Query {
	
	/**
	 * Funcio encarregada de generar el codi MySQL per enviar-ho a la Base de dades
	 *
	 * @param option Defineix el codi a enviar
	 * @param user Usuari del qual s'enviaran o consultaran les dades
	 * @return      Cadena de caracters amb el codi a enviar
	 */
	String queryList(int option,User user){
		switch(option){
		case 0:
			String cad = "USE db_espotifi;";
			return cad;
		case 1://inserir usuari
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			user.setData_reg(dateFormat.format(date));
			String cad1= "INSERT INTO usuari(nickname,password,data_reg,data_ult) values('"+user.getNickname()+"','"+user.getPassword()+"','"+user.getData_reg()+"','"+user.getData_reg()+"');";
			return cad1;
		case 2://
			return null;
		
		}
		
		return null;
		
		
	}

}
