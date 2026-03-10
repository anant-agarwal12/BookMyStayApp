/*
    Use Case 2 : Room types and static availability
 */
import java.util.*;

abstract class Room{
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;
    static int availibility = 3;

    public Room(int numberOfBeds , int squareFeet , double pricePerNight){
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
        availibility -= 1;
    }

    public void displayRoomDetails(){
        System.out.println("Number Of Beds: " + numberOfBeds);
        System.out.println("Room size: " + squareFeet + " sq. ft");
        System.out.println("Price Per Night: " + pricePerNight);
        System.out.println("Room Availability After Booking: " + availibility);
    }

}

class SingleRoom extends Room{
    public SingleRoom(){
        super(1,250,1500.0);
    }
}

class DoubleRoom extends Room{
    public DoubleRoom(){
        super(2,400,2500.0);
    }
}

class SuiteRoom extends Room{
    public SuiteRoom(){
        super(3,500,5000.0);
    }
}


public class HotelBookingApp{
    public static void main(String[] args){
       SingleRoom room1 = new SingleRoom();
       System.out.println("Room Type: Single Room");
       room1.displayRoomDetails();
       System.out.println();

       DoubleRoom room2 = new DoubleRoom();
       System.out.println("Room Type: Double Room");
       room2.displayRoomDetails();
       System.out.println();

       SuiteRoom room3 = new SuiteRoom();
       System.out.println("Room Type: Suite Room");
       room3.displayRoomDetails();
    }
}

