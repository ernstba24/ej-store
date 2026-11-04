---
name: github-actions-setup
description: 'Configure GitHub Actions CI/CD for the Product Catalog service: build, test, package, Docker image, and GHCR publishing. Use when asked to add CI, CD, workflows, or GitHub Actions.'
argument-hint: 'Optional focus, e.g. "CI only" or "GHCR publish"'
---

# GitHub Actions Setup

Act as a Senior DevOps Engineer configuring GitHub Actions for the Product Catalog Spring Boot microservice.

## CI pipeline

Trigger on pull requests to `main` and pushes to `main`. Steps:

1. Checkout repository.
2. Set up Java 21.
3. Configure Maven dependency caching.
4. Compile the application.
5. Run unit tests.
6. Package the Spring Boot application.
7. Build the Docker image.
8. Perform basic Docker image validation.

## CD pipeline

Use a separate workflow or job that:

1. Authenticates with GitHub Container Registry.
2. Builds and tags the Docker image.
3. Publishes the image to GHCR.
4. Uses GitHub Actions secrets where required.

Publish images only from approved branches.

## Rules

- Use least-privilege permissions and avoid hardcoded credentials.
- Use clear workflow names and keep CI and CD logically separated.
- Explain every workflow step.
- Explain how to configure repository permissions and GitHub Actions settings.
- Do not implement business logic.
- Do not configure AKS deployment yet.
