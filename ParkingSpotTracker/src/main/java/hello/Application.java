package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Database.ParkingSpotService;
import ParkingSpotServer.Test;

@SpringBootApplication
public class Application {
	
    public static void main(String[] args) {
    	if(false) {
    		ParkingSpotService parkingSpotService = new ParkingSpotService();
    		BridgeToDatabase btd = new BridgeToDatabase();
    		btd.parkingSpotService = parkingSpotService;
    		SpringApplication.run(Application.class, args);
    	} else {
    		ParkingSpotService parkingSpotService = new ParkingSpotService();
    		BridgeToDatabase btd = new BridgeToDatabase();
    		btd.parkingSpotService = parkingSpotService;
    		SpringApplication.run(Application.class, args);
    		//new Test().main(null);
    		//new UserTest().main(null);
    		new Thread()
    		{
    		    public void run() {
    		    	new Test().main(null);
    		    }
    		}.start();
    		new Thread()
    		{
    		    public void run() {
    		    	new UserTest().main(null);
    		    }
    		}.start();
    	}
    }
}
