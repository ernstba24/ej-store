---
name: scaffold-component
description: 'Generate boilerplate for a Spring Boot component such as controller, service, repository, DTO, mapper, exception handler, config, or test scaffold with TODO placeholders. Use when asked to scaffold or create a skeleton class.'
argument-hint: 'Component to scaffold, e.g. "CategoryController"'
---

# Scaffold Component

Generate approved boilerplate only; the developer implements business logic.

## Procedure

1. Confirm the requirement and the component's responsibility. Propose the plan first if the request is unclear or large.
2. Follow the existing layered structure: controller, service, repository, entity, dto, mapper, exception, config.
3. Use Java 21, constructor injection, DTOs at API boundaries, and Jakarta Validation only for approved constraints.
4. Create class and method skeletons with `// TODO:` placeholders for business rules, validation, queries, transactions, and API behavior.
5. Do not invent domain fields or requirements, and do not silently fill TODOs.
6. Add test scaffolding only when requested; leave scenarios for the developer.
7. Explain what the component does, why it is needed, how Spring manages it, relevant annotations, and common mistakes.
8. Stop and let the developer implement the logic.

Do not generate the entire application at once or add dependencies without explanation.
