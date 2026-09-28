Imagine you’re arriving at a busy parking lot, eager to park your car. At the entrance, you’re issued a ticket. 
You then drive in, find a spot suited to your vehicle’s size, and park. Later, when you prepare to leave, you 
present your ticket at the exit, the system calculates your fee, and the spot is freed up for the next vehicle. 
Behind the scenes, the parking lot is assigning spots based on vehicle size, recording entry and exit times, 
and updating availability for new arrivals. Now, let’s design a parking lot system that handles all this.

# Requirements

Here are the key functional requirements we’ve identified:

1. The parking lot has multiple parking spots, including compact, regular, and oversized spots.
2. The parking lot supports parking for motorcycles, cars, and trucks. 
3. Customers can park their vehicles in spots assigned based on vehicle size. 
4. Customers receive a parking ticket with vehicle details and entry time at the entry point and pay a fee based on duration,
vehicle size, and time of day at the exit point.

Below are the non-functional requirements:

1. The system must scale to support large parking lots with many spots and vehicles. 
2. The system must reliably track spot assignments and ticket details to ensure accurate operations.

# Core Objects

1. Vehicle: This object represents a vehicle that needs a spot. It encapsulates details like the license plate and size (small
for motorcycle, medium for cars, large for trucks).
2. Parking Spot: This object models an individual parking spot in the parking lot.
3. Ticket: This object represents a parking ticket issued when a vehicle enters the parking lot. It stores critical details, 
including the ticket ID, the associated Vehicle, the assigned ParkingSpot, and entry time, which are later used to calculate 
fees and free up spots upon exit.
4. ParkingManager: This object is responsible for parking lot's spot allocation, managing the assignment, lookup and release of
ParkingSpot instance.
5. ParkingLot: This acts as facade, providing a central interface to manage the system's key functionalities: vehicle entry, spot
assignment, ticketing, and fee calculation. It keeps its logic lightweight by delegating tasks such as spot allocation to the 
ParkingManager, fee computation to a FareCalculator class, and coordinating the flow of vehicles in and out without handling the details.

**Design choice:** We chose these five objects to separate concerns. Vehicle and ParkingSpot define the core physical entities, 
Ticket tracks sessions, ParkingManager handles allocation, and ParkingLot coordinates as a facade.