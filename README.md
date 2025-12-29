# 📚 Library Management System

A full-stack web application designed to manage library operations efficiently. This system allows administrators to manage a collection of books, maintain member records, and track borrowing transactions, including automatic fine calculation for overdue items.

## 🚀 Project Overview

The Library Management System is built using a modern tech stack with a **Spring Boot** backend and a **Tailwind CSS** frontend. It follows a clean architecture with clear separation between entities, services, and controllers to ensure scalability and maintainability.

### Key Features
* **Book Management**: Add, update, search (by title/author), and delete books from the catalog.
* **Member Management**: Register new library members and manage their profiles.
* **Transaction Tracking**: 
    * **Issue Books**: Track which book is issued to which member with automatic due date setting (14 days).
    * **Return Books**: Process returns and automatically update book availability.
    * **Fine Calculation**: Automatically calculates a fine of $2.00 per day if a book is returned after its due date.
* **Real-time Availability**: Automatically manages `availableCopies` for books as they are issued or returned.
* **Dashboard**: A responsive web interface to visualize all library activities.

## 🛠️ Tech Stack

### Backend
* **Java 17**: Core programming language.
* **Spring Boot 3.2.0**: Framework for building the REST API.
* **Spring Data JPA / Hibernate**: For Object-Relational Mapping (ORM) and database interactions.
* **MySQL**: Relational database for persistent storage.
* **Jackson**: For seamless JSON serialization/deserialization.

### Frontend
* **HTML5/JavaScript**: Core structure and logic.
* **Tailwind CSS**: For a modern, responsive user interface design.
* **Fetch API**: For asynchronous communication with the backend.

## 📂 Project Structure
```bash
library-management-system/
├── frontend/                  # Web Interface
│   └── index.html             # Single-page application using Tailwind CSS
├── backend/                   # Spring Boot Application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/library/management/
│   │   │   │   ├── config/    # Web and Security configurations (e.g., CorsConfig)
│   │   │   │   ├── controller/# REST API Endpoints (Book, Member, Transaction)
│   │   │   │   ├── entity/    # JPA Database Models (Book, Member, Transaction)
│   │   │   │   ├── exception/ # Global error and exception handling
│   │   │   │   ├── repository/# Spring Data JPA Repositories
│   │   │   │   ├── service/   # Business logic (Fines, Stock, Issuing)
│   │   │   │   └── LibraryManagementSystemApplication.java # Main entry point
│   │   │   └── resources/
│   │   │       └── application.properties # Database and server configuration
│   │   └── test/              # Unit and integration tests
│   ├── pom.xml                # Maven dependencies and build configuration
│   └── target/                # Compiled bytecode and build artifacts
└── README.md                  # Project documentation
```

## ⚙️ Setup & Installation

### Prerequisites
* JDK 17 or higher
* Maven
* MySQL Server

### Database Setup
1. Create a database named `library_db` in MySQL.
2. Update the `src/main/resources/application.properties` file with your MySQL username and password.

### Running the Application
1. **Backend**: 
   Navigate to the `backend` folder and run:
   ```bash
   mvn spring-boot:run
   ```

2. **Frontend**:
    Open `frontend/index.html` in any modern web browser.