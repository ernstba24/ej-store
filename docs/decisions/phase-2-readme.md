## Phase 2 – Identity and Security

### Objective

Implement authentication and authorization for the Product Catalog Service using Keycloak, OAuth2/OIDC, JWT, and Spring Security.

The developer owns the security implementation and business decisions. GitHub Copilot assists with scaffolding, configuration templates, explanations, and code reviews.

### Milestones and User Stories

#### Milestone 1 – Set Up Keycloak

**US 1.1 – Run Keycloak Locally**

As a developer, I want to run Keycloak locally using Docker Compose so that I have an Identity Provider for the application.

**Developer responsibilities**
- Understand and configure the Keycloak environment.
- Start the containers and verify the admin console.

**Copilot responsibilities**
- Generate Docker Compose configuration.
- Provide environment variable templates.

**Acceptance criteria**
- Keycloak starts successfully.
- Keycloak is accessible through the browser.
- Keycloak uses its own PostgreSQL database.

**US 1.2 – Configure the Security Realm**

As an administrator, I want to create an application realm so that the e-commerce platform has its own identity configuration.

**Developer responsibilities**
- Create the realm, clients, roles, and test users.

**Copilot responsibilities**
- Explain configuration options and provide setup documentation.

**Acceptance criteria**
- Application realm exists.
- Product Catalog client is configured.
- Test users and roles are created.

#### Milestone 2 – Understand and Obtain Tokens

**US 2.1 – Authenticate a User**

As a customer, I want to authenticate through Keycloak so that I can obtain an access token to access protected APIs.

**Developer responsibilities**
- Use Postman to authenticate and obtain an access token.
- Inspect the JWT using a JWT decoder.

**Copilot responsibilities**
- Help configure Postman requests.
- Explain token claims.

**Acceptance criteria**
- Authentication succeeds for a valid user.
- An access token is returned.
- The `iss`, `sub`, `aud`, `exp`, and role/scope claims are understood.

**US 2.2 – Understand Token Validation**

As a developer, I want to understand how JWT validation works so that I can secure APIs without contacting Keycloak on every request.

**Developer responsibilities**
- Understand signing, public/private keys, JWKS, issuer, expiration, and audience validation.

**Copilot responsibilities**
- Explain the validation process.
- Help inspect Keycloak's JWKS configuration.

**Acceptance criteria**
- JWT signature validation is understood.
- Expected issuer and audience are identified.
- Expired and invalid tokens are understood to be rejected.

#### Milestone 3 – Secure Product Catalog Service

**US 3.1 – Configure Spring Security**

As a developer, I want Product Catalog to operate as an OAuth2 Resource Server so that it can validate Keycloak access tokens.

**Developer responsibilities**
- Add and review Spring Security dependencies.
- Implement the security configuration.
- Understand the Spring Security filter chain.

**Copilot responsibilities**
- Scaffold dependencies.
- Generate a basic `SecurityConfig` structure.

**Acceptance criteria**
- Product Catalog starts with Spring Security enabled.
- JWT authentication is configured.
- Requests with missing or invalid tokens are rejected on protected endpoints.

**US 3.2 – Validate JWTs**

As a developer, I want the service to validate JWTs so that only tokens issued by the trusted Keycloak realm are accepted.

**Developer responsibilities**
- Configure and verify issuer and audience validation.

**Copilot responsibilities**
- Generate initial Resource Server configuration.
- Help troubleshoot validation errors.

**Acceptance criteria**
- Valid tokens are accepted.
- Invalid signatures are rejected.
- Expired tokens are rejected.
- Tokens from an untrusted issuer or incorrect audience are rejected.

#### Milestone 4 – Implement Authorization

**US 4.1 – Map Roles and Permissions**

As a developer, I want to map Keycloak roles or scopes to Spring Security authorities so that the application can identify what an authenticated user is allowed to do.

**Developer responsibilities**
- Implement JWT authority mapping.
- Understand `GrantedAuthority` and `SecurityContext`.

**Copilot responsibilities**
- Explain Spring Security components.
- Review the implementation.

**Acceptance criteria**
- Roles or scopes from the JWT are available to Spring Security.
- The authenticated user's identity and authorities can be accessed.
- Role mapping works for different test users.

**US 4.2 – Protect API Endpoints**

As an administrator, I want to restrict Product Catalog operations based on user permissions.

**Developer responsibilities**
- Define and implement authorization rules using request-level or method-level security, such as `@PreAuthorize`.

**Copilot responsibilities**
- Explain available security mechanisms.
- Review authorization rules.

**Acceptance criteria**
- Public and protected endpoints are clearly defined.
- Users without permission cannot perform restricted operations.
- Authorized users can access permitted operations.

#### Milestone 5 – Test and Verify Security

**US 5.1 – Test Authentication and Authorization**

As a developer, I want to verify security behavior so that unauthorized access is prevented.

**Developer responsibilities**
- Execute tests using Postman.
- Define and write security test scenarios.

**Copilot responsibilities**
- Scaffold test classes.
- Help analyze test failures.

**Acceptance criteria**

| Scenario | Expected result |
|---|---|
| No access token | 401 Unauthorized |
| Invalid token | 401 Unauthorized |
| Expired token | 401 Unauthorized |
| Valid token, insufficient permissions | 403 Forbidden |
| Valid token, correct permissions | 200 OK |

**US 5.2 – Document the Implementation**

As a developer, I want to document the security implementation so that the architecture and decisions are understood.

**Developer responsibilities**
- Document the authentication flow, authorization rules, and security decisions.

**Copilot responsibilities**
- Help organize and draft documentation.

**Acceptance criteria**
- Authentication flow is documented.
- JWT validation process is documented.
- Role and permission mappings are documented.
- Relevant Architecture Decision Records (ADRs) are updated.

### Definition of Done

Phase 2 is complete when:

- [ ] Keycloak is running locally.
- [ ] Realm, clients, roles, and test users are configured.
- [ ] Access tokens can be obtained and inspected.
- [ ] Product Catalog operates as a Spring Security Resource Server.
- [ ] JWT signature, issuer, expiration, and audience are validated.
- [ ] Roles and permissions are mapped to Spring Security authorities.
- [ ] API endpoints enforce authorization rules.
- [ ] Authentication and authorization scenarios pass.
- [ ] Security documentation and ADRs are updated.