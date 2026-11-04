---
applyTo: "services/product-catalog-service/**/*.java"
---

# Product Catalog Service Instructions

## Service Responsibility

The Product Catalog Service owns product information
and product categories.

It is responsible for:
- Product identification
- Product descriptions
- Product pricing information
- Product categorization
- Product lifecycle status

It does NOT own:
- Inventory quantities
- Warehouse availability
- Shopping carts
- Orders
- Payments
- Shipping

These responsibilities belong to separate microservices.

## Product Domain

Initial product attributes to consider:

- Product ID
- SKU
- Name
- Description
- Price
- Currency
- Category
- Product status
- Created timestamp
- Updated timestamp

These are proposed fields, not finalized requirements.
Discuss the domain model before generating its implementation.

## Architecture

Use a layered architecture:

- controller
- service
- repository
- entity
- dto
- mapper
- exception
- config

Use DTOs to separate API contracts from persistence entities.

Use constructor injection.

Keep controllers responsible for HTTP concerns.
Keep business logic in the service layer.
Keep persistence concerns in repositories.

## API Guidelines

Use REST conventions.

Consider:
- POST for product creation
- GET for product retrieval
- GET for product listings
- PUT or PATCH for updates
- DELETE for deletion or deactivation

Discuss pagination, filtering, and sorting
before implementing them.

## Implementation Restrictions

Do not implement product business rules automatically.

Generate class structures and method signatures
with TODO placeholders.

The developer implements:
- Product creation rules
- SKU uniqueness behavior
- Price validation rules
- Product update rules
- Category assignment rules
- Product activation/deactivation behavior