package com.swen646.accommodation;

public class AccommodationTest {

    public static void main(String[] args) {
    	
    	testReservationCalculation();
    	
    	testAccountAddAndRetrieve();
    	
    	testAccountRemoveReservation();
    	
    	testManagerOperations();
    	
    	System.out.println("\nAll tests executed successfully.");
    	
    }

    // Helper method to create a sample address for tests
    private static Address createSampleAddress() {
        return new Address(
                "123 Main St",
                "Okinawa",
                "Okinawa",
                "904-0000",
                "Japan"
        );
    }
    
 // Helper method to format test section headers
    private static void printTestHeader(String title) {
        System.out.println("\n================================================");
        System.out.println(title);
        System.out.println("================================================");
    }
    
 // Test reservation pricing and completion
    private static void testReservationCalculation() {

        Address address = createSampleAddress();

        HotelReservation hotel = new HotelReservation(
                "H001",
                java.time.LocalDate.of(2026, 3, 20),
                3,
                2,
                1,
                1,
                450,
                address,
                address
        );

        hotel.setHasKitchenette(true);
        hotel.complete();

        printTestHeader("TEST 1: HOTEL PRICE CALCULATION");
        System.out.println("Hotel reservation total price: " + hotel.calculateTotalPrice());
    }
    
 // Test account addReservation() and getReservation()
    private static void testAccountAddAndRetrieve() {

        Address address = createSampleAddress();

        HotelReservation hotel = new HotelReservation(
                "H001",
                java.time.LocalDate.of(2026, 3, 20),
                3,
                2,
                1,
                1,
                450,
                address,
                address
        );

        hotel.setHasKitchenette(true);
        hotel.complete();

        Account account = new Account(
                "A001",
                address,
                "user@email.com",
                "090-1234-5678"
        );

        account.addReservation(hotel);

        Reservation retrieved = account.getReservation("H001");

        printTestHeader("TEST 2: ACCOUNT ADD AND RETRIEVE");
        if (retrieved != null) {
            System.out.println("Reservation retrieved successfully: " + retrieved.getReservationNumber());
        } else {
            System.out.println("Reservation not found.");
        }
        
        
    }
 // Test account removeReservation()
    private static void testAccountRemoveReservation() {

        Address address = createSampleAddress();

        HotelReservation hotel = new HotelReservation(
                "H001",
                java.time.LocalDate.of(2026, 3, 20),
                3,
                2,
                1,
                1,
                450,
                address,
                address
        );

        hotel.setHasKitchenette(true);
        hotel.complete();

        Account account = new Account(
                "A001",
                address,
                "user@email.com",
                "090-1234-5678"
        );

        account.addReservation(hotel);
        account.removeReservation("H001");

        Reservation removedCheck = account.getReservation("H001");

        printTestHeader("TEST 3: ACCOUNT REMOVE RESERVATION");
        if (removedCheck == null) {
            System.out.println("Reservation successfully removed.");
        } else {
            System.out.println("Reservation removal failed.");
        }
        
    }
    
    // Test Manager class functionality
    private static void testManagerOperations() {

        printTestHeader("TEST 4: MANAGER OPERATIONS");

        Address address = createSampleAddress();

        Account account = new Account(
                "A002",
                address,
                "manager@email.com",
                "090-9999-9999"
        );

        Manager manager = new Manager();
        manager.addAccount(account);

        HotelReservation reservation = new HotelReservation(
                "H002",
                java.time.LocalDate.of(2026, 5, 1),
                2,
                1,
                1,
                1,
                300,
                address,
                address
        );

        reservation.setHasKitchenette(false);

        manager.addReservation("A002", reservation);
        manager.completeReservation("A002", "H002");

        double totalPrice = manager.calculateTotalReservationPrice(reservation);

        System.out.println("Manager calculated total price: " + totalPrice);
    }
    
}




