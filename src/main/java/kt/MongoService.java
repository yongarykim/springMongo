package kt;



import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
public class MongoService implements IMongoService{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	//@Resource
	//private UserMapper userMapper;
	
	public List<Spot> getSpots(String user){
		
		//When Autowired: UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//return userMapper.getUser(name); 
		
		//Spot spot=mongoTemplate.findOne(new Query(where("user").is(user)), Spot.class);
		List<Spot> spot=mongoTemplate.find(new Query(where("user").is(user)), Spot.class);
		return spot;
	}

	public void putSpot(double x, double y, String user) {
		
		Spot spot=new Spot(x,y,user );
		mongoTemplate.save(spot);
	}

	
	
	public GeoResults<Spot> getRange(double x, double y, double radius) {
		
		NearQuery query = NearQuery.near(x,y).maxDistance(radius); //.query(regularQuery)
					      //.spherical(true).maxDistance(maxDistance,Metrics.MILES).distanceMultiplier(Metrics.MILES).query(regularQuery);//maxDistance(new Distance(radius,Metrics.MILES));
		
		GeoResults<Spot> spot=mongoTemplate.geoNear( query, Spot.class);
		return spot;
	}

	public Spot getSpot(double x, double y) {
		
		double[] pos= new double[2];
		pos[0]=0; pos[1]=0;
		
		Query query = new Query( where("location").is(pos) );
		Spot spot=mongoTemplate.findOne(query, Spot.class);
		
		return spot;
	}

	public void addFv(int f, int v) {
		
		Spot spot = getSpot(0,0);
		
		spot.addFv(f,v);
		mongoTemplate.save(spot);
	}

	
	
	
	
}
