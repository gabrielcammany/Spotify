package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import model.Canco;
import model.Musica;

@SuppressWarnings("serial")
public class Estadistica extends JPanel  {
	private ArrayList <String> valors;
	private ArrayList <String> nomCancons;
	private Musica musica;
	
	/*public Estadistica(ArrayList <String> valors, ArrayList <String> nomCancons){
		this.setBackground(Color.BLUE);
		this.valors = valors;
		this.nomCancons = nomCancons;
	}*/
	public Estadistica(Musica musica) {
		this.musica = musica;
	}
	
	/*public void paintComponent(Graphics g) {
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
	}*/
	
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
	
		
		ArrayList <Canco> cancons;
		int mesGran;
		int R = 255;
		int G = 20;
		int B = 0;
		int xBarra = 10;
		int xText = 25;
		int i = 0;
		
		cancons = new ArrayList<Canco> (musica.getMusica());
		Collections.sort(cancons);
		
		mesGran = Integer.parseInt(cancons.get(0).getnReproduccio());
		
		while (i < 10 && i < cancons.size()) {
			g.setColor(new Color (R, G, B));
			g.fillRect(200, xBarra, (Integer.parseInt(cancons.get(i).getnReproduccio()) * 600 / mesGran), 28);
			g.drawString(cancons.get(i).getNom() + " (" + cancons.get(i).getnReproduccio() + ")", 10, xText);
			
			R-=20;
			G+=20;
			B+=20;
			xBarra += 38;
			xText += 38;
			
			i++;
		}
		
	}
	
	/*private int obteMesGran() {
		int maxim = Integer.MIN_VALUE;
		
		for (int i = 0; i < musica.getMusica().size(); i ++) {
			if (Integer.parseInt(musica.getMusica().get(i).getnReproduccio()) > maxim) {
				maxim = Integer.parseInt(musica.getMusica().get(i).getnReproduccio());
			}
		}
		
		return maxim;
	}*/

	
}
