# System Design - Study Track

## Objectives
- Understand core system design principles (scalability, availability, consistency)
- Practice trade-offs and back-of-the-envelope estimations
- Build small reference implementations for common components

## Study Path
1. Foundations: CAP, latency vs throughput, load patterns, caching, queues
2. Storage & Indexing: SQL vs NoSQL, partitioning (sharding), replication, consistency
3. Communication: sync vs async, REST/gRPC, messaging, event-driven
4. Scaling Patterns: stateless services, horizontal scaling, autoscaling, rate limiting
5. Reliability: retries, idempotency, circuit breakers, chaos basics
6. Observability: logs, metrics, traces; SLO/SLI/SLA

## Hands-on (Mini-casos)
- URL Shortener: writeup + minimal service (Go) + tests
- Rate Limiter: token bucket/leaky bucket lib (Go)
- Feed Service: pagination (time-based vs cursor) + caching strategy

## Integration Points
- Projects: `projects/go/*` for services and libs
- Notes: `notes/system-design/*` for design docs and diagrams
- Challenges: Reference to `leetcode/*` when data structures matter

## Suggested Resources
- Design high-level: create `notes/system-design/intro.md`
- Implementations: scaffold under `projects/go/system-design-labs/*`
