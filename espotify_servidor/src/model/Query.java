package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Clase query
 *
 */
public class Query {
	
	
	public Query(){}
	
	/**
	 * Funcio encarregada de generar el codi MySQL per enviar-ho a la Base de dades
	 *
	 * @param option Defineix el codi a enviar
	 * @param user Usuari del qual s'enviaran o consultaran les dades
	 * @return      Cadena de caracters amb el codi a enviar
	 */
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
			System.out.println("query --> " + cad7);
			return cad7;
		case 8: //Segons el id de la llista troba el nom de la llista
			int idLl =((int) obj);
			String cad8 = "SELECT nom_llista, privacitat FROM llista WHERE id_llista = "+idLl+";";
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
			
			String cad12 = ("DELETE FROM canco WHERE canco.id_canco = "+idCanco.toString()+";");
			return cad12;
		case 13://eliminamos una cancion de la musica disponible.
			String aux = ((String)obj);
			String[] s = aux.split("/");
			
			String cad13 = ("INSERT usuari_follower (id_user,id_follower) VALUES('"+s[0]+"','"+s[1]+"');");
			return cad13;
		case 14://eliminamos una cancion de la musica disponible.
			String cad14 = ("DELETE FROM usuari_follower WHERE usuari_follower.id_user = "+((String[])obj)[0]+" AND usuari_follower.id_follower = "+((String[])obj)[1]+";");
			return cad14;
		case 16://eliminamos una cancion de todas las listas en las que este
			
			int id_anco = ((int)obj);
			String cad16 = ("SELECT id_canco_llista FROM canco_llista WHERE id_canco = "+id_anco+";");
			return cad16;
		case 17://eliminamos una cancion de la musica disponible.
			int id_canco_llista = ((int)obj);
			String cad17 = ("DELETE FROM canco_llista WHERE canco_llista.id_canco_llista = "+id_canco_llista+";");
			return cad17;
			
		case 18://eliminamos una cancion de la musica disponible.
			int id_usuari = ((int)obj);
			String cad18 = ("DELETE FROM usuari WHERE usuari.id_usuaris = "+id_usuari+";");
			return cad18;
		case 19://eliminamos una cancion de la musica disponible.
			int id_usuari_llista = ((int)obj);
			String cad19 = ("SELECT id_usuari_llista FROM usuari_llista WHERE id_usuari = "+id_usuari_llista+";");
			return cad19;
		case 20://eliminamos una cancion de la musica disponible.
			int id_usuari_llista_delete = ((int)obj);
			System.out.println("---->" + id_usuari_llista_delete);
			String cad20 = ("DELETE FROM usuari_llista WHERE usuari_llista.id_usuari_llista = "+id_usuari_llista_delete+";");
			return cad20;
		case 21://eliminamos una cancion de la musica disponible.
			int id_usuari_foll = ((int)obj);
			System.out.println("id_usuari_foll---->" + id_usuari_foll);

			String cad21 = ("SELECT id_user_follower FROM usuari_follower WHERE id_user = "+id_usuari_foll+" OR id_follower = "+id_usuari_foll+";");
			return cad21;
		case 22://eliminamos una cancion de la musica disponible.
			int id_usuari_foll_delete = ((int)obj);
			System.out.println("id_usuari_foll_delete---->" + id_usuari_foll_delete);
			String cad22 = ("DELETE FROM usuari_follower WHERE usuari_follower.id_user_follower = "+id_usuari_foll_delete+";");
			return cad22;
		case 23://Inserimos la lista en la tabla lista
			String cad23 = ("INSERT INTO llista(nom_llista,privacitat)VALUES('"+((Llistes)obj).getNom_llista()+"',"+((Llistes)obj).getPrivacitat()+")");
			return cad23;
		case 24://Buscamos el id de la lista
			String cad24 = ("SELECT id_llista FROM llista WHERE nom_llista = '"+((Llistes)obj).getNom_llista()+"';");
			return cad24;
		case 25://Buscamos el id de la lista
			String id  = ((String)obj);
			String[] idCancons =id.split("/");
			String cad25 = ("INSERT INTO canco_llista(id_llista,id_canco)VALUES("+idCancons[0]+","+idCancons[1]+");");
			return cad25;
		case 26://Buscamos el id de las canco llista para poder eliminarlas
			Integer idLlista  = ((Integer)obj);
			String cad26 = ("SELECT id_canco_llista FROM canco_llista WHERE id_llista = "+idLlista+";");
			return cad26;
		case 27://Buscamos el id de la usari llista que dice que el usuario
			Integer idLlista2  = ((Integer)obj);
			String cad27 = ("SELECT id_canco_llista FROM canco_llista WHERE id_llista = "+idLlista2+";");
			return cad27;
		case 28://Inserimos la lista en la tabla lista
			String cad28 = ("INSERT INTO usuari_llista(id_llista,id_usuari)VALUES('"+((String[])obj)[0]+"',"+((String[])obj)[1]+")");
			return cad28;
		case 29: //Actualizar nEstrelles i nVotacio
			String nomCanco =((Canco) obj).getNom();
			String nVotacio= ((Canco) obj).getnVotacio();
			String nEstrelles = ((Canco) obj).getEstrelles();
			String cad29 = "UPDATE canco SET nVotacio ='"+nVotacio+"', num_estrelles = '" + nEstrelles +"' WHERE nom = '"+nomCanco+"';";
			return cad29;
			
		case 30: //Elimina canco de una llista publica d'un usuari
			String pre = ((String)obj);
			String[] ids = pre.split("/");
			String cad30 = "SELECT id_canco_llista FROM canco_llista WHERE id_llista =" + ids[1]+" AND id_canco = "+ids[0]+";";
			
			return cad30;
			
		case 31://eliminamos una cancion de la musica disponible.
			int id_canco_llista_delete = ((int)obj);
			String cad31 = ("DELETE FROM canco_llista WHERE canco_llista.id_canco_llista = "+id_canco_llista_delete+";");
			return cad31;
		case 32://Afegir una canco a una llista
			int cancoillista[] = ((int[])obj);
			String cad32 = ("INSERT INTO canco_llista(id_llista,id_canco)VALUES("+cancoillista[1]+","+cancoillista[0]+ ");");
			return cad32;
		case 33://Eliminar llista
			int idllista = ((int)obj);
			System.out.println("DELETE FROM llista WHERE llista.id_llista = " + idllista +";");
			String cad33 = ("DELETE FROM llista WHERE llista.id_llista = " + idllista +";");
			return cad33;
		case 34://Eliminar llista
			int idllistaD = ((int)obj);
			String cad34 = ("DELETE FROM usuari_llista WHERE usuari_llista.id_llista = "+idllistaD+";");
			return cad34;
			
		case 35:
			int idUsuariQL = ((int) obj);
			String cad35 = ("SELECT id_usuari FROM usuari_llista WHERE id_usuari = "+idUsuariQL+";");
			return cad35;
			
		case 36:
			int idUsuariQFollowers = ((int)obj);
			String cad36 = ("SELECT id_follower FROM usuari_follower WHERE id_follower = "+idUsuariQFollowers+";");
			return cad36;
			
		case 37:
			int idUsuariQFollowing = ((int)obj);
			String cad37 = ("SELECT id_user FROM usuari_follower WHERE id_user = " + idUsuariQFollowing+";");
			return cad37;
			
		case 38:
			int idUsuariQCanco = ((int)obj);
			String cad38 = ("SELECT id_canco FROM canco_llista, usuari_llista WHERE canco_llista.id_llista = usuari_llista.id_llista AND usuari_llista.id_usuari = "+idUsuariQCanco+";");		
			return cad38;
		case 39:
			String dades = ((String)obj);
			String[] data = dades.split("/");
			
			System.out.println("---> "+ data [0]+ "     " +data [1]);
			
			String cad39 = ("UPDATE `usuari` SET `data_ult` = '"+data[0]+"' WHERE `usuari`.`id_usuaris` = "+ data[1] + ";");
			return cad39;
		default:
			break;
			
		}
		
		return null;
	}
}