package hw4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class writes a file contains the summary of data grouped by station.
 * @author Yihan
 *
 */
public class IndegoBikeDataSummary {
	private HashMap<Integer, Station> stations;
	// A hashing that connects a station and all trips that have the station as start station.
	private HashMap<Station, ArrayList<Trip>> srelation; 
	
	// A hashing that connects a station and all trips that have the station as end station.
	private HashMap<Station, ArrayList<Trip>> erelation;
	
	/**
	 *  Constructor of the data summary writer.
	 *  @param tr, an IndegoBikeTripReader object.
	 *  @param sr, an IndegoBikeStationReader object.
	 */	
	public IndegoBikeDataSummary(IndegoBikeTripReader tr, IndegoBikeStationReader sr) {
		HashMap<Integer, Trip> trips = tr.getTrips();
		stations = sr.getStations();
		srelation=new HashMap<>(); 
		erelation=new HashMap<>(); 
		for(Trip t:trips.values()) {
			int ts=t.getStartStation();
			int te=t.getEndStation();
			for(Station s: stations.values()) {
				int sid=s.getId();
				// Check if the station is involved in either trip start station or end station.
				// If so, put the tuple in the corresponding HashMap.
				if(sid==ts) {
					if(srelation.containsKey(s)) {
						ArrayList<Trip> nv=srelation.get(s);
						nv.add(t);
						srelation.put(s, nv);
					}
					else {
						ArrayList<Trip> v=new ArrayList<>();
						v.add(t);
						srelation.put(s,v);
					}
				}
				else if(sid==te) {
					if(erelation.containsKey(s)) {
						ArrayList<Trip> nv=erelation.get(s);
						nv.add(t);
						erelation.put(s, nv);
					}
					else {
						ArrayList<Trip> v=new ArrayList<>();
						v.add(t);
						erelation.put(s,v);
					}
				}
				else {
					continue;
				}
			}
		}
	}
	
	/**
	 * This method counts the number of trips that involve the given station.
	 * @param s, the given station.
	 * @return an int number of trips that involve the given station.
	 */
	private int countTripNum(Station s) {
		// Count the number of trips having the given station as start and end, respectively.
		int sn=0;
		int en=0;
		if(srelation.containsKey(s)) {
			sn=srelation.get(s).size();
		}
		if(erelation.containsKey(s)) {
			en=erelation.get(s).size();
		}
		return sn+en;
	}
	
	/**
	 * This method calculates the average duration of the trips starting from the given station.
	 * @param s, the given station.
	 * @return a double of the average duration of the trips starting from the given station.
	 */
	private double calcAvgDuration(Station s) {
		if(!srelation.containsKey(s)) {
			return 0;
		}
		ArrayList<Trip> trips=srelation.get(s);
		double sum=0;
		for(Trip t:trips) {
			sum+=t.getDuration();
		}
		double ret=sum/trips.size();
		return ret;
	}
	
	/**
	 * This method calculates the average Euclidean distance of the trips starting from the given station.
	 * @param s, the given station.
	 * @return a double of the average Euclidean distance of the trips starting from the given station.
	 */
	private double calcAvgEuclideanDistance(Station s) {
		if(!srelation.containsKey(s)) {
			return 0;
		}
		ArrayList<Trip> trips=srelation.get(s);
		double sum=0;
		// Uses EuclideanDistance() method in IndegoBikeDataAnalyzer class to calculate the Euclidean distance of trips starting from this station.
		for(Trip t:trips) {
			double sLat=t.getStartLat();
			double sLon=t.getStartLon();
			double eLat=t.getEndLat();
			double eLon=t.getEndLon();
			sum+=IndegoBikeDataAnalyzer.euclideanDistance(sLat,sLon,eLat,eLon);
		}
		double ret=sum/trips.size();
		return ret;
	}
	
	
	/**
	 * This method searches the maximum duration of trips starting from the given station.
	 * @param s, the given station.
	 * @return an int of maximum duration of trips starting from the given station.
	 */
	private int findMaxDuration(Station s) {
		if(!srelation.containsKey(s)) {
			return 0;
		}
		int max=0;
		ArrayList<Trip> trips=srelation.get(s);
		for(Trip t:trips) {
			int duration = t.getDuration();
			if(duration>max) {
				max=duration;
			}
		}
		return max;
	}
	
	/**
	 * This method searches the maximum Euclidean distance of trips starting from the given station.
	 * @param s, the given station.
	 * @return a double of maximum Euclidean distance of trips starting from the given station.
	 */
	private double findMaxEuclideanDistance(Station s) {
		if(!srelation.containsKey(s)) {
			return 0;
		}
		double max=0;
		ArrayList<Trip> trips=srelation.get(s);
		for(Trip t:trips) {
			double sLat=t.getStartLat();
			double sLon=t.getStartLon();
			double eLat=t.getEndLat();
			double eLon=t.getEndLon();
			double ed = IndegoBikeDataAnalyzer.euclideanDistance(sLat,sLon,eLat,eLon);
			if(ed>max) {
				max=ed;
			}
		}
		return max;
	}
	
	/**
	 * This method calculates the percentage of one way trips of all trips involving the given station.
	 * @param s , the given station.
	 * @return a double of the percentage of one way trips of all trips involving the given station.
	 */
	private double calcPercentageOfOneWay(Station s) {
		if(!srelation.containsKey(s)) {
			return 0;
		}
		double c=0; // Counter.
		ArrayList<Trip> st=srelation.get(s);
		ArrayList<Trip> et=erelation.get(s);
		double total=st.size()+et.size();
		for(Trip t:st) {
			if(t.getRouteCategory().equals("One Way")) {
				c++;
			}
		}
		for(Trip t:et) {
			if(t.getRouteCategory().equals("One Way")) {
				c++;
			}
		}
		return c/total*100;
	}
	
	/**
	 * This method calculates the difference between trips starting from the given station and trips ending at the given station.
	 * @param s, the given station.
	 * @return an int of the difference between trips starting from the given station and trips ending at the given station.
	 */
	private int calcDiffBetweenStartAtAndEndAt(Station s) {
		if(!srelation.containsKey(s)) {
			return 0;
		}
		// Calculate the difference by subtracting the sizes of two hashed relations.
		ArrayList<Trip> st=srelation.get(s);
		ArrayList<Trip> et=erelation.get(s);
		return st.size()-et.size();
	}
	
	/**
	 * This method writes the summary into a file.
	 */
	public void writeToFile() {
		try {
			PrintWriter out = new PrintWriter("stationSummary.txt");
			for(Station s:stations.values()) {
				out.println(s.getId()+", "+s.getName()+", "+countTripNum(s)+", "+calcAvgDuration(s)
				+", "+calcAvgEuclideanDistance(s)+", "+findMaxDuration(s)+", "+findMaxEuclideanDistance(s)+", "
				+calcPercentageOfOneWay(s)+", "+calcDiffBetweenStartAtAndEndAt(s));
				out.flush();
			}
			out.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// From here are the methods for EC.
	
	/**
	 * This method prints out all pairs of stations that are considered close to each other.
	 */
	public void printCloseStations(){
		System.out.println("The close station pairs are shown as follows:");
		// Loop through stations and find another station that is close enough to it.
		for(Station s1:stations.values()) {
			// Use trip start/end location's longitudes/latitudes as the station's longitude/latitude.
			double sLat1;
			double sLon1;
			double eLat1;
			double eLon1;
			if(srelation.containsKey(s1)) {
				sLat1=srelation.get(s1).get(0).getStartLat();
				sLon1=srelation.get(s1).get(0).getStartLon();
				eLat1=srelation.get(s1).get(0).getEndLat();
				eLon1=srelation.get(s1).get(0).getEndLon();
			}
			else if(erelation.containsKey(s1)) {
				sLat1=erelation.get(s1).get(0).getStartLat();
				sLon1=erelation.get(s1).get(0).getStartLon();
				eLat1=erelation.get(s1).get(0).getEndLat();
				eLon1=erelation.get(s1).get(0).getEndLon();
			}
			else {
				continue;
			}
			for(Station s2:stations.values()) {
				// Skip the same station in the second loop.
				if(s1.getId()==s2.getId()) {
					continue;
				}
				double sLat2;
				double sLon2;
				double eLat2;
				double eLon2;
				if(srelation.containsKey(s2)) {
					sLat2=srelation.get(s2).get(0).getStartLat();
					sLon2=srelation.get(s2).get(0).getStartLon();
					eLat2=srelation.get(s2).get(0).getEndLat();
					eLon2=srelation.get(s2).get(0).getEndLon();
				}
				else if(erelation.containsKey(s2)) {
					sLat2=erelation.get(s2).get(0).getStartLat();
					sLon2=erelation.get(s2).get(0).getStartLon();
					eLat2=erelation.get(s2).get(0).getEndLat();
					eLon2=erelation.get(s2).get(0).getEndLon();
				}
				else {
					continue;
				}
				// Print out the close stations.
				if(isClose(sLat1, sLon1, eLat1, eLon1, sLat2, sLon2, eLat2, eLon2)) {
					System.out.println(s1.getName()+", "+s2.getName());
				}
			}
		}
		
	}
	
	/**
	 * This method compares two points and determines whether they are close enough.
	 * @param sLat1, latitude of the first point start station.
	 * @param sLon1, longitude of the first point start station.
	 * @param eLat1, latitude of the first point end station.
	 * @param eLon1, longitude of the first point end station.
	 * @param sLat2, latitude of the second point start station.
	 * @param sLon2, longitude of the second point start station.
	 * @param eLat2, latitude of the second point end station.
	 * @param eLon2, longitude of the second point end station.
	 * @return a boolean of whether the two points are close enough.
	 */
	public boolean isClose(double sLat1, double sLon1, double eLat1, double eLon1, 
			double sLat2, double sLon2, double eLat2, double eLon2) {
		double diffLat=Math.abs(sLat1-sLat2);
		double diffLon=Math.abs(eLon1-sLon2);
		double diff=(diffLat+diffLon)/2;
		if(diff<0.02) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * This method searches the least popular end station.
	 * @return a Station that is least popular.
	 */
	public Station findLeastPopularEnd() {
		int l=Integer.MAX_VALUE;
		Station ret = null;
		for(Station s:stations.values()) {
			if(erelation.containsKey(s)) {
				if(erelation.get(s).size()<l) {
					ret=s;
					l=erelation.get(s).size();
				}
			}
		}
		return ret;
	}
	
	/**
	 * This method counts stations that were not involved by any trips.
	 * @return the number of unused stations.
	 */
	public int countUnusedStations() {
		int c=0; // Counter.
		for(Station s:stations.values()) {
			if(!erelation.containsKey(s)) {
				if(!srelation.containsKey(s)) {
					c++;
				}
			}
		}
		return c;
	}
}
