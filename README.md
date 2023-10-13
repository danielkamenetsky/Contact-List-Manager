# Capco-contact-list-manager

This project is designed to manage contacts and notes. It offers RESTful endpoints to handle CRUD operations for both contacts and notes.

## Table of Contents

- [Challenges faced](#challenges-faced)
- [Current State](#current-state)
- [Future Plans](#future-plans)
- [Setup & Installation](#setup--installation)
- [Usage](#usage)
- [Contributions](#contributions)

## Challenges faced:

### Security Configuration
Encountered issues with the security setup, especially concerning the login mechanism.

### Swagger API Integration
Faced challenges integrating the Swagger API, which resulted in conflicts with the security configurations.

### Integration Tests
The integration tests unveiled certain security-related errors, specifically 401 (Unauthorized) and 403 (Forbidden) HTTP status codes when trying to perform operations.

## Current State:

As of now, to ensure the CRUD functionalities could be tested through tools like Postman, CSRF security has been disabled. The basic functionalities such as pagination and sorting have been tested and verified.

## Future Plans:

1. Reinstate and refine security configurations, ensuring smooth integration with Swagger.
2. Further improve integration test coverage and address the currently known issues.
3. Enhance the system with more features, potentially integrating more services and third-party tools.

## Setup & Installation:

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


## Contributions:

Contributions are always welcome! Please ensure to test your changes thoroughly before submitting a pull request.

## License:
MIT License - Daniel Kamenetsky
