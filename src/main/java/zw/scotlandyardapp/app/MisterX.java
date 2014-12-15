package zw.scotlandyardapp.app; 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class MisterX extends Player{
	private int anyconnect;
	private int revealposition;
	private Vector<String> log;
	public ArrayList <Integer> result ;
	
	public MisterX(){
		super();
		this.anyconnect = 5;//black tokens
		this.setRole("MisterX");
		this.log = new Vector<String>();
		revealposition = -1;
		this.result = new ArrayList<Integer>();
	}
	
	public void printLog(){
		System.out.println("Mr .X's log:");
		for(String i: log){
			System.out.print(i+" ");
		}
		System.out.println("\n");
	}
	
	public Vector<String> getLog(){
		return this.log;
	}
	
	public void move(int rounds){
		if( (rounds % 3) == 0){
			//Mister X reveals position 5 times,at every 4th round,in my case
			revealposition = getPosition();
		}
		ArrayList <Integer> flee1  = new ArrayList<Integer>();
		ArrayList <Pair> options  = new ArrayList<Pair>();
		ArrayList <Integer> flee2  = new ArrayList<Integer>();
		ArrayList <Integer> flee0  = new ArrayList<Integer>();
		ArrayList <Integer> flee3  = new ArrayList<Integer>();
			flee0 = findNextAll("subway");
			if(flee0.size() != 0){
				for(Integer i: flee0){//excluding occupied positions
					if(getMap().get(i).second.equals("detective")){
						continue;
					}else{
						Pair pair = new Pair(i,"subway");
						options.add(pair);
					}
				}
			}
	
			
			flee1 = findNextAll("bus");
			if(flee1.size() != 0){
				for(Integer i: flee1){//excluding occupied positions
					if(getMap().get(i).second.equals("detective")){
						continue;
					}else{
						Pair pair = new Pair(i,"bus");
						options.add(pair);
					}
				}
			}
			
			
			flee2 = findNextAll("taxi");
			if(flee2.size() > 0){
				for(int i: flee2){//excluding occupied positions
					if(getMap().get(i).second.equals("detective")){
						continue;
					}else{
						Pair pair = new Pair(i,"taxi");
						options.add(pair);
					}
				}
			}
		
			int max = options.size();
			Pair pos = new Pair(0,"");
			if(max > 0){
				Pair replace = new Pair(this.getPosition(),"vacant");
				getMap().add(this.getPosition(), replace);//freeing current position
				pos = options.get(randInt(0,max - 1));//randomly acquired new position
				this.log.add(pos.second);//writing to log
				this.setPosition(pos.first);//colonizing new node
     			
			}else {
				flee3 = findNextAll("ferry");
				if(flee3.size() > 0 && this.anyconnect > 0){
					for(Integer i: flee3){//excluding occupied positions
						if(getMap().get(i).second.equals("detective")){
							continue;
						}else{
							Pair pair = new Pair(i,"black");
							options.add(pair);
						}
					}
						this.anyconnect-=1;
						max = options.size();
						Pair replace = new Pair(this.getPosition(),"vacant");
						getMap().add(this.getPosition(), replace);//freeing current position
						pos = options.get(randInt(0,max - 1));//randomly acquired new position
						this.log.add("black");//writing to log
						this.setPosition(pos.first);//colonizing a new node via black token
					}
				}
			}
	/*
	 * This function when Mr.X reveals position finds the possible destinations Mr X would have taken given the log table
	 * */
	public ArrayList<Integer> revealPosition(int rounds){
		ArrayList <Integer> result1 = new ArrayList<Integer>();
		String init = "";
		if(rounds < 7){
			if(revealposition != -1){
				switch (rounds) {
		            case 3:
		            	init = log.get(rounds);
		            	result = findNextAll(init,revealposition);
		                break;
		            case 4: 
		            	init = log.get(rounds);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
		            	}
		            	break;
		            case 5:  
		            	init = log.get(5);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
						}
		                break;
		            case 6:  
		            	init = log.get(6);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
						}
		                break;
		            default:
		                     break;
				}
			}
		}else if(rounds >= 7 && rounds < 11 ){
			if(revealposition != -1){
				switch (rounds) {
		            case 7:
		            	init = log.get(rounds);
		            	result = findNextAll(init,revealposition);
		                break;
		            case 8: 
		            	init = log.get(rounds);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
						}
		            	
		                break;
		            case 9:  
		            	init = log.get(rounds);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
						}
		                break;
		            case 10:  
		            	init = log.get(rounds);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
						}
		                break;
		            default:
		                     break;
				}
			}
			
		}else if(rounds >=11 && rounds < 15 ){
			if(revealposition != -1){
				switch (rounds) {
		            case 11:
		            	init = log.get(rounds);
		            	result = findNextAll(init,revealposition);
		                break;
		            case 12: 
		            	init = log.get(rounds);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
						}
		            	
		                break;
		            case 13:  
		            	init = log.get(rounds);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
						}
		                break;
		            case 14:  
		            	init = log.get(rounds);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
						}
		                break;
		            default:
		                     break;
				}
			}
			
		}else if(rounds >=15 && rounds < 19 ){
			if(revealposition != -1){
				switch (rounds) {
		            case 15:
		            	init = log.get(rounds);
		            	result = findNextAll(init,revealposition);
		                break;
		            case 16: 
		            	init = log.get(rounds);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
						}
		            	
		                break;
		            case 17:  
		            	init = log.get(rounds);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
						}
		                break;
		            case 18:  
		            	init = log.get(rounds);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
						}
		                break;
		            default:
		                     break;
				}
			}
			
		}else if(rounds >= 19 && rounds < 22 ){
			if(revealposition != -1){
				switch (rounds) {
		            case 19:
		            	init = log.get(rounds);
		            	result = findNextAll(init,revealposition);
		                break;
		            case 20: 
		            	init = log.get(rounds);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
						}
		            	
		                break;
		            case 21:  
		            	init = log.get(rounds);
		            	for(Integer i : result){
		            		result1 = findNextAll(init,i);
		            		for(Integer ik : result1){
			            		result.add(ik);
			            	}
						}
		                break;
//		            case 6:  
//		            	init = log.get(rounds);
//		            	for(Integer i : result){
//		            		result1 = findNextAll(init,i);
//		            		for(Integer ik : result1){
//			            		result.add(ik);
//			            	}
//						}
//		                break;
		            default:
		                     break;
				}
			}
			
		}
		return result;
	}
}
