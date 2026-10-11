# Daylist Java Backend

A simple REST API for the Daylist To-Do List frontend, built with Java 17+, Spring Boot, Spring Data JPA and H2.

## Run locally
1. Install Java 17 or later and Maven.
2. From this folder, run `mvn spring-boot:run`.
3. Health check: `http://localhost:8080/api/health`
4. Tasks endpoint: `http://localhost:8080/api/tasks`

## API endpoints
- `GET /api/tasks` — list all tasks
- `GET /api/tasks?completed=false` — filter by completion
- `GET /api/tasks?project=Study` — filter by project
- `GET /api/tasks/{id}` — get one task
- `POST /api/tasks` — create task (JSON body)
- `PUT /api/tasks/{id}` — update task
- `PATCH /api/tasks/{id}/complete?value=true` — update completion state
- `DELETE /api/tasks/{id}` — delete task
- `GET /api/health` — health check

Example JSON:
```json
{
  "title": "Finish Java assignment",
  "description": "Test and submit the program",
  "project": "Study",
  "dueDate": "2026-10-15",
  "priority": 1,
  "completed": false
}
```

The default H2 database is suitable for a student demo. For production deployment, configure a managed database and restrict CORS to the exact frontend domain. This demo API does not include authentication; do not use it for private multi-user data without adding authentication and authorization.
