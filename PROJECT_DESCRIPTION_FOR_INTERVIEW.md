# NASA API Implementation - Project Description for Interview

## 🎯 **Project Overview**

This is a **full-stack web application** that integrates with NASA's public APIs to provide users with access to:
1. **Astronomy Picture of the Day (APOD)** - Daily astronomical images from NASA
2. **Mars Rover Photos** - Images captured by NASA's Mars rovers (Curiosity, Spirit, Opportunity)

---

## 💼 **What This Project Does**

### **Core Functionality:**

1. **Astronomy Picture of the Day (APOD)**
   - Fetches daily astronomical images from NASA's API
   - Displays images with descriptions, dates, and metadata
   - Allows authenticated users to view APOD content
   - Admin users can perform CRUD operations (Create, Read, Update, Delete) on saved APOD data

2. **Mars Rover Photos**
   - Allows users to search Mars rover photos by:
     - **Rover**: Curiosity, Spirit, or Opportunity
     - **Camera**: 9 different camera types (FHAZ, RHAZ, MAST, CHEMCAM, MAHLI, MARDI, NAVCAM, PANCAM, MINITES)
     - **Date**: Specific Earth dates (YYYY-MM-DD format)
   - Displays photos in a gallery format

3. **User Management & Security**
   - Role-based access control (Employee, Manager, Admin roles)
   - Secure authentication using Spring Security
   - JWT token-based authentication for API access
   - Custom login pages with session management

4. **Admin Dashboard**
   - View all saved APOD images
   - Update existing APOD records
   - Delete APOD records
   - Only accessible to ADMIN role users

---

## 🏗️ **Architecture & Design Patterns**

### **Layered Architecture:**
- **Controller Layer**: REST API controllers and MVC controllers
- **Service Layer**: Business logic implementation
- **Repository Layer**: Data access using Spring Data JPA
- **Entity Layer**: Domain models (NasaApod, MarsRover, etc.)

### **Design Patterns Used:**
- **MVC (Model-View-Controller)**: Separates presentation, business logic, and data
- **Dependency Injection**: Using Spring's @Autowired
- **RESTful API Design**: Standard HTTP methods (GET, POST, PUT, DELETE)
- **Exception Handling**: Global exception handler with custom error responses

---

## 🛠️ **Technology Stack**

### **Backend:**
- **Spring Boot 3** & **Spring Framework 6** - Main framework
- **Java 17** - Programming language
- **Spring Security** - Authentication and authorization
- **JWT (JSON Web Tokens)** - Token-based authentication
- **OAuth2 Resource Server** - For API security
- **Spring Data JPA** - Database interaction
- **MySQL** - Relational database
- **RESTful Web Services** - API endpoints
- **RestTemplate** - HTTP client for calling NASA APIs

### **Frontend:**
- **Thymeleaf** - Server-side template engine
- **HTML5, CSS3** - Markup and styling
- **Bootstrap** - Responsive UI framework
- **Server-side rendering** - Dynamic content generation

### **Tools & Libraries:**
- **Maven** - Build and dependency management
- **Swagger/OpenAPI** - API documentation
- **JUnit & Mockito** - Unit testing
- **Test Containers** - Integration testing

### **Deployment:**
- **Docker** - Containerization
- **AWS Elastic Beanstalk** - Cloud deployment platform

---

## 🔐 **Security Implementation**

1. **Authentication:**
   - Custom login page with form-based authentication
   - BCrypt password encoding for secure password storage
   - Session management with JSESSIONID cookies

2. **Authorization:**
   - Role-based access control:
     - **ROLE_EMPLOYEE**: Can view APOD and Mars Rover photos
     - **ROLE_ADMIN**: Can perform CRUD operations on APOD data
   - Method-level security using `@PreAuthorize` annotations

3. **JWT Authentication:**
   - JWT token generation for API access
   - RSA key pair for signing/verifying tokens
   - Token expiration (30 minutes)

4. **Database Security:**
   - User credentials stored with BCrypt hashing
   - JDBC-based user details management
   - Custom SQL queries for user authentication

---

## 📡 **API Endpoints**

### **REST APIs (Protected):**
- `GET /api/apod` - Get today's Astronomy Picture of the Day
- `GET /api/apods` - Get all saved APOD records
- `GET /api/apod/{id}` - Get specific APOD by ID
- `POST /api/save-apod` - Save current APOD to database
- `PUT /api/apod/{id}` - Update APOD record (Admin only)
- `DELETE /api/apod/{id}` - Delete APOD record (Admin only)
- `GET /api/rover/{roverName}/{earthDate}/{camera}` - Get Mars Rover photos

### **Authentication:**
- `POST /authenticate` - Get JWT token
- `GET /get-token` - Alternative token endpoint

### **MVC Pages:**
- `/nasa/home-page` - Home page (Employee+)
- `/nasa/mars-apod` - View APOD (Employee+)
- `/nasa/mars-rover` - Mars Rover search page (Employee+)
- `/nasa/list-apods` - Admin dashboard (Admin only)
- `/show-login-page` - Custom login page

---

## 🗄️ **Database Schema**

### **Tables:**
1. **nasa_apod** - Stores Astronomy Picture of the Day data
   - Fields: ID, title, explanation, url, hdurl, date, media_type, etc.

2. **nasa_members** - User credentials
   - Fields: user_id (PK), pw (BCrypt hash), active

3. **nasa_roles** - User roles and authorities
   - Fields: user_id, role (ROLE_EMPLOYEE, ROLE_MANAGER, ROLE_ADMIN)

---

## ✨ **Key Features**

1. **External API Integration**: Integrates with NASA's REST APIs
2. **Data Persistence**: Saves APOD data to MySQL database
3. **Role-Based Access**: Different permissions for different user roles
4. **RESTful API**: Complete CRUD operations via REST endpoints
5. **Swagger Documentation**: Auto-generated API documentation
6. **Exception Handling**: Custom error responses and global exception handling
7. **Responsive Design**: Mobile-friendly Bootstrap UI
8. **Session Management**: Secure session handling and logout
9. **JWT Support**: Token-based authentication for API clients
10. **Admin Dashboard**: Full CRUD interface for administrators

---

## 📝 **How to Explain to Interviewer**

### **1. Elevator Pitch (30 seconds):**
> "I developed a full-stack Spring Boot application that integrates with NASA's APIs to display Astronomy Pictures of the Day and Mars Rover photos. It features role-based authentication, a RESTful API, an admin dashboard for content management, and is deployed on AWS. The project demonstrates my understanding of Spring Security, JWT authentication, database design, and API integration."

### **2. Technical Highlights (2-3 minutes):**
> "This project showcases several important concepts:
> 
> **Security:** I implemented Spring Security with role-based access control, BCrypt password encryption, and JWT token authentication for API access. Different endpoints are secured based on user roles - employees can view content while admins can manage it.
> 
> **Architecture:** I used a layered architecture with REST controllers for API access and MVC controllers for web pages. The service layer contains business logic, and I used Spring Data JPA for database operations.
> 
> **API Integration:** The application makes RESTful calls to NASA's public APIs using RestTemplate, handles responses, and persists relevant data to MySQL.
> 
> **Frontend:** I used Thymeleaf for server-side rendering and Bootstrap for a responsive, mobile-friendly UI."

### **3. Problem-Solving Example:**
> "One challenge was implementing secure authentication. I created a custom Spring Security configuration with JdbcUserDetailsManager for database-backed authentication. I also added JWT support so the application can work both as a web app with session-based auth and as an API with token-based auth. I handled this by configuring both form login and OAuth2 resource server in the same security filter chain."

### **4. Key Achievements:**
- ✅ Integrated external APIs (NASA)
- ✅ Implemented complete authentication and authorization
- ✅ Built RESTful APIs with proper HTTP methods
- ✅ Created admin dashboard with CRUD operations
- ✅ Deployed to AWS Elastic Beanstalk
- ✅ Documented APIs with Swagger
- ✅ Applied best practices (exception handling, layered architecture)

---

## 🎓 **What This Project Demonstrates**

1. **Spring Boot Expertise**: Deep understanding of Spring Boot 3 and Spring Framework 6
2. **Security Knowledge**: Implementation of authentication, authorization, and encryption
3. **API Development**: Creating and consuming RESTful APIs
4. **Database Design**: MySQL schema design and JPA/Hibernate usage
5. **Full-Stack Development**: Both backend APIs and frontend UI
6. **DevOps**: Docker containerization and cloud deployment
7. **Testing**: Unit and integration testing
8. **Documentation**: API documentation with Swagger/OpenAPI

---

## 📊 **Project Structure**

```
src/main/java/com/openapi/nasa/
├── rest/           # REST API Controllers
├── mvcController/  # Web MVC Controllers  
├── service/        # Business Logic Layer
├── daorepo/        # Data Access Layer
├── entity/         # JPA Entities
├── model/          # Data Models (Mars Rover, etc.)
├── security/       # Security Configuration (JWT, Spring Security)
├── exceptionHandler/ # Custom Exception Handling
├── response/       # Response DTOs
└── util/           # Utility Classes
```

---

## 💡 **Interview Talking Points**

**When asked about challenges:**
- Explain how you handled different authentication methods (form login + JWT)
- Discuss role-based access control implementation
- Mention API integration and error handling

**When asked about scalability:**
- Discuss how Spring Boot's architecture supports horizontal scaling
- Mention database indexing for performance
- Talk about stateless JWT tokens for API scalability

**When asked about best practices:**
- Layered architecture for maintainability
- Exception handling for robustness
- API documentation with Swagger
- Secure password storage with BCrypt
- RESTful API design principles

---

## 🚀 **Live Demo**

- **Demo URL**: Mention if deployed (e.g., AWS Elastic Beanstalk)
- **Show**: Login flow, APOD display, Mars Rover search, Admin dashboard

---

**Remember:** Focus on **what you built**, **why you made certain decisions**, and **what you learned** from the project!

