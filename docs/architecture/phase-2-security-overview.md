# Phase 2 — Identity and Security Architecture

**Project:** E-Commerce Microservices Platform  
**Phase:** 2  
**Status:** Proposed Architecture  
**Primary technologies:** Keycloak, OAuth 2.0, OpenID Connect, JWT, Spring Security

---

## 1. Purpose

Introduce centralized identity management and distributed authentication and authorization into the e-commerce platform.

The objective is to secure the existing Product Catalog Service and establish a security architecture that can be reused by future microservices.

The architecture must support:

- Customer authentication.
- Identity management.
- Token issuance and validation.
- Authorization based on roles and permissions.
- Secure communication between clients and services.
- Future service-to-service authentication.
- Integration with an API Gateway.
- Future Angular frontend integration.

## 2. Architectural Principles

1. Centralize identity management.
2. Separate authentication from business services.
3. Do not implement custom password management or token issuance.
4. Use industry-standard authentication protocols.
5. Each microservice remains responsible for enforcing its own authorization rules.
6. Do not trust requests simply because they originate from the API Gateway.
7. Keep identity data separate from business profile data.
8. Use short-lived access tokens and secure refresh-token practices.
9. Apply least-privilege access.
10. Avoid unnecessary coupling between the Identity Provider and business services.

## 3. High-Level Components

### 3.1 Identity Provider — Keycloak

Responsibilities:

- Customer identity management.
- Credential management.
- Login and logout.
- Authentication flows.
- Access token issuance.
- Refresh token issuance and management.
- Role and group management.
- Client registration.
- OpenID Connect discovery.
- Public signing key publication.

Keycloak is an independent infrastructure component, not a custom business microservice.

### 3.2 Product Catalog Service

Responsibilities:

- Product catalog business operations.
- Validate incoming access tokens.
- Enforce product-related authorization rules.
- Identify the authenticated principal.
- Protect administrative product operations.

The service uses Spring Security Resource Server.

### 3.3 Future User Service

Responsibilities:

- Customer profile management.
- Customer preferences.
- Customer business information.
- Customer-specific application data.

It does not own password management or independently issue authentication tokens.

### 3.4 Future API Gateway

Responsibilities:

- Centralized API entry point.
- Request routing.
- Optional initial token validation.
- Request filtering.
- Rate limiting.
- Cross-cutting API concerns.

The gateway will not replace authorization enforcement inside individual services.

## 4. Authentication Architecture

The proposed authentication protocol is OpenID Connect on top of OAuth 2.0.

For the future Angular application, use:

Authorization Code Flow with PKCE.

The Angular application is a public client and must not contain a client secret.

Keycloak authenticates the user and issues:

- Access Token
- ID Token
- Refresh Token, when configured and appropriate

The access token is used to access protected APIs.

The ID token represents the authentication event and user identity to the OIDC client. It is not used as an API access token.

## 5. Token Validation Architecture

Each protected Spring Boot microservice operates as an OAuth 2.0 Resource Server.

The service must validate:

- JWT signature.
- Issuer (iss).
- Expiration (exp).
- Not-before (nbf).
- Audience (aud), where configured.
- Required scopes or authorities.

The Identity Provider publishes signing keys through its JWKS endpoint.

Microservices use these public keys to validate JWT signatures without contacting Keycloak for every request.

## 6. Authorization Architecture

Authorization is enforced within the business service.

Initial proposed roles:

- CUSTOMER
- ADMIN

Initial proposed permissions:

- product:read
- product:write
- product:delete

Example policy:

| Operation | CUSTOMER | ADMIN |
|---|---|---|
| View products | Allowed | Allowed |
| Create products | Denied | Allowed |
| Update products | Denied | Allowed |
| Delete products | Denied | Allowed |

These are initial learning-project policies and can evolve.

Authorization must be based on validated token claims and application-specific rules.

## 7. Identity vs Business Profile

Identity data and customer profile data have different responsibilities.

Keycloak owns:
- Credentials.
- Authentication sessions.
- Identity lifecycle.
- Identity provider associations.
- Security-related account state.

User Service owns:
- Customer profile.
- Contact preferences.
- Business-specific customer information.
- Customer application preferences.

A stable identity identifier (subject / sub claim) links the two systems.

Do not use email as the primary identity relationship because email addresses can change.

## 8. Security Boundaries

- External clients must use HTTPS outside local development.
- Passwords must never be stored in business microservice databases.
- Access tokens must not be logged.
- Client secrets must not be committed to Git.
- APIs must validate token signatures and relevant claims.
- Sensitive operations must enforce authorization at the service level.
- Service-to-service authentication must be designed separately from customer authentication.

## 9. Out of Scope for Phase 2

- API Gateway implementation.
- Angular implementation.
- Service-to-service OAuth integration.
- Multi-factor authentication.
- Social login.
- External identity federation.
- Fine-grained policy engines.
- Production identity-provider high availability.

These may be introduced in later phases.

## 10. Phase 2 Success Criteria

Phase 2 is considered complete when:

1. Keycloak runs locally.
2. A realm and client configuration are established.
3. A test user can authenticate.
4. An access token can be obtained.
5. The Product Catalog Service validates the token.
6. Unauthenticated requests to protected endpoints return HTTP 401.
7. Authenticated users without sufficient permissions receive HTTP 403.
8. Authorized requests succeed.
9. Token expiration is handled correctly.
10. The developer can explain the complete authentication and authorization flow.