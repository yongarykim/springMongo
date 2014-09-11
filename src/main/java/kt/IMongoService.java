package kt;

import java.util.List;

import org.springframework.data.geo.GeoResults;

public interface IMongoService {
	public List<Spot> getSpots(String user);
	public void putSpot(double x, double y, String user);
	
	public GeoResults<Spot> getRange( double x, double y, double radius );
	
	public Spot getSpot(double x, double y);
	public void addFv(int f, int v);
	
}
