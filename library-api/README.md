# Library Management API

A RESTful API for managing library operations including books, members, and borrowing/returning functionality.

## 🚀 Features

- **Book Management**: CRUD operations for books
- **Member Management**: CRUD operations for library members
- **Borrowing System**: Borrow and return books with availability tracking
- **Business Rules**: Prevent double-borrowing, track return dates
- **Validation**: Input validation for all entities
- **Exception Handling**: Global exception handling with meaningful errors

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.5.12**
- **Spring Data JPA**
- **MySQL 8.0**
- **Maven**

## 📋 Prerequisites

- JDK 17 or higher
- MySQL 8.0
- Maven 3.6+

## ⚙️ Setup & Installation

1. **Clone the repository**
```bash
git clone https://github.com/shubhamKushwah-5/LibraryManagementProject.git
cd library-api
```

2. **Configure MySQL Database**

Create database:
```sql
CREATE DATABASE library_db;
```

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=root123
```

3. **Run the application**
```bash
mvn spring-boot:run
```

Application runs on `http://localhost:8081`

## 📚 API Endpoints

### Books

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get book by ID |
| POST | `/api/books` | Add new book |
| PUT | `/api/books/{id}` | Update book |
| DELETE | `/api/books/{id}` | Delete book |
| GET | `/api/books/available` | Get available books |
| GET | `/api/books/author/{author}` | Get books by author |

### Members

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/members` | Get all members |
| GET | `/api/members/{id}` | Get member by ID |
| POST | `/api/members` | Add new member |
| PUT | `/api/members/{id}` | Update member |
| DELETE | `/api/members/{id}` | Delete member |

### Borrowings

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/borrowings/borrow?bookId={id}&memberId={id}` | Borrow a book |
| POST | `/api/borrowings/return/{borrowingId}` | Return a book |
| GET | `/api/borrowings` | Get all borrowings |
| GET | `/api/borrowings/active` | Get active borrowings |
| GET | `/api/borrowings/member/{memberId}` | Get member's borrowings |

## 📝 Sample Requests

### Add Book
```json
POST /api/books
{
  "title": "Clean Code",
  "author": "Robert Martin",
  "isbn": "978-0132350884"
}
```

### Add Member
```json
POST /api/members
{
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "1234567890"
}
```

### Borrow Book
```
POST /api/borrowings/borrow?bookId=1&memberId=1
```

### Return Book
```
POST /api/borrowings/return/1
```

## 🏗️ Project Structure
```
src/main/java/
├── model/           # Entity classes (Book, Member, Borrowing)
├── repository/      # JPA Repositories
├── service/         # Business logic layer
├── controller/      # REST Controllers
└── exception/       # Exception handlers
```

## 🎯 Business Rules

- Books can only be borrowed if `available = true`
- Borrowing a book sets `available = false`
- Returning a book sets `available = true`
- Cannot borrow already borrowed books
- Cannot return already returned books
- Borrow date is set automatically
- Return date is set when book is returned

## ✅ Database Schema

**Books Table:**
- id (PK, auto-increment)
- title
- author
- isbn (unique)
- is_available (boolean)

**Members Table:**
- id (PK, auto-increment)
- name
- email (unique)
- phone

**Borrowings Table:**
- id (PK, auto-increment)
- book_id (FK)
- member_id (FK)
- borrow_date
- return_date
- is_returned (boolean)

## 📖 What I Learned

This project demonstrates:
- **3-tier architecture** implementation
- **JPA relationships** (@ManyToOne)
- **Business logic** in service layer
- **Transaction management** (borrow/return updates multiple tables)
- **State management** (book availability)
- **RESTful API design**
- **Input validation**
- **Exception handling**

## 🔮 Future Enhancements

- [ ] Due date tracking (overdue books)
- [ ] Fine calculation for late returns
- [ ] Book reservation system
- [ ] Member borrowing limits
- [ ] Search functionality
- [ ] Authentication & authorization
- [ ] Deployment to cloud

## 👨‍💻 Author

Built as part of Spring Boot learning journey.

---

**Note:** This is a learning project demonstrating RESTful API development with Spring Boot.