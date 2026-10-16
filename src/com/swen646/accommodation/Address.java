package com.swen646.accommodation;

public class Address {
	
	private String street;

	private String city;

	private String state;

	private String zip;

	private String country;
	
	
	// Creates a new address with required fields
	public Address(String street, String city, String state, String zip, String country) {

	    if (street == null || street.isBlank()) {
	        throw new InvalidReservationException("Street cannot be null or blank.");
	    }

	    if (city == null || city.isBlank()) {
	        throw new InvalidReservationException("City cannot be null or blank.");
	    }

	    if (state == null || state.isBlank()) {
	        throw new InvalidReservationException("State cannot be null or blank.");
	    }

	    if (zip == null || zip.isBlank()) {
	        throw new InvalidReservationException("Zip cannot be null or blank.");
	    }

	    if (country == null || country.isBlank()) {
	        throw new InvalidReservationException("Country cannot be null or blank.");
	    }

	    this.street = street;
	    this.city = city;
	    this.state = state;
	    this.zip = zip;
	    this.country = country;
	}

	// Returns address information
	@Override
	public String toString() {
	    return "<street>" + street + "</street>\n"
	            + "        <city>" + city + "</city>\n"
	            + "        <state>" + state + "</state>\n"
	            + "        <zip>" + zip + "</zip>\n"
	            + "        <country>" + country + "</country>";
	}

	// Returns street address
	public String getStreet() {
	    return street;
	}

	// Returns city
	public String getCity() {
	    return city;
	}

	// Returns state
	public String getState() {
	    return state;
	}

	// Returns ZIP code
	public String getZip() {
	    return zip;
	}

	// Returns country
	public String getCountry() {
	    return country;
	}

	// Set methods so that address can be updated in controlled manner
	
	// Updates street address
	public void setStreet(String street) {
	    this.street = street;
	}

	// Updates city
	public void setCity(String city) {
	    this.city = city;
	}

	// Updates state
	public void setState(String state) {
	    this.state = state;
	}

	// Updates ZIP code
	public void setZip(String zip) {
	    this.zip = zip;
	}

	// Updates country
	public void setCountry(String country) {
	    this.country = country;
	}


}
