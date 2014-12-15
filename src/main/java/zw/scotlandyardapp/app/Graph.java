package zw.scotlandyardapp.app; 

public class Graph {
	
	private int src;
	private String via;
	private int dst;
	public Graph(int src, String via, int dst){
		this.src = src;
		this.dst = dst;
		this.via = via;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public int getSrc() {
		return src;
	}
	public void setSrc(int src) {
		this.src = src;
	}
	public int getDst() {
		return dst;
	}
	public void setDst(int dst) {
		this.dst = dst;
	}
}
