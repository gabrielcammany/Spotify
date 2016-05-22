package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.User;


/**
 * control del buscar usuari a fer follow
 *
 */
public class ControladorBotons implements MouseListener{
	
	private JTextField jtNomUsuariBusca;
	private JPanel repro;
	
	public ControladorBotons (JTextField jtNomUsuariBusca, JPanel repro) {
		this.jtNomUsuariBusca = jtNomUsuariBusca;
		this.repro = repro;
	}
	
	/**
	 * control del buscar usuari a fer follow
	 *@param rep un mouse event del boto buscar usuari
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		//si el text field de buscar usuari a fer follow no esta buit enviarem una pericio de busqueda
		if(!jtNomUsuariBusca.getText().isEmpty()){
			if(!jtNomUsuariBusca.getText().equals(User.getNickname())){
				ControladorFinestres.mostraPopUp(1,jtNomUsuariBusca.getText());
			}else{
				JOptionPane.showMessageDialog(repro,
					    "No et pots seguir a tu mateix",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
