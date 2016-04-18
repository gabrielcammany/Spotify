package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorLog {
	
	public ErrorLog(){}
	
	public void errorInsertUser(){
		JFrame frame= new JFrame();
		JOptionPane.showMessageDialog(frame,
			    "Usuari no disponible.",
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}

}
