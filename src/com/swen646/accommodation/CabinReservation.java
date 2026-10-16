package com.swen646.accommodation;

import java.time.LocalDate;

public class CabinReservation extends Reservation {
	
	// Updated constructor - constructs a cabin reservation by initializing required reservation details
	// and delegating common initialization to the Reservation superclass
	
	public CabinReservation(
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
	
	// XML parsing constructor for CabinReservation
	public CabinReservation(String xml, Address accountMailingAddress) {

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

	    // Cabin-specific fields
	    String kitchenValue = getTagValue(xml, "hasFullKitchen");
	    if (kitchenValue != null) {
	        setHasFullKitchen(Boolean.parseBoolean(kitchenValue));
	    }

	    String loftValue = getTagValue(xml, "hasLoft");
	    if (loftValue != null) {
	        setHasLoft(Boolean.parseBoolean(loftValue));
	    }
	}

	// Calculates the cabin price per night
	@Override
	public double calculatePricePerNight() {

	    double price = 60; // base rate

	    price += getNumberOfBeds() * 12;
	    price += getNumberOfBedrooms() * 18;
	    price += getNumberOfBathrooms() * 15;
	    price += getSquareFeet() * 0.06;

	    if (getHasFullKitchen()) {
	        price += 30;
	    }

	    if (getHasLoft()) {
	        price += 20;
	    }

	    return price;
	}
	
	// Indicates whether the cabin has a full kitchen
	private boolean hasFullKitchen;
	
	// Returns whether the cabin has a full kitchen
	public boolean getHasFullKitchen() {
	    return hasFullKitchen;
	}

	// Updates whether the cabin has a full kitchen
	public void setHasFullKitchen(boolean hasFullKitchen) {

	    if (getStatus() == ReservationStatus.COMPLETED) {
	        throw new IllegalOperationException(
	                "setHasFullKitchen",
	                getAccountNumber(),
	                getReservationNumber(),
	                "Cannot modify full kitchen after reservation is completed."
	        );
	    }

	    if (getStatus() == ReservationStatus.CANCELLED) {
	        throw new IllegalOperationException(
	                "setHasFullKitchen",
	                getAccountNumber(),
	                getReservationNumber(),
	                "Cannot modify full kitchen on a cancelled reservation."
	        );
	    }

	    this.hasFullKitchen = hasFullKitchen;
	}

	// Indicates whether the cabin includes a loft
	private boolean hasLoft;
	
	// Returns whether the cabin includes a loft
	public boolean getHasLoft() {
	    return hasLoft;
	}

	// Updates whether the cabin includes a loft
	public void setHasLoft(boolean hasLoft) {

	    if (getStatus() == ReservationStatus.COMPLETED) {
	        throw new IllegalOperationException(
	                "setHasLoft",
	                getAccountNumber(),
	                getReservationNumber(),
	                "Cannot modify loft after reservation is completed."
	        );
	    }

	    if (getStatus() == ReservationStatus.CANCELLED) {
	        throw new IllegalOperationException(
	                "setHasLoft",
	                getAccountNumber(),
	                getReservationNumber(),
	                "Cannot modify loft on a cancelled reservation."
	        );
	    }

	    this.hasLoft = hasLoft;
	}

	// Returns formatted cabin reservation data
	@Override
	public String toString() {
	    return "";
	}



}
