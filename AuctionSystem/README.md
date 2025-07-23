# Online Auction System - Problem Statement

## Objective
Design and implement an Online Auction System that allows users to register, create auctions, place bids, and manage auction outcomes. The system should handle user interactions, auction lifecycle, and bid validation efficiently.

## Requirements

### 1. User Management
- Users can register with a unique username and email.
- Users can log in and log out of the system.
- Users can view their own profile, including their created auctions and bids.

### 2. Auction Management
- A logged-in user can create an auction with the following details:
    - Item name
    - Item description
    - Starting bid amount
    - Auction start time
    - Auction end time
- Auctions can be in one of three states: NOT_STARTED, ACTIVE, or ENDED.
- Users can view a list of all active auctions.
- Users can view details of a specific auction, including current highest bid and bidder (if any).

### 3. Bidding
- A logged-in user can place a bid on an active auction.
- A bid is valid only if:
    - The auction is in the ACTIVE state.
    - The bid amount is higher than the current highest bid (or starting bid if no bids exist).
- Users cannot bid on their own auctions.
- The system should track the highest bid and the bidder for each auction.
- When an auction ends, the system should determine the winner (the user with the highest bid) and notify the winner and the auction creator.

### 4. Auction Lifecycle
- An auction becomes ACTIVE at its start time.
- An auction becomes ENDED at its end time.
- Once an auction ends, no further bids can be placed.
- The system should automatically update auction states based on the current time.

### 5. Notifications
- Notify the auction creator and the winning bidder when an auction ends.
- Notifications can be simulated by printing messages to the console (e.g., "User [username] won auction [item name] with bid [amount]").

## Constraints
- The system should handle multiple users and auctions concurrently.
- Assume the system runs in memory (no database required).
- For simplicity, time can be simulated (e.g., using a system clock or a custom time variable).
- Input validation is required for:
    - Unique usernames and emails.
    - Valid bid amounts (positive numbers, higher than current highest bid).
    - Auction start time must be before end time.
- The system should be thread-safe if concurrent access is simulated.

## Input/Output Expectations
- **Input**: The system should accept commands to:
    - Register a user (e.g., `register username email`)
    - Log in/out (e.g., `login username`, `logout username`)
    - Create an auction (e.g., `create_auction username item_name description start_bid start_time end_time`)
    - List all active auctions (e.g., `list_auctions`)
    - View auction details (e.g., `view_auction auction_id`)
    - Place a bid (e.g., `bid username auction_id amount`)
    - Simulate time progression (e.g., `set_time current_time`)
- **Output**: Appropriate success/error messages for each command and notifications for auction outcomes.

## Sample Commands and Outputs
```
register alice alice@example.com
> User alice registered successfully.

login alice
> User alice logged in.

create_auction alice "Vintage Watch" "Antique pocket watch" 100 2025-07-12T10:00:00 2025-07-12T12:00:00
> Auction 1 created successfully.

register bob bob@example.com
> User bob registered successfully.

login bob
> User bob logged in.

bid bob 1 150
> Bid placed successfully.

set_time 2025-07-12T12:01:00
> Auction 1 ended. Winner: bob with bid 150.
> Notification: User bob won auction Vintage Watch with bid 150.
> Notification: User alice, your auction Vintage Watch ended. Winner: bob with bid 150.
```

## Evaluation Criteria
- **Correctness**: The system should correctly handle user registration, auction creation, bidding, and auction lifecycle.
- **Modularity**: Code should be well-organized, with clear separation of concerns (e.g., user management, auction management, bidding logic).
- **Error Handling**: Proper validation and error messages for invalid inputs or actions (e.g., bidding on own auction, invalid bid amount).
- **Scalability**: The design should be extensible for future features (e.g., adding categories to auctions).
- **Code Quality**: Clean, readable code with appropriate comments and naming conventions.
- **Thread Safety** (if applicable): Ensure no race conditions in concurrent scenarios.

## Assumptions
- Time is represented in a standard format (e.g., ISO 8601: `YYYY-MM-DDThh:mm:ss`).
- No external storage is required; all data is stored in memory.
- No GUI is required; a console-based interface is sufficient.
- Authentication is simplified (no passwords required for this exercise).

## Deliverables
- Implement the system in a programming language of your choice.
- Provide a main driver function/class to demonstrate the system with sample commands.
- Ensure the code is executable and handles the sample commands provided above.