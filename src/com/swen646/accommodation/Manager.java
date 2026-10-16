package com.swen646.accommodation;

import java.util.List;
import java.util.ArrayList;


public class Manager {
	// Hardcoded directory path where account data is stored
	public static final String DATA_DIRECTORY = "";

	// List of accounts loaded and managed in memory
	private List<Account> accounts = new ArrayList<>();

	
	// Loads all accounts and their reservations on startup
	public Manager() {

	    // Pseudo-code -
	    // When the system starts, the Manager will load all account data from disk.
	    // The UI does not know where the data is stored.
	    //
	    // For each account directory found:
	    //   - Read the account file
	    //   - If the account file cannot be loaded, throw IllegalLoadException
	    //
	    // For each reservation file inside the account directory:
	    //   - Read the reservation file
	    //   - If the reservation file cannot be loaded, throw IllegalLoadException
	    //
	    // At this stage, file loading is not implemented yet.
	    // This comment documents where and why the exception would be thrown.
	}

	// Returns all loaded accounts
	public List<Account> getAccounts() {
	    return accounts;
	}

	// Retrieves an account by account number
	public Account getAccount(String accountNumber) {

	    if (accountNumber == null || accountNumber.isBlank()) {
	        throw new IllegalArgumentException("Account number cannot be null or blank.");
	    }

	    for (Account account : accounts) {
	        if (account.getAccountNumber().equals(accountNumber)) {
	            return account;
	        }
	    }

	    return null;
	}

	// Adds new account to the manager
	public void addAccount(Account account) {

	    // Validate required input
	    if (account == null) {
	        throw new IllegalArgumentException("Account cannot be null.");
	    }

	    // Check for duplicate account number
	    for (Account a : accounts) {
	        if (a.getAccountNumber() != null && a.getAccountNumber().equals(account.getAccountNumber())) {
	            // Duplicate account detected
	            throw new DuplicateObjectException(account.getAccountNumber());
	        }
	    }

	    // Add account to in-memory list
	    accounts.add(account);

	    // pseudo-code - In later weeks, Manager will also persist account data to disk.
	}

	// Updates the stored data for a specific account
	public void updateAccount(String accountNumber) {
	}

	// Adds draft reservation to an account
	public void addReservation(String accountNumber, Reservation reservation) {

	    // Validate inputs
	    if (accountNumber == null || accountNumber.isEmpty()) {
	        throw new IllegalArgumentException("Account number cannot be null or empty.");
	    }
	    if (reservation == null) {
	        throw new IllegalArgumentException("Reservation cannot be null.");
	    }

	    // Retrieve the account
	    Account account = getAccount(accountNumber);
	    if (account == null) {
	        throw new IllegalArgumentException("No account found for account number: " + accountNumber);
	    }

	    // Check for duplicate reservation number within the account
	    for (Reservation r : account.getReservations()) {
	        if (r.getReservationNumber() != null
	                && r.getReservationNumber().equals(reservation.getReservationNumber())) {
	            // Duplicate reservation detected
	            throw new DuplicateObjectException(accountNumber, reservation.getReservationNumber());
	        }
	    }

	    // Assign reservation to the account and add to list
	    reservation.setAccountNumber(accountNumber);
	    account.addReservation(reservation);

	    // pseudo-code - Later weeks will persist reservation data to disk under the account directory.
	}


	// Completes a reservation for an account
	public void completeReservation(String accountNumber, String reservationNumber) {

	    if (accountNumber == null || accountNumber.isBlank()) {
	        throw new IllegalArgumentException("Account number cannot be null or blank.");
	    }

	    if (reservationNumber == null || reservationNumber.isBlank()) {
	        throw new IllegalArgumentException("Reservation number cannot be null or blank.");
	    }

	    Account account = getAccount(accountNumber);
	    if (account == null) {
	        throw new IllegalArgumentException("No account found for account number: " + accountNumber);
	    }

	    Reservation reservation = account.getReservation(reservationNumber);
	    if (reservation == null) {
	        throw new IllegalArgumentException("No reservation found for reservation number: " + reservationNumber);
	    }

	    reservation.complete();
	}

	// Cancels a reservation for an account
	public void cancelReservation(String accountNumber, String reservationNumber) {

	    if (accountNumber == null || accountNumber.isBlank()) {
	        throw new IllegalArgumentException("Account number cannot be null or blank.");
	    }

	    if (reservationNumber == null || reservationNumber.isBlank()) {
	        throw new IllegalArgumentException("Reservation number cannot be null or blank.");
	    }

	    Account account = getAccount(accountNumber);
	    if (account == null) {
	        throw new IllegalArgumentException("No account found for account number: " + accountNumber);
	    }

	    Reservation reservation = account.getReservation(reservationNumber);
	    if (reservation == null) {
	        throw new IllegalArgumentException("No reservation found for reservation number: " + reservationNumber);
	    }

	    reservation.cancel();
	}

	// Updates an existing reservation
	public void updateReservation(String accountNumber, Reservation reservation) {
	}

	// Calculates the price per night for a reservation
	public double calculatePricePerNight(Reservation reservation) {

	    if (reservation == null) {
	        throw new IllegalArgumentException("Reservation cannot be null.");
	    }

	    return reservation.calculatePricePerNight();
	}

	// Calculates the total price for a reservation
	public double calculateTotalReservationPrice(Reservation reservation) {

	    if (reservation == null) {
	        throw new IllegalArgumentException("Reservation cannot be null.");
	    }

	    return reservation.calculateTotalPrice();
	}

	
}
