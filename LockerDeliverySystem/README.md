# Locker Delivery System Problem Statement

## Objective
Design and implement a Locker Delivery System to manage the allocation, delivery, and pickup of packages using automated lockers. The system should efficiently assign lockers to packages, handle package deliveries, and facilitate customer pickups using unique codes.

## Problem Description
An e-commerce company wants to implement a locker-based delivery system where packages are stored in lockers of various sizes for customers to pick up at their convenience. The system should allow delivery personnel to place packages in appropriately sized lockers, generate pickup codes for customers, and enable customers to retrieve their packages using these codes. The system must also handle locker availability and return scenarios.

## Requirements

### Functional Requirements
1. **Locker Management**:
    - The system should support lockers of different sizes (e.g., Small, Medium, Large).
    - Each locker can store only one package at a time.
    - Lockers should be assigned based on package size, prioritizing the smallest suitable locker.

2. **Package Delivery**:
    - Delivery personnel can input package details (package ID, size) and the system assigns an available locker of appropriate size.
    - Upon successful assignment, the system generates a unique pickup code for the customer and notifies them (assume notification is printed to console for simplicity).
    - If no suitable locker is available, the system should indicate failure.

3. **Package Pickup**:
    - Customers can input the pickup code to retrieve their package.
    - The system verifies the code and, if valid, opens the corresponding locker and marks it as available.
    - If the code is invalid or expired, the system should indicate an error.

4. **Package Return**:
    - Customers can return a package by placing it back in a locker.
    - The system assigns an available locker and generates a new return code for the return process.

5. **Locker Status**:
    - The system should track the status of each locker (available or occupied).
    - Provide functionality to view the status of all lockers.

### Non-Functional Requirements
1. **Scalability**: The system should handle a large number of lockers and packages efficiently.
2. **Reliability**: Ensure accurate assignment of lockers and validation of pickup/return codes.
3. **Extensibility**: The design should allow easy addition of new features, such as multiple locker locations or time-based code expiration.
4. **Simplicity**: The system should have a clear and intuitive interface for both delivery personnel and customers.

## Input and Output

### Input
- Initialize the system with a specified number of lockers of each size (e.g., 10 Small, 5 Medium, 3 Large).
- Commands to perform operations like:
    - Add a package (package ID, size).
    - Pickup a package (pickup code).
    - Return a package (package ID, size).
    - View locker status.

### Output
- For package delivery: Print the assigned locker ID and pickup code or an error if no locker is available.
- For package pickup: Confirm package retrieval and mark locker as available or indicate invalid/expired code.
- For package return: Print the assigned locker ID and return code or an error if no locker is available.
- For locker status: Display a list of all lockers with their status (e.g., Locker ID, Size, Status: Available/Occupied).

## Constraints
- Locker sizes: Small, Medium, Large.
- Package sizes: Small (fits in Small or larger), Medium (fits in Medium or larger), Large (fits only in Large).
- Pickup codes are unique 6-digit alphanumeric codes.
- Assume all inputs are valid unless specified (e.g., package size matches defined sizes).
- The system should handle at least 100 lockers and 1000 packages.

## Example Workflow
1. **Initialize System**:
    - Create 5 Small, 3 Medium, and 2 Large lockers.
    - Output: System initialized with 10 lockers.

2. **Add Package**:
    - Input: Package P1, Size: Medium.
    - Output: Assigned Locker M1, Pickup Code: ABC123.

3. **Pickup Package**:
    - Input: Code ABC123.
    - Output: Package P1 retrieved, Locker M1 is now available.

4. **Return Package**:
    - Input: Package P1, Size: Medium.
    - Output: Assigned Locker M2, Return Code: XYZ789.

5. **View Locker Status**:
    - Output:
      ```
      Locker S1: Small, Available
      Locker M2: Medium, Occupied
      Locker L1: Large, Available
      ...
      ```

## Implementation Guidelines
- Use an object-oriented approach to design the system.
- Suggested classes: `Locker`, `Package`, `LockerDeliverySystem`.
- Use appropriate data structures (e.g., lists, hash maps) to manage lockers and codes efficiently.
- Ensure thread-safety is considered if multiple operations occur concurrently (optional for basic implementation).
- Write clean, modular, and well-documented code.
- Language: Choose any programming language (e.g., Python, Java, C++).

## Evaluation Criteria
- **Correctness**: The system handles all operations as per requirements.
- **Code Quality**: Code is modular, readable, and well-documented.
- **Efficiency**: Efficient locker assignment and code validation.
- **Edge Cases**: Handle cases like no available lockers, invalid codes, or incorrect package sizes.
- **Extensibility**: The design allows for easy addition of new features.