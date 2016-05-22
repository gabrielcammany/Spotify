package network;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.google.gson.JsonObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import controller.DeleteController;

public class ConectorDB {
    private static String userName;
    private static String password;
    private static String db;
    private static int port;
    private static String url;
    private static Connection conn = null;
    private static Statement s;
    private static int portSocket;
	
	public static int getPortSocket() {
		return portSocket;
	}
	public ConectorDB(){
		
	}
	public ConectorDB(JsonObject o) {
		
		ConectorDB.userName = o.get("user").getAsString();
		ConectorDB.password = o.get("pass").getAsString();
		ConectorDB.db = o.get("database").getAsString();
		ConectorDB.port = o.get("port").getAsInt();
		ConectorDB.url = o.get("url").getAsString();
		ConectorDB.url += ":"+ConectorDB.port+"/";
		ConectorDB.url += ConectorDB.db;
		ConectorDB.portSocket = o.get("portSocket").getAsInt();
	}

    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conn = (Connection) DriverManager.getConnection(url, userName, password);
            if (conn != null) {
                System.out.println("Conexio a base de dades "+url+" ... Ok");
            }
            return true;
        }catch(com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e) {
            JOptionPane.showMessageDialog(DeleteController.getFs(), "No tens connexio a internet", "Error", JOptionPane.ERROR_MESSAGE);
        }catch(SQLException e){
            System.out.println("Problema al connecta-nos a la BBDD --> "+url);
        	e.printStackTrace();
        }catch(ClassNotFoundException e){
        	
        }
        return false;
    }
    
    public static void insertQuery(String query){
        try {
        	System.out.println(query);
            s =(Statement) conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Problema al Inserir --> " + ex.getSQLState());
            //controller = new ButtonsController();
            //controller.incorrectUser();
            //JOptionPane.showMessageDialog(null,"Error al inserir usuari"); 
        }
        
    }
    
    public void updateQuery(String query){
    	 try {
             s =(Statement) conn.createStatement();
             s.executeUpdate(query);

         } catch (SQLException ex) {
             System.out.println("Problema al Modificar --> " + ex.getSQLState());
         }
    }
    
    public void deleteQuery(String query){
    	 try {
             s =(Statement) conn.createStatement();
             s.executeUpdate(query);
             
         } catch (SQLException ex) {
             System.out.println("Problema al Eliminar --> " + ex.getSQLState());
         }
    	
    }
    
    public ResultSet selectQuery(String query){
    	ResultSet rs = null;
    	 try {
             s =(Statement) conn.createStatement();
             rs = s.executeQuery (query);
             if (!rs.isBeforeFirst()){
            	 //System.out.println("[SERVER] Usuari no disponible.");
            	
             } 
             if(rs == null)return null;
         } catch (SQLException ex) {
             System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
         }
		return rs;
    }
    
    public void disconnect(){
    	try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Problema al tancar la connexi� --> " + e.getSQLState());
		}
    }

}
