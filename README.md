# 🎓 eLearning Platform

A comprehensive Learning Management System (LMS) built with Spring Boot 3.3.5 and Java 21, featuring JWT-based authentication, role-based access control, and a modern REST API architecture.

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [API Documentation](#-api-documentation)
- [Getting Started](#-getting-started)
- [Database Schema](#-database-schema)
- [User Roles](#-user-roles)
- [Security](#-security)
- [Testing](#-testing)
- [Contributing](#-contributing)

## 🚀 Features

### 👥 **User Management**
- **User Registration & Authentication**
  - Secure user registration with username validation
  - JWT-based authentication system
  - Password encryption using BCrypt
  - Role-based access control (Student, Instructor, Approver)

- **User Roles & Permissions**
  - **Student**: Enroll in courses, track progress, access materials
  - **Instructor**: Create courses, upload materials, manage enrollments
  - **Approver**: Review and approve courses for publication

### 📚 **Course Management**
- **Course Creation & Management**
  - Instructors can create new courses
  - Course approval workflow (Pending → Approved)
  - Course status tracking
  - Course metadata (title, description, instructor)

- **Course Materials**
  - File upload support for course materials
  - Material organization by course
  - Support for various file types
  - Material metadata (title, description, file URL)

### 🎯 **Enrollment System**
- **Student Enrollment**
  - Students can enroll in approved courses
  - Enrollment tracking and management
  - Progress monitoring (percentage-based)
  - Enrollment history

### 🔐 **Security Features**
- **JWT Authentication**
  - Stateless authentication using JSON Web Tokens
  - Token-based session management
  - Secure token generation and validation
  - Role-based authorization

- **API Security**
  - Protected endpoints with JWT validation
  - Role-based access control
  - Secure password handling
  - CORS configuration

### 📊 **Progress Tracking**
- **Learning Progress**
  - Real-time progress tracking
  - Percentage-based completion tracking
  - Progress updates via API
  - Enrollment progress monitoring

### 🌐 **REST API Interface**
- **Comprehensive REST Endpoints**
  - Complete CRUD operations for all entities
  - Role-based endpoint access control
  - JSON request/response format
  - RESTful API design patterns

### 📖 **API Documentation**
- **Swagger/OpenAPI Integration**
  - Interactive API documentation
  - Endpoint testing interface
  - Request/response examples
  - Authentication documentation

## 🛠 Tech Stack

### **Backend Framework**
- **Java 21** - Latest LTS version with modern language features
- **Spring Boot 3.3.5** - Latest stable version with Jakarta EE support
- **Spring Web** - RESTful web services
- **Spring Data JPA** - Data persistence layer
- **Spring Security** - Authentication and authorization
- **Maven** - Build automation and dependency management

### **Database**
- **MySQL** - Primary production database
- **H2 Database** - In-memory database for testing
- **Hibernate** - JPA implementation for ORM

### **Security & Authentication**
- **JWT (JSON Web Tokens)** - Stateless authentication
- **JJWT 0.12.6** - Latest JWT library for Java
- **BCrypt** - Password hashing and verification

### **API & Documentation**
- **SpringDoc OpenAPI 2.6.0** - Swagger UI integration
- **REST API** - RESTful web services architecture
- **JSON** - Data exchange format
- **OpenAPI 3.0** - API specification standard

### **Testing**
- **JUnit 5** - Unit testing framework
- **Spring Boot Test** - Integration testing
- **H2 Database** - Test database

## 🏗 Architecture

### **Layered Architecture**
```
┌─────────────────────────────────────┐
│           Presentation Layer        │
│     (Controllers, Web Pages)        │
├─────────────────────────────────────┤
│            Business Layer           │
│        (Services, Security)         │
├─────────────────────────────────────┤
│           Persistence Layer         │
│    (Repositories, Entities)         │
├─────────────────────────────────────┤
│            Database Layer           │
│        (MySQL/H2 Database)          │
└─────────────────────────────────────┘
```

### **Package Structure**
```
com.example.elearning_platform/
├── config/          # Configuration classes
├── controller/      # REST API controllers
├── model/          # JPA entities
├── repository/     # Data access layer
├── security/       # JWT and security components
└── eLearningPlatform.java  # Main application class
```

## 📚 API Documentation

### **Base URL**: `http://localhost:8080`

### **Authentication Endpoints**
| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| POST | `/api/users/register` | Register new user | Public |
| POST | `/api/users/login` | User login | Public |
| PUT | `/api/users/{id}` | Update user profile | Authenticated |

### **Course Management Endpoints**
| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| POST | `/api/courses/` | Create new course | Instructor |
| PUT | `/api/courses/{id}` | Update course | Instructor |
| GET | `/api/courses/{id}` | Get course details | Authenticated |
| GET | `/api/courses/approved` | List approved courses | Public |
| POST | `/api/courses/{id}/enroll` | Enroll in course | Student |
| GET | `/api/courses/{id}/enrollments` | Get course enrollments | Instructor |

### **Course Materials Endpoints**
| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| POST | `/api/courses/{id}/materials` | Upload course material | Instructor |
| GET | `/api/courses/{id}/materials` | Get course materials | Enrolled Students |

### **Enrollment Endpoints**
| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| PUT | `/api/enrollments/{id}/progress` | Update progress | Student |

### **Additional Endpoints**
| Method | Endpoint | Description | Access |
|--------|----------|-------------|---------|
| GET | `/api/users/test` | Test endpoint | Public |
| GET | `/api/courses/pending` | List pending courses | Approver |
| GET | `/api/courses/{id}/students` | Get enrolled students | Instructor |

### **API Documentation**
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## 📮 Using Postman with the API

### **Setup Postman Collection**

1. **Import the API Collection**
   - Open Postman
   - Click "Import" → "Link"
   - Paste: `http://localhost:8080/v3/api-docs`
   - Click "Continue" → "Import"

2. **Set Environment Variables**
   - Create a new environment in Postman
   - Add these variables:
     ```
     base_url: http://localhost:8080
     token: (leave empty, will be set after login)
     ```

### **Authentication Flow**

#### **Step 1: Register a User**
```http
POST {{base_url}}/api/users/register
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123",
  "role": "STUDENT"
}
```

#### **Step 2: Login to Get JWT Token**
```http
POST {{base_url}}/api/users/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123"
}
```

**Response:**
```json
{
  "username": "john_doe",
  "role": "STUDENT",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

#### **Step 3: Set Authorization Header**
- Copy the `token` from login response
- In Postman, go to "Authorization" tab
- Select "Bearer Token"
- Paste the token value
- Or set environment variable: `token: eyJhbGciOiJIUzI1NiJ9...`

### **Common API Requests**

#### **User Management**
```http
# Get all users (Admin only)
GET {{base_url}}/api/users
Authorization: Bearer {{token}}

# Update user profile
PUT {{base_url}}/api/users/1
Authorization: Bearer {{token}}
Content-Type: application/json

{
  "username": "john_doe_updated",
  "role": "INSTRUCTOR"
}
```

#### **Course Management**
```http
# Create a course (Instructor only)
POST {{base_url}}/api/courses
Authorization: Bearer {{token}}
Content-Type: application/json

{
  "title": "Java Programming 101",
  "description": "Learn Java from scratch",
  "instructorId": 1
}

# Get all courses
GET {{base_url}}/api/courses
Authorization: Bearer {{token}}

# Get course by ID
GET {{base_url}}/api/courses/1
Authorization: Bearer {{token}}

# Update course
PUT {{base_url}}/api/courses/1
Authorization: Bearer {{token}}
Content-Type: application/json

{
  "title": "Advanced Java Programming",
  "description": "Advanced Java concepts"
}
```

#### **Course Approval (Approver only)**
```http
# Get pending courses
GET {{base_url}}/api/courses/pending
Authorization: Bearer {{token}}

# Approve a course
PUT {{base_url}}/api/courses/1/approve
Authorization: Bearer {{token}}
```

#### **Enrollment Management**
```http
# Enroll in a course (Student only)
POST {{base_url}}/api/enrollments
Authorization: Bearer {{token}}
Content-Type: application/json

{
  "studentId": 1,
  "courseId": 1
}

# Get student enrollments
GET {{base_url}}/api/enrollments/student/1
Authorization: Bearer {{token}}

# Update progress
PUT {{base_url}}/api/enrollments/1/progress
Authorization: Bearer {{token}}
Content-Type: application/json

{
  "progress": 75.5
}
```

#### **File Upload (Course Materials)**
```http
# Upload course material (Instructor only)
POST {{base_url}}/api/courses/1/materials
Authorization: Bearer {{token}}
Content-Type: multipart/form-data

Form Data:
- title: "Chapter 1 - Introduction"
- description: "Introduction to Java"
- file: [Select file from computer]
```

### **Postman Collection Structure**

```
📁 eLearning Platform API
├── 🔐 Authentication
│   ├── Register User
│   └── Login User
├── 👥 User Management
│   ├── Get All Users
│   ├── Get User by ID
│   └── Update User
├── 📚 Course Management
│   ├── Create Course
│   ├── Get All Courses
│   ├── Get Course by ID
│   ├── Update Course
│   ├── Delete Course
│   └── Get Pending Courses
├── ✅ Course Approval
│   └── Approve Course
├── 📝 Enrollment
│   ├── Enroll in Course
│   ├── Get Student Enrollments
│   └── Update Progress
└── 📎 Course Materials
    ├── Upload Material
    └── Get Course Materials
```

### **Testing Tips**

1. **Environment Variables**: Use `{{base_url}}` and `{{token}}` for easy switching between environments
2. **Pre-request Scripts**: Add this to automatically set the token:
   ```javascript
   pm.request.headers.add({
     key: 'Authorization',
     value: 'Bearer ' + pm.environment.get('token')
   });
   ```
3. **Test Scripts**: Add response validation:
   ```javascript
   pm.test("Status code is 200", function () {
       pm.response.to.have.status(200);
   });
   ```
4. **Role Testing**: Create different users with different roles to test authorization

## 🚀 Getting Started

### **Prerequisites**
- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+ (for production)
- Git

### **Installation**

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd elearning-platform
   ```

2. **Configure Database**
   ```properties
   # Update src/main/resources/application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/lms_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the Application**
   - API Base URL: `http://localhost:8080/api`
   - API Documentation: `http://localhost:8080/swagger-ui.html`
   - H2 Console (testing): `http://localhost:8080/h2-console`

### **Default Configuration**
- **Server Port**: 8080
- **Database**: MySQL (production), H2 (testing)
- **JWT Secret**: Configured in application properties
- **File Upload**: 10MB max file size

## 🗄 Database Schema

### **User Table**
```sql
CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);
```

### **Course Table**
```sql
CREATE TABLE course (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    status VARCHAR(255) NOT NULL,
    instructor_id BIGINT,
    FOREIGN KEY (instructor_id) REFERENCES user(id)
);
```

### **Course Material Table**
```sql
CREATE TABLE course_material (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    file_url VARCHAR(255),
    course_id BIGINT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id)
);
```

### **Enrollment Table**
```sql
CREATE TABLE enrollment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    course_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    progress DOUBLE DEFAULT 0.0,
    FOREIGN KEY (course_id) REFERENCES course(id),
    FOREIGN KEY (student_id) REFERENCES user(id)
);
```

## 👥 User Roles

### **Student**
- Enroll in approved courses
- Access course materials
- Track learning progress
- Update enrollment progress

### **Instructor**
- Create new courses
- Upload course materials
- Manage course content
- View course enrollments
- Update course information

### **Approver**
- Review pending courses
- Approve or reject courses
- Manage course publication
- Oversee course quality

## 🔒 Security

### **Authentication Flow**
1. User registers with username/password
2. Password is encrypted using BCrypt
3. User logs in with credentials
4. JWT token is generated and returned
5. Token is included in subsequent requests
6. Token is validated on protected endpoints

### **Authorization**
- Role-based access control
- JWT token validation
- Protected API endpoints
- Secure password handling

### **Security Headers**
- CORS configuration
- CSRF protection
- Secure session management

## 🧪 Testing

### **Running Tests**
```bash
# Run all tests
mvn test

# Run with coverage
mvn test jacoco:report

# Run specific test class
mvn test -Dtest=eLearningPlatformTests
```

### **Test Configuration**
- **Test Database**: H2 in-memory database
- **Test Profile**: `application-test.properties`
- **Test Data**: Automatically created and cleaned up

### **Test Coverage**
- Unit tests for all controllers
- Integration tests for API endpoints
- Security tests for authentication
- Database tests for repositories

## 📈 Performance & Scalability

### **Current Capabilities**
- Stateless JWT authentication
- Connection pooling with HikariCP
- Optimized database queries
- Efficient file handling

### **Scalability Features**
- Horizontal scaling support
- Database connection pooling
- Stateless architecture
- RESTful API design

## 🔧 Configuration

### **Application Properties**
```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/lms_db
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# File Upload Configuration
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# JWT Configuration
jwt.secret=your-secret-key
jwt.expiration=86400000
```

## 🚀 Deployment

### **Production Deployment**
1. Configure production database
2. Set environment variables
3. Build production JAR
4. Deploy to application server
5. Configure reverse proxy (optional)

### **Docker Support** (Future Enhancement)
```dockerfile
FROM openjdk:21-jdk-slim
COPY target/elearning-platform-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 🤝 Contributing

### **Development Setup**
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new features
5. Submit a pull request

### **Code Standards**
- Follow Java coding conventions
- Write comprehensive tests
- Document new features
- Update API documentation

## 📞 Support

For support and questions:
- Create an issue in the repository
- Check the API documentation
- Review the test cases for usage examples

## 🔮 Future Enhancements

### **Planned Backend Features**
- Real-time notifications (WebSocket)
- Advanced analytics API
- File storage integration (AWS S3)
- Advanced assessment APIs
- Payment gateway integration
- Email notification service
- Advanced search and filtering
- Bulk operations API

---

**Built with ❤️ using Spring Boot 3.3.5 and Java 21**
