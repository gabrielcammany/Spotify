package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;

import model.Canco;
import model.Reproductor;
import network.InfoServidor;
import view.FinestraReproduccio;
import view.Finestra_Registre;
import view.Finestra_login;
/**
 * Controlador encarregat de gestionar la relacion entre les diferents finestres 
 * (Login, Registre, finestra principal)
 * 
 *
 */
public class ControladorFinestres {
	public Finestra_login fLogin;
	public Finestra_Registre fRegistre;
	public FinestraReproduccio fReproduccio;
	private InfoServidor infoServidor;
	boolean reproduir = false;
	Reproductor r;
	

	public ControladorFinestres(){
		
	}
	
	public ControladorFinestres (InfoServidor infoServidor) {
		this.fLogin = new Finestra_login(this);
		this.infoServidor = infoServidor;
		this.infoServidor.setControladorFinestra(this);
		r = new Reproductor("");
		Thread t = new Thread(){
			@Override
			public void run(){
				int i = 0;
				while(true){
					if(reproduir){
						i = 0;
						r.run();
						reproduir = false;
					}
					i++;
				}
			}
		};
		t.start();
		
	}
		
	/**
	 * Metode encarregat de gestionar un nou registre a l'aplicacio
	 */
	
	public void nouRegistre() {
		//tanquem finestra de login
		fLogin.tancaFinestraLogin();
		fRegistre = new Finestra_Registre(this);
		
		fRegistre.getjbRegistre().addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //InfoServidor info = new InfoServidor();
                infoServidor.enviarUsuari(1, fRegistre.getjtfUsuari().getText(),fRegistre.getjtfPassword().getPassword());
            }
        }); 
		
	}
	
	public void creaMusicaDisponible(ArrayList<Canco> alMusica) {
		
	}
	
	/**
	 * Proces de login
	 * @throws ClassNotFoundException 
	 */
	public void Login() throws ClassNotFoundException {
		//InfoServidor info = new InfoServidor();
		infoServidor.enviarUsuari(2, fLogin.getjtfUsuari().getText(), fLogin.getjtfPassword().getPassword());
		Reproduccio();	
		
	}
	
	/**
	 * Reproduir la musica
	 * @throws ClassNotFoundException 
	 */
	
	public void Reproduccio() throws ClassNotFoundException{
		/*
		 * 
		 * Comprovar dades correctes
		 * 
		 * depen de si ve per login o registre tancar la finestra correcte per no fer pataPUM
		 * 
		 */

		fReproduccio = new FinestraReproduccio(this);
		//Realitzem la peticio de can�ons al servidor
		
		//infoServidor =new InfoServidor();
		infoServidor.peticioMusica();
		
		
	}
	
	public void actualitzaMusicaDisponible(ArrayList<Canco> alMusica){
		fReproduccio.setMusicaDisponible(alMusica);
		
		
	}
	
	public void novaOpcio(String opcio) {
		fReproduccio.actualitzaOpcio(opcio);
	}
	
	public JTable obteTaulaMusica() {
		return fReproduccio.getTaulaMusica();
	}
	
	public InfoServidor getServidor() {
		return infoServidor;
	}
		
	
}
