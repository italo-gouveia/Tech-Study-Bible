# Observability (Logs, Metrics, Traces) - Study Track

## Objectives
- Instrument services for logs, metrics, and traces
- Define SLO/SLI and alerting strategies
- Use local tooling stack for hands-on

## Study Path
1. Logging: structure, correlation IDs, levels
2. Metrics: counters, gauges, histograms; RED/USE
3. Tracing: spans, context propagation; sampling
4. Dashboards & Alerts: practical SLOs and alerts
5. Profiling: pprof basics for CPU/memory

## Hands-on
- OpenTelemetry instrumentation (Go): HTTP client/server
- Exporters: Prometheus metrics, OTLP traces
- Local stack: Prometheus + Grafana + Jaeger (notes)

## Integration Points
- Notes: `notes/observability/*`
- Projects: `projects/obs-labs/*`
