
# 🎖️ Online Bookstore API

Welcome to the Online Bookstore API! This project is a comprehensive example of building a fully functional online bookstore using Spring Boot. It encompasses a wide range of features including user registration, book browsing, shopping cart management, and order processing, providing a robust foundation for a real-world application.



## 🌟 Project Overview

The Online Bookstore API aims to meet the growing demand for online book shopping by offering a scalable and efficient solution for managing book inventories, processing orders, and ensuring secure user authentication. This project demonstrates how to create a sophisticated application with modern web technologies and best practices.

## 🛠️ Technologies and Tools

- **Spring Boot**: Core framework for building the application.
- **Spring Data JPA**: Manages database interactions.
- **Spring Security**: Handles authentication and authorization.
- **Liquibase**: Manages database migrations.
- **Maven**: Project management and dependency resolution.
- **H2 Database**: In-memory database for testing(CI).
- **MySQL**: Main database for the application.
- **Swagger**: API documentation and testing tool.
- **JUnit & Mockito**: For unit testing and mocking.
- **GitHub Actions**: Continuous Integration (CI) tool.
- **Checkstyle**: Ensures code style consistency.

## 📋 Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)

## 🚀 Features

- User Registration and Authentication
- Book Browsing and Searching
- Shopping Cart Management
- Order Processing
- User Profile Management
- Admin Dashboard for Managing Books and Orders
- Secure RESTful API Endpoints

## 📷 Visual Overview

[![Watch on Loom](https://img.shields.io/badge/Watch%20on-Loom-00a4d9)](https://www.loom.com/share/763aca67b62640aaa5ba1d31ecf74b56?sid=e3052068-fa6b-4fc7-8e8b-4a3f9dd86ab4)

## 📦 Installation

1. **Clone the repository**:
    ```sh
    git clone https://github.com/SkillfulDev/online-book-store.git
    cd online-bookstore
    ```

2. **Configure the database**:
   Create a new MySQL database:
    ```sql
    CREATE DATABASE online_book_store;
    ```
   Update the `application.properties` file with your MySQL database credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/online_book_store
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

3. **Install dependencies and build the project**:
    ```sh
    mvn clean install
    ```

4. **Run the application**:
    ```sh
    mvn spring-boot:run
    ```
   The server will start on `http://localhost:8080`.

### Using Docker

1. **Build the Docker image**:
    ```sh
    docker build -t books-service .
    ```

2. **Run the Docker container**:
    ```sh
    docker run -d -p 8080:8080 --name books-service books-service
    ```

## 📝 Usage

- Access the API documentation at `http://localhost:8080/swagger-ui.html`.
- Use tools like Postman or cURL to interact with the API endpoints.

## 📖 API Documentation

Explore the detailed API documentation generated by Swagger to understand the various endpoints and their functionalities.


---

Happy coding! 🎉

## 🌟 Functionalities

### User Endpoints

- **Register a new user**: `POST /api/auth/register`
    ```json
    {
      "email": "john.doe@example.com",
      "password": "securePassword123",
      "repeatPassword": "securePassword123",
      "firstName": "John",
      "lastName": "Doe",
      "shippingAddress": "123 Main St, City, Country"
    }
    ```

- **Authenticate a user**: `POST /api/auth/login`
    ```json
    {
      "email": "john.doe@example.com",
      "password": "securePassword123"
    }
    ```

### Book Endpoints

- **Retrieve book catalog**: `GET /api/books`
- **Retrieve book details**: `GET /api/books/{id}`
- **Create a new book**: `POST /api/books`
- **Update a specific book**: `PUT /api/books/{id}`
- **Delete a specific book**: `DELETE /api/books/{id}`

### Category Endpoints

- **Create a new category**: `POST /api/categories`
- **Retrieve all categories**: `GET /api/categories`
- **Retrieve a specific category by its ID**: `GET /api/categories/{id}`
- **Update a specific category**: `PUT /api/categories/{id}`
- **Delete a specific category**: `DELETE /api/categories/{id}`
- **Retrieve books by a specific category**: `GET /api/categories/{id}/books`

### Shopping Cart Endpoints

- **Retrieve user's shopping cart**: `GET /api/cart`
- **Add book to the shopping cart**: `POST /api/cart`
- **Update quantity of a book in the shopping cart**: `PUT /api/cart/cart-items/{cartItemId}`
- **Remove a book from the shopping cart**: `DELETE /api/cart/cart-items/{cartItemId}`

### Order Endpoints

- **Place an order**: `POST /api/orders`
- **Retrieve user's order history**: `GET /api/orders`
- **Retrieve all OrderItems for a specific order**: `GET /api/orders/{orderId}/items`
- **Retrieve a specific OrderItem within an order**: `GET /api/orders/{orderId}/items/{itemId}`
- **Update order status**: `PATCH /api/orders/{id}`

## 🔧 CI/CD Configuration

### Checkstyle Configuration

Add Checkstyle to `pom.xml` and create a `checkstyle.xml` in the root directory.

### GitHub Actions

Create a `.github/workflows/ci.yml` file:
```yaml
name: Java CI

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 21
        uses: actions/setup-java@v2
        with:
          java-version: '21'
          distribution: 'adopt'
          cache: maven
      - name: Build with Maven
        run: mvn --batch-mode --update-snapshots verify
```

## 📄 Challenges and Solutions

- **Database Configuration**: Used MySQL for local development and H2 for CI/CD testing.
- **Security Implementation**: Implemented JWT authentication for secure access.
- **API Design**: Designed RESTful endpoints adhering to best practices.

## 🔮 Future Enhancements

- User profile management.
- Book reviews and ratings.
- Payment gateway integration.
- Enhanced search functionality.

## 👥 Contributors

- Chernonog Yevhen
