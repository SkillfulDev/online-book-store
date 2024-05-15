
# 📚 Online Bookstore API

Welcome to the Online Bookstore API! This project is a comprehensive example of building a fully functional online bookstore using Spring Boot. It encompasses a wide range of features including user registration, book browsing, shopping cart management, and order processing, providing a robust foundation for a real-world application.

## 🌟 Project Overview

The Online Bookstore API aims to meet the growing demand for online book shopping by offering a scalable and efficient solution for managing book inventories, processing orders, and ensuring secure user authentication. This project demonstrates how to create a sophisticated application with modern web technologies and best practices.

## 🛠️ Technologies and Tools

- **Spring Boot**: Core framework for building the application.
- **Spring Data JPA**: Manages database interactions.
- **Spring Security**: Handles authentication and authorization.
- **Liquibase**: Manages database migrations.
- **Maven**: Project management and dependency resolution.
- **H2 Database**: In-memory database for testing.
- **MySQL**: Main database for the application.
- **Swagger**: API documentation and testing tool.
- **JUnit & Mockito**: For unit testing and mocking.
- **GitHub Actions**: Continuous Integration (CI) tool.
- **Checkstyle**: Ensures code style consistency.

## 🎨 Features and Functionality

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

## 🚀 Setting Up the Project

### Prerequisites

- **Java 21+**
- **Maven 4+**
- **MySQL 8+**
- **Docker**

### Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/online-book-store.git
    cd online-book-store
    ```

2. **Set up MySQL:**
    Create a new MySQL database:
    ```sql
    CREATE DATABASE online_book_store;
    ```

3. **Configure environment variables:**
    Create an `application.properties` file in the `src/main/resources` directory:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/online_book_store
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

4. **Install dependencies and build the project:**
    ```bash
    mvn clean install
    ```

5. **Run the application:**
    ```bash
    mvn spring-boot:run
    ```
    The server will start on `http://localhost:8080`.

### Using Docker

1. **Build the Docker image:**
    ```bash
    docker build -t books-service .
    ```

2. **Run the Docker container:**
    ```bash
    docker run -d -p 8080:8080 --name books-service books-service
    ```

## 📝 Swagger Documentation

Swagger provides interactive API documentation:
- Navigate to `http://localhost:8080/swagger-ui.html` to view and test API endpoints.

## 🧪 Running Tests

To run tests:
```bash
mvn test
```

### Testing with JUnit and Mockito

The project uses JUnit for unit testing and Mockito for mocking dependencies, ensuring reliable and maintainable tests.

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
      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'
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

- Your Name

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

Feel free to clone the repository, explore the code, and contribute! If you encounter issues or have suggestions, please open an issue or submit a pull request. Happy coding!
