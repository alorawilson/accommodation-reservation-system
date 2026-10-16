package com.swen646.accommodation;

import java.time.LocalDate;

public class HouseReservation extends Reservation {
	
	// Updated Constructor - constructs a house reservation by initializing all required reservation
	// details and delegating common initialization to the Reservation superclass
	public HouseReservation(
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

	// XML parsing constructor for HouseReservation
	public HouseReservation(String xml, Address accountMailingAddress) {

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

	    // Set status from XML
	    String statusValue = getTagValue(xml, "status");
	    if (statusValue != null) {
	        setStatusInternal(ReservationStatus.valueOf(statusValue));
	    }

	    // House-specific field
	    String floorsValue = getTagValue(xml, "numberOfFloors");
	    if (floorsValue != null) {
	        setNumberOfFloors(Integer.parseInt(floorsValue));
	    }
	}
	
	// Calculates the house price per night
	@Override
	public double calculatePricePerNight() {

	    double price = 80; // base rate

	    price += getNumberOfBeds() * 15;
	    price += getNumberOfBedrooms() * 25;
	    price += getNumberOfBathrooms() * 20;
	    price += getSquareFeet() * 0.07;

	    price += getNumberOfFloors() * 30;

	    return price;
	}
	
	// Number of floors in the house
	private int numberOfFloors;
	
	// Returns the number of floors in the house
	public int getNumberOfFloors() {
	    return numberOfFloors;
	}

	// Updates the number of floors in the house
	public void setNumberOfFloors(int numberOfFloors) {

	    // Must be at least 1 floor
	    if (numberOfFloors <= 0) {
	        throw new InvalidReservationException("Number of floors must be greater than zero.");
	    }

	    // Cannot modify after completion
	    if (getStatus() == ReservationStatus.COMPLETED) {
	        throw new IllegalOperationException(
	                "setNumberOfFloors",
	                getAccountNumber(),
	                getReservationNumber(),
	                "Cannot modify floors after reservation is completed."
	        );
	    }

	    // Cannot modify if cancelled
	    if (getStatus() == ReservationStatus.CANCELLED) {
	        throw new IllegalOperationException(
	                "setNumberOfFloors",
	                getAccountNumber(),
	                getReservationNumber(),
	                "Cannot modify floors on a cancelled reservation."
	        );
	    }

	    this.numberOfFloors = numberOfFloors;
	}

	// Returns formatted house reservation data
	@Override
	public String toString() {
	    return "";
	}



}
