# BookMyShow — Machine Coding

---

## 1. Project Overview
Build a lightweight BookMyShow-like service with the minimal features needed to demonstrate modeling and basic booking logic. Keep the system intentionally small: core domain entities, basic seat availability, and simple booking confirmation.

## 2. Scope (In-scope / Out-of-scope)

**In-scope**
- Domain entities: User, Location (City), Theatre, Screen, Seat, Show, Booking.
- APIs to: create/read movies (optional), create/read theatres & screens, view seat layout for a show, create a booking (no holds), and cancel a booking.
- Persistence: in-memory storage is acceptable; provide repository interfaces.
- Unit tests for booking flow and seat availability.

**Out-of-scope**
- Payment integration, seat hold TTL/expiry, concurrency workers, dynamic pricing, admin dashboards, metrics.

## 3. Entities (minimal fields)

### User
- `userId` (UUID)
- `name` (string)
- `email` (string)

### Location
- `cityId` (UUID)
- `name` (string)

### Theatre
- `theatreId` (UUID)
- `name` (string)
- `cityId` (UUID)
- `address` (string)

### Screen
- `screenId` (UUID)
- `theatreId` (UUID)
- `name` (string)
- `seatLayoutId` (UUID)

### Seat
- `seatId` (string, e.g., A1)
- `screenId` (UUID)
- `row` (string)
- `number` (int)
- `type` (REGULAR, PREMIUM)
- `price` (decimal)

### Show
- `showId` (UUID)
- `screenId` (UUID)
- `movieTitle` (string)
- `startTime` (timestamp)
- `endTime` (timestamp)

### Booking
- `bookingId` (UUID)
- `userId` (UUID)
- `showId` (UUID)
- `seatIds` (list of seatId)
- `status` (CONFIRMED, CANCELLED)
- `createdAt` (timestamp)

## 4. Simplified Functional Requirements
- Create and list `User`, `Location`, `Theatre`, `Screen`, and `Show` records (CRUD optional; at least create + read).
- Retrieve seat layout and availability for a given `showId`.
- Create a booking: client supplies `userId`, `showId`, and `seatIds`. The operation must verify seats are available and then atomically mark them as BOOKED and create a Booking record.
- Cancel a booking: marks seats AVAILABLE and booking CANCELLED.

_Note:_ This simplified version **does not** implement temporary holds. Booking is immediate and atomic.

## 5. API Endpoints (suggested)
- `POST /users` — create user.
- `GET /locations/{cityId}/theatres` — list theatres in a city.
- `POST /theatres` — create theatre (name, cityId, address).
- `POST /theatres/{theatreId}/screens` — create screen with seat layout.
- `POST /shows` — create a show (screenId, movieTitle, startTime).
- `GET /shows/{showId}/seats` — get seat layout + availability for the show.
- `POST /bookings` — create booking `{ userId, showId, seatIds }` -> returns bookingId or error if any seat not available.
- `POST /bookings/{bookingId}/cancel` — cancel booking.

## 6. Concurrency & Correctness (minimal)
- Ensure atomic booking: when two requests try to book the same seat(s) concurrently, only one succeeds. For the assignment, implement a simple locking mechanism at the in-memory repository level (e.g., synchronized blocks or per-show mutexes) or optimistic compare-and-swap.
- Return `409 Conflict` when requested seat(s) are already booked.

## 7. Data Storage
- In-memory repositories (maps) keyed by entity IDs are fine.
- Provide repository interfaces so persistent DB can be swapped later.

## 8. Testing
- Unit tests for:
    - Booking happy path (available seats -> booking created, seats marked booked).
    - Attempt to book already-booked seat -> failure.
    - Cancel booking -> seats become available.
- Optionally simulate two concurrent booking attempts in a unit test to verify locking.

## 9. Acceptance Criteria
- Minimal API runs locally and supports the flows above.
- No double-booking in concurrent tests.
- Clear, readable code with separation between controllers, services, and repositories.

## 10. Deliverables
- README (this simplified doc)
- Implementation of core APIs
- Unit tests covering booking and cancellation
- README instructions to run locally (start server, sample seed data)

---