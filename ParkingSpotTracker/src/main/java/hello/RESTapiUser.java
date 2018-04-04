package hello;

import java.awt.Point;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
	
import org.apache.coyote.Request;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Database.ExecuteQuery;
import ParkingSpot.ParkingSpot;
import User.UserRequest;
import ParkingSpot.ParkingSpotCalculator;

@RestController
public class RESTapiUser {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @RequestMapping(value = "/parking", method = RequestMethod.POST)
    public ParkingSpot[] parking (@RequestParam Map<String, String> params) {
    	System.out.println("received request: " + params.toString());
    	int x = Integer.parseInt(params.get("x"));
    	int y = Integer.parseInt(params.get("y"));
    	int threshold = Integer.parseInt(params.get("threshold"));
    	System.out.println("x:" + x + " y: " + y + "threshold: " + threshold);
        /*ParkingSpot[] array = new ParkingSpot[2];
        array[0] = new ParkingSpot(1001, new Point(2,3), 10, 20);
        array[1] = new ParkingSpot(1002, new Point(3,4), 20, 40);
        return array;*/
        return new ParkingSpotCalculator().findParkingSpots(new UserRequest(x, y, threshold), getParkingSpots());
    }
    
    public ParkingSpot[] getParkingSpots() {
    	/*ParkingSpot[] array = new ParkingSpot[2];
        array[0] = new ParkingSpot(1001, new Point(2,3), 10, 20);
        array[1] = new ParkingSpot(1002, new Point(3,4), 20, 40);
        return array;
    	*/
    	ParkingSpot array[] = null;
    	ExecuteQuery eq = new ExecuteQuery();
    	BridgeToDatabase btd = new BridgeToDatabase();
    	List<ParkingSpot> list = null;
    	while(true) {
    		if(!eq.isLocked()) {
				try {
					list = btd.parkingSpotService.retrieveAllParkingSpots();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				eq.releaseLock();
    			break;
    		}
    	}
    	if(list == null) {
    		System.out.println("LIST IS NULL");
    		return null;
    	} else {
    		array = new ParkingSpot[list.size()];
    		int i = 0;
    		for(ParkingSpot p : list) {
    			System.out.println(p.toString());
    			array[i] = p;
    			++i;
    		}
    	}
    	return array;
    }
}
