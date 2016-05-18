package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CloseWindowController extends WindowAdapter {



	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("cierra sesionnn");
	}
	

}
