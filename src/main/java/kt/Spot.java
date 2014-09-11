package kt;

import java.util.LinkedList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document  //(collection = "test")
public class Spot {
	
	@Id
	private String id;
	
	private double[] location;    //{x,y}
	private String user;
	private LinkedList<int[]> fv; //List of {floor,value} : always size 2.
	
	//=============================================
	public Spot() {
		this.location = new double[2];
	}
	
	public Spot(double x, double y, String user) {
		this();
		this.location[0]=x;
		this.location[1]=y;
		this.user = user;
		this.fv=null;
	}

	


	@Override
	public String toString() {
		return "Spot [id=" + id + ", location={" + location[0]+"," + location[1] + "}"
				+ ", user=" + user + "]";
	}
	
	
	
	
	///////////////////////////getter setter//////////////////////
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public double[] getLocation() {
		return location;
	}



	public void setLocation(double[] location) {
		this.location = location;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}

	public LinkedList<int[]> getFv() {
		return fv;
	}

	public void setFv(LinkedList<int[]> fv) {
		this.fv = fv;
	}
	
	///
	public void addFv(int f, int v){
		
		int[] tuple = new int[2];
		tuple[0]=f; tuple[1]=v;
			
		if(this.fv==null)
			this.fv=new LinkedList<int[]>() ;
			
		this.fv.add(tuple);
		
	}
	
}
