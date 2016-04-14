package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Estadistica extends JPanel {
	private ArrayList <String> valors;
	private ArrayList <String> nomCancons;
	
	public Estadistica(ArrayList <String> valors, ArrayList <String> nomCancons){
		this.setBackground(Color.BLUE);
		this.valors = valors;
		this.nomCancons = nomCancons;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		int mesGran;
		int R = 255;
		int G = 20;
		int B = 0;
		int xBarra = 10;
		int xText = 25;
		
		
		
		mesGran = Integer.parseInt(valors.get(0));
		
		
		for (int i = 0; i < 10 ; i ++) {
			g.setColor (new Color (R, G, B));
			g.fillRect(150, xBarra, (Integer.parseInt(valors.get(i)) * 600 / mesGran), 28);
			g.drawString(nomCancons.get(i) + " (" + valors.get(i) + ")", 10, xText);
			
			R-=20;
			G+=20;
			B+=20;
			xBarra += 38;
			xText += 38;
			
			
		}
		
	}

}
