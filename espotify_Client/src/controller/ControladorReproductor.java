package controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;


/**
 * Controlador encarregat de gestionar els botons encarregats de reproduir la musica
 * (play/pausa, next/back)
 * 
 *
 */

public class ControladorReproductor implements MouseListener {
	
	private String opcio;
	private ControladorFinestres controladorfinestres;
	private boolean play = false;
	private boolean playing = false;
	private String nom;
	private String nomReproduccio;
	private String artista;

	
	public ControladorReproductor(String opcio,ControladorFinestres fr) {
		this.opcio = opcio;
		this.controladorfinestres = fr;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Boolean algoSeleccionat = true;
		
		try{
			//mirem si estem a la taula de musica disponible
			nom = (String) controladorfinestres.fReproduccio.getTaulaMusica().getValueAt(controladorfinestres.fReproduccio.getTaulaMusica().getSelectedRow(), 0);
			artista = (String) controladorfinestres.fReproduccio.getTaulaMusica().getValueAt(controladorfinestres.fReproduccio.getTaulaMusica().getSelectedRow(), 3);

		}catch(Exception e1){
		}
		if(nom == null){
			try{
				//mirem si estem a la taula de llista musica following
				nom = (String) controladorfinestres.fReproduccio.getTaulaLlistaMusicaFollowing().getValueAt(controladorfinestres.fReproduccio.getTaulaLlistaMusicaFollowing().getSelectedRow(), 0);
				artista = (String) controladorfinestres.fReproduccio.getTaulaLlistaMusicaFollowing().getValueAt(controladorfinestres.fReproduccio.getTaulaLlistaMusicaFollowing().getSelectedRow(), 3);
			}catch(Exception e1){
			}
			if(nom == null){
				//mirem si ja hi havia algo reproduintse
				nom = nomReproduccio;
			}
			if(nom == null) algoSeleccionat = false;
		}

		if(algoSeleccionat){
			switch (opcio) {
			case "play":
				System.out.println("click play");
				System.out.println(nom + " " + artista);
				if(controladorfinestres.r.getSong().equals(nom + "_" + artista)) {
					controladorfinestres.r.pause();
				}
				else {
					if (controladorfinestres.r.isPlaying()) {
						controladorfinestres.r.endSong();
					}
					controladorfinestres.restartReproductor();
					controladorfinestres.r.setPath(nom,artista);
					
	
					JTable taulaMusica = controladorfinestres.obteTaulaMusica();
					
					//Enviem Request al servidor per tal que ens retorni la canço seleccionada
					try {
						
						controladorfinestres.getServidor().peticio("requestCanco", nom + "/" + artista );
						controladorfinestres.r.start();
						playing = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.out.println("netejoclick--->" +nom);
				nom = null;
				System.out.println("limpiaoclick--->" +nom);
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
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		ImageIcon im = new ImageIcon("Images/"+opcio+"d.png");
		switch (opcio) {
		case "play":
			
			Boolean algoSeleccionat = true;
			try{
				//comprovem si havia seleccionat alguna canco
				nom = (String) controladorfinestres.fReproduccio.getTaulaMusica().getValueAt(controladorfinestres.fReproduccio.getTaulaMusica().getSelectedRow(), 0);
				artista = (String) controladorfinestres.fReproduccio.getTaulaMusica().getValueAt(controladorfinestres.fReproduccio.getTaulaMusica().getSelectedRow(), 3);
			}catch(Exception e1){}
			if(nom == null){
				try{
					nom = (String) controladorfinestres.fReproduccio.getTaulaLlistaMusicaFollowing().getValueAt(controladorfinestres.fReproduccio.getTaulaLlistaMusicaFollowing().getSelectedRow(), 0);
					artista = (String) controladorfinestres.fReproduccio.getTaulaLlistaMusicaFollowing().getValueAt(controladorfinestres.fReproduccio.getTaulaLlistaMusicaFollowing().getSelectedRow(), 3);
				}catch(Exception e1){}
				if(nom == null){
					nom = nomReproduccio;
				}
				if(nom == null) algoSeleccionat = false;
			}

			if(algoSeleccionat){
				//si havia seleccionat alguna mentre prem el boto donara efecte optic de premut

				if (controladorfinestres.r.isPlaying() && controladorfinestres.r.getSong().equals(nom + "_" + artista)) {
					ImageIcon impause = new ImageIcon("Images/paused.png");
					ImageIcon icp = new ImageIcon(impause.getImage().getScaledInstance(50, 51, Image.SCALE_DEFAULT));
					if (e.getSource() instanceof JLabel) {
						JLabel aux = (JLabel)e.getSource();
						aux.setForeground(new Color(164,164,164));
						aux.setFocusable(true);
						aux.setFocusTraversalKeysEnabled(true);
						aux.setIcon(icp);
					}
				}else{
					ImageIcon icp = new ImageIcon(im.getImage().getScaledInstance(50, 51, Image.SCALE_DEFAULT));
					if (e.getSource() instanceof JLabel) {
						JLabel aux = (JLabel)e.getSource();
						aux.setForeground(new Color(164,164,164));
						aux.setFocusable(true);
						aux.setFocusTraversalKeysEnabled(true);
						aux.setIcon(icp);
					}
				}
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
		Boolean algoSeleccionat = true;
		Boolean esFollowing = false;
		
		try{
			//mirem si estem a la taula de musica disponible
			nom = (String) controladorfinestres.fReproduccio.getTaulaMusica().getValueAt(controladorfinestres.fReproduccio.getTaulaMusica().getSelectedRow(), 0);
			artista = (String) controladorfinestres.fReproduccio.getTaulaMusica().getValueAt(controladorfinestres.fReproduccio.getTaulaMusica().getSelectedRow(), 3);
		}catch(Exception e1){
		}
		if(nom == null){
			try{
				//mirem si estem a la taula de llista musica disponible
				nom = (String) controladorfinestres.fReproduccio.getTaulaLlistaMusicaFollowing().getValueAt(controladorfinestres.fReproduccio.getTaulaLlistaMusicaFollowing().getSelectedRow(), 0);
				artista = (String) controladorfinestres.fReproduccio.getTaulaLlistaMusicaFollowing().getValueAt(controladorfinestres.fReproduccio.getTaulaLlistaMusicaFollowing().getSelectedRow(), 3);
			}catch(Exception e1){				
			}
			if(nom == null){
				//mirem si ja hi havia algo reproduintse
				nom = nomReproduccio;
			}
			if(nom == null) algoSeleccionat = false;
		}

		if(algoSeleccionat){
			ImageIcon im = new ImageIcon("Images/"+opcio+".png");
			switch (opcio) {
			case "play":
				if (controladorfinestres.r.isPlaying() && controladorfinestres.r.getSong().equals(nom + "_" + artista)) {
					ImageIcon icp = new ImageIcon(im.getImage().getScaledInstance(50, 51, Image.SCALE_DEFAULT));
					if (e.getSource() instanceof JLabel) {
						JLabel aux = (JLabel)e.getSource();
						aux.setForeground(new Color(164,164,164));
						aux.setFocusable(true);
						aux.setFocusTraversalKeysEnabled(true);
						aux.setIcon(icp);
					}
				}else{
					ImageIcon impause = new ImageIcon("Images/pause.png");
					ImageIcon icp = new ImageIcon(impause.getImage().getScaledInstance(50, 51, Image.SCALE_DEFAULT));
					if (e.getSource() instanceof JLabel) {
						JLabel aux = (JLabel)e.getSource();
						aux.setForeground(new Color(164,164,164));
						aux.setFocusable(true);
						aux.setFocusTraversalKeysEnabled(true);
						aux.setIcon(icp);
					}
				}
				nom = null;
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
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	
	
	

}
