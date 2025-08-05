<div align="center">
  <h1>E-Commerce Platform</h1>
  
  <p>
    <img src="https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white" alt="Angular">
    <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot">
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
    <img src="https://img.shields.io/badge/GraphQL-E10098?style=for-the-badge&logo=graphql&logoColor=white" alt="GraphQL">
    <img src="https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white" alt="TypeScript">
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  </p>
</div>

A full-stack e-commerce platform built with Angular and Spring Boot, featuring a modern UI and robust backend services.

## 🚀 Features

### Frontend (Angular 17)
- **Responsive Design**: Built with Angular Material for a modern, mobile-friendly UI
- **State Management**: Utilizes Apollo Client for efficient data fetching and state management and HTTP Client for API calls
- **Server-Side Rendering**: Improved performance and SEO with Angular Universal
- **Interactive UI**: Smooth animations and transitions for better user experience

### Backend (Spring Boot 3.3.7)
- **RESTful API**: Clean, well-documented endpoints
- **Database**: MySQL integration with Spring Data JPA
- **Security**: JWT-based authentication
- **Payment Integration**: Razorpay payment gateway integration

## 🛠 Tech Stack

### Frontend
- **Framework**: Angular 17
- **UI Components**: Angular Material
- **State Management**: Apollo Client with GraphQL and HTTP Client for API calls
- **Styling**: SCSS
- **Package Manager**: npm

### Backend
- **Framework**: Spring Boot 3.3.7
- **Language**: Java 23
- **Database**: MySQL
- **ORM**: Spring Data JPA
- **Build Tool**: Maven

## 🚀 Getting Started

### Prerequisites
- Node.js 18+ & npm
- Java 23 JDK
- MySQL 8.0+
- Maven 3.6+

### Backend Setup
1. Navigate to the backend directory:
   ```bash
   cd E-Commarce_Backend
   ```
2. Configure your database settings in `application.properties`
3. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```
   The backend will be available at `http://localhost:8080`

### Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd E-Commarce-Frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   ng serve
   ```
   The frontend will be available at `http://localhost:4200`

## 🏗 Project Structure

### Backend
```
E-Commarce_Backend/
├── src/
│   ├── main/
│   │   ├── java/com/ecommarce/
│   │   │   ├── config/       # Configuration classes
│   │   │   ├── controller/   # REST controllers
│   │   │   ├── model/        # Entity classes
│   │   │   ├── repository/   # Data access layer
│   │   │   ├── service/      # Business logic
│   │   │   └── ECommarceBackendApplication.java
│   │   └── resources/        # Application properties and static resources
└── pom.xml                   # Maven configuration
```

### Frontend
```
E-Commarce-Frontend/
├── src/
│   ├── app/
│   │   ├── components/      # Reusable UI components
│   │   ├── services/        # API services
│   │   ├── models/          # TypeScript interfaces
│   │   ├── pages/           # Feature modules
│   │   ├── shared/          # Shared modules and components
│   │   └── app.module.ts    # Root module
├── assets/                  # Static assets
└── environments/            # Environment configurations
```

## 🛠 Development

### Building for Production

**Backend:**
```bash
mvn clean package
```

**Frontend:**
```bash
ng build --configuration production
```

### Docker Support
Both frontend and backend include Docker configurations for containerized deployment.
