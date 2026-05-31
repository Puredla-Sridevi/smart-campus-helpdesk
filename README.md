# Smart Campus Helpdesk System


## 📌 Status

✅ Completed Backend Development  
✅ Production-Ready REST APIs  
✅ Swagger Documentation Available  
✅ JWT Authentication Implemented


## 🚀 Project Overview

Smart Campus Helpdesk System is a role-based complaint management platform developed to streamline issue reporting, tracking, assignment, communication, and resolution within educational institutions.

The system enables students to raise complaints, administrators to manage and assign issues, and staff members to track and resolve assigned complaints. It provides secure authentication, complaint lifecycle management, audit tracking, dashboards, search capabilities, and API documentation.

Built using modern backend development practices, the project demonstrates enterprise-grade concepts such as JWT Authentication, Role-Based Access Control (RBAC), RESTful API design, activity auditing, centralized exception handling, and layered architecture.

---

## 🎯 Business Problem

Educational institutions often struggle with:

* Manual complaint tracking
* Lack of accountability
* Delayed issue resolution
* Poor communication between stakeholders
* No visibility into complaint progress

Smart Campus Helpdesk addresses these challenges through a centralized complaint management workflow with real-time status tracking and role-based operations.

---

## ✨ Core Features

### 🔐 Authentication & Security

* JWT-based Authentication
* Role-Based Access Control (Student, Staff, Admin)
* Email OTP Verification
* Forgot Password & Password Reset
* BCrypt Password Encryption
* Stateless Authentication using Spring Security
* Protected API Endpoints

### 👨‍🎓 Student Module

* Register and Verify Account
* Secure Login
* Create Complaints
* View Submitted Complaints
* Track Complaint Progress
* View Complaint Details

### 👨‍💼 Admin Module

* Monitor User Accounts
* Block / Unblock Users
* View All Complaints
* Assign Complaints to Staff Members
* Update Complaint Priority
* Search Complaints
* Filter Complaints by Status
* Filter Complaints by Priority
* View Complaint Activity History
* Dashboard Analytics

### 👨‍🔧 Staff Module

* View Assigned Complaints
* View Individual Complaint Details
* Update Complaint Status
* Track Resolution Workflow
* Dashboard Analytics

### 💬 Comment Module

- Add Comments on Complaints
- View Complaint Discussion History
- Associate Comments with Complaints
- Track Comment Author Information
- Improve Communication Between Students, Staff, and Admin

### 📋 Complaint Lifecycle Management

* Complaint Creation
* Complaint Assignment
* Priority Management
* Status Tracking
* Resolution Workflow
* Complaint Search
* Complaint History Tracking

### 📊 Analytics & Monitoring

* Admin Dashboard
* Staff Dashboard
* Complaint Statistics
* Priority-Based Metrics
* Status-Based Metrics

### 📝 Audit & Activity Tracking

* Complaint Creation Logs
* Assignment Logs
* Status Change History
* Priority Change History
* User Action Tracking
* Timestamped Activity Records

### ⚙️ Platform Features

* Pagination
* Sorting
* Search Functionality
* Global Exception Handling
* Validation Handling
* Swagger API Documentation

---

## 🏗️ Technical Architecture

The application follows a layered architecture to ensure maintainability, scalability, and separation of concerns.

```text
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
Database Layer
```

### Design Principles

* Separation of Concerns
* Clean Architecture
* RESTful API Design
* Reusable Service Layer
* Secure Authentication Flow
* Centralized Error Handling

---

## 🛠️ Technology Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate

### Database

* MySQL

### Authentication & Security

* JWT (JSON Web Token)
* BCrypt Password Encoder

### API Documentation

* Swagger / OpenAPI

### Build Tool

* Maven

### Additional Libraries

* Lombok
* Jakarta Validation
* Spring Mail

### Development Tools

* Postman
* Git
* GitHub
* IntelliJ IDEA

---

## 🗄️ Database Entities

### User

* User Management
* Role Management
* Account Status Management

### Complaint

* Complaint Information
* Status Tracking
* Priority Tracking
* Assignment Tracking

### Complaint History

* Activity Logging
* Audit Trail
* User Actions Tracking

### Comment

- Complaint Discussions
- User Communication
- Comment History

---

## 🔒 Security Features

* JWT Token-Based Authentication
* Role-Based Authorization
* Endpoint Protection
* Password Encryption
* OTP Verification
* Secure Password Reset Flow
* Method-Level Security using @PreAuthorize

---

## 📈 Key Engineering Highlights

* Developed a complete role-based complaint management workflow.
* Implemented JWT Authentication and Authorization using Spring Security.
* Designed and implemented complaint assignment and tracking mechanisms.
* Built dashboard analytics for both administrators and staff members.
* Developed activity history tracking for complete auditability.
* Implemented complaint search, filtering, sorting, and pagination.
* Integrated OTP-based email verification and password recovery flows.
* Added centralized exception handling using Global Exception Handler.
* Documented APIs using Swagger/OpenAPI.
* Followed enterprise backend development best practices and layered architecture.

---

## 📚 API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI Specification:

```text
http://localhost:8080/v3/api-docs
```

---

## 📊 Project Statistics

* 4 Database Entities
* 20+ REST APIs
* 3 User Roles (Student, Staff, Admin)
* JWT-Based Security
* OTP Email Verification
* Complaint History Audit System
* Dashboard Analytics
* Swagger API Documentation
---

## 👩‍💻 Author

**Puredla Sridevi**

B.Tech – Computer Science Engineering

Andhra University

Aspiring Full Stack Java Developer

---
## ⭐ Resume Highlights

* Designed and developed a role-based complaint management platform using Java, Spring Boot, Spring Security, Hibernate, and MySQL.
* Implemented JWT Authentication, OTP Verification, Password Recovery, and Role-Based Access Control (RBAC).
* Developed 20+ secure RESTful APIs following industry-standard layered architecture principles.
* Built complete complaint lifecycle management including creation, assignment, prioritization, status tracking, and resolution workflows.
* Implemented audit trail functionality using Complaint History for complete activity tracking and accountability.
* Developed Admin and Staff Dashboard Analytics for operational monitoring and performance insights.
* Integrated complaint comments, search, pagination, sorting, filtering, validation, and centralized exception handling.
* Documented APIs using Swagger/OpenAPI to improve developer experience and API usability.