package zw.scotlandyardapp.app; 
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Player {
	private int position;
	private int subway;
	private int bus;
	private int taxi;
	private int tokens;
	private String role;
	private static Vector<Pair> map;
	private static Vector<TransportMode> tmode;
	private ArrayList<LondonMap> londonmap;
	public int x1,y1;
	
	//how to find random number in given range in java
	//Read http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
	public int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public Player(){
		this.setSubway(4);
		this.setBus(8);
		this.setTaxi(10); 
		this.setTokens(this.bus + this.subway + this.taxi);
		this.londonmap = new ArrayList<LondonMap>();
		this.tmode = new Vector<TransportMode>(468);
		this.setRole("detective");
		
	}
	
	public void generateMap(){
		map = new Vector<Pair>();
		for(int i = 1; i < 200; i++){
			map.add(new Pair(i,"vacant"));
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

	public static Vector<Pair> getMap() {
		return map;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
		getMap().add(getPosition(), new Pair(getPosition(),getRole()));//set and mark as occupied
			
	}

	public int getSubway() {
		return subway;
	}

	public void setSubway(int subway) {
		this.subway = subway;
	}

	public int getBus() {
		return bus;
	}

	public void setBus(int bus) {
		this.bus = bus;
	}

	public int getTaxi() {
		return taxi;
	}

	public void setTaxi(int taxi) {
		this.taxi = taxi;
	}
	/*This move function defines how a detective moves
	 * for the tokens in hand,for each type of token ,a list of possible locations to go from current position is used to randomly
	 * generate next node to move to
	 * if there is another detective on a possible node , that node is not used and no movement will occur if all neighbours are 
	 * detective occupied
	 * Detectives just move without even knowing there exists Mr X log,ie the moves are not smart
	 * */
	
	public void move(){
		ArrayList <Integer> flee1  = new ArrayList<Integer>();
		ArrayList <Integer> options  = new ArrayList<Integer>();
		ArrayList <Integer> flee2  = new ArrayList<Integer>();
		ArrayList <Integer> flee0  = new ArrayList<Integer>();
		flee0 = findNextAll("subway");
		if(this.getSubway() > 0 && flee0.size() > 0){
			for(Integer i: flee0){//excluding occupied positions
				if(map.get(i).second.equals(this.getRole())){
					continue;
				}else{
					options.add(i);
				}
			}
		}
		
		flee1 = findNextAll("bus");
		if(this.getBus() > 0){
			for(Integer i: flee1){
				if(map.get(i).second.equals(this.getRole())){
					continue;
				}else{
					options.add(i);
				}
			}
		}
		
		flee2 = findNextAll("taxi");
		if(this.getTaxi() > 0 && flee2.size() > 0){
			for(int i: flee2 ){
				if(map.get(i).second.equals(this.getRole())){
					continue;
				}else{
					options.add(i);
				}
			 }
		}
			
		if(options.size() > 0){
			int max = options.size();
			int index = randInt(0,max - 1);//generating next move randomly,out of the possibilities available
			Pair replace = new Pair(this.position,"vacant");
			map.add(this.position,replace);//freeing previous position
			if(map.get(index).second.equals("MisterX")){
				this.setPosition(options.get(index));//setting new position
				System.out.println("Mr X is caught at position node "+ index);
				//System.exit(0);
			}else{
				
				this.setPosition(options.get(index));//setting new position
			}//not needed now,but useful to keep track of tokens and maybe to just visualize
				if(isInList(options.get(index),flee0)){
					this.subway-=1;
				}else if(isInList(options.get(index),flee1)){
					this.bus-=1;
				}else if (isInList(options.get(index),flee2)){
					this.taxi-=1;
				}
		}
}

	public boolean isInList(int pos,ArrayList <Integer> flee1){
		if(flee1.size() == 0){
			return false;
		}
		int flag = 0;
		for(Integer i: flee1){
			if(i != pos){
				continue;
			}else{
				flag = 1;
				break;
			}
		}
		return flag == 1;
		
	}
	/**findNextAll
	 * finds, next possible positions given a starting position and transport mode one wishes to use
	 * */
	public ArrayList<Integer> findNextAll(String input){
		ArrayList <Integer> dst;
		dst = new ArrayList<Integer>();
		int k = 0;
		for (int j = 0; j < tmode.size(); j++){
			if((k = tmode.get(j).findNext(this.position,input)) != 0){
				dst.add(k);
			}
		}
		return dst;
	}
	public ArrayList<Integer> findNextAll(String input,int pos){
		ArrayList <Integer> dst;
		dst = new ArrayList<Integer>();
		int k = 0;
		for (int j = 0; j < tmode.size(); j++){
			if((k = tmode.get(j).findNext(pos,input)) != 0){
				dst.add(k);
			}
		}
		return dst;
	}

	public static Vector<TransportMode> getTmode() {
		return tmode;
	}

	public static void setTmode(Vector<TransportMode> tmode) {
		Player.tmode = tmode;
	}

	public int getTokens() {
		return tokens;
	}

	public void setTokens(int tokens) {
		this.tokens = tokens;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	


    
    public ArrayList<LondonMap> getL(){
    	return this.londonmap;
    }
    
    public void moveSmart(int rounds,MisterX x){
    	if(rounds < 2){
    		move();
    	}else{
    		moveSmartHelper(x,rounds);
    	}
    }
    /*
     * This function finds the node closest to Mister X,once Mister X reveals his last known position
     * To get the shortest distance,I used the Euclidean distance metric
     * The source is the list of those possibilities that Mr X could be found*/
    
    public int shortestPath(ArrayList<Integer> source,int target){
    	int x1 = londonmap.get(0).getX();
		int y1 = londonmap.get(0).getY();
		int x2 = londonmap.get(0).getX();
		int y2 = londonmap.get(0).getY();
		int result = source.get(0);
		double dist = Math.sqrt(Math.pow((double)(x1-x2), 2.0) + Math.pow((double)(y1-y2),2.0));
    	for(Integer i : source){
    		x1 = londonmap.get(i).getX();
    		y1 = londonmap.get(i).getY();
    		x2 = londonmap.get(i).getX();
    		y2 = londonmap.get(i).getY();
    		double dist1 = Math.sqrt(Math.pow((double)(x1-x2), 2.0) + Math.pow((double)(y1-y2),2.0));
    		
    		if(dist1 < dist){
    			dist = dist1;
    			result = i;
    		}else{
    			continue;
    		}
    		
    	}
    	return result;
    }
    
    public void moveSmartHelper(MisterX X,int rounds){
    	ArrayList <Integer> flee1  = new ArrayList<Integer>();
		ArrayList <Integer> options  = new ArrayList<Integer>();
		ArrayList <Integer> flee2  = new ArrayList<Integer>();
		ArrayList <Integer> flee0  = new ArrayList<Integer>();
		flee0 = findNextAll("subway");
		if(this.getSubway() > 0 && flee0.size() > 0){
			for(Integer i: flee0){//excluding occupied positions
				if(map.get(i).second.equals(this.getRole())){
					continue;
				}else{
					options.add(i);
				}
			}
		}
		
		flee1 = findNextAll("bus");
		if(this.getBus() > 0){
			for(Integer i: flee1){
				if(map.get(i).second.equals(this.getRole())){
					continue;
				}else{
					options.add(i);
				}
			}
		}
		
		flee2 = findNextAll("taxi");
		if(this.getTaxi() > 0 && flee2.size() > 0){
			for(int i: flee2 ){
				if(map.get(i).second.equals(this.getRole())){
					continue;
				}else{
					options.add(i);
				}
			 }
		}
			
		if(options.size() > 0){
			int max1 = options.size();
			ArrayList<Integer> where = new ArrayList<Integer>();
			where = X.revealPosition(rounds);//where Mister X could be,based on previous revealed position
			
			int max2 = where.size();
			System.out.println(max2);
			int index1 = randInt(0,max2 - 1);
			int index;
			index = shortestPath(options,index1);
			Pair replace = new Pair(this.position,"vacant");
			map.add(this.position,replace);//freeing previous position
			if(map.get(index).second.equals("MisterX")){
				this.setPosition(options.get(index));//setting new position
				System.out.println("Mr X is caught at position node "+ index);
				//System.exit(0);
			}else{
				
				this.setPosition(options.get(index));//setting new position
			}//not needed now,but useful to keep track of tokens and maybe to just visualize
				if(isInList(options.get(index),flee0)){
					this.subway-=1;
				}else if(isInList(options.get(index),flee1)){
					this.bus-=1;
				}else if (isInList(options.get(index),flee2)){
					this.taxi-=1;
				}
		}
    }
		
}































