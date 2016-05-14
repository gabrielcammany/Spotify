package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.Canco;
import model.Llistes;
import model.Reproductor;
import model.User;
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
	public static Finestra_login fLogin;
	public static Finestra_Registre fRegistre;
	public static FinestraReproduccio fReproduccio;
	private static InfoServidor infoServidor;
	private static User user;
	private static boolean reproduir = false;
	private static Reproductor r;
	
	private static ArrayList<Canco> alMusic;
	

	public ControladorFinestres(){
		
	}
	
	public ControladorFinestres (InfoServidor infoServidor) {
		fLogin = new Finestra_login();
		ControladorFinestres.infoServidor = infoServidor;
		setR(new Reproductor(""));
		/*Thread t = new Thread(){
			@Override
			public void run(){
				while(true){
					if(reproduir){
						r.run();
						reproduir = false;
					}
				}
			}
		};
		t.start();*/
		
	}
		
	/**
	 * Metode encarregat de gestionar un nou registre a l'aplicacio
	 */
	
	public static void nouRegistre() {
		//tanquem finestra de login
		fLogin.tancaFinestraLogin();
		fRegistre = new Finestra_Registre();
		
		fRegistre.getjbRegistre().addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //InfoServidor info = new InfoServidor();
                infoServidor.enviarUsuari(1, fRegistre.getjtfUsuari().getText(),fRegistre.getjtfPassword().getPassword());
            }
        }); 
		
	}
	

	
	/**
	 * Proces de login
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void Login() throws ClassNotFoundException, UnknownHostException, IOException {
		//InfoServidor info = new InfoServidor();
		if((infoServidor.enviarUsuari(2, fLogin.getjtfUsuari().getText(), fLogin.getjtfPassword().getPassword()))){
			//infoServidor.demanaSessio();
			Reproduccio();
		} else {
			fLogin.mostraMissatgeError("L'usuari no existeix.");
		}
		
	}
	
	/**
	 * Reproduir la musica
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	
	public static void Reproduccio() throws ClassNotFoundException, UnknownHostException, IOException{
		/*
		 * 
		 * Comprovar dades correctes
		 * 
		 * depen de si ve per login o registre tancar la finestra correcte per no fer pataPUM
		 * 
		 */
		//tanquem les finesttra segons d'on veniem
		if(fLogin != null){
			fLogin.tancaFinestraLogin();
		}
		if(fRegistre != null){
			fRegistre.tancaFinestraRegistre();
		}

		fReproduccio = new FinestraReproduccio();
		//Realitzem la peticio de cancos disponibles al servidor
		
		//infoServidor =new InfoServidor();
		//infoServidor.demanaSessio();
		infoServidor.peticioMusica();
		infoServidor.peticioUsuaris();
	}
	
	/*
	 * Aquesta funcio parla amb la finestra per actualitzar la llista de musica disponible
	 * rep un ArrayList de cancons
	 * 
	 */
	
	public static void actualitzaMusicaDisponible(ArrayList<Canco> alMusica){
		alMusic = alMusica;
		fReproduccio.setMusicaDisponible(alMusica);
		
		
	}
	
	public static void actualitzaUsuaris(ArrayList<User> alUsers) {
		fReproduccio.setUsuaris(alUsers);
	}
	
	/*
	 * Aquesta funcio parla amb la finestra per actualitzar la musica propia
	 * rep un ArrayList de cancons
	 * 
	 */
	public static void actualitzaMusicaPropia(ArrayList<Canco> alMusica){
		fReproduccio.setMusicaPropia();
		
	}
	
	/*
	 * Aquesta funcio parla amb la finestra per actualitzar les llistes dels usuaris que seguim
	 * rep un ArrayList llistes
	 * 
	 */
	public static void actualitzaLlistesFollowing(/*  rebre array de llistes    */ArrayList<String> alListes){
		fReproduccio.setLlistesFollowing(/*  pasar array de llistes    */alListes);
		
	}
	
	
	/*
	 * Aquesta funcio parla amb la finestra per actualitzar el nom dels usuaris que seguim com per fer 
	 * follow o unfollow
	 * 
	 */

	public static  void actualitzaUsuarisFollowing(ArrayList<User> alUsuari){
		fReproduccio.setUsuarisFollowing(alUsuari);
		
	}
	
	/*
	 * Aquesta funcio parla amb la finestra per dirli que s'ha clicat una opcio de mostrar contingut
	 * 
	 */
	public static void novaOpcio(String opcio) {
		fReproduccio.actualitzaOpcio(opcio);
	}
	
	public static JTable obteTaulaMusica() {
		return fReproduccio.getTaulaMusica();
	}
	
	public static InfoServidor getServidor() {
		return infoServidor;
	}
	
	public static void restartReproductor() {
		setR(new Reproductor(""));
	}
	
	public static ArrayList<String> getlistesPropies() {
		ArrayList<String> nomsLlistes = new ArrayList();
		for (int i = 0; i<User.getlPropies().size(); i++) {
			nomsLlistes.add(User.getlPropies().get(i).getNom_llista());	
		}
		return nomsLlistes;
	}
	
	public static void actualitzaTablaLlistaPropia(int indexSeleccio){
		Llistes llistaSeleccionada = new Llistes();
		llistaSeleccionada = User.getlPropies().get(indexSeleccio);
		
		ArrayList<Canco> musicaLlista = new ArrayList<Canco>();
		
		for (int i = 0; i < llistaSeleccionada.getAllIdCanco().size(); i ++) {
			for (int j = 0; j < alMusic.size(); j++) {
				if(llistaSeleccionada.getAllIdCanco().get(i) == alMusic.get(j).getidCanco()) {
					System.out.println("TROBADA");
					System.out.println(alMusic.get(j).getidCanco());
					musicaLlista.add(alMusic.get(j));
				}
			}
		}
		
		fReproduccio.actualitzaLlistaSeleccionada(musicaLlista);
	}
	
	
	public static void mostraPopUp(int option) {
		switch (option){
			case 0:
				JOptionPane.showMessageDialog(fReproduccio,
					    "Usuari no trobat",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
				break;
				
			case 1:
				Object[] options = {"Fer follow",
						"Cancelar"};
				int n = JOptionPane.showOptionDialog(fReproduccio,
						"Vols fer follow a l'usuari?",
								"Following",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								options,
								options[1]);
				
				break;
		}
	}

	public static Reproductor getR() {
		return r;
	}

	public static void setR(Reproductor r) {
		ControladorFinestres.r = r;
	}
	
}
