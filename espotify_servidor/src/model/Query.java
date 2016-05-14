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
			return cad6;
		case 7: //Demanar llistes del usuari seleccionat
			int idUser =((int) obj);
			String cad7 = "SELECT id_llista FROM usuari_llista WHERE id_usuari = "+idUser+";";
			return cad7;
		case 8: //Segons el id de la llista troba el nom de la llista
			int idLl =((int) obj);
			String cad8 = "SELECT nom_llista FROM llista WHERE id_llista = "+idLl+";";
			return cad8;	
		case 9: //Demanar les cancons de la llista
			int idLl2 =((int) obj);
			String cad9 = "SELECT id_canco FROM canco_llista WHERE id_llista = "+idLl2+";";
			return cad9;
		case 10:
			String infoSong= ((String) obj);
			String cad10 = "INSERT INTO canco(nom,album,artista,genere,ubicacio,nVotacio,num_estrelles,num_reproduccio) VALUES("+infoSong+");";
			return cad10;
		case 11: //Demanar les cancons de la llista
			int idUserF =((int) obj);
			String cad11 = "SELECT * FROM usuari_follower WHERE id_user = "+idUserF+";";
			return cad11;
		case 12://eliminamos una cancion de la musica disponible.
			Integer idCanco = ((Integer)obj);
			
			String cad12 = ("DELETE FROM canco WHERE canco.id_canco = "+idCanco.toString());
			return cad12;
		case 13://eliminamos una cancion de la musica disponible.
			String aux = ((String)obj);
			String[] s = aux.split("/");
			
			String cad13 = ("INSERT usuari_follower (id_user,id_follower) VALUES('"+s[0]+"','"+s[1]+"');");
			return cad13;
		case 14://eliminamos una cancion de la musica disponible.
			String aux3 = ((String)obj);
			
			String cad14 = ("DELETE FROM usuari_follower WHERE usuari_follower.id_usuari_follower = "+aux3);
			return cad14;
		case 15://eliminamos una cancion de la musica disponible.
			String aux2 = ((String)obj);
			String[] s2 = aux2.split("/");
			String cad15 = ("SELECT id_usuari_follower FROM usuari_follower WHERE usuari_follower.id_user = "+s2[0]+" AND usuari_follower.id_follower = "+s2[1]);
			return cad15;
		}
			
			
		
		return null;
		
		
	}

}