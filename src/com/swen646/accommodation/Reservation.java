package com.swen646.accommodation;

import java.time.LocalDate;

public abstract class Reservation {
	// Price of reservation
	private double price;

	// Reservation number gen by UI
	private String reservationNumber;

	// Account number this reservation belongs to ("-99" when not assigned)
	private String accountNumber;

	// Physical address of lodging
	private Address physicalAddress;

	// Mailing address of lodging
	private Address mailingAddress;

	// Date reservation starts
	private LocalDate startDate;

	// Number of nights for stay
	private int numberOfNights;

	// Number of beds (2)
	private int numberOfBeds;

	// Number of bedrooms (1)
	private int numberOfBedrooms;

	// Number of bathrooms (1)
	private int numberOfBathrooms;

	// Lodging size square feet
	private int squareFeet;
	
	// Current reservation status (draft, completed, cancelled)
	private ReservationStatus status;
	
	// Protected no-argument constructor used for XML parsing
	protected Reservation() {
	}
	
	// Updated - Creates a reservation with all required reservation details
	public Reservation(
	        String reservationNumber,
	        LocalDate startDate,
	        int numberOfNights,
	        int numberOfBeds,
	        int numberOfBedrooms,
	        int numberOfBathrooms,
	        int squareFeet,
	        Address physicalAddress,
	        Address mailingAddress) {
		
		// Validate reservation number
		if (reservationNumber == null || reservationNumber.isBlank()) {
		    throw new InvalidReservationException("Reservation number cannot be null or blank.");
		}

		// Validate start date
		if (startDate == null) {
		    throw new InvalidReservationException("Start date cannot be null.");
		}
		
		// Validate number of nights
		if (numberOfNights <= 0) {
		    throw new InvalidReservationException("Number of nights must be greater than zero.");
		}
		
		// Validate number of beds
		if (numberOfBeds <= 0) {
		    throw new InvalidReservationException("Number of beds must be greater than zero.");
		}

		// Validate number of bedrooms
		if (numberOfBedrooms <= 0) {
		    throw new InvalidReservationException("Number of bedrooms must be greater than zero.");
		}

		// Validate number of bathrooms
		if (numberOfBathrooms <= 0) {
		    throw new InvalidReservationException("Number of bathrooms must be greater than zero.");
		}

		// Validate square feet
		if (squareFeet <= 0) {
		    throw new InvalidReservationException("Square feet must be greater than zero.");
		}
		
		// Validate physical address
		if (physicalAddress == null) {
		    throw new InvalidReservationException("Physical address cannot be null.");
		}
		
	    this.reservationNumber = reservationNumber;
	    this.startDate = startDate;
	    this.numberOfNights = numberOfNights;
	    this.numberOfBeds = numberOfBeds;
	    this.numberOfBedrooms = numberOfBedrooms;
	    this.numberOfBathrooms = numberOfBathrooms;
	    this.squareFeet = squareFeet;
	    this.physicalAddress = physicalAddress;
	    this.mailingAddress = mailingAddress;

	    // Default values required by the project
	    this.accountNumber = "-99";
	    this.status = ReservationStatus.DRAFT;
	}

	// Calculates the price per night for the reservation (implemented by subclasses)
	public abstract double calculatePricePerNight();

	// Calculates total price for reservation
	public double calculateTotalPrice() {

	    // Price is not finalized until reservation is completed
	    if (status != ReservationStatus.COMPLETED) {
	        return 0;
	    }
	    return price;
	}

	// Marks reservation as completed
	public void complete() {

	    // Cannot complete if already cancelled
	    if (status == ReservationStatus.CANCELLED) {
	        throw new IllegalOperationException(
	                "complete",
	                accountNumber,
	                reservationNumber,
	                "Reservation is cancelled and cannot be completed."
	        );
	    }

	    // Cannot complete if already completed
	    if (status == ReservationStatus.COMPLETED) {
	        throw new IllegalOperationException(
	                "complete",
	                accountNumber,
	                reservationNumber,
	                "Reservation is already completed."
	        );
	    }

	    // Reservation must be draft to complete
	    if (status != ReservationStatus.DRAFT) {
	        throw new IllegalOperationException(
	                "complete",
	                accountNumber,
	                reservationNumber,
	                "Only draft reservations can be completed."
	        );
	    }

	    // Set price only when reservation is completed
	    // NOTE: calculatePricePerNight() is implemented by subclasses
	    double perNight = calculatePricePerNight();
	    price = perNight * numberOfNights;

	    // Mark as completed
	    status = ReservationStatus.COMPLETED;
	}


	// Cancels reservation if allowed
	public void cancel() {

	    // Cannot cancel a completed reservation
	    if (status == ReservationStatus.COMPLETED) {
	        throw new IllegalOperationException(
	                "cancel",
	                accountNumber,
	                reservationNumber,
	                "Reservation is already completed and cannot be cancelled."
	        );
	    }

	    // Can only cancel if the reservation start date is in the future
	    if (startDate != null && !startDate.isAfter(LocalDate.now())) {
	        throw new IllegalOperationException(
	                "cancel",
	                accountNumber,
	                reservationNumber,
	                "Reservation start date is not in the future."
	        );
	    }

	    // Perform cancel
	    status = ReservationStatus.CANCELLED;
	}


	// Updates reservation values when allowed
	public void update() {

	    // Cannot update cancelled reservation
	    if (status == ReservationStatus.CANCELLED) {
	        throw new IllegalOperationException(
	                "update",
	                accountNumber,
	                reservationNumber,
	                "Reservation is cancelled and cannot be updated."
	        );
	    }

	    // Cannot update completed reservation
	    if (status == ReservationStatus.COMPLETED) {
	        throw new IllegalOperationException(
	                "update",
	                accountNumber,
	                reservationNumber,
	                "Reservation is already completed and cannot be updated."
	        );
	    }

	    // Cannot update if reservation start date is in the past
	    if (startDate != null && startDate.isBefore(LocalDate.now())) {
	        throw new IllegalOperationException(
	                "update",
	                accountNumber,
	                reservationNumber,
	                "Reservation start date is in the past."
	        );
	    }

	    // NOTE: Actual field updates (dates, counts, etc.) will be handled
	    // through setter methods.
	}

	
	// get methods so that other classes can read reservation data safely
	
	// Returns reservation number
	public String getReservationNumber() {
	    return reservationNumber;
	}

	// Returns account number
	public String getAccountNumber() {
	    return accountNumber;
	}

	// Returns physical address
	public Address getPhysicalAddress() {
	    return physicalAddress;
	}

	// Returns mailing address
	public Address getMailingAddress() {
	    return mailingAddress;
	}

	// Returns start date
	public LocalDate getStartDate() {
	    return startDate;
	}

	// Returns number of nights
	public int getNumberOfNights() {
	    return numberOfNights;
	}

	// Returns number of beds
	public int getNumberOfBeds() {
	    return numberOfBeds;
	}

	// Returns number of bedrooms
	public int getNumberOfBedrooms() {
	    return numberOfBedrooms;
	}

	// Returns number of bathrooms
	public int getNumberOfBathrooms() {
	    return numberOfBathrooms;
	}

	// Returns square footage
	public int getSquareFeet() {
	    return squareFeet;
	}

	// Returns reservation price
	public double getPrice() {
	    return price;
	}

	// Returns reservation status
	public ReservationStatus getStatus() {
	    return status;
	}

	//Set methods for this class - data allowed to change after creation 
	
	// Sets account number when reservation is assigned to an account
	public void setAccountNumber(String accountNumber) {

	    // Validate account number
	    if (accountNumber == null || accountNumber.isBlank()) {
	        throw new InvalidReservationException("Account number cannot be null or blank.");
	    }

	    // Cannot assign an account to a cancelled reservation
	    if (status == ReservationStatus.CANCELLED) {
	        throw new IllegalOperationException(
	                "setAccountNumber",
	                this.accountNumber,
	                reservationNumber,
	                "Cannot assign an account to a cancelled reservation."
	        );
	    }

	    this.accountNumber = accountNumber;
	}

	// Updates mailing address (may be null initially)
	public void setMailingAddress(Address mailingAddress) {

	    // Cannot change mailing address after completion
	    if (status == ReservationStatus.COMPLETED) {
	        throw new IllegalOperationException(
	                "setMailingAddress",
	                accountNumber,
	                reservationNumber,
	                "Cannot change mailing address after reservation is completed."
	        );
	    }

	    // Cannot change mailing address if cancelled
	    if (status == ReservationStatus.CANCELLED) {
	        throw new IllegalOperationException(
	                "setMailingAddress",
	                accountNumber,
	                reservationNumber,
	                "Cannot change mailing address on a cancelled reservation."
	        );
	    }

	    // Mailing address is allowed to be null 
	    this.mailingAddress = mailingAddress;
	}
	
	// Helper: gets the text value inside an XML tag, ex: <startDate>2026-03-01</startDate>
	protected static String getTagValue(String xml, String tagName) {
		
	    if (xml == null || tagName == null || tagName.isBlank()) {
	        return null;
	    }

	    String openTag = "<" + tagName + ">";
	    String closeTag = "</" + tagName + ">";

	    int start = xml.indexOf(openTag);
	    int end = xml.indexOf(closeTag);

	    if (start == -1 || end == -1) {
	        return null;
	    }

	    start = start + openTag.length();
	    if (start > end) {
	        return null;
	    }

	    return xml.substring(start, end).trim();
	}

	// Used only for XML loading
	protected void setStatusInternal(ReservationStatus status) {
	    this.status = status;
	}


}
