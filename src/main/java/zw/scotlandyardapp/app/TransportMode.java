package zw.scotlandyardapp.app; 

public class TransportMode {
	private int id1,id2;
	private String mode;	
	public TransportMode(String mode,int id1, int id2){
		this.setMode(mode);
		this.setId1(id1);
		this.setId2(id2);
	}

	public int getId1() {
		return id1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public int findNext(int current,String input){
		if(this.id2 == current && input.equals(this.mode)){
		   return this.id1;
		}else if(this.id1 == current && input.equals(this.mode)){
		   return this.id2;
		}else{
			return 0;
		}
		
	}	
	
}
