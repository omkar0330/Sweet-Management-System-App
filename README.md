A Java-based web application designed to streamline the management of sweet inventory, users, and authentication for sweet shops or confectionery businesses.

🚀 Features
🔐 User Authentication using JWT

📦 Sweet Inventory Management (Add, Update, Delete, View)

👥 User Role Management

🛡️ Secure API Access with Spring Security

🗃️ Repository Layer using Spring Data JPA

🛠️ Tech Stack
Java 17

Spring Boot

Spring Security

JWT (JSON Web Tokens)

Hibernate / JPA

MySQL (or any relational DB)

Maven

📁 Project Structure
Code
src/
├── controller/
│   └── AuthController.java
├── model/
│   ├── Sweet.java
│   └── User.java
├── repository/
│   ├── SweetRepository.java
│   └── UserRepository.java
├── security/
│   ├── JwtUtils.java
│   └── SecurityConfig.java
└── Main.java
🔧 Setup Instructions
Clone the repository:

bash
git clone https://github.com/omkar0330/Sweet-Management-System-App.git
Configure your database in application.properties.

Build and run the project:

bash
mvn clean install
mvn spring-boot:run
