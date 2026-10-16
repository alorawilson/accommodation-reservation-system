package com.swen646.accommodation;

/**
 * Thrown when a duplicate account or reservation is detected.
 * This is an unchecked exception.
 */
public class DuplicateObjectException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String accountNumber;
    private final String reservationNumber;

    /**
     * Creates an exception for a duplicate account.
     *
     * @param accountNumber the duplicate account number
     */
    public DuplicateObjectException(String accountNumber) {
        super("DuplicateObjectException: Account with number '" + accountNumber + "' already exists.");
        this.accountNumber = accountNumber;
        this.reservationNumber = null;
    }

    /**
     * Creates an exception for a duplicate reservation within an account.
     *
     * @param accountNumber     the account number
     * @param reservationNumber the duplicate reservation number
     */
    public DuplicateObjectException(String accountNumber, String reservationNumber) {
        super("DuplicateObjectException: Reservation '" + reservationNumber
                + "' already exists for account '" + accountNumber + "'.");
        this.accountNumber = accountNumber;
        this.reservationNumber = reservationNumber;
    }

    @Override
    public String toString() {
        return getMessage();
    }

    // Optional getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }
}
