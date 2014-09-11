package kt;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.geo.GeoResults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MongoController {

	@Resource
	private IMongoService mongoService;
	
	
	@RequestMapping("/list.do")
	public String listDo(HttpServletRequest request, @RequestParam String user){
		
		List<Spot> sp = mongoService.getSpots(user);
		String spStr = sp.toString();
		request.setAttribute("result", "user:" + user + ", toString():"+spStr);
		return "hello";  //WEB-INF/view/hello.jsp 
	}

	@RequestMapping("/add.do")
	public String addDo(HttpServletRequest request, @RequestParam double x, @RequestParam double y, @RequestParam String user){
		
		mongoService.putSpot(x, y, user);
		request.setAttribute("message", "add OK");
		return "showMessage";  //WEB-INF/view/hello.jsp 
	}
	
	@RequestMapping("/range.do")
	public String rangeDo(HttpServletRequest request, @RequestParam double radius){
		
		GeoResults<Spot> sp = mongoService.getRange(0,0,radius);
		String spStr = sp.toString();
		request.setAttribute("result", "radius:" + radius + ", toString():"+spStr);
		return "hello";  //WEB-INF/view/hello.jsp 
	}
	
	@RequestMapping("/addfv.do")
	public String addfvdo(HttpServletRequest request, @RequestParam int f, @RequestParam int v){
		
		mongoService.addFv(f, v);
		request.setAttribute("message", "add Fv OK"); 
		return "showMessage";
	}
	
	
	@RequestMapping("/go.do")     //  go.do?url=front  로 이용.  
	public String goDo(@RequestParam String url){
		return url;
	}
	
}
