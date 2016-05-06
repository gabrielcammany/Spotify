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
	public Query(){}
	
	public String queryList(int option,Object obj){
		
		switch(option){
		case 0:
			String cad = "USE db_espotifi;";
			return cad;
		case 1://inserir usuari
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			((User) obj).setData_reg(dateFormat.format(date));
			String cad1= "INSERT INTO usuari(nickname,password,data_reg,data_ult) values('"+((User) obj).getNickname()+"','"+((User) obj).getPassword()+"','"+((User) obj).getData_reg()+"','"+((User) obj).getData_reg()+"');";
			return cad1;
		case 2://Select d'un nickname especific
			String cad2 = "SELECT nickname FROM usuari WHERE nickname = '"+((User) obj).getNickname()+"';";
			
			//String cad2 = "Select * FROM db_espotifi.usuari AS user;";
			return cad2;
		case 3://Select d'un password especific
			String cad3 = "Select user.nickname FROM db_espotifi.usuari AS user WHERE user.nickname = '"+((User) obj).getNickname()+"' AND user.password = '"+((User) obj).getPassword()+"';";
			return cad3;
		case 4://enviar canco 
			String cad4= "SELECT * FROM canco;";
			return cad4;
		case 5://enviar usuari 
			String cad5= "SELECT * FROM usuari;";
			return cad5;
		case 6: //Actualizar numero de reproducciones
			String nom =((Canco) obj).getNom();
			String nRep= ((Canco) obj).getnReproduccio();
			String artista = ((Canco) obj).getArtista();
			String cad6 = "UPDATE canco SET num_reproduccio ='"+nRep+"' WHERE nom = '"+nom+"' AND artista= '"+artista+"';";
			
			System.out.println("#######" + cad6);
			return cad6;
		}
		
		return null;
		
		
	}

}