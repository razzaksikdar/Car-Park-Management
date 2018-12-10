
package polestar.software.exercise.model;

public class Car {

	private final String carReg;
	private final int parkingTicketNumber;
	
	public Car(String carReg, int parkingTicketNumber) {
		this.carReg = carReg;
		this.parkingTicketNumber = parkingTicketNumber;
	}
	
	public String getCarReg() {
		return carReg;
	}
	
	public int getParkingTicketNumber() {
		return parkingTicketNumber;
	}	
}
