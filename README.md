**Spring Boot Multi-Module Application**
User Register | Customer | Book Module | File Storage
MySQL + MongoDB | REST APIs | JUnit Testing | SLF4j-Log4j

**Project Overview**
This is a full-stack backend application developed using Spring Boot, providing modular CRUD operations for User Registration, Customer Management, Book Details, and File/Image Storage.
It supports simultaneous communication with MySQL & MongoDB, enabling hybrid data handling with optimized performance and caching.

**Features**

**CRUD operations for:**
UserRegister Module
Customer Module
Book Module
File Storage (upload/download)

**REST APIs with:**
Proper HTTP status codes
Global Exception Handling

**API validation (Bean Validation)**

Database Integration:
MySQL (Spring Data JPA)
MongoDB (MongoRepository)
Java 8 Functional Programming:
Streams, Lambda, Optional, Base64

**Performance Improvements:**
Spring Cache for faster retrieval

**Logging:**
SLF4J + Log4j2 for monitoring & debugging

**Testing:**
JUnit 5 & Mockito
Controller/Service layer test coverage

**API Documentation:**
Swagger / OpenAPI UI
Postman collections used for validation

**Database Configuration**
application.properties / application.yml includes separate configs for:
MySQL (Primary)
Oracle
MongoDB (Secondary)

**Deployed On Docker:**
link: http://localhost:8080/swagger-ui.html

