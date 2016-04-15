package view;

import javafx.scene.paint.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class FinestraReproduccio {
	
	private JFrame jfReproduccio;
	
	public FinestraReproduccio(){
		
		jfReproduccio = new JFrame();
		jfReproduccio.setVisible(true);
		jfReproduccio.setSize(800, 700);
		jfReproduccio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jpReproduccio = new JPanel();
		//centrar a la pantalla
		jpReproduccio.setLayout(new MigLayout("al center center, wrap, gapy 10"));
		//color negre del fons		
		jfReproduccio.add(jpReproduccio);
		
		
		
	}
	
}
