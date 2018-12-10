package polestar.software.exercise.main;

import java.util.Arrays;
import java.util.Scanner;

public class MainCarParkManager {


	public static void main(String[] args) {
		
		String myStringArray=null;
		System.out.println("Please Enter Your Set of Command:");

		/**
		 * taking a string of comma separated commands from the stdin
		 * implementing "StringIndexOutOfBoundsException" handler 
		 */
		try {
		Scanner dis=new Scanner(System.in);
        myStringArray = dis.next();
        }catch(StringIndexOutOfBoundsException e){
            System.out.println("invalid input"+e.getMessage());
        }

		/** 
		 * separating each comma separated command 
		 */
        String CSV = myStringArray; 
        String[] values = CSV.split(","); 
        
        
        System.out.println("=======================================================");
        System.out.println("Your set of command is:" + Arrays.toString(values));
        System.out.println("=======================================================");
        System.out.println("-----------------S T E P  B Y  S T E P-----------------");
        System.out.println("=======================================================");
        
        
        ParkingServices serviceController = new ParkingServices();
       
       /**
        *  p means park the car with the given license plate.
        *  u means unpark the car in the space taken by that ticket
        *  c means compact the carpark
        */
        
        for (String command : values) {
					
					char instruction = command.charAt(0);
					
					switch(instruction) {
						case 'p' : 
							final String carReg = command.substring(1);
							serviceController.park(carReg);
							break;
						case 'u' : 
							final String parkingTicketNumber = command.substring(1);
							serviceController.unpark(Integer.parseInt(parkingTicketNumber));
							break;
						case 'c' :
							serviceController.compact();
							break;
						default :
							System.out.println("Invalid Instruction: " + instruction);
					}
			
				
				System.out.println("Command :"+command.substring(0)+"  Car Park Status: " + serviceController.resultant());
			}
        System.out.println("=======================================================");
        System.out.println("Final Car Park Status :" + serviceController.resultant());
        System.out.println("=======================================================");
        System.out.println("-------------------T H A N K  Y O U--------------------");
        System.out.println("=======================================================");
	}

}
