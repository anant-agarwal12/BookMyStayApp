import java.util.*;

class Reservation {

   private String guestName;
   private String roomType;

   public Reservation(String guestName, String roomType) {
      this.guestName = guestName;
      this.roomType = roomType;
   }

   public String getGuestName() {
      return guestName;
   }

   public String getRoomType() {
      return roomType;
   }
}

class RoomAllocationService {

   // Stores all allocated room IDs (to avoid duplicates)
   private Set<String> allocatedRoomIds;

   // Stores roomType -> set of assigned room IDs
   private Map<String, Set<String>> assignedRoomsByType;

   // Constructor
   public RoomAllocationService() {
      allocatedRoomIds = new HashSet<>();
      assignedRoomsByType = new HashMap<>();
   }

   // Allocate room + confirm booking
   public void allocateRoom(Reservation reservation, RoomInventory inventory) {

      String roomType = reservation.getRoomType();
      Map<String, Integer> availability = inventory.getRoomAvailability();

      // Check availability
      if (availability.get(roomType) > 0) {

         // Generate unique room ID
         String roomId = generateRoomId(roomType);

         // Store globally
         allocatedRoomIds.add(roomId);

         // Store per room type
         assignedRoomsByType
                 .computeIfAbsent(roomType, k -> new HashSet<>())
                 .add(roomId);

         // Update inventory (IMPORTANT)
         inventory.updateAvailability(
                 roomType,
                 availability.get(roomType) - 1
         );

         // Confirm booking
         System.out.println(
                 "Booking confirmed for Guest: " +
                         reservation.getGuestName() +
                         ", Room ID: " + roomId
         );

      } else {
         System.out.println(
                 "No rooms available for Guest: " +
                         reservation.getGuestName()
         );
      }
   }

   // Generate unique room ID
   private String generateRoomId(String roomType) {

      int count = assignedRoomsByType
              .getOrDefault(roomType, new HashSet<>())
              .size() + 1;

      String roomId = roomType + "-" + count;

      // Extra safety (ensures uniqueness globally)
      while (allocatedRoomIds.contains(roomId)) {
         count++;
         roomId = roomType + "-" + count;
      }

      return roomId;
   }
}

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
      roomAvailability.put(roomType, count);
   }
}

class BookingRequestQueue {

   private Queue<Reservation> requestQueue;

   public BookingRequestQueue() {
      requestQueue = new LinkedList<>();
   }

   public void addRequest(Reservation reservation) {
      requestQueue.offer(reservation);
   }

   public Reservation getNextRequest() {
      return requestQueue.poll();
   }

   public boolean hasPendingRequests() {
      return !requestQueue.isEmpty();
   }
}


public class HotelBookingApp {

   public static void main(String[] args) {

      System.out.println("Room Allocation Processing");

      // Step 1: Inventory
      RoomInventory inventory = new RoomInventory();

      // Step 2: Booking Queue
      BookingRequestQueue queue = new BookingRequestQueue();

      queue.addRequest(new Reservation("Abhi", "Single"));
      queue.addRequest(new Reservation("Subha", "Single"));
      queue.addRequest(new Reservation("Vanmathi", "Suite"));

      // Step 3: Allocation Service
      RoomAllocationService allocationService = new RoomAllocationService();

      // Step 4: Process queue (FIFO)
      while (queue.hasPendingRequests()) {
         Reservation r = queue.getNextRequest();
         allocationService.allocateRoom(r, inventory);
      }
   }
}