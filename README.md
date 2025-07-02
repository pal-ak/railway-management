# 🚆 Railway Management System (IRCTC Clone)

A full-stack **Spring Boot** application that simulates an IRCTC-like railway reservation system, allowing users to register, log in, check train availability, and book tickets. The system supports **admin operations**, **role-based access**, **JWT authentication**, and **API key protection**.

---

## 🛠 Tech Stack

- **Backend**: Java, Spring Boot
- **Security**: Spring Security, JWT, API Key Interceptor
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Auth**: Role-Based (`ADMIN`, `USER`)
- **Concurrency Handling**: Pessimistic Locking for bookings

---

## 📂 Project Structure
```plaintext
railway-management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── railway/
│   │   │           ├── RailwayManagementApplication.java
│   │   │
│   │   │           ├── config/
│   │   │           │   └── WebConfig.java
│   │   │
│   │   │           ├── controller/
│   │   │           │   ├── AdminController.java
│   │   │           │   ├── AuthController.java
│   │   │           │   ├── BookingController.java
│   │   │           │   └── UserController.java
│   │   │
│   │   │           ├── dto/
│   │   │           │   ├── AddTrainRequest.java
│   │   │           │   ├── AuthResponse.java
│   │   │           │   ├── BookingRequest.java
│   │   │           │   ├── BookingResponse.java
│   │   │           │   ├── LoginRequest.java
│   │   │           │   ├── RegisterRequest.java
│   │   │           │   └── TrainAvailabilityResponse.java
│   │   │
│   │   │           ├── model/
│   │   │           │   ├── Booking.java
│   │   │           │   ├── Role.java
│   │   │           │   ├── Train.java
│   │   │           │   └── User.java
│   │   │
│   │   │           ├── repository/
│   │   │           │   ├── BookingRepository.java
│   │   │           │   ├── TrainRepository.java
│   │   │           │   └── UserRepository.java
│   │   │
│   │   │           ├── security/
│   │   │           │   ├── ApiKeyInterceptor.java
│   │   │           │   ├── JwtFilter.java
│   │   │           │   ├── JwtUtil.java
│   │   │           │   └── SecurityConfig.java
│   │   │
│   │   │           ├── service/
│   │   │           │   ├── AuthService.java
│   │   │           │   ├── BookingService.java
│   │   │           │   ├── CustomUserDetailsService.java
│   │   │           │   └── TrainService.java
│
│   ├── resources/
│   │   ├── application.properties
│   │   └── data.sql (optional for testing)
│
├── build.gradle
└── README.md
```



## ✨ Features

### 👤 User Operations
- **Register and Log in** – JWT-based authentication for secure login sessions.
- **Search Trains** – Find trains between source and destination stations.
- **View Seat Availability** – Check available seats for a particular train.
- **Book a Seat** – Book a seat only if `availability > 0`.
- **View Booking Details** – Retrieve all your past or current bookings.

### 🛠️ Admin Operations
- **Add New Trains** – Create and manage train schedules.
- **Secured Admin Endpoints** – Protected using:
  - API Key (via request header)
  - `ROLE_ADMIN` authority-based access control

---

## 🔐 Security Features
- **JWT Token-based Authentication** – Secure and stateless session management.
- **Spring Security Role Checks** – Enforced using `@PreAuthorize` for endpoint-level authorization.
- **API Key Interceptor** – Validates access to sensitive admin-only routes.
- **Pessimistic Locking** – Ensures safe seat booking under high concurrency to prevent double booking.


## 📡 API Endpoints


| Method | Endpoint                 | Access          | Description                 |
| ------ | ------------------------ | --------------- | --------------------------- |
| POST   | /api/register            | Public          | Register a new user         |
| POST   | /api/login               | Public          | Login and receive JWT       |
| GET    | /api/trains/availability | User            | Get trains between stations |
| POST   | /api/bookings/book       | User            | Book a seat                 |
| GET    | /api/bookings/{id}       | User            | Get booking info            |
| POST   | /api/admin/add-train     | Admin + API Key | Add new train               |
