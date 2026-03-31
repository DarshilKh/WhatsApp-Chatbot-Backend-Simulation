# WhatsApp Chatbot Backend Simulation

A simple WhatsApp chatbot backend built with Java and Spring Boot as part of an internship assignment.0

Live Hosted Link - https://whatsapp-chatbot-backend-simulation-oj0s.onrender.com/

---

## Tech Stack

- Java 21
- Spring Boot 3.2.3
- Maven
- Lombok

---

## Project Structure

```
src/
├── main/java/com/example/chatbot/
│   ├── ChatbotApplication.java
│   ├── controller/WebhookController.java
│   ├── model/MessageRequest.java
│   └── service/ChatService.java
└── main/resources/
    └── application.properties
```

---

## How to Run Locally

### Prerequisites
- Java 21+
- Maven 3.8+

### Steps

```bash
mvn clean install
mvn spring-boot:run
```

Server starts at `http://localhost:8080`

---

## API Endpoint

### POST /webhook

**Request:**
```bash
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"message": "Hi"}'
```

**Responses:**

| Input | Response |
|-------|----------|
| `Hi` | `Hello` |
| `Bye` | `Goodbye` |
| `  HI  ` (any case/whitespace) | `Hello` |
| `How are you?` | `I didn't understand that` |
| `""` or `null` | `I didn't understand that` |

---

## Sample curl Commands

```bash
# Hi → Hello
curl -X POST http://localhost:8080/webhook -H "Content-Type: application/json" -d '{"message": "Hi"}'

# Bye → Goodbye
curl -X POST http://localhost:8080/webhook -H "Content-Type: application/json" -d '{"message": "Bye"}'

# Unknown
curl -X POST http://localhost:8080/webhook -H "Content-Type: application/json" -d '{"message": "How are you?"}'

# Case insensitive
curl -X POST http://localhost:8080/webhook -H "Content-Type: application/json" -d '{"message": "  HI  "}'

# Empty
curl -X POST http://localhost:8080/webhook -H "Content-Type: application/json" -d '{"message": ""}'
```

---

## Running Tests

```bash
mvn test
```

5 tests covering: Hi, Bye, unknown input, case insensitivity, and empty message.


---

## Message Logging

All incoming messages are logged to the console via SLF4J:

```
INFO  c.e.c.controller.WebhookController - Received message: Hi
```
