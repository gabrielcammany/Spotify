package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CustomPanel extends JPanel {
	
	private ImageIcon imagen;
	
	public CustomPanel() {
		
	}
	
	public void paint(Graphics g) {
		Dimension tam = getSize();
		imagen = new ImageIcon("/Images/backGround1.jpg");
		g.drawImage(imagen.getImage(), 0, 0, tam.width, tam.height, null);
		setOpaque(false);
		super.paint(g);
	}
}
