# Task Manager API

A Spring Boot REST API for managing users and tasks.

This project was built as a practical backend project for Java and Spring Boot interview preparation.

## Features

- User CRUD
- Task CRUD
- User and Task relationship
- Request DTOs
- Response DTOs
- Mapper classes
- Validation
- Global Exception Handling
- Duplicate email handling
- Not Found handling
- Pagination and Sorting
- Filter tasks by status
- Transaction management with @Transactional
- H2 in-memory database

## Technologies

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Bean Validation
- Lombok
- Maven
- Git
- GitHub

## Project Structure

controller -> REST API endpoints

service -> Business logic

repository -> Database access

entity -> JPA entities

dto -> Request and response objects

mapper -> Converts Entity to DTO

exception -> Global error handling

## User APIs

POST /api/users

Create a new user.

GET /api/users

Get all users.

GET /api/users/{id}

Get user by id.

PUT /api/users/{id}

Update user.

DELETE /api/users/{id}

Delete user.

## Task APIs

POST /api/tasks/users/{userId}

Create a new task for a user.

GET /api/tasks

Get all tasks.

GET /api/tasks/{id}

Get task by id.

PUT /api/tasks/{id}

Update task.

DELETE /api/tasks/{id}

Delete task.

GET /api/tasks/users/{userId}

Get all tasks for a specific user.

GET /api/tasks/status/{status}

Filter tasks by status.

GET /api/tasks/paged

Get tasks with pagination and sorting.

## Example User Request

```json
{
  "name": "Minoo",
  "email": "minoo@test.com"
}
```

## Example Task Request

```json
{
  "title": "Learn Spring Boot",
  "description": "Practice backend project",
  "status": "TODO",
  "dueDate": "2026-06-10"
}
```

## Task Status Values

TODO

IN_PROGRESS

DONE

## H2 Console

The project uses H2 in-memory database.

H2 Console URL:

http://localhost:8080/h2-console

JDBC URL:

jdbc:h2:mem:task_manager_db

Username:

sa

Password:

empty

## Run Project

```bash
mvn spring-boot:run
```

The app runs on:

http://localhost:8080

## Pagination Example

GET /api/tasks/paged?page=0&size=5&sortBy=dueDate&direction=asc

## What I Practiced

- Layered architecture in Spring Boot
- REST API design
- JPA relationships
- DTO and Mapper pattern
- Validation and exception handling
- Transaction management
- Git and GitHub workflow