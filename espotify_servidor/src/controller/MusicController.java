package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import view.FinestraServidor;

/**
 * 
 * Clase per reproduir les cancons del servidor
 *
 */
public class MusicController extends MouseAdapter {
	private ReproductorController reproductorController;
	private FinestraServidor finestraServidor;
	private String opcio;
	private String nom;
	private String artista;
	
	public MusicController(String opcio, ReproductorController reproductorController, FinestraServidor finestraServidor) {
		this.reproductorController = reproductorController;
		this.finestraServidor = finestraServidor;
		this.opcio = opcio;

	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mousePressed(e);
		
		nom = (String) finestraServidor.getTaulaMusica().getValueAt(finestraServidor.getTaulaMusica().getSelectedRow(), 0);
		artista = (String) finestraServidor.getTaulaMusica().getValueAt(finestraServidor.getTaulaMusica().getSelectedRow(), 3);
		
		if (nom == null || artista == null) {
			JOptionPane.showMessageDialog(finestraServidor, "Has de seleccionar una canço"); 
		} else {
			switch(opcio) {
			case "play":
				reproductorController.reprodueix(nom, artista);
				break;
			case "pause": 
				reproductorController.pausa();
				break;
			}

		}
		
	}
	
}