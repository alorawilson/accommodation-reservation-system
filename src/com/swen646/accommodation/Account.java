package com.swen646.accommodation;

import java.util.List;
import java.util.ArrayList;



public class Account {
	// Unique account number (cannot be changed)
	private String accountNumber;

	// Mailing address associated with account
	private Address mailingAddress;

	// Email address for account
	private String email;

	// Phone number for account
	private String phone;

	// Reservations associated with the account
	private List<Reservation> reservations = new ArrayList<>();

	
	//CONSTRUCTOR//
	// Creates a new account with required account information
	public Account(String accountNumber, Address mailingAddress, String email, String phone) {

	    // Validate account number
	    if (accountNumber == null || accountNumber.isBlank()) {
	        throw new InvalidReservationException("Account number cannot be null or blank.");
	    }

	    // Validate mailing address
	    if (mailingAddress == null) {
	        throw new InvalidReservationException("Mailing address cannot be null.");
	    }

	    // Validate email
	    if (email == null || email.isBlank()) {
	        throw new InvalidReservationException("Email cannot be null or blank.");
	    }

	    // Validate phone
	    if (phone == null || phone.isBlank()) {
	        throw new InvalidReservationException("Phone cannot be null or blank.");
	    }

	    this.accountNumber = accountNumber;
	    this.mailingAddress = mailingAddress;
	    this.email = email;
	    this.phone = phone;
	}

	// Returns list of reservations for this account
	public List<Reservation> getReservations() {
	    return reservations;
	}

	// Adds new reservation to the account
	public void addReservation(Reservation reservation) {

	    if (reservation == null) {
	        throw new InvalidReservationException("Reservation cannot be null.");
	    }

	    reservations.add(reservation);
	}
	
	// Removes reservation from the account
	public void removeReservation(String reservationNumber) {

	    if (reservationNumber == null || reservationNumber.isBlank()) {
	        throw new InvalidReservationException("Reservation number cannot be null or blank.");
	    }

	    Reservation reservation = getReservation(reservationNumber);

	    if (reservation == null) {
	        throw new InvalidReservationException("Reservation not found.");
	    }

	    reservations.remove(reservation);
	}

	// Retrieves reservation by reservation number
	public Reservation getReservation(String reservationNumber) {

	    if (reservationNumber == null || reservationNumber.isBlank()) {
	        throw new InvalidReservationException("Reservation number cannot be null or blank.");
	    }

	    for (Reservation reservation : reservations) {
	        if (reservation.getReservationNumber().equals(reservationNumber)) {
	            return reservation;
	        }
	    }

	    return null;
	}

	// Returns formatted account information
	@Override
	public String toString() {
	    return "<account>\n"
	            + "    <accountNumber>" + accountNumber + "</accountNumber>\n"
	            + "    <mailingAddress>\n"
	            + "        " + mailingAddress.toString().replace("\n", "\n        ") + "\n"
	            + "    </mailingAddress>\n"
	            + "    <email>" + email + "</email>\n"
	            + "    <phone>" + phone + "</phone>\n"
	            + "</account>";
	}

	// Get methods will allow other classes to safely read account data
	
	// Returns account number
	public String getAccountNumber() {
	    return accountNumber;
	}

	// Returns mailing address
	public Address getMailingAddress() {
	    return mailingAddress;
	}

	// Returns email address
	public String getEmail() {
	    return email;
	}

	// Returns phone number
	public String getPhone() {
	    return phone;
	}

	// Set methods information that can change
	
	// Updates mailing address for this account
	public void setMailingAddress(Address mailingAddress) {
	    this.mailingAddress = mailingAddress;
	}

	// Updates email for this account
	public void setEmail(String email) {
	    this.email = email;
	}

	// Updates phone number for this account
	public void setPhone(String phone) {
	    this.phone = phone;
	}


}
