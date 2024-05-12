# Airline Reservation System

This project is a Java Spring Boot application for an airline reservation system. The system provides users with online ticket booking and seat reservation for both domestic and international flights, as well as information about available flights.

## Project Overview

This project encompasses several key features:

### 1. Home Page

- Users enter the airline's website.
- They select their travel purpose (vacation, business, other) on the home page.

### 2. Selecting Flight Parameters

- Users choose the desired flight date, departure, and destination airports.

### 3. Viewing Flight Options

- Users receive a list of flight options offered by partner airlines.
- They can check details such as prices, schedules, and additional services for each flight option.

### 4. Flight Selection

- Users select the most suitable flight option.

### 5. Entering Passenger Information

- Users enter their name, surname, preferred seating section, and contact information.
- If the user is registered, automatic information retrieval from their profile is available.

### 6. Payment and Receipt Generation

- Users select their preferred payment methods.
- Upon completion of payment, a receipt is generated and sent via email.

## Project Components

This project consists of the following classes:

- **Airport:** Stores general information about airports.
- **Plane:** Contains information about aircraft.
- **Ticket:** Holds information about the ticket purchased by a passenger.
- **Flight:** Manages data related to flights at airports.
- **Passenger:** Stores passenger information.
- **NoticeBoard:** Maintains a list of flights scheduled for the day and any cancellations.

## Getting Started

To run the program locally, follow these steps:

1. Clone the code from this repository or download it as a ZIP file.
2. Install Java SE Development Kit (JDK) and Maven.
3. Navigate to the project directory in your terminal or command prompt and execute the command `mvn spring-boot:run`.

## Technologies

This project utilizes 
- Java Spring Boot
- Microservice
- Spring Data JPA
- Spring Web
- PostgreSQL
