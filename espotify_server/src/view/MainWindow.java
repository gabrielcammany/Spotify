package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.ButtonsController;


@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	private JScrollPane jspMessages;
	private JTextArea jtaMessages;
	private JButton jbStart;
	private JButton jbStop;
	private JLabel jlNcars;
	
	public MainWindow() {
		jlNcars =new JLabel();
		
		
		jtaMessages = new JTextArea();
		jspMessages = new JScrollPane(jtaMessages);
		jtaMessages.setEditable(false);
		jtaMessages.setBackground(Color.BLACK);
		jtaMessages.setForeground(Color.WHITE);
		JPanel jpButtons = new JPanel();
		jbStart = new JButton("Start");
		jbStop = new JButton("Stop");
		jbStop.setEnabled(false);
		jbStop.setForeground(Color.RED);
		jbStart.setForeground(Color.GREEN);
		jpButtons.add(jbStart);
		jpButtons.add(jbStop);
		
		this.getContentPane().add(jlNcars, BorderLayout.NORTH);
		this.getContentPane().add(jspMessages, BorderLayout.CENTER);
		this.getContentPane().add(jpButtons, BorderLayout.PAGE_END);
		
		this.setSize(450, 200);
		this.setResizable(false);
		this.setTitle("*** eSpotifai Server  ***");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void registerController(ButtonsController controller) {
		jbStart.addActionListener(controller);
		jbStart.setActionCommand("START");
		jbStop.addActionListener(controller);
		jbStop.setActionCommand("STOP");
	}
	
	public void addText(String text) {
		jtaMessages.setText(jtaMessages.getText() + "\n" + text);
	}
	
	public void changeButtonsStateStarted() {
		jbStart.setEnabled(false);
		jbStop.setEnabled(true);
	}
	
	public void changeButtonsStateStopped() {
		jbStart.setEnabled(true);
		jbStop.setEnabled(false);
	}
}
