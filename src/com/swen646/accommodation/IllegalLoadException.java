package com.swen646.accommodation;

/**
 * Thrown when an account or reservation file cannot be loaded.
 * This is an unchecked exception (extends RuntimeException).
 */
public class IllegalLoadException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String accountNumber;
    private final String fileName;
    private final String loadType; // "account" or "reservation"

    /**
     * Creates an exception indicating a failure to load a required file.
     *
     * @param accountNumber the account being loaded when the failure occurred
     * @param fileName      the file that could not be loaded
     * @param loadType      what failed ("account" or "reservation")
     */
    public IllegalLoadException(String accountNumber, String fileName, String loadType) {
        super("IllegalLoadException: Failed to load " + loadType
                + " file '" + fileName + "' for account '" + accountNumber + "'.");
        this.accountNumber = accountNumber;
        this.fileName = fileName;
        this.loadType = loadType;
    }

    @Override
    public String toString() {
        return getMessage();
    }

    // Optional getters (maybe for testing)
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public String getLoadType() {
        return loadType;
    }
}
