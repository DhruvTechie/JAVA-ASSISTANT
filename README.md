[README.md](https://github.com/user-attachments/files/22580934/README.md)
# GFG-Type Documentary PDF Viewer

A comprehensive Java-based authentication and user management system with Gmail integration, database connectivity, and interactive console interface.

## 🚀 Important Setup Steps (MUST DO BEFORE RUNNING)

### 1. Extract the Database
- **Extract the MySQL database** from the provided database file
- Ensure MySQL server is running on your system
- Create a database named `authentication` in your MySQL server
- Import the database schema and data

### 2. Create Required Directory
- **Create a folder named `tampStore` on your D: drive**
- The application uses this directory for temporary storage and file operations
- Ensure the D: drive has sufficient permissions for read/write operations

### 3. Database Configuration
- Update the database connection details in `src/main/java/org/example/connection/ConnectionDataBase.java`
- Modify the following variables according to your MySQL setup:
  ```java
  String url = "jdbc:mysql://localhost:3306/authentication";
  String user = "root";
  String pass = "142005"; // Change to your MySQL password
  ```

## 🛠️ Technologies & Skills Used

### Core Technologies
- **Java 21** - Main programming language
- **Maven** - Build automation and dependency management
- **MySQL** - Database management system
- **JDBC** - Database connectivity

### Libraries & Dependencies
- **Jakarta Mail API 2.1.3** - Email functionality
- **Eclipse Angus Jakarta Mail 1.0.0** - Email implementation
- **MySQL Connector/J 9.4.0** - MySQL database driver

### Programming Concepts & Skills
- **Object-Oriented Programming (OOP)**
  - Classes and Objects
  - Inheritance and Polymorphism
  - Encapsulation
  - Method Overloading

- **Data Structures**
  - Custom Stack implementation with push, pop, peek operations
  - Custom Queue implementation with enqueue, dequeue operations
  - HashMap for temporary data storage

- **Database Operations**
  - JDBC Connection Management
  - Prepared Statements for secure queries
  - ResultSet handling
  - CRUD operations (Create, Read, Update, Delete)

- **Email Integration**
  - SMTP configuration
  - Gmail API integration
  - OTP (One-Time Password) generation and verification
  - Email authentication

- **Input Validation & Error Handling**
  - Custom input verification system
  - Exception handling with try-catch blocks
  - User input sanitization

- **Console UI & User Experience**
  - Colored console output using ANSI escape codes
  - Interactive menu system
  - Thread.sleep() for better user experience
  - Formatted display with colors and styling

- **Security Features**
  - Password encryption (simple reverse + key-based encryption)
  - Gmail verification for account creation
  - Primary key constraints (Gmail and Username)
  - Duplicate account prevention

## 📋 Program Flow

### Main Application Flow
```
JavaAssistant (Main Class)
    ↓
TampJavaAssistant.handler()
    ↓
User Choice Menu:
    1. Host Login
    2. User Authentication
    3. Exit Program
```

### User Authentication Flow
```
User Selection (Choice 2)
    ↓
ConnectionDataBase.connection(2)
    ↓
AuthHandler.databaseConnection()
    ↓
AuthHandler.userDefine()
    ↓
User Options:
    1. Log In (existing account)
    2. Sign Up (new account)
    3. Exit
```

### Sign Up Process
```
Sign Up Selection
    ↓
Gmail Verification:
    - Generate 4-digit OTP
    - Send email via GmailSender
    - Verify OTP (3 attempts)
    ↓
Username & Password Setup:
    - Check username uniqueness
    - Password validation
    - Password encryption
    ↓
Database Insertion:
    - Store encrypted credentials
    - Auto-login after successful signup
    ↓
User Interface Access
```

### Log In Process
```
Log In Selection
    ↓
Username & Password Verification:
    - Decrypt stored password
    - Compare with input
    ↓
Success → User Interface
Failure → Retry or Exit
```

### Host Authentication Flow
```
Host Selection (Choice 1)
    ↓
ConnectionDataBase.connection(1)
    ↓
HostAuthHandler.hostLogIn()
    ↓
Host Credentials Verification
    ↓
Host Interface Access
```

## 🏗️ Project Structure

```
src/main/java/org/example/
├── JavaAssistant.java          # Main entry point
├── TampJavaAssistant.java      # Main handler class
├── TampGMail.java             # Gmail testing utility
├── auth/                      # Authentication package
│   ├── AuthHandler.java       # Main auth controller
│   ├── LogInHandler.java      # Login functionality
│   ├── LogOutHandler.java     # Logout functionality
│   ├── SignInHandler.java     # Registration functionality
│   └── SignOutHandler.java    # Account deletion
├── connection/                # Database package
│   └── ConnectionDataBase.java # Database connectivity
├── datastructure/             # Custom data structures
│   ├── InputVerification.java # Input validation
│   ├── Queue.java            # Custom queue implementation
│   └── Stack.java            # Custom stack implementation
├── gmail/                     # Email package
│   ├── Color.java            # Console color codes
│   └── GMailSender.java      # Email sending functionality
├── host/                      # Host management
│   ├── DataBaseManage.java   # Host database operations
│   └── HostAuthHandler.java  # Host authentication
└── user/                      # User management
    ├── MainDataBaseInfo.java # User data operations
    └── ShowData.java         # Data display utilities
```

## 🚀 How to Run

1. **Prerequisites Setup** (Complete the 3 important steps above)
2. **Compile the project:**
   ```bash
   mvn compile
   ```
3. **Run the application:**
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.JavaAssistant"
   ```
   Or simply run the main class from your IDE

## 📧 Gmail Configuration

The application uses a dedicated Gmail account for sending verification emails:
- **Email:** projectbootsem2@gmail.com
- **App Password:** jumb dqlj yuok eeqt
- **SMTP Settings:** smtp.gmail.com:587

## 🔒 Security Features

- **Password Encryption:** Simple reverse string + key-based encryption
- **Gmail Verification:** OTP-based email verification for new accounts
- **Primary Key Constraints:** Gmail and Username uniqueness
- **Input Validation:** Comprehensive input verification system
- **SQL Injection Prevention:** Prepared statements for database queries

## 🎨 User Interface Features

- **Colored Console Output:** ANSI color codes for better visual experience
- **Interactive Menus:** User-friendly navigation system
- **Loading Animations:** Thread.sleep() for smooth transitions
- **Error Messages:** Clear and colored error notifications
- **Success Confirmations:** Visual feedback for successful operations

## 📊 Database Schema

The application uses a MySQL database named `authentication` with the following structure:
- **Table:** `auth`
- **Columns:**
  - `gmail` (Primary Key) - User's email address
  - `username` (Primary Key) - User's chosen username
  - `password` - Encrypted password

## 🔧 Customization

- **Database Connection:** Modify connection details in `ConnectionDataBase.java`
- **Gmail Settings:** Update email credentials in `GMailSender.java`
- **Colors:** Customize console colors in `Color.java`
- **Encryption:** Modify password encryption logic in `SignInHandler.java`

## 📝 Notes

- The application requires Java 21 or higher
- MySQL server must be running
- Internet connection required for Gmail functionality
- D: drive must be accessible for temporary storage
- The application uses console-based interface (no GUI)

## 🤝 Contributing

This is an individual project demonstrating various Java programming concepts, database connectivity, email integration, and user authentication systems.

---

**Author:** Individual Project - Java Assistant System  
**Version:** 1.0-SNAPSHOT  
**Java Version:** 21  
**Build Tool:** Maven
