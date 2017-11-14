package hw4;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;

import org.omg.CORBA.UserException;

/*
 * This class reads the .csv files of trip data and information.
 * This class also creates data structures to store information for further analysis.
 */
public class IndegoBikeTripReader {
	private String fileName; // The trip file.
	private HashMap<Integer, Trip> trips; // A map that contains the trip ID as key and the Trip object as value.
	
	// The constructor of the trip file reader.
	public IndegoBikeTripReader(String s) throws UserException {
		fileName=s;
		trips=new HashMap<>();
		try {
			File inputFile=new File(fileName);
			Scanner in = new Scanner(inputFile);	
			in.nextLine();
			while (in.hasNextLine()) {
				String lCurrent=in.nextLine();
				String[] set=lCurrent.split(",");
				
				// Parse the trip ID.
				int id=Integer.parseInt(set[0]);
//				System.out.println(id);
				
				// Parse the duration.
				int duration=Integer.parseInt(set[1]);
				
				// Parse the year, date, hour and min of start time to int and form a LocalDateTime variable.
				String tp=set[2].substring(1, set[2].length()-1);
				tp=tp.substring(0,10)+"T"+tp.substring(11,tp.length());
				LocalDateTime st=LocalDateTime.parse(tp);
				
				// Parse the year, date, hour and min of end time to int and form a LocalDateTime variable.
				tp=set[3].substring(1, set[3].length()-1);
				tp=tp.substring(0,10)+"T"+tp.substring(11,tp.length());
				LocalDateTime et=LocalDateTime.parse(tp);
				
				// Parse the start station ID.
				int startID=Integer.parseInt(set[4]);
				
				// Parse the end station ID.
				int endID=Integer.parseInt(set[7]);
				
				// Parse the start and end latitudes as well as longitudes. 
				// Trips with invalid information will have end and start longitudes, latitudes with 0s.
				double startLat;
				double startLon;
				double endLat;
				double endLon;
				if(set[5].length()<=2||set[6].length()<=2||set[8].length()<=2||set[9].length()<=2) {
					startLat=0;
					startLon=0;
					endLat=0;
					endLon=0;
//					throw new Exception("No valid longtitues/Latitutes!");
				}
				else {
					startLat=Double.parseDouble(set[5]);
					startLon=Double.parseDouble(set[6]);
					endLat=Double.parseDouble(set[8]);
					endLon=Double.parseDouble(set[9]);
				}
				
				// Parse the bike ID.
				int bikeID;
				if(Character.compare('"',set[10].charAt(0))==0) {
					bikeID=Integer.parseInt(set[10].substring(1, 6));
				}
				else {
					bikeID=Integer.parseInt(set[10]);
				}
				
				// Parse plan duration.
				int planDuration=Integer.parseInt(set[11]);
				
				// Parse the route category.
				String routeCategory=set[12].substring(1, set[12].length()-1);
				
				// Parse the passholder's type.
				String passholder=set[13].substring(1, set[13].length()-1);
				
				// Create a Trip object. Put its ID and the object in the map.
				Trip trip=new Trip(id, duration, st, et, startID, endID, startLat, startLon, 
						endLat, endLon, bikeID,
						planDuration, routeCategory, passholder);
				trips.put(id, trip);		
			}
			in.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// Returns the trips hashMap.
	public HashMap<Integer, Trip> getTrips() {
		return trips;
	}
	
	
}
