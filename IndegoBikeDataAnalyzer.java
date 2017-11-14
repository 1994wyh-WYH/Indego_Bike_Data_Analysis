package hw4;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class deals with the customized questions of data analysis.
 * @author Yihan
 */
public class IndegoBikeDataAnalyzer {
	private HashMap<Integer, Trip> trips;
	private HashMap<Integer, Station> stations;
	
	/**
	 *  Constructor of the analyzer.
	 *  @param tr, an IndegoBikeTripReader object.
	 *  @param sr, an IndegoBikeStationReader object.
	 */
	
	public IndegoBikeDataAnalyzer(IndegoBikeTripReader tr, IndegoBikeStationReader sr) {
		trips=tr.getTrips();
		stations=sr.getStations();
	}
	
	/**
	 *  This method gives answer to how many One Way trips there were in the third quarter of 2017.
	 * @param category
	 * @return number of one way trips
	 */
	public int countOneWay(String category) {
		int c=0; // The counter.
		for(Trip t:trips.values()) {
			if(t.getRouteCategory().equals(category)){
				c++;
			}
		}
		return c;
	}
	
	/**
	 *  This method gives answer to how many stations that had a Go-Live Date in 2016 are still Active.
	 * @param year
	 * @return number of active stations that have a go-live date.
	 */
	public int countActive(int year) {
		int c=0; // The counter.
		// If it does have a go-live date and is active now, increase c by 1.
		for(Station s:stations.values()) {
			if(s.getGoLiveDate().getYear()==year) {
				if(s.isActive()) {
					c++;
				}
			}
		}
		return c;
	}
	
	/**
	 *  This method gives answer to what percentage of trips ended at a specific station.
	 * @param sname, station name.
	 * @return percentage of trips ended at the given station.
	 */
	public double countPercentageOfSpecificStationEndedTrip(String sname) {
		double c=0; // The counter of trips.
		for(Trip t: trips.values()) {
			int endS=t.getEndStation();
			if(stations.containsKey(endS)) {
				Station s=stations.get(endS);
				if(s.getName().equals(sname)) {
					c++;
				}
			}
			else {
				continue;
			}
		}
		double ratio=c/trips.size(); // Counted trips divided by overall number of trips.
		return ratio*100;
	}
	
	/**
	 *  This method gives answer to in which month the most Indego30 trips were taken.
	 * @param type, passholder's type for which the most popular month is searched.
	 * @return a Month variable indicating in which month did most trips of a specific kind happen. 
	 */
	public Month getMostPopularMonth(String type) {
		int[] count={0,0,0,0,0,0,0,0,0,0,0,0}; // Initialize an array to hold 
		for(Trip t: trips.values()) {
			if(t.getPassholderType().equals(type)) {
				// Check and count number of Indego30 trips in each month.
				if(t.getStartTime().getMonth()==Month.JANUARY) {
					count[0]++;
				}
				else if(t.getStartTime().getMonth()==Month.FEBRUARY) {
					count[1]++;
				}
				else if(t.getStartTime().getMonth()==Month.MARCH) {
					count[2]++;
				}
				else if(t.getStartTime().getMonth()==Month.APRIL) {
					count[3]++;
				}
				else if(t.getStartTime().getMonth()==Month.MAY) {
					count[4]++;
				}
				else if(t.getStartTime().getMonth()==Month.JUNE) {
					count[5]++;
				}
				else if(t.getStartTime().getMonth()==Month.JULY) {
					count[6]++;
				}
				else if(t.getStartTime().getMonth()==Month.AUGUST) {
					count[7]++;
				}
				else if(t.getStartTime().getMonth()==Month.SEPTEMBER) {
					count[8]++;
				}
				else if(t.getStartTime().getMonth()==Month.OCTOBER) {
					count[9]++;
				}
				else if(t.getStartTime().getMonth()==Month.NOVEMBER) {
					count[10]++;
				}
				else{
					count[11]++;
				}
			}
		}
		// Find the largest number among the array elements.
		int max=count[0];
		int index=0;
		for(int i=1; i<count.length; i++) {
			if(count[i]>max) {
				max=count[i];
				index=i;
			}
		}
		Month ret=Month.of(index+1);
		return ret;
	}
	
	/**
	 *  This method answers the question of what is the ID of the bike that has traveled the most in terms of duration.
	 * @return the ID of the longest trip in terms of duration.
	 */
	public int getIDOfLongestDuration() {
		int bikeID=0;
		int duration=0;
		for(Trip t: trips.values()) {
			if(t.getDuration()>duration) {
				bikeID=t.getBikeID();
			}
		}
		return bikeID;
	}
	
	/**
	 *  This methods answers what percentage of trips happened between a specific period of time.
	 * @param t1, start time.
	 * @param t2, end time.
	 * @return ratio of trips happened during a given time period.
	 */
	public double countPercentageOfTripsDuring(LocalTime t1, LocalTime t2) {
		double c=0; // Counter.
		for(Trip t:trips.values()) {
			LocalTime st=t.getStartTime().toLocalTime();
			LocalTime et=t.getEndTime().toLocalTime();
			// Using total minutes to compare two times.
			if(st.getMinute()+st.getHour()*60 > t1.getMinute()+t1.getHour()*60 && st.getMinute()+st.getHour()*60 < t2.getMinute()+t2.getHour()*60) {
				if(et.getMinute()+et.getHour()*60 > t1.getMinute()+t1.getHour()*60 && et.getMinute()+et.getHour()*60 < t2.getMinute()+t2.getHour()*60) {
					c++;
				}
			}
		}
		double ret=c/trips.size();
		return ret*100;
	}
	
	/**
	 *  This method answers the question of how many bikes were being used at a specific time.
	 *  @param time, the time spot at which number of bikes being used is counted.
	 *  @return counted number of bikes being used at a given time.
	 */
	public int countNumOfBikesAtATime(LocalDateTime time) {
		int c=0; //Counter.
		// Because each bike can only be used by one trip at a certain time, counting bikes in this case is equivalent to counting trips.
		for(Trip t:trips.values()) {
			if(t.getStartTime().compareTo(time)<=0 && t.getEndTime().compareTo(time)>=0) {
				c++;
			}
		}
		return c;
	}
	
	/**
	 * This method calculates the Euclidean distance between the trip start location and end location.
	 * @param sLat, latitude of the first point.
	 * @param sLon, longitude of the first point.
	 * @param eLat, latitude of the second point.
	 * @param eLon, longitude of the second point.
	 * @return calculated Euclidean distance between two points.
	 */
	public static double euclideanDistance(double sLat, double sLon, double eLat, double eLon) {
		double diffLat=eLat-sLat;
		double diffLon=eLon-sLon;
		double ret=Math.sqrt(Math.pow(diffLat, 2)+Math.pow(diffLon, 2));
		return ret;
	}
	
	/**
	 * This method prints all the trip information for the longest trip by distance.
	 */
	public void printLongestTrip() {
		double maxDis=0;
		Trip longestTrip=null;
		for(Trip t:trips.values()) {
			if(euclideanDistance(t.getStartLat(),t.getStartLon(),t.getEndLat(),t.getEndLon())>maxDis) {
				longestTrip=t;
			}
		}
		System.out.println("The ID of the longest trip is: "+longestTrip.getId());
		System.out.println("The duration of the longest trip is: "+longestTrip.getDuration());
		System.out.println("The start time of the longest trip is: "+longestTrip.getStartTime());
		System.out.println("The end time of the longest trip is: "+longestTrip.getEndTime());
		System.out.println("The start station ID of the longest trip is: "+longestTrip.getStartStation());
		System.out.println("The start location latitude of the longest trip is: "+longestTrip.getStartLat());
		System.out.println("The start location longitude of the longest trip is: "+longestTrip.getStartLon());
		System.out.println("The end location ID of the longest trip is: "+longestTrip.getEndStation());
		System.out.println("The end location latitude of the longest trip is: "+longestTrip.getEndLat());
		System.out.println("The end location longitude of the longest trip is: "+longestTrip.getEndLon());
		System.out.println("The bike ID of the longest trip is: "+longestTrip.getBikeID());
		System.out.println("The plan duration of the longest trip is: "+longestTrip.getPlanDuration());
		System.out.println("The route category of the longest trip is: "+longestTrip.getRouteCategory());
		System.out.println("The passholder type of the longest trip is: "+longestTrip.getPassholderType());
		
	}
	
	/**
	 * This method prints the total count of the number of trips that involved a station which was the only station to go live on its respective go-live date.
	 */
	public void countTripsWithOnlyGoLiveStation() {
		// Create a hashing between go-live dates and the stations that has it to be its go-live date.
		HashMap<LocalDate, ArrayList<Integer>> dates=new HashMap<>(); 
		for(Station s:stations.values()) {
			if(dates.containsKey(s.getGoLiveDate())) {
				ArrayList<Integer> nsid=dates.get(s.getGoLiveDate());
				nsid.add(s.getId());
				dates.put(s.getGoLiveDate(), nsid);
			}
			else {
				ArrayList<Integer> v=new ArrayList<>();
				v.add(s.getId());
				dates.put(s.getGoLiveDate(), v);
			}
		}
		// Select station IDs of which station is the only station to go on its go-live date.
		ArrayList<Integer> onlyStation=new ArrayList<>();
		for(ArrayList<Integer> v:dates.values()) {
			if(v.size()==1) {
				Integer sid=(Integer) v.get(0);
				onlyStation.add(sid);
			}
		}
		int c=0; // The counter of trips that involve the selected stations.
		for(Trip t:trips.values()) {
			for(Integer sid:onlyStation) {
				Integer ss=(Integer)(t.getStartStation());
				Integer es=(Integer)(t.getEndStation());
				if(ss.equals(sid) || es.equals(sid)) {
					c++;
				}
			}
		}
		// Print the number of selected trips.
		System.out.println("The total count of the number of trips that involved a station which was the only station to go live on its respective go-live date is: "+c);
	}
	
	/**
	 * This method returns the percentage of trips having a plan duration of 30.
	 * @param duration, the given plan duration to search.
	 * @return percentage of trips having a plan duration of 30.
	 */
	public double countPercentageOfPlanDurationOf(int duration) {
		double c=0; // The counter.
		for(Trip t:trips.values()) {
			if(t.getPlanDuration()==duration) {
				c++;
			}
		}
		double ratio=c/trips.size();
		return ratio*100;
	}
}
