# Digital Wallet System Problem Statement

## Objective
Design and implement a Digital Wallet System that allows users to manage their funds, perform transactions, and view transaction history. The system should support basic wallet operations while ensuring data consistency and security.

## Functional Requirements
1. **User Management**:
    - Users can register with a unique user ID and name.
    - Each user has a wallet with an initial balance (default to 0).

2. **Wallet Operations**:
    - **Add Money**: Users can add money to their wallet.
    - **Send Money**: Users can transfer money to another user's wallet.
    - **Check Balance**: Users can view their current wallet balance.
    - **Transaction History**: Users can view a list of their past transactions (both sent and received).

3. **Transaction Management**:
    - Each transaction should have a unique transaction ID.
    - Transactions should include details: sender ID, receiver ID, amount, timestamp, and status (e.g., SUCCESS, FAILED).
    - Ensure that transactions are valid (e.g., sufficient balance, valid user IDs).
    - Support rollback in case of failed transactions to maintain wallet balance consistency.

4. **Concurrency Handling**:
    - The system should handle concurrent transactions correctly (e.g., multiple users sending/receiving money simultaneously).
    - Prevent race conditions that could lead to inconsistent wallet balances.

## Non-Functional Requirements
1. **Scalability**: The system should be designed to handle a large number of users and transactions efficiently.
2. **Reliability**: Ensure no money is lost or duplicated during transactions (data integrity).
3. **Performance**: Operations like adding money, sending money, and checking balance should be fast (O(1) or O(log n) complexity where possible).
4. **Security**: Protect against invalid transactions (e.g., negative amounts, non-existent users).
5. **Modularity**: The code should be modular, reusable, and easy to maintain.

## Input/Output Specifications
### Input Commands
The system should support the following commands (format can be flexible, e.g., method calls, CLI input, or API endpoints):
1. `register(user_id, name)`: Register a new user.
2. `add_money(user_id, amount)`: Add specified amount to the user's wallet.
3. `send_money(sender_id, receiver_id, amount)`: Transfer amount from sender to receiver.
4. `check_balance(user_id)`: Return the current balance of the user.
5. `transaction_history(user_id)`: Return the list of transactions for the user.

### Output
- For `register`: Confirmation message or error if user_id already exists.
- For `add_money`: Updated balance or error if user_id is invalid.
- For `send_money`: Success message with transaction ID or error (e.g., insufficient balance, invalid user).
- For `check_balance`: Current balance or error if user_id is invalid.
- For `transaction_history`: List of transactions (with details like transaction ID, sender, receiver, amount, timestamp, status) or empty list if no transactions.

## Constraints
- User IDs are unique strings (alphanumeric, max length 50).
- Amounts are non-negative floating-point numbers (up to 2 decimal places, max 1,000,000).
- Maximum number of users: 10,000.
- Maximum number of transactions per user: 1,000.
- Timestamps should be in a standard format (e.g., ISO 8601 or epoch time).

## Assumptions
- All transactions are in a single currency.
- No network failures or external system dependencies (e.g., payment gateways).
- The system runs in memory (no persistent storage required unless specified).
- Users cannot have a negative balance.

## Example Scenario
1. Register user: `register("user1", "Alice")` → Success
2. Register user: `register("user2", "Bob")` → Success
3. Add money: `add_money("user1", 100.50)` → Balance: 100.50
4. Send money: `send_money("user1", "user2", 50.00)` → Success, Transaction ID: T123, user1 balance: 50.50, user2 balance: 50.00
5. Check balance: `check_balance("user1")` → 50.50
6. Transaction history: `transaction_history("user2")` → [{T123, from: user1, to: user2, amount: 50.00, timestamp: "2025-07-08T07:31:00Z", status: SUCCESS}]

## Edge Cases to Handle
1. Attempting to register an existing user_id.
2. Sending money with insufficient balance.
3. Sending money to a non-existent user.
4. Adding negative or zero amounts.
5. Concurrent transactions affecting the same user's balance.
6. Empty transaction history for a new user.

## Deliverables
- Implement the system in a programming language of your choice.
- Ensure the code is well-structured, with clear separation of concerns (e.g., user management, wallet operations, transaction handling).
- Handle all edge cases and concurrency scenarios.
- Provide clear documentation or comments explaining the design and approach.
- (Optional) Include unit tests to validate functionality.