# **Low-Level Design Problem Statement: Expense Management Application**

## Overview

Design a backend system for **Expange**, an in-memory **expense management application** to track, split, and settle expenses among individuals and groups. The system must support expense logging, balance tracking, group-based management, and real-time user notifications — all with scalable, modular, and maintainable code using object-oriented design principles and appropriate design patterns.

---

## Functional Requirements

### 1. **User Management**

* Register: name, email or phone, password.
* Login & authentication.
* Update profile.
* View personal balance sheet: **owed amounts** and **owing amounts**.

### 2. **Group Management**

* Create/delete groups.
* Add/remove members (only registered users).
* View group members and group expense history.

### 3. **Expense Management**

* Add an expense:

    * Total amount.
    * Payer.
    * Participants.
    * Split Type: **Equal**, **Exact**, **Percentage**.
* Edit/delete expenses.
* View:

    * All personal/group expenses.
    * Filter by user, group, date.

### 4. **Debt Calculation & Settlement**

* System maintains a balance sheet per user.
* Settlements:

    * Record payment between two users.
    * Simplify debts to minimize transactions (like cycle detection/graph simplification).

### 5. **Notifications**

* Notify involved users when:

    * Expense is added/edited/deleted.
    * A group is updated.
    * A settlement is made.

### 6. **Expense History**

* View all expenses with:

    * Date
    * Split type
    * Participants
    * Amounts
* Filter: group, friend, date.

---

## Non-Functional Requirements

| Category        | Requirement                                                      |
| --------------- | ---------------------------------------------------------------- |
| Scalability     | Support **up to 50M users**, each with \~2–3 expenses/day.       |
| Concurrency     | Thread-safe expense edits, settlements.                          |
| Consistency     | Ensure atomic updates to balances and expenses.                  |
| Low Latency     | Fast reads/writes for balance sheet and expense creation.        |
| Reliability     | Avoid duplicate/incorrect balances.                              |
| Maintainability | Follow **SOLID principles**, clean modular code.                 |
| Security        | Encrypted password storage, authorized access for notifications. |

---

## Core Components

| Component             | Description                                    |
| --------------------- | ---------------------------------------------- |
| `User`                | ID, name, contact info, balance sheet.         |
| `Group`               | Users and their shared expenses.               |
| `Expense`             | Amount, payer, participants, split type.       |
| `Split`               | Equal, Exact, Percentage logic.                |
| `Transaction`         | Represents a **settlement** between two users. |
| `BalanceManager`      | Tracks and simplifies who owes whom.           |
| `ExpenseManager`      | Handles expense creation, edits, deletions.    |
| `NotificationService` | Notifies users for group/expense updates.      |

---

## Design Patterns

| Pattern   | Used In                                                                |
| --------- | ---------------------------------------------------------------------- |
| Singleton | `ExpenseManager`, `NotificationService`, `BalanceManager`              |
| Factory   | For creating different `Expense` types based on split type             |
| Strategy  | For executing different split strategies                               |
| Observer  | For notifying users on expense/group updates                           |
| Command   | Encapsulate `add`, `edit`, `delete` expense operations                 |
| Facade    | Simplified API access to interact with `ExpenseManager`, `UserManager` |

---

## Example Scenarios

### ➤ **Scenario 1: Equal Split**

* **User1** pays **₹1000** for group electricity bill.
* Participants: **User1, User2, User3, User4**.
* Each owes **₹250** to **User1**.

### ➤ **Scenario 2: Exact Split**

* **User2** pays **₹300** for food.
* Split: **User1: ₹100, User2: ₹100, User3: ₹100**.

### ➤ **Scenario 3: Settlement**

* **User1** settles ₹250 to **User2**.
* Balances are updated and simplified accordingly.

---

## Constraints and Assumptions

* One currency only.
* Users must register before use.
* Notifications are purely in-app (no SMS/email).
* No third-party payment integration.
* In-memory implementation only (no DB for now).
* Thread-safe operations are expected conceptually (JS is single-threaded, but design should support concurrency if ported to Java/Go).

---