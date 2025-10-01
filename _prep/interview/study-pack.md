# Study Pack - Backend Go + React

This pack consolidates key topics we covered, with succinct explanations and code snippets you can review fast.

## Go - Concurrency Essentials
- Goroutines vs Threads: lightweight, small elastic stacks; M:N scheduler; millions possible
- Channels: sync through message passing (unbuffered block on send/recv; buffered block on full/empty)
- Context: propagate deadlines/cancel; avoid goroutine leaks by selecting on `<-ctx.Done()>`
- Race conditions: prefer channels; when sharing memory, guard with `sync.Mutex/RWMutex`; run `-race`

### Worker Pool with Cancellation
```go
package main
import (
    "context"
    "fmt"
    "sync"
    "time"
)

func worker(ctx context.Context, id int, jobs <-chan int, results chan<- int, wg *sync.WaitGroup) {
    defer wg.Done()
    for {
        select {
        case <-ctx.Done():
            return
        case j, ok := <-jobs:
            if !ok { return }
            time.Sleep(50 * time.Millisecond)
            select {
            case <-ctx.Done(): return
            case results <- j * 2:
            }
        }
    }
}

func main() {
    ctx, cancel := context.WithTimeout(context.Background(), 500*time.Millisecond)
    defer cancel()
    jobs := make(chan int, 10)
    results := make(chan int, 10)
    var wg sync.WaitGroup
    for i := 1; i <= 3; i++ { wg.Add(1); go worker(ctx, i, jobs, results, &wg) }
    for j := 1; j <= 10; j++ { jobs <- j }
    close(jobs)
    go func(){ wg.Wait(); close(results) }()
    for r := range results { fmt.Println("result:", r) }
}
```
PT: “Goroutines para paralelizar, channels para coordenar, context para cancelar e evitar vazamentos.”
EN: “Goroutines parallelize, channels coordinate, and context propagates deadlines/cancellation to prevent leaks.”

### Patterns: select, timeout, non-blocking
```go
// Non-blocking receive
select { case v := <-ch: _ = v; default: /* proceed */ }

// Timeout with timer reuse
timer := time.NewTimer(100*time.Millisecond)
defer timer.Stop()
select { case <-ch: case <-timer.C: }
```

## HTTP Client/Server (Go)
- Client: set timeouts; pass context; retries with backoff+jitter for idempotent ops
- Server: graceful shutdown; health endpoints; request-scoped context
```go
// Client
client := &http.Client{ Timeout: 3 * time.Second }
ctx, cancel := context.WithTimeout(context.Background(), 2*time.Second); defer cancel()
req, _ := http.NewRequestWithContext(ctx, http.MethodGet, "https://api", nil)
resp, err := client.Do(req)

// Server graceful shutdown
srv := &http.Server{ Addr: ":8080", Handler: mux }
go func(){ _ = srv.ListenAndServe() }()
stop := make(chan os.Signal, 1); signal.Notify(stop, os.Interrupt); <-stop
ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second); defer cancel()
_ = srv.Shutdown(ctx)
```
PT: “Cliente com timeout e contexto; servidor com desligamento gracioso.”
EN: “Client enforces deadlines via context; server shuts down gracefully honoring in-flight requests.”

## Databases (Go)
- `sql.DB` is a pool: tune `SetMaxOpenConns/SetMaxIdleConns/SetConnMaxLifetime`
- Transactions: `db.Transaction(func(tx *gorm.DB) error { ... })`
- Always propagate context: `db.WithContext(ctx)`
```go
// GORM transaction with context
txErr := db.WithContext(ctx).Transaction(func(tx *gorm.DB) error {
    if err := tx.Create(&u).Error; err != nil { return err }
    return nil
})
```
PT: “`sql.DB` é pool; use transações para atomicidade e `WithContext` para cancelamento.”
EN: “Treat `sql.DB` as a pool; use transactions for atomicity and `WithContext` for cancellation.”

## React - Core Hooks
- `useState`, `useEffect` (cleanup), `useMemo`, `useCallback`, `useContext`
- Memoize expensive calculations; stabilize handlers to avoid re-renders
```tsx
const Child = React.memo(function Child({ total, onAdd }:{ total:number; onAdd:()=>void }){
  return <button onClick={onAdd}>Total: {total}</button>;
});
function Cart(){
  const [items, setItems] = React.useState<number[]>([]);
  const total = React.useMemo(()=>items.reduce((a,b)=>a+b,0),[items]);
  const onAdd = React.useCallback(()=>setItems(p=>[...p,1]),[]);
  return <Child total={total} onAdd={onAdd}/>;
}
```
PT: “Memoizo cálculos e handlers para evitar rerender desnecessário.”
EN: “Memoize computations and handlers to avoid unnecessary re-renders.”

## gRPC
- REST vs gRPC: JSON vs Protobuf; edge vs service-to-service; streaming support
- Deadlines, metadata (request-id), interceptors
```proto
syntax = "proto3";
package user.v1;
message GetUserRequest { int64 id = 1; }
message User { int64 id = 1; string name = 2; }
service UserService { rpc GetUser(GetUserRequest) returns (User); }
```
```go
// Deadline-aware call
ctx, cancel := context.WithTimeout(context.Background(), time.Second); defer cancel()
resp, err := client.GetUser(ctx, &userv1.GetUserRequest{Id:42})
```
PT: “REST para borda; gRPC entre serviços quando contrato forte e latência importam.”
EN: “REST at the edge; gRPC for service-to-service when strong contracts and low latency matter.”

## Kubernetes (high level)
- Pod/Deployment/Service/Ingress; liveness/readiness; HPA
```yaml
apiVersion: apps/v1
kind: Deployment
metadata: { name: go-app }
spec:
  replicas: 3
  selector: { matchLabels: { app: go-app } }
  template:
    metadata: { labels: { app: go-app } }
    spec:
      containers:
      - name: go-app
        image: myrepo/go-app:latest
        ports: [{ containerPort: 8080 }]
---
apiVersion: v1
kind: Service
metadata: { name: go-app-svc }
spec:
  selector: { app: go-app }
  ports: [{ port: 80, targetPort: 8080 }]
  type: ClusterIP
```
PT: “Deployment mantém réplicas saudáveis; Service expõe e balanceia.”
EN: “Deployment maintains healthy replicas; Service exposes and balances.”

## Algorithms - Patterns and Templates
- Two pointers, sliding window, hashing, stack, binary search, heaps
- Know time/space for each; explain trade-offs succinctly

### Templates (Go)
```go
// Palindrome
def IsPalindrome(s string) bool {
    i, j := 0, len(s)-1
    for i < j { if s[i] != s[j] { return false }; i++; j-- }
    return true
}
// First unique rune
func FirstUniqueRune(s string) rune {
    m := make(map[rune]int)
    for _, r := range s { m[r]++ }
    for _, r := range s { if m[r]==1 { return r } }
    return 0
}
// Sliding window (longest substring without repeat)
func LengthOfLongestSubstring(s string) int {
    last := make(map[rune]int); start, best := 0, 0
    for i, r := range []rune(s) {
        if p, ok := last[r]; ok && p >= start { start = p + 1 }
        if i-start+1 > best { best = i-start+1 }
        last[r] = i
    }
    return best
}
```
PT: “Explique ideia, complexidade e por que é seguro (Unicode, janela, mapa).”
EN: “State the idea, complexity, and safety notes (Unicode, window invariants, map usage).”

## Phrasing Tips (interview)
- Context: "I standardize ctx as the first parameter and propagate deadlines/cancel."
- DB: "Treat sql.DB as a pool; tune per SLO; transactions for atomicity."
- Resilience: "Retries with backoff+jitter, circuit breakers, idempotency keys."
- React: "Separate server-state (React Query) from UI-state (hooks/Context)."

---
Checklist rápido antes da call:
- Rode `go test -race` nos exemplos; revise `ctx`, timeouts e shutdown.
- Tenha 2-3 frases curtas para cada tópico e 1 snippet mental pronto.
- Em algoritmos: fale primeiro, code depois; valide com 2-3 casos edge.

## Resilience (Retries, Backoff, Circuit Breaker, Idempotency)
- Retries: only idempotent ops; exponential backoff + jitter; cap total time
- Circuit breaker: fail fast under persistent errors; protect dependencies
- Idempotency: keys/tokens for create-like ops; safe to retry
- Timeouts everywhere; per-dependency isolation (bulkheads)
```go
// Exponential backoff + jitter (simplified)
base := 50 * time.Millisecond
for attempt := 0; attempt < 5; attempt++ {
    err := callOnce(ctx)
    if err == nil { break }
    d := base * (1<<attempt)
    jitter := time.Duration(rand.Int63n(int64(d/2)))
    time.Sleep(d/2 + jitter)
}
```
PT: “Retento só o que é idempotente, com backoff+jitter; quebro circuito em cascatas de erro.”
EN: “Retry only idempotent operations with backoff+jitter; use circuit breakers to fail fast.”

## Observability (Logs, Metrics, Traces)
- Logs: estruturados (JSON), request-id/correlation-id; níveis consistentes
- Metrics: RED/USE; counters, gauges, histograms; SLO/SLI
- Traces: spans por requisição; context propagation; sampling
```go
// Prometheus counter example
var reqs = promauto.NewCounterVec(prometheus.CounterOpts{
    Name: "http_requests_total", Help: "Total requests"}, []string{"path","code"})

func handler(w http.ResponseWriter, r *http.Request){
    start := time.Now()
    // ... handle ...
    code := http.StatusOK
    reqs.WithLabelValues(r.URL.Path, strconv.Itoa(code)).Inc()
    log.Printf("path=%s code=%d dur=%s", r.URL.Path, code, time.Since(start))
}
```
PT: “Métricas para taxa/erro/latência; logs com request-id; traces para cadeia entre serviços.”
EN: “Metrics for rate/error/latency; structured logs with request-id; traces across services.”
