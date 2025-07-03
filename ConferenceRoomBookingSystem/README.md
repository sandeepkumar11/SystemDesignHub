# Conference Room Booking System

## Problem Statement
Design and implement a Conference Room Booking System to manage the scheduling of conference rooms in an office. The system should allow users to book conference rooms for specific time slots, check room availability, cancel bookings, and list all bookings. The system must handle multiple rooms and ensure there are no conflicting bookings.

## Requirements

### 1. System Setup
- The system should support multiple conference rooms, each identified by a unique name or ID.
- Each room has a capacity (maximum number of people it can accommodate).
- The system operates within a single day, with time slots defined in hours (e.g., 9:00 to 17:00).

### 2. Core Functionalities
#### 2.1 Add a Conference Room
- Allow adding a new conference room to the system with a unique name/ID and a specified capacity.
- Example: Add a room named "Boardroom" with a capacity of 10 people.

#### 2.2 Book a Conference Room
- Users can book a room for a specific time slot (start time and end time) on a given day.
- A booking includes:
    - Room name/ID.
    - Start time and end time (in hours, e.g., 10:00 to 11:00).
    - Number of attendees (must not exceed the room's capacity).
- The system must check for conflicts:
    - A room cannot be booked if it is already reserved for any overlapping time slot.
- If the booking is successful, return a confirmation (e.g., booking ID or success message).
- If the booking fails (due to conflict or invalid input), return an appropriate error message.

#### 2.3 Cancel a Booking
- Allow users to cancel an existing booking using a unique booking ID or sufficient details (e.g., room name, time slot).
- If the booking exists, remove it and make the room available for that time slot.
- Return a confirmation or error message based on the operation's success.

#### 2.4 Check Room Availability
- Allow users to check the availability of a specific room for a given time slot.
- Return whether the room is available or list any conflicting bookings.
- Optionally, allow users to find all available rooms for a given time slot and number of attendees.

#### 2.5 List All Bookings
- Display all bookings in the system, including:
    - Room name/ID.
    - Time slot (start and end time).
    - Number of attendees.
- Optionally, filter bookings by room or time range.

### 3. Constraints
- Time slots are in hourly increments (e.g., 9:00, 10:00, etc.).
- The system operates for a single day (no need to handle multiple days).
- Room capacity must be a positive integer.
- Start time must be before end time, and both must be within the operational hours (e.g., 9:00 to 17:00).
- No two bookings for the same room can overlap in time.
- Input validation is required (e.g., invalid time slots, non-existent rooms, or exceeding room capacity should be handled gracefully).

### 4. Input/Output Format
- **Input**: The system can accept inputs via function calls, command-line interface, or any other suitable method (depending on implementation).
    - Example commands:
        - Add room: `add_room("Boardroom", 10)`
        - Book room: `book_room("Boardroom", "10:00", "11:00", 8)`
        - Cancel booking: `cancel_booking(booking_id)`
        - Check availability: `check_availability("Boardroom", "10:00", "11:00")`
        - List bookings: `list_bookings()`
- **Output**:
    - For successful operations, return a confirmation message or relevant data (e.g., booking ID, list of bookings).
    - For failed operations, return a descriptive error message (e.g., "Room already booked", "Invalid time slot").

### 5. Assumptions
- All times are in 24-hour format.
- The system does not need to persist data (in-memory storage is sufficient).
- No authentication or user roles are required (assume a single user or admin).
- Operational hours are fixed (e.g., 9:00 to 17:00).

### 6. Evaluation Criteria
- **Correctness**: The system should handle all edge cases (e.g., overlapping bookings, invalid inputs).
- **Code Quality**: Code should be modular, readable, and well-structured with appropriate use of data structures.
- **Efficiency**: Operations like booking, canceling, and checking availability should be efficient (e.g., avoid linear scans where possible).
- **Extensibility**: The design should allow easy addition of new features (e.g., multiple days, user authentication).
- **Input Validation**: Handle invalid inputs gracefully with clear error messages.

### 7. Example Scenario
1. Add rooms:
    - `add_room("Boardroom", 10)`
    - `add_room("Meeting Room A", 5)`
2. Book rooms:
    - `book_room("Boardroom", "10:00", "11:00", 8)` → Success (Booking ID: 1)
    - `book_room("Boardroom", "10:30", "11:30", 6)` → Fails (overlapping time slot)
    - `book_room("Meeting Room A", "10:00", "11:00", 4)` → Success (Booking ID: 2)
3. Check availability:
    - `check_availability("Boardroom", "10:00", "11:00")` → Not available
    - `check_availability("Boardroom", "12:00", "13:00")` → Available
4. Cancel booking:
    - `cancel_booking(1)` → Success, "Boardroom" now available from 10:00 to 11:00
5. List bookings:
    - Output: `Meeting Room A, 10:00-11:00, 4 attendees`

### 8. Bonus Features (Optional)
- Support booking across multiple days.
- Allow recurring bookings (e.g., every Monday at 10:00).
- Implement a priority system for bookings (e.g., higher-priority bookings can override lower-priority ones).
- Suggest alternative time slots or rooms if a booking request conflicts.

### 9. Implementation Notes
- Choose a programming language you are comfortable with (e.g., Python, Java, C++, JavaScript).
- Use appropriate data structures (e.g., lists, hash maps, interval trees) for efficient time slot management.
- Ensure thread-safety if concurrent bookings are considered (optional, depending on the problem scope).
- Provide a simple interface for testing (e.g., command-line inputs or function calls).