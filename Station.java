package hw4;

import java.time.LocalDate;
/**
 * This class represents the station object.
 * @author Yihan
 */
public class Station {

	private int id; // Station ID.
	private String name; //Name of the station.
	private LocalDate goLiveDate; //Date of the go-live date of the station.
	private boolean status; // A boolean variable to indicate whether active or not. Active->true. Inactive->false.
	
	/**
	 * Constructor of a Station object.
	 * @param id, station ID.
	 * @param name, station name.
	 * @param goLiveDate, station go-live date.
	 * @param status, station status.
	 */
	public Station(int id, String name, LocalDate goLiveDate, boolean status) {
		this.id = id;
		this.name = name;
		this.goLiveDate = goLiveDate;
		this.status = status;
	}
	
	/**
	 *  Getter of the station ID.
	 * @return the station ID.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 *  Getter of the station name.
	 * @return the station name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 *  Getter of the go-live date of the station.
	 * @return the go-live date of the station.
	 */
	public LocalDate getGoLiveDate() {
		return goLiveDate;
	}
	
	/**
	 *  This method returns the status parameter.
	 * @return the status parameter.
	 */
	public boolean isActive() {
		return status;
	}
	
}
