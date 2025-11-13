# Velora Sports Backend

Velora Sports Backend is a multi-layer Spring Boot application built to help sports organizations manage athletes, coaches, and administrators. It covers user and role management, organization memberships, workout planning, exercise catalogs, and athlete performance tracking. The project combines JWT authentication, role-based authorization, Redis-backed caching, and AOP-driven logging/ownership checks to deliver a secure and scalable platform.

---

## Features

- End-to-end authentication flow with access & refresh token lifecycle.
- Role-aware authorization at the HTTP method level (`SUPER_ADMIN`, `ATHLETE`, `COACH`, etc.).
- Organization and membership orchestration with status tracking.
- Workout program and workout item creation, including exercise assignments, repetitions, rest intervals, and points.
- Athlete progress monitoring: daily/weekly scores, completion rates, historical statistics.
- Personalized athlete feeds for home, tasks, profile, and organization-wide statistics.
- Cross-cutting logging and ownership validation using Spring AOP.
- Centralized exception handling for validation, authorization, and unexpected errors.

---

## Tech Stack

- **Language & Framework**: Java 21, Spring Boot 3.5
- **Modules**: Spring Web, Spring Data JPA, Spring Security, Spring Validation, Spring Cache (Redis)
- **Database**: PostgreSQL (JDBC driver 42.7)
- **Caching**: Redis with JSON serialization
- **Authentication**: JJWT for access and refresh tokens
- **Mapper**: MapStruct 1.6
- **Utilities**: Lombok for boilerplate reduction
- **API Docs**: springdoc-openapi (Swagger UI)
- **Build & Packaging**: Maven Wrapper (`mvnw`), multi-stage Dockerfile

---

## Architecture Overview

| Package | Responsibility |
| --- | --- |
| `controllers/api` | REST endpoints (Auth, User, Organization, Membership, Workout Program, Workout Item, Feed, etc.) |
| `business` | Service contracts (`abstracts`), service implementations (`concretes`), DTOs (requests/responses), MapStruct mappers |
| `dataAcess` | Spring Data JPA repositories |
| `entities` | JPA entities, enums, and the `BaseEntity` superclass |
| `common` | Shared configuration (`SecurityConfig`, `RedisConfig`), bootstrap data (`DataInitializer`), message constants |
| `aop` | Ownership checks (`SecurityAspect`) and logging (`LoggingAspect`) |
| `common/security` | `JwtService`, `JwtFilter`, and security helpers |
| `exceptions` | Global exception handler and custom error classes |

Tests live under `src/test/java` for service- and controller-level coverage.

---

## Configuration

### Environment Variables

| Variable | Description | Default |
| --- | --- | --- |
| `SPRING_PROFILES_ACTIVE` | Active profile (`local`, `prod`) | `default` |
| `DB_URL` | JDBC URL for production PostgreSQL | – |
| `DB_USER` | Production DB username | – |
| `DB_PASSWORD` | Production DB password | – |
| `REDIS_HOST` | Redis host (prod) | – |
| `REDIS_PORT` | Redis port (prod) | – |
| `JWT_SECRET_KEY` | Base64-encoded secret | `application.properties` |
| `JWT_EXPIRATIONMINUTE` | Access token lifetime in minutes | `15` |

Local development defaults are defined in `src/main/resources/application-local.properties`. Update the PostgreSQL and Redis credentials there if needed.

### Profiles

- `local`: connects to local PostgreSQL/Redis instances, logs SQL queries.
- `prod`: uses environment-driven credentials suitable for Render or other deployment targets.

---

## Setup & Running

### 1. Clone the repository
```bash
git clone https://github.com/<your-username>/Velora_Sports_Backend.git
cd Velora_Sports_Backend
```

### 2. Run locally (requires PostgreSQL & Redis)
```bash
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=local
```
The API becomes available at `http://localhost:8080`.  
Swagger UI: `http://localhost:8080/swagger-ui/index.html`

### 3. Run with Docker
```bash
docker build -t velora-sports-backend .
docker run -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_URL=jdbc:postgresql://<host>:<port>/<db> \
  -e DB_USER=<user> \
  -e DB_PASSWORD=<password> \
  -e REDIS_HOST=<redis-host> \
  -e REDIS_PORT=<redis-port> \
  velora-sports-backend
```

### 4. Execute tests
```bash
./mvnw clean test
```
Unit tests cover `AuthManager`, `UserManager`, and `UserController`.

---

## API Highlights

- `POST /api/auth/register` – Create a user and issue initial access/refresh tokens.
- `POST /api/auth/login` – Authenticate existing users and refresh tokens.
- `POST /api/auth/refreshToken/{refreshToken}` – Renew access credentials via refresh token.
- `POST /api/user/create` – Create users (requires `ROLE_SUPER_ADMIN`).
- `POST /api/workout-program/create` – Coaches assign programs to athletes.
- `POST /api/workout-item/create` – Coaches add daily workout items.
- `POST /api/feed/getHomeFeed/{athleteId}` – Personalized home feed for an athlete.
- `POST /api/athlete-progress/setStatus/{athleteId}/{progressId}` – Update completion state for a workout task.

Swagger UI documents the full contract.

---

## Domain Model

- **Identity & Roles**: `User`, `Role`, `UserRole`
- **Organization Layer**: `Organization`, `Membership`
- **Coach/Athlete**: `Coach`, `Athlete`
- **Training**: `WorkoutProgram`, `WorkoutItem`, `Exercise`
- **Progress Tracking**: `AthleteProgress` storing points, status, target entity references, and organization context
- **Token Lifecycle**: `RefreshToken`
- **Observability**: `Log` tracks service calls, duration, user, and status

`BaseEntity` provides UUID IDs, timestamps, and a soft-delete marker.

---

## Security

- `SecurityConfig` wires the JWT filter chain, disables CSRF, and enforces stateless sessions.
- `JwtFilter` validates `Authorization: Bearer` headers using `JwtService`.
- Method-level authorization uses `@PreAuthorize`.
- `SecurityAspect` verifies that:
  - Athletes mutate only their own progress records.
  - Coaches manage workout data solely for athletes they supervise.
- Custom exceptions and the `GlobalExceptionHandler` return consistent JSON error responses.

---

## Caching & Observability

- Redis cache configured via `RedisConfig` with a 10-minute TTL and JSON serialization.
- `AthleteProgressService.getLastMonthScores` is cached per organization.
- `LoggingAspect` captures service method calls, arguments, results, execution time, and the acting user for auditability.

---

## Directory Structure

```
src/main/java/com/ozalp/Velora/Sports
├── aop
├── business
├── common
├── controllers
├── dataAcess
├── entities
└── exceptions
```

Tests reside in `src/test/java/com/ozalp/Velora/Sports`.

---

## Development Notes

- When updating MapStruct mappers, run `./mvnw clean compile` to regenerate sources.
- Add new roles by extending `RoleEnum` and ensuring `DataInitializer` seeds them.
- If Redis/PostgreSQL are unavailable locally, temporarily swap to simple caching or H2 for development experiments.
- Expand logging by introducing custom annotations around methods and handling them in `LoggingAspect`.

---

## Roadmap & Contributions

- The project does not yet expose a formal roadmap or contribution guide.
- Suggestions:
  - Re-enable `OrganizationStatus` tracking on organizations.
  - Improve `UserControllerTest` assertions to reflect real responses.
  - Add integration tests or contract tests for feed endpoints.