package com.swen646.accommodation;

/**
 * Thrown when an operation is not allowed based on reservation state or timing.
 * This is an unchecked exception.
 */
public class IllegalOperationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String operation;
    private final String accountNumber;
    private final String reservationNumber;
    private final String reason;

    /**
     * Creates an exception indicating an illegal operation was attempted.
     *
     * @param operation         the operation attempted (e.g., "cancel", "complete", "update")
     * @param accountNumber     the account id
     * @param reservationNumber the reservation id
     * @param reason            specific reason the operation is illegal
     */
    public IllegalOperationException(String operation, String accountNumber, String reservationNumber, String reason) {
        super("IllegalOperationException: Cannot '" + operation + "' reservation '" + reservationNumber
                + "' for account '" + accountNumber + "'. Reason: " + reason);
        this.operation = operation;
        this.accountNumber = accountNumber;
        this.reservationNumber = reservationNumber;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return getMessage();
    }

    // Optional getters
    public String getOperation() {
        return operation;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public String getReason() {
        return reason;
    }
}
