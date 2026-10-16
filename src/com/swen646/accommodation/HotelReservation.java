package com.swen646.accommodation;

import java.time.LocalDate;


public class HotelReservation extends Reservation {
	
	// Updated Constructor - constructs a hotel reservation by initializing all required reservation
	// details and delegating common initialization to the Reservation superclass
	public HotelReservation(
	        String reservationNumber,
	        LocalDate startDate,
	        int numberOfNights,
	        int numberOfBeds,
	        int numberOfBedrooms,
	        int numberOfBathrooms,
	        int squareFeet,
	        Address physicalAddress,
	        Address mailingAddress) {

	    super(
	        reservationNumber,
	        startDate,
	        numberOfNights,
	        numberOfBeds,
	        numberOfBedrooms,
	        numberOfBathrooms,
	        squareFeet,
	        physicalAddress,
	        mailingAddress
	    );
	}

	// XML parsing constructor for HotelReservation
	public HotelReservation(String xml, Address accountMailingAddress) {
		
	    super(
	        getTagValue(xml, "reservationNumber"),
	        LocalDate.parse(getTagValue(xml, "startDate")),
	        Integer.parseInt(getTagValue(xml, "numberOfNights")),
	        Integer.parseInt(getTagValue(xml, "numberOfBeds")),
	        Integer.parseInt(getTagValue(xml, "numberOfBedrooms")),
	        Integer.parseInt(getTagValue(xml, "numberOfBathrooms")),
	        Integer.parseInt(getTagValue(xml, "squareFeet")),
	        accountMailingAddress,
	        accountMailingAddress
	    );
	    
	    // Parse and set status from XML
	    String statusValue = getTagValue(xml, "status");
	    if (statusValue != null) {
	        setStatusInternal(ReservationStatus.valueOf(statusValue));
	    }

	    // Hotel-specific field(s)
	    String kitchenetteValue = getTagValue(xml, "hasKitchenette");
	    if (kitchenetteValue != null) {
	        setHasKitchenette(Boolean.parseBoolean(kitchenetteValue));
	    }
	}

	
	// Calculates the hotel price per night
	@Override
	public double calculatePricePerNight() {

	    double price = 50; // base rate

	    price += getNumberOfBeds() * 10;
	    price += getNumberOfBedrooms() * 20;
	    price += getNumberOfBathrooms() * 15;
	    price += getSquareFeet() * 0.05;

	    if (getHasKitchenette()) {
	        price += 25;
	    }

	    return price;
	}

	// Indicates whether the hotel room includes a kitchenette
	private boolean hasKitchenette;
	
	// Returns whether the hotel room includes a kitchenette
	public boolean getHasKitchenette() {
	    return hasKitchenette;
	}

	// Updates whether the hotel room includes a kitchenette
	public void setHasKitchenette(boolean hasKitchenette) {

	    // Cannot modify after completion
	    if (getStatus() == ReservationStatus.COMPLETED) {
	        throw new IllegalOperationException(
	                "setHasKitchenette",
	                getAccountNumber(),
	                getReservationNumber(),
	                "Cannot modify kitchenette after reservation is completed."
	        );
	    }

	    // Cannot modify if cancelled
	    if (getStatus() == ReservationStatus.CANCELLED) {
	        throw new IllegalOperationException(
	                "setHasKitchenette",
	                getAccountNumber(),
	                getReservationNumber(),
	                "Cannot modify kitchenette on a cancelled reservation."
	        );
	    }

	    this.hasKitchenette = hasKitchenette;
	}

	// Returns formatted hotel reservation data
	@Override
	public String toString() {
	    return "";
	}
	
	
	


}
