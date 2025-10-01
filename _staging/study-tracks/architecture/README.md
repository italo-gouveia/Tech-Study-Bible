# Architecture (DDD, Hexagonal) - Study Track

## Objectives
- Model domains with clear boundaries and ubiquitous language
- Apply Hexagonal/Ports & Adapters for testable design
- Practice layering, aggregates, repositories, and application services

## Study Path
1. Domain Modeling: entities, value objects, aggregates
2. Application Layer: use cases, services, DTOs
3. Infrastructure: adapters (DB, HTTP, messaging), repositories
4. Hexagonal: ports (interfaces) and adapters (impl)
5. Boundaries: modules, packages, dependency rules

## Hands-on
- Refactor a small feature behind ports/adapters (Go/Java)
- In-memory repository + persistence adapter swap
- Validation and invariants at aggregate boundaries

## Integration Points
- Projects: `projects/*` (create `architecture-labs`)
- Notes: `notes/architecture/*` with diagrams/examples
