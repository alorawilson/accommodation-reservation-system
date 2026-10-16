# Accommodation Reservation Management System

A Java-based accommodation reservation management system developed as part of **SWEN 646 – Software Design and Implementation** at the University of Maryland Global Campus (UMGC).

This project demonstrates object-oriented software development through a reservation management system supporting three accommodation types: hotels, cabins, and houses.

## Project Overview

The system manages customer accounts and their associated lodging reservations. It applies object-oriented programming principles to organize business logic, calculate reservation pricing, validate operations, and manage reservation lifecycle states.

## Key Features

- **Account Management:** Create, retrieve, and update customer accounts.
- **Reservation Management:** Add, update, complete, and cancel lodging reservations.
- **Multiple Accommodation Types:** Support hotel, cabin, and house reservations through inheritance and polymorphism.
- **Dynamic Pricing:** Calculate accommodation-specific nightly rates and finalized reservation totals.
- **Lifecycle Management:** Enforce DRAFT, COMPLETED, and CANCELLED reservation states.
- **Exception Handling:** Detect duplicate objects and invalid reservation operations.
- **In-Memory Data Management:** Organize accounts and reservations using Java collections.

## Technologies and Concepts

- Java
- Object-Oriented Programming (OOP)
- Inheritance and Polymorphism
- Abstract Classes and Enumerations
- Encapsulation
- Java Collections
- Custom Exception Handling
- UML Class Modeling
- Software Design Documentation

## Architecture

The application uses a central `Manager` class to coordinate account and reservation operations.

An abstract `Reservation` class defines shared attributes and lifecycle behavior, while `HotelReservation`, `CabinReservation`, and `HouseReservation` implement accommodation-specific pricing calculations.

The `Account` class maintains customer information and associated reservations.

## Implementation Status

The current implementation focuses on the Java domain model, business logic, and in-memory reservation management.

The Software Design Document also includes proposed graphical interface mockups and a file-based persistence architecture. These features are documented for future development and are not implemented in the current version.

## Project Documentation

- [Software Design Document](docs/software-design-document.pdf)
- [UML Class Diagram](docs/class-diagram.png)
- [Requirements Matrix](docs/RequirementsMatrix_.pdf)

## Academic Context

**Course:** SWEN 646 – Software Design and Implementation  
**Institution:** University of Maryland Global Campus  
**Project Type:** Java Object-Oriented Software Development

This project demonstrates the translation of software requirements into an object-oriented architecture, implementation of business rules, and development of supporting technical documentation.
