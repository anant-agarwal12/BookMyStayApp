import java.util.Map;

class Room {

   private String type;
   private int beds;
   private int size;
   private double price;

   public Room(String type, int beds, int size, double price) {
      this.type = type;
      this.beds = beds;
      this.size = size;
      this.price = price;
   }

   public String getType() {
      return type;
   }

   public int getBeds() {
      return beds;
   }

   public int getSize() {
      return size;
   }

   public double getPrice() {
      return price;
   }
}

class RoomSearchService {

   public void searchAvailableRooms(
           RoomInventory inventory,
           Room singleRoom,
           Room doubleRoom,
           Room suiteRoom) {

      Map<String, Integer> availability = inventory.getRoomAvailability();

      // Single Room
      if (availability.get("Single") > 0) {
         System.out.println("\nSingle Room:");
         printRoomDetails(singleRoom, availability.get("Single"));
      }

      // Double Room
      if (availability.get("Double") > 0) {
         System.out.println("\nDouble Room:");
         printRoomDetails(doubleRoom, availability.get("Double"));
      }

      // Suite Room
      if (availability.get("Suite") > 0) {
         System.out.println("\nSuite Room:");
         printRoomDetails(suiteRoom, availability.get("Suite"));
      }
   }

   private void printRoomDetails(Room room, int available) {
      System.out.println("Beds: " + room.getBeds());
      System.out.println("Size: " + room.getSize() + " sqft");
      System.out.println("Price per night: " + room.getPrice());
      System.out.println("Available: " + available);
   }
}

public class HotelBookingApp{

   public static void main(String[] args) {
      gi
      RoomInventory inventory = new RoomInventory();

      Room single = new Room("Single", 1, 250, 1500.0);
      Room doub = new Room("Double", 2, 400, 2500.0);
      Room suite = new Room("Suite", 3, 750, 5000.0);
      ce
      RoomSearchService service = new RoomSearchService();

      System.out.println("Room Search");

      service.searchAvailableRooms(inventory, single, doub, suite);
   }
}