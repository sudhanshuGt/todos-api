﻿# 🛠️ Spring Boot Todo API

This is a secure and minimal RESTful API for a Todo application built using **Spring Boot** with **JWT-based authentication**. Designed to work seamlessly with frontend clients (Flutter, React, etc.).


# Deployed on : https://todos-api-xqqr.onrender.com

---

## 🔗 API Endpoints

### 🔐 Authentication

**POST `/auth/signup`**  
Registers a new user.  
Send a JSON object with `name`, `email`, and `password`. On success, returns a JWT token.

```json
Request:
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "secret123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6Ikp..."
}
POST /auth/login
Authenticates an existing user. Returns a JWT token if successful.

json
Copy
Edit
Request:
{
  "email": "john@example.com",
  "password": "secret123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6Ikp..."
}
All the following /todos endpoints require an Authorization: Bearer <your_token> header.

📝 Todos
GET /todos
Returns all todos for the authenticated user.

json
Copy
Edit
Response:
[
  {
    "id": 1,
    "todo": "Buy groceries",
    "completed": false
  },
  {
    "id": 2,
    "todo": "Finish project",
    "completed": true
  }
]
POST /todos
Adds a new todo item.

json
Copy
Edit
Request:
{
  "todo": "Learn Spring Boot",
  "completed": false
}

Response:
{
  "id": 3,
  "todo": "Learn Spring Boot",
  "completed": false
}
PUT /todos/{id}
Updates a todo by ID.

json
Copy
Edit
Request:
{
  "todo": "Learn Spring Boot Advanced",
  "completed": true
}

Response:
{
  "id": 3,
  "todo": "Learn Spring Boot Advanced",
  "completed": true
}
DELETE /todos/{id}
Deletes a todo by ID.

http
Copy
Edit
Response:
204 No Content
