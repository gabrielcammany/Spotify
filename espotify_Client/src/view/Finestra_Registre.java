package view;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;
/**
 * Gestio de la finestra encarregada de la introduccio de dades referents al registre. 
 * @author carlaurrea
 *
 */

public class Finestra_Registre {
	
	public Finestra_Registre() {
		JFrame jfReg = new JFrame();
		jfReg.setLayout(new MigLayout());
		
		jfReg.setVisible(true);
		jfReg.setSize(350, 350);
		
		jfReg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfReg.setLocationRelativeTo(null);
		jfReg.setTitle("eSpotyfai - Registre");
		
	}

}
