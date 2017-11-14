package hw4;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class reads the .csv files of station data and information.
 * This class also creates data structures to store information for further analysis.
 * @author Yihan
 */
public class IndegoBikeStationReader {
	private String fileName; // The station data file.
	private HashMap<Integer, Station> stations; // A map that contains the station ID as key and Station object as value.
	
	/**
	 *  The constructor of the trip file reader.
	 * @param s, a string of the file name.
	 */
	public IndegoBikeStationReader(String s) {
		fileName=s;
		stations=new HashMap<>();
		try {
			File inputFile=new File(fileName);
			Scanner in = new Scanner(inputFile);	
			in.nextLine();
			while(in.hasNextLine()) {
				String lCurrent=in.nextLine();
				String[] set=lCurrent.split(",");
				
				// Parse the station ID.
				int id=Integer.parseInt(set[0]);
				
				// Parse the station name.
				String name;
				// Need to handle cases where there is a "," in the station name!
				if(set.length<5) {
					name=set[1];
				}
				else {
					name=set[1].substring(1, set[1].length())
							+","+set[2].substring(0, set[2].length()-1);
				}
				
				// Parse go-live date and active status.
				
				LocalDate goLiveDate;
				boolean status;
				// Similarly, case should be different when the set length is 1 larger after parsing, caused by the "," in station name.
				if(set.length>4) {
					// Cases are different when the formats(length) of the date are different.
					if(set[3].length()==9) {
						int year=Integer.parseInt(set[3].substring(5,set[3].length()));
						int month=Integer.parseInt(set[3].substring(0, 1));
						int day=Integer.parseInt(set[3].substring(2, 4));
						goLiveDate=LocalDate.of(year, month, day);
					}
					else if(set[3].length()==10) {
						int year=Integer.parseInt(set[3].substring(6,set[3].length()));
						int month=Integer.parseInt(set[3].substring(0, 2));
						int day=Integer.parseInt(set[3].substring(3, 5));
						goLiveDate=LocalDate.of(year, month, day);
					}
					else {
						int year=Integer.parseInt(set[3].substring(4,set[3].length()));
						int month=Integer.parseInt(set[3].substring(0, 1));
						int day=Integer.parseInt(set[3].substring(2, 3));
						goLiveDate=LocalDate.of(year, month, day);
					}
					// Extract the active or not status.
					if(set[4].equals("Active")) {
						status=true;
					}
					else {
						status=false;
					}	
				}
				
				else {
					if(set[2].length()==9) {
						int year=Integer.parseInt(set[2].substring(5,set[2].length()));
						int month=Integer.parseInt(set[2].substring(0, 1));
						int day=Integer.parseInt(set[2].substring(2, 4));
						goLiveDate=LocalDate.of(year, month, day);
					}
					else if(set[2].length()==10) {
						int year=Integer.parseInt(set[2].substring(6,set[2].length()));
						int month=Integer.parseInt(set[2].substring(0, 2));
						int day=Integer.parseInt(set[2].substring(3, 5));
						goLiveDate=LocalDate.of(year, month, day);
					}
					else {
						int year=Integer.parseInt(set[2].substring(4,set[2].length()));
						int month=Integer.parseInt(set[2].substring(0, 1));
						int day=Integer.parseInt(set[2].substring(2, 3));
						goLiveDate=LocalDate.of(year, month, day);
					}
					// Extract the active or not status.
					if(set[3].equals("Active")) {
						status=true;
					}
					else {
						status=false;
					}
				}
				
				// Create a Station object and put its id as well as the object in the hashMap.
				Station station=new Station(id, name, goLiveDate, status);
				stations.put(id, station);
			}
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *  This method returns the stations hashMap.
	 * @return the stations hashMap.
	 */
	public HashMap<Integer, Station> getStations() {
		return stations;
	}
	
}
