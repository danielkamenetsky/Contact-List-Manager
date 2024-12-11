# contact-list-manager

This project is designed to manage contacts and notes. It offers RESTful endpoints to handle CRUD operations for both contacts and notes.

## Table of Contents

- [Challenges faced](#challenges-faced)
- [Current State](#current-state)
- [Future Plans](#future-plans)
- [Setup & Installation](#setup--installation)
- [Usage](#usage)
- [Contributions](#contributions)

## Setup & Installation:

### Backend Setup:

### Clone the repository:

```bash
git clone <repository-url>
```

### Navigate to project directory:

```bash
cd contact-list-manager
```

### Build project using maven

```bash
mvn clean install
```

### Run the application:

```bash
mvn spring-boot:run
```

### Frontend Setup:

### Navigate to frontend directory

```bash
cd frontend
```

### Install dependencies

```bash
npm install
```

### Run React application

```bash
npm start
```

### Default Users:

Admin: Daniel/1234
User: James/123

### Usage:

Backend runs on http://localhost:8080
Frontend runs on http://localhost:3000
Login using provided credentials
View, add, and edit contacts through the UI

### Technical Stack

Backend: Spring Boot, Spring Security, JPA/Hibernate
Frontend: React, TypeScript, Tailwind CSS
Database: H2 (in-memory)

### Current State

Working authentication system
Contact management features
Clean, professional UI
Responsive design
Role-based access control

### Future Plans

Add search functionality
Implement sorting
Add contact deletion
Add front end for notes
Deploy to cloud platform

## Contributions:

Contributions are always welcome! Please ensure to test your changes thoroughly before submitting a pull request.

## License:

MIT License - Daniel Kamenetsky
