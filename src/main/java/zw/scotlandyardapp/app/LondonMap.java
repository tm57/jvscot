package zw.scotlandyardapp.app;

public class LondonMap{
	private int xcoord;
	private int ycoord;
	
	public LondonMap(int xcoord,int ycoord){
		this.xcoord = xcoord;
		this.ycoord = ycoord;		
	}
	
	public void setX(int xcoord){
		this.xcoord = xcoord;
	}
	
	public void setY(int ycoord){
		this.ycoord = ycoord;
	}
	
	public int getX(){
		return this.xcoord;
	}
	
	public int getY(){
		return this.ycoord;
	}
}