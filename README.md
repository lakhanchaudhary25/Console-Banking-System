# 🏦 Enterprise Console Bank Engine

An advanced, pure-Java backend banking application built to demonstrate enterprise-level software architecture, strict Object-Oriented design, and in-memory data management without relying on external frameworks.

## 🎯 The Objective
The goal of this project was to move beyond basic procedural programming and build a system that reflects real-world backend engineering standards. The application completely decouples the user interface from business logic, ensuring a scalable, secure, and easily testable codebase.

## 🏗️ Architecture & Design
This system is engineered using a strict **Three-Tier Architecture**:
* **Presentation Layer (App):** Acts as the Composition Root. It handles user input, catches and formats domain exceptions, and prevents system crashes without touching business logic.
* **Service Layer (Business Logic):** The "brain" of the bank. It validates rules, processes transfers, manages transaction histories, and creates safe Data Transfer Objects (DTOs).
* **Data Access Layer (Repository):** An in-memory database utilizing `HashMap` for $O(1)$ retrieval speeds, protected by Java 8 `Optional` to eliminate NullPointerExceptions.

## 🚀 Key Technical Features
* **Manual Transaction Rollbacks:** Engineered a custom `try-catch` rollback system inside the transfer method to simulate ACID properties and prevent data loss (the "Infinite Money Glitch") during failed multi-account operations.
* **Custom Exception Handling:** Built specific unchecked domain exceptions (`InsufficientFundsException`, `AccountNotFoundException`) to create a clear, fail-fast boundary between user errors and system failures.
* **Strict Encapsulation:** Protected entity states by entirely avoiding setters. All balance modifications are routed securely through validated instance methods.

## 🛠️ Tech Stack
* **Language:** Java (JDK 14+ recommended for Records)
* **Core Concepts:** OOP, Collections Framework, Stream API, Exception Handling, Dependency Injection (Constructor-based)

## ⚙️ How to Run Locally

1. Clone the repository: 
   ```bash
   git clone [https://github.com/lakhanchaudhary25/console-bank.git](https://github.com/lakhanchaudhary25/console-bank.git)
