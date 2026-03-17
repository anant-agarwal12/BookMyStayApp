import java.util.HashMap;
import java.util.Map;
class RoomInventory {

   private Map<String, Integer> roomAvailability;

   // Constructor
      RoomInventory() {
      roomAvailability = new HashMap<>();
      initializeInventory();
   }

   // Initialize default room data
   private void initializeInventory() {
      roomAvailability.put("Single", 10);
      roomAvailability.put("Double", 5);
      roomAvailability.put("Suite", 2);
   }

   // Get current availability
   public Map<String, Integer> getRoomAvailability() {
      return roomAvailability;
   }

   // Update availability
   public void updateAvailability(String roomType, int count) {
      roomAvailability.pugt(roomType, count);
   }
}

public class HotelBookingApp{

   public static void main(String[] args) {

      // Step 1: Initialize inventory
      RoomInventory inventory = new RoomInventory();

      // Step 2: Display initial availability
      System.out.println("Initial Room Availability:");
      printInventory(inventory.getRoomAvailability());

      // Step 3: Update some values
      inventory.updateAvailability("Single", 8);
      inventory.updateAvailability("Suite", 1);

      // Step 4: Display updated availability
      System.out.println("\nUpdated Room Availability:");
      printInventory(inventory.getRoomAvailability());
   }

   // Helper method to print inventory
   public static void printInventory(Map<String, Integer> map) {
      for (String roomType : map.keySet()) {
         System.out.println(roomType + " Rooms: " + map.get(roomType));
      }
   }
}