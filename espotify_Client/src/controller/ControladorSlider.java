package controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * 
 * controlador del volum
 *
 */
public class ControladorSlider implements ChangeListener{

	private JSlider slider;
	boolean barra;
	 
	public ControladorSlider(JSlider slider, boolean barra){
		this.slider = slider;
		this.barra = barra;
	}
	
	
	@Override
	public void stateChanged(ChangeEvent event) {
		
			if(barra){
				if(ControladorFinestres.getR().isPlaying()){
					try {
						ControladorFinestres.getR().getPlayer().setGain((float)slider.getValue()/100);
					} catch (BasicPlayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					ControladorFinestres.getR().setVolum((float)slider.getValue()/100);
				}
				
			/*}else{
				if((ControladorFinestres.getR().isAlive())){
					ControladorFinestres.getR().endSong2();
					ControladorFinestres.setR(new Reproductor());
					ControladorFinestres.getR().setBytes((long) ((float)(((float)slider.getValue()/100))*((ControladorFinestres.getR().getFile().length()))));
					ControladorFinestres.getR().restart();
					ControladorFinestres.getR().start();
					}*/

			}
		
      }


}
