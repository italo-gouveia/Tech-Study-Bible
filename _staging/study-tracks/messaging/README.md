# Messaging (Kafka, RabbitMQ) - Study Track

## Objectives
- Understand message brokers and delivery guarantees
- Model producers/consumers and event-driven workflows
- Apply patterns: pub/sub, CQRS, outbox, retries, DLQ

## Study Path
1. Broker Basics: topics/queues, partitions, offsets
2. Delivery: at-most-once, at-least-once, exactly-once (trade-offs)
3. Ordering & Idempotency: keys, deduplication
4. Patterns: outbox, saga, DLQ, backoff
5. Monitoring: lag, throughput, consumer groups

## Hands-on
- Event model writeup for a simple service
- Idempotent consumer design notes
- Outbox pattern explanation with steps

## Integration Points
- Notes: `notes/messaging/*`
- Projects: `projects/messaging-labs/*`
