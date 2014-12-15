package zw.scotlandyardapp.app; 
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class Init extends JComponent{
	private Vector<TransportMode> tmode;
	private ArrayList<LondonMap> londonmap;
	public ArrayList<Player> players;
	public MisterX X;
	public boolean myInitialize;
	public boolean myPlay;
	public int rounds;
	public Init(){
		this.rounds = 1;
		this.londonmap = new ArrayList<LondonMap>();
		this.tmode = new Vector<TransportMode>(468);
		this.myInitialize = false;
		this.myPlay = false;
		this.players = new ArrayList<Player> ();
		X = new MisterX();
		for(int i = 0; i < 5; i++){
			
			Player p = new Player();
			p.generateTransportMode("london.map");
			p.generateMap();
			int rnd = i*3 + 100;
			p.setPosition(rnd);
			players.add(p);
		}
		X.setPosition(X.randInt(121,198));

	}

	  @Override
	    public void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		for (TransportMode t : tmode){
					int a,b,c,d;
					a = londonmap.get(t.getId1()-1).getX();
					b = londonmap.get(t.getId1()-1).getY();
					c = londonmap.get(t.getId2()-1).getX();
					d = londonmap.get(t.getId2()-1).getY();
					if(t.getMode().equals("taxi")){
						g2d.setColor(Color.BLUE);
						g2d.drawLine(a, b, c, d);
						g2d.fillOval(a, b, 10, 10);
						g2d.fillOval(c, d, 10, 10);
					}
					if(t.getMode().equals("ferry")){
						g2d.setColor(Color.red);
						g2d.drawLine(a, b, c, d);
						g2d.fillOval(a, b, 10, 10);
						g2d.fillOval(c, d, 10, 10);
					}
					if(t.getMode().equals("bus")){
						g2d.setColor(Color.green);
						g2d.drawLine(a, b, c, d);
						g2d.fillOval(a, b, 10, 10);
						g2d.fillOval(c, d, 10, 10);
					}
					if(t.getMode().equals("subway")){
						g2d.setColor(Color.RED);
						g2d.drawLine(a, b, c, d);
						g2d.fillOval(a, b, 10, 10);
						g2d.fillOval(c, d, 10, 10);
					}
				}
			if(myInitialize == true){
				for (int j = 0; j < 5; j++){
					int pos = players.get(j).getPosition();
					int xc = londonmap.get(pos-1).getX();
					int yc = londonmap.get(pos-1).getY();
					Font f = new Font ("Monospaced", Font.BOLD , 14);
				    g2d.setFont (f);
				   	g2d.setColor(Color.BLACK);
					g2d.drawString("DT"+j, xc, yc);
				}
				Font f = new Font ("Monospaced", Font.BOLD , 14);
			    g2d.setFont (f);
			   	g2d.setColor(Color.RED);
			   	int pos = X.getPosition();
				int xc = londonmap.get(pos-1).getX();
				int yc = londonmap.get(pos-1).getY();
				g2d.drawString("Mr.X", xc, yc);
				myInitialize = false;
			}
			if(myPlay == true){
					
					int idx = 0;
					int i = 0;
				
						X.move(rounds-1);
						Font ff = new Font ("Monospaced", Font.BOLD , 16);
					    g2d.setFont (ff);
					   	g2d.setColor(Color.RED);
						g2d.drawString("Mr.X", londonmap.get(X.getPosition()-1).getX(), londonmap.get(X.getPosition()-1).getY());
						try {
						    Thread.sleep(100);              
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						}
						for (Player player: players){
							Font f = new Font ("Monospaced", Font.BOLD , 16);
						    g2d.setFont (f);
						   	g2d.setColor(Color.BLACK);
						   	player.moveSmart(rounds,X);
						   	rounds++;
						   	int index = player.getPosition()-1;
						   	if(player.getMap().get(index).second.equals("MisterX")){
						   		JOptionPane.showMessageDialog(null,"Mr X is caught at "+index,"Mr X is caught at"+index,JOptionPane.INFORMATION_MESSAGE);
						   		//System.exit(0);
						   	}
							g2d.drawString("DT"+idx, londonmap.get(player.getPosition()-1).getX(), londonmap.get(player.getPosition()-1).getY());
							try {
							    Thread.sleep(100);              
							} catch(InterruptedException ex) {
							    Thread.currentThread().interrupt();
							}
							idx++;
					}
			}
					
		}
			
	  
		public void generateTransportMode(String filename){
			int count = 0;
			InputStream stream = Player.class.getResourceAsStream(filename);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	        String line;
	        try{
		        while ((line = reader.readLine()) != null){        	
		        	if(count < 15){
		        		count++;
		        	}else if(!line.isEmpty()){
		        		if(line.substring(0, 4).equals("node")){
		        			StringTokenizer tok = new StringTokenizer(line ,"	");
		                    String dummy = tok.nextToken();
		                    int x = Integer.parseInt(tok.nextToken());
		   				 	int y = Integer.parseInt(tok.nextToken());
		   				 	int z = Integer.parseInt(tok.nextToken());
		   				 	LondonMap point = new LondonMap(6*y,6*z);
		   				 	londonmap.add(point);
		        		}else{
		        			 StringTokenizer tt = new StringTokenizer(line ,"	");
		                     String mode = tt.nextToken();
		                     int x = Integer.parseInt(tt.nextToken());
		    				 int y = Integer.parseInt(tt.nextToken());
		    				 TransportMode t = new TransportMode(mode,x,y);
		    				 tmode.addElement(t);
		        		}
		        		
		        	}else{
		        		continue;
		        		}
		        }
	        }catch (IOException e){
	            e.printStackTrace();
	           }finally{
	        	   try{
	        		   if (reader != null)
	        			   reader.close();
	        	   }catch(IOException ex){
	        		   ex.printStackTrace();
	        		}
	        	 }
		  }
	 
}

