
package polestar.software.exercise.main;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

import polestar.software.exercise.model.Car;

public class ParkingServices {

	/**
	 * Defining an integer value that would be updated atomically to ensure unique parking ticket number. 
	 * Initiating the counter at 4999 as we want to start the ticket number from 5000 by implementing incrementAndGet() method.
	 */

	static int counter= 4999;
	private static final AtomicInteger ticketNumber = new AtomicInteger(counter);

	/**
	 * Creating a new AtomicReferenceArray of the given length 10 equivalent to the available parking space 10.  
	 * All elements of that array is initially null.
	 */

	int numberOfParkingSpace = 10;
	private AtomicReferenceArray<Car> parkingSpace = new AtomicReferenceArray<Car>(numberOfParkingSpace);


	/**
	 * This section would park a car in the first available space near the entrance, 
	 * that would provide a ticket number for that space. 
	 * A unique ticket numbers will begin at 5000 and increment each time a new ticket is issued.
	 * initiating Car object with carReg and parkingTicketNumber
	 * 
	 * @param carReg - Car registration number
	 */
	
	public void park(String carReg) {
		int parkingTicketNumber = 0;
		for(int parkingLocation = 0; parkingLocation < parkingSpace.length(); parkingLocation++) {
			if(parkingSpace.get(parkingLocation) == null) {
				parkingTicketNumber = ticketNumber.incrementAndGet();
				Car car = new Car(carReg, parkingTicketNumber);
				parkingSpace.set(parkingLocation, car);
				break;
			}
		  }
	    }

	/**
	 * This section takes a parking ticket number and unpark the car that’s in that particular Location.
	 * @param parkingTicketNumber- unpark the car associated. 
	 */
	
	public void unpark(int parkingTicketNumber) {
		for(int parkingLocation = 0; parkingLocation <parkingSpace.length(); parkingLocation++) {
			Car car = parkingSpace.get(parkingLocation);
			if(car != null && car.getParkingTicketNumber() == parkingTicketNumber) {
				parkingSpace.set(parkingLocation, null);
				break;
			}
		}
	}
	

	/**
	 * Compacting the car park.  
	 * That would move all the parked cars so that they take a contiguous set of spaces nearest the entrance. 
	 */
	
	public void compact() {
		int firstParkingLocation = 0;
		int lastParkingLocation = parkingSpace.length()-1;
		while(firstParkingLocation <= lastParkingLocation) {
			Car car = parkingSpace.get(firstParkingLocation);
			if(car == null) {
				if(parkingSpace.get(firstParkingLocation+1) != null) {
					parkingSpace.set(firstParkingLocation, parkingSpace.get(firstParkingLocation+1));
					parkingSpace.set(firstParkingLocation+1, null);
					firstParkingLocation++;
				}
				else {
					lastParkingLocation--;
				     }
			}
			else {
				firstParkingLocation++;
			}
		}
	}

	
	/**
	 * @return Final status of each space in the car park
	 */
	
	public String resultant() {
		
		StringBuffer endResult = new StringBuffer();
		
		for(int i = 0; i < parkingSpace.length(); i++) {
			Car car = parkingSpace.get(i);
			endResult.append(car == null ? ",": car.getCarReg()+",");
		}
		return endResult.toString();
	}
}


