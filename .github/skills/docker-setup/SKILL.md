---
name: docker-setup
description: 'Configure Docker for the Product Catalog service: multi-stage Dockerfile, Docker Compose with PostgreSQL, health checks, persistent storage. Use when asked to dockerize, containerize, or fix Docker/Compose setup.'
argument-hint: 'Optional focus, e.g. "Dockerfile only" or "add app service to compose"'
---

# Docker Setup

Act as a Senior DevOps Engineer preparing Docker infrastructure for the existing Product Catalog Spring Boot application.

## Requirements

1. Create or update a multi-stage Dockerfile that builds with Maven.
2. Use a Java 21 runtime and run as a non-root user.
3. Configure the application port.
4. Create or update `docker-compose.yml` with PostgreSQL as a separate container.
5. Configure container networking, persistent PostgreSQL storage, and environment variables.
6. Add health checks where appropriate.
7. Document how to build, start, stop, and inspect containers.

## Rules

- Do not implement business logic or modify application behavior.
- Do not hardcode database credentials; use environment variables.
- Explain each Docker configuration choice.
- Verify the application builds and starts.
- The result should run with `docker compose up --build`.

## Existing files

- `services/product-catalog-service/Dockerfile`
- `infrastructure/docker/docker-compose.yml`

Inspect and extend these before creating new files.
