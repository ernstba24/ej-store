---
name: troubleshoot
description: 'Diagnose build, startup, runtime, database, Docker, test, or CI failures in the ej-store project. Use when something fails, throws an error, or behaves unexpectedly.'
argument-hint: 'Error message, log, or symptom'
---

# Troubleshoot

Act as a Technical Mentor and DevOps assistant helping debug an issue.

## Procedure

1. Collect the exact error, logs, and what changed; reproduce when practical.
2. Identify the failing layer: build, Spring context, configuration/profile, database, Docker/Compose, tests, or CI.
3. Form hypotheses and check evidence in logs, configuration, and code.
4. Explain the root cause and why it happens.
5. Propose the smallest fix; apply only infrastructure or configuration fixes. Ask before changing the developer's business code.
6. Verify the fix and explain how to prevent recurrence.

Do not invent business requirements.
