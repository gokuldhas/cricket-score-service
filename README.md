# Cricket Score Service

## Overview
Cricket Score Service is a microservice application designed to manage and provide live cricket scores. It is built using Spring Boot, Java 22, and follows microservice architecture principles.

## Features
- Create, update, and delete match scores.
- Fetch live scores by match ID.
- Store and retrieve match data from an H2 in-memory database.
- Expose APIs with Swagger for easy interaction.

## Technologies Used
- Java 22
- Spring Boot
- Spring Data JPA
- H2 In-memory Database
- Swagger 2
- JUnit 5 for Testing

## Setup Instructions

### Prerequisites
- Java 22 (JDK)
- Maven 3.6+

### Steps to run:
1. Clone the repository:
    ```bash
    git clone https://github.com/your-repository/cricket-score-service.git
    ```
2. Navigate into the project directory:
    ```bash
    cd cricket-score-service
    ```
3. Build the project:
    ```bash
    mvn clean install
    ```
4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

5. Access the application:
   - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

### Running Tests
To run tests:
```bash
mvn test
```
![image](https://github.com/user-attachments/assets/fcda3e32-18f4-454d-acd1-5f71c3e4d4f1)


# Complete Application Microservice Boundaries
Here are five microservices for complete Application:

## *Score Service(This Service):
Responsible for fetching, storing, and updating live cricket scores from various matches.
This will have endpoints to provide real-time score updates and historical score data.

## Player Stats Service:
Responsible for retrieving player statistics (batting average, bowling figures, etc.).
This microservice will include endpoints to fetch individual and team player data.

## Match Info Service:
Provides information on upcoming and ongoing matches (schedule, venues, teams, etc.).
Endpoints will handle data related to match schedules, venues, teams, and their lineups.

## News and Articles Service:
Manages articles, news updates, and commentary on matches.
CRUD operations on articles and commentaries related to cricket news.

## User Profile Service:
Handles user accounts, preferences, and personalization (favorite teams, match alerts).
This service will offer user authentication, user preferences, and subscriptions
