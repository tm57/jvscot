package zw.scotlandyardapp.app;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
	JFrame frame = new JFrame("Scotlandyard App");
	Init init;
	private JMenuItem initialize = new JMenuItem("initialize");
	private JMenuItem play = new JMenuItem("play");
	private JMenuItem quit = new JMenuItem("quit");
	StatusBar statusBar = new StatusBar();
	public int rounds;
	public App(){
		this.rounds = 0;
		init = new Init();
		init.generateTransportMode("london.map");
		frame.add(init);
		}
	private class myListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == initialize){
				init.myInitialize = true;
				init.repaint();
				statusBar.setMessage("The search begins,players are placed on the map ");
			}else if(event.getSource() == quit){
				System.exit(0);
			}else if(event.getSource() == play){
				init.myPlay = true;
				init.repaint();
				rounds+=1;
				if(rounds == 23){
					JOptionPane.showMessageDialog(null,"Mr X escapes","Information",JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				statusBar.setMessage("Round "+ rounds);
				}
			}
		
	}
	//Read http://www.java-tips.org/java-se-tips/javax.swing/creating-a-status-bar.html
	 public class StatusBar extends JLabel {
		    
		    /** Creates a new instance of StatusBar */
		    public StatusBar() {
		        super();
		        super.setPreferredSize(new Dimension(100, 16));
		        setMessage("Ready");
		    }
		    
		    public void setMessage(String message) {
		        setText(" "+message);        
		    }        
		}
	
	public void Scot()throws InterruptedException{
		
		frame.getContentPane().add(statusBar, java.awt.BorderLayout.SOUTH);
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		fileMenu.add(initialize);
		fileMenu.add(play);
		fileMenu.add(quit);
		myListener listener = new myListener();
		initialize.addActionListener(listener);
		play.addActionListener(listener);
		quit.addActionListener(listener);
		
		
		frame.setJMenuBar(menuBar);
		frame.setSize(680,680);
		frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			

	}
	public static void main(String [] args)throws InterruptedException{
		App app = new App();
		app.Scot();
	
	}
 }
