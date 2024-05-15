# Online Book Store Project

## Introduction

Welcome to the Online Book Store project! This project aims to create a fully functional online bookstore using Spring Boot. It includes various features such as user registration, book browsing, shopping cart management, order processing, and more. This project is designed to be a comprehensive example of a real-world Spring Boot application.

## Technologies and Tools Used

- **Spring Boot:** For building the application.
- **Spring Data JPA:** For database interactions.
- **Spring Security:** For securing the application.
- **Liquibase:** For database migrations.
- **Maven:** For project management and dependency management.
- **H2 Database:** For in-memory testing.
- **MySQL:** As the main database.
- **Swagger:** For API documentation.
- **JUnit & Mockito:** For testing.
- **GitHub Actions:** For Continuous Integration (CI).
- **Checkstyle:** For code style checks.

## Functionalities

### User Endpoints

#### User Registration
- **POST /api/auth/register:** Register a new user.
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

#### User Login
- **POST /api/auth/login:** Authenticate a user.
    ```json
    {
      "email": "john.doe@example.com",
      "password": "securePassword123"
    }
    ```

### Book Endpoints
- **GET /api/books:** Retrieve book catalog.
- **GET /api/books/{id}:** Retrieve book details.
- **POST /api/books:** Create a new book.
- **PUT /api/books/{id}:** Update a specific book.
- **DELETE /api/books/{id}:** Delete a specific book.

### Category Endpoints
- **POST /api/categories:** Create a new category.
- **GET /api/categories:** Retrieve all categories.
- **GET /api/categories/{id}:** Retrieve a specific category by its ID.
- **PUT /api/categories/{id}:** Update a specific category.
- **DELETE /api/categories/{id}:** Delete a specific category.
- **GET /api/categories/{id}/books:** Retrieve books by a specific category.

### Shopping Cart Endpoints
- **GET /api/cart:** Retrieve user's shopping cart.
- **POST /api/cart:** Add book to the shopping cart.
- **PUT /api/cart/cart-items/{cartItemId}:** Update quantity of a book in the shopping cart.
- **DELETE /api/cart/cart-items/{cartItemId}:** Remove a book from the shopping cart.

### Order Endpoints
- **POST /api/orders:** Place an order.
- **GET /api/orders:** Retrieve user's order history.
- **GET /api/orders/{orderId}/items:** Retrieve all OrderItems for a specific order.
- **GET /api/orders/{orderId}/items/{itemId}:** Retrieve a specific OrderItem within an order.
- **PATCH /api/orders/{id}:** Update order status.

