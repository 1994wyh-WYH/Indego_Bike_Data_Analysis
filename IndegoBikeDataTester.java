package hw4;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.omg.CORBA.UserException;
/**
 * This class tests the query of the data in terms of questions.
 * This class also tests the write-to-file summary.
 * @author Yihan
 *
 */
public class IndegoBikeDataTester {

	public static void main(String[] args) {
		// Files names.
		String tripFile="/Users/Yihan/Desktop/CIT591/hw4/indego-trips-2017-q3.csv";
		String stationFile="/Users/Yihan/Desktop/CIT591/hw4/indego-stations-2017-10-20.csv";
		// Initialize objects.
		IndegoBikeTripReader tr = null;
		try {
			tr = new IndegoBikeTripReader(tripFile);
		} catch (UserException e) {
			e.printStackTrace();
		}
		IndegoBikeStationReader sr=new IndegoBikeStationReader(stationFile);
		IndegoBikeDataAnalyzer da=new IndegoBikeDataAnalyzer(tr,sr);
		
		// Answers to questions.
		System.out.println(da.countOneWay("One Way")+" One Way trips happened in the third quarter of 2017.");
		System.out.println(da.countActive(2016)+" stations that had a Go-Live Date in 2016 are still Active.");
		System.out.println(da.countPercentageOfSpecificStationEndedTrip("Philadelphia Zoo")+" % of all trips ended at the Philadelphia Zoo.");
		System.out.println("The most Indego30 trips were taken in "+da.getMostPopularMonth("Indego30")+".");
		System.out.println("Bike whose ID is "+da.getIDOfLongestDuration()+" has traveled the most in terms of duration.");

		LocalTime t1=LocalTime.of(0, 0);
		LocalTime t2=LocalTime.of(5, 0);
		System.out.println(da.countPercentageOfTripsDuring(t1, t2) + " % of all trips happened between 12.00am (midnight) and 5am.");
		
		LocalDateTime time=LocalDateTime.of(2017, 9, 15, 7, 0);
		System.out.println("On 9/15/17 at 7:00am, "+da.countNumOfBikesAtATime(time)+" bikes were being used.");
		
		da.printLongestTrip();
		da.countTripsWithOnlyGoLiveStation();
		System.out.println("The percentage of trips with a plan duration of 30 is: "+da.countPercentageOfPlanDurationOf(30)+" %.");
		
		// Summary of data.
		IndegoBikeDataSummary summary=new IndegoBikeDataSummary(tr,sr);
		summary.writeToFile();
		
		// From here are the EC questions.
//		summary.printCloseStations();
		System.out.println("The least popular station is: "+summary.findLeastPopularEnd().getName()+".");
		System.out.println("The number of unused station(s) is: "+summary.countUnusedStations()+".");
	}

}
