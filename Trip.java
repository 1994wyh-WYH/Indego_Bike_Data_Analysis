package hw4;

import java.time.LocalDateTime;

/**
 * This class represents the trip object.
 * @author Yihan
 */
public class Trip {
	private int id; // Trip ID.
	private int duration; // Duration of the trip.
	private LocalDateTime startTime; // Trip start time.
	private LocalDateTime endTime; // Trip end time.
	private int startStation; // Start station ID.
	private int endStation; // End station ID.
	private double startLat; // Trip start location latitude.
	private double startLon; // Trip start location longitude.
	private double endLat; // Trip end location latitude.
	private double endLon; // Trip end location longitude.
	private int bikeID; //Bike ID.
	private int planDuration; // Trip plan duration.
	private String routeCategory; // Trip route category.
	private String passholderType; // Passholder's type.
	
	/**
	 *  Constructor of a Trip object.
	 * @param id, trip ID.
	 * @param duration, trip duration.
	 * @param startTime, trip start time.
	 * @param endTime, trip end time.
	 * @param startStation, trip start station ID.
	 * @param endStation, trip end station ID.
	 * @param startLat, trip start station latitude.
	 * @param startLon, trip start station longitude.
	 * @param endLat, trip end station latitude.
	 * @param endLon, trip end station longitude.
	 * @param bikeID, bike ID.
	 * @param planDuration, trip plan duration.
	 * @param routeCategory, trip route category.
	 * @param passholderType, passholder's type.
	 */
	public Trip(int id, int duration, LocalDateTime startTime, LocalDateTime endTime, int startStation, int endStation,
			double startLat, double startLon, double endLat, double endLon, int bikeID, int planDuration,
			String routeCategory, String passholderType) {
		this.id = id;
		this.duration = duration;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startStation = startStation;
		this.endStation = endStation;
		this.startLat = startLat;
		this.startLon = startLon;
		this.endLat = endLat;
		this.endLon = endLon;
		this.bikeID = bikeID;
		this.planDuration = planDuration;
		this.routeCategory = routeCategory;
		this.passholderType = passholderType;
	}
	
	/**
	 *  Getter of trip ID.
	 * @return trip ID.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 *  This method returns trip duration.
	 * @return trip duration.
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 *  This method returns trip start time.
	 * @return trip start time.
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	/**
	 *  This method returns trip end time.
	 * @return trip end time.
	 */
	public LocalDateTime getEndTime() {
		return endTime;
	}
	
	/** 
	 * This method returns trip start station ID.
	 * @return trip start station ID.
	 */
	public int getStartStation() {
		return startStation;
	}
	
	/**
	 * This method returns trip end station ID.
	 * @return trip end station ID.
	 */
	public int getEndStation() {
		return endStation;
	}
	
	/**
	 *  This method returns trip start latitude.
	 * @return trip start latitude.
	 */
	public double getStartLat() {
		return startLat;
	}
	
	/**
	 *  This method returns trip start longitude.
	 * @return trip start longitude.
	 */
	public double getStartLon() {
		return startLon;
	}
	
	/**
	 * This method returns trip end latitude.
	 * @return trip end latitude.
	 */
	public double getEndLat() {
		return endLat;
	}
	
	/**
	 * This method returns trip end longitude.
	 * @return trip end longitude.
	 */
	public double getEndLon() {
		return endLon;
	}
	
	/**
	 * This method returns the bike ID.
	 * @return the bike ID.
	 */
	public int getBikeID() {
		return bikeID;
	}
	
	/**
	 * This method returns the trip plan duration.
	 * @return the trip plan duration.
	 */
	public int getPlanDuration() {
		return planDuration;
	}
	
	/**
	 * This method returns the trip route category.
	 * @return the trip route category.
	 */
	public String getRouteCategory() {
		return routeCategory;
	}
	
	/**
	 * This method returns the passholder's type.
	 * @return the passholder's type.
	 */
	public String getPassholderType() {
		return passholderType;
	}
	
	
	
}
