package zw.scotlandyardapp.app; 
/*
 * a simple class to define a node and its state,who is occupying the node
 * example node 14 "detective" means node 14 is currently occupied by detective
 * node 14 "vacant" means detective has left node 14 etc 
 * */
public class Pair {
	public int first;
	public String second;
	public Pair(int first,String second){
		this.first = first;
		this.second = second;
	}
}
