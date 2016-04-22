package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.UnsupportedEncodingException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * Controlador encarregat de gestionar els botons encarregats de reproduir la musica
 * (play/pausa, next/back)
 * 
 *
 */

public class ControladorReproductor implements MouseListener {
	
	private String opcio;
	
	
	
	public ControladorReproductor(String opcio) {
		this.opcio = opcio;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (opcio) {
		case "play":
			System.out.println("click play");
			break;
		case "next":
			System.out.println("click next");
			break;
		case "back":
			System.out.println("click back");
			break;

		default:
			break;
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		ImageIcon im = new ImageIcon("Images/"+opcio+"d.png");
		switch (opcio) {
		case "play":
			ImageIcon icp = new ImageIcon(im.getImage().getScaledInstance(50, 51, Image.SCALE_DEFAULT));
			System.out.println("click play");
			if (e.getSource() instanceof JLabel) {
				JLabel aux = (JLabel)e.getSource();
				aux.setForeground(new Color(164,164,164));
				aux.setFocusable(true);
				aux.setFocusTraversalKeysEnabled(true);
				aux.setIcon(icp);
			}
			break;
		case "next":
			System.out.println("click next");
			ImageIcon icn = new ImageIcon(im.getImage().getScaledInstance(46, 40, Image.SCALE_DEFAULT));
			if (e.getSource() instanceof JLabel) {
				JLabel aux = (JLabel)e.getSource();
				aux.setForeground(new Color(164,164,164));
				aux.setFocusable(true);
				aux.setFocusTraversalKeysEnabled(true);
				aux.setIcon(icn);
			}
			break;
		case "back":
			System.out.println("click back");
			ImageIcon icb = new ImageIcon(im.getImage().getScaledInstance(46, 40, Image.SCALE_DEFAULT));
			if (e.getSource() instanceof JLabel) {
				JLabel aux = (JLabel)e.getSource();
				aux.setForeground(new Color(164,164,164));
				aux.setFocusable(true);
				aux.setFocusTraversalKeysEnabled(true);
				aux.setIcon(icb);
			}
			break;

		default:
			break;
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// desclic
				ImageIcon im = new ImageIcon("Images/"+opcio+".png");
				switch (opcio) {
				case "play":
					ImageIcon icp = new ImageIcon(im.getImage().getScaledInstance(50, 51, Image.SCALE_DEFAULT));
					if (e.getSource() instanceof JLabel) {
						JLabel aux = (JLabel)e.getSource();
						aux.setForeground(new Color(164,164,164));
						aux.setIcon(icp);
					}
					break;
				case "next":
					ImageIcon icn = new ImageIcon(im.getImage().getScaledInstance(46, 40, Image.SCALE_DEFAULT));
					if (e.getSource() instanceof JLabel) {
						JLabel aux = (JLabel)e.getSource();
						aux.setForeground(new Color(164,164,164));
						aux.setIcon(icn);
					}
					break;
				case "back":
					ImageIcon icb = new ImageIcon(im.getImage().getScaledInstance(46, 40, Image.SCALE_DEFAULT));
					if (e.getSource() instanceof JLabel) {
						JLabel aux = (JLabel)e.getSource();
						aux.setForeground(new Color(164,164,164));
						aux.setIcon(icb);
					}
					break;

				default:
					break;
				}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
