# ej-store

E-commerce platform workspace. Phase 1 starts with the Product Catalog
microservice; business rules and domain fields remain to be defined by the
developer.

## Project layout

- `.github/` — Copilot project and service instructions.
- `docs/architecture/` — architecture documentation.
- `docs/decisions/` — architecture decision records.
- `services/product-catalog-service/` — Java 21 / Spring Boot 3 catalog service.
- `infrastructure/docker/` — local development Docker Compose configuration.

## Run the Product Catalog service locally

Requirements: Java 21, Maven 3.9+, and Docker with the Compose plugin.

### Run the service and PostgreSQL in Docker

```bash
cd infrastructure/docker
docker compose up --build
```

Compose builds the service in a Maven/Java 21 build stage and copies its
packaged application into a smaller Java 21 runtime image. The runtime runs as
a non-root user and checks the Actuator health endpoint. PostgreSQL runs in a
separate container on a private Compose network; its data is stored in a named
volume. Both services publish ports only on localhost. Compose reads database
settings from `infrastructure/docker/.env`; if needed, copy
`.env.example` to `.env` and set a local password before starting.

Stop the containers while keeping the database volume:

```bash
docker compose down
```

Inspect container status and follow logs:

```bash
docker compose ps
docker compose logs -f
```

### Run the service outside Docker

Start PostgreSQL from the repository root:

```bash
cd infrastructure/docker
docker compose up -d product-catalog-db
```

Then run the application:

```bash
cd ../../services/product-catalog-service
mvn spring-boot:run
```

The `dev` profile is the default. Local database settings can be overridden
with `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`.

Run the service tests:

```bash
mvn test
```

The `test` profile uses an in-memory H2 database and does not require Docker.

## GitHub Actions

The `Product Catalog CI` workflow runs on pull requests targeting `main` and
pushes to `main`. It checks out the repository, sets up Java 21 with Maven
dependency caching, compiles the service, runs tests, packages the application,
builds the Docker image, and validates that the image runs as the non-root
`app` user with a working Java runtime.

The separate `Publish Product Catalog to GHCR` workflow is run manually
(**Actions → Publish Product Catalog to GHCR → Run workflow**) and only from
`main`. It first runs the CI workflow and publishes only if CI passes. It sets
up Docker Buildx (required for the GitHub Actions build cache), then
authenticates to GitHub Container Registry using the automatically
provided `GITHUB_TOKEN`, then builds and publishes the image as
`ghcr.io/<owner>/<repository>` with a commit-SHA tag and `latest`. No personal
access token or additional secret is needed.

In the repository's **Settings → Actions → General**, allow GitHub Actions to
create and approve pull requests only if your project needs it; this workflow
does not require that permission. The workflow declares `contents: read` for
CI and `contents: read` plus `packages: write` for publishing. Ensure Actions
are enabled and the repository's workflow permissions allow the workflow-level
`packages: write` grant. The `main`-only push trigger is the approved publishing
branch policy; protect `main` with branch protection rules or a ruleset to
control what can be merged and published. Image visibility can be adjusted
under the package's settings after its first successful publication.

## Local endpoints

- Health: <http://localhost:8080/actuator/health>
- OpenAPI JSON: <http://localhost:8080/api-docs>
- Swagger UI: <http://localhost:8080/swagger-ui.html>

The catalog API has no business endpoints yet; those contracts should be
defined before implementation.
