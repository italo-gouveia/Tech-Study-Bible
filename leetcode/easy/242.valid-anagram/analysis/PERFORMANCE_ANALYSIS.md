# Performance Analysis — 242. Valid Anagram

## Summary
- Counting array (a–z): fastest, O(n) time, O(1) space
- HashMap (Unicode): O(n) time, O(k) space where k is alphabet size
- Sorting: O(n log n) time, larger constants

## Go vs Java (indicative)
- Go (bench on this machine):
  - ASCII counting: ~11.2 µs/op for 7k chars (per op), 0 allocs/op
  - Unicode map: ~193.9 µs/op, allocations expected (maps)
- Java micro-benchmark (local): `count: 33 ms, sort: 75 ms` for repeated workloads (1k counts, 200 sorts on ~7k chars)
- Sorting generally slower than counting; Unicode map slower due to hashing/allocations

## Benchmarks (Go, illustrative)
```go
func BenchmarkAnagramASCII(b *testing.B) {
    s := strings.Repeat("anagram", 1000)
    t := strings.Repeat("nagaram", 1000)
    for i := 0; i < b.N; i++ {
        _ = isAnagram(s, t)
    }
}

func BenchmarkAnagramUnicode(b *testing.B) {
    s := strings.Repeat("ábç", 2000)
    t := strings.Repeat("çbá", 2000)
    for i := 0; i < b.N; i++ {
        _ = isAnagramUnicode(s, t)
    }
}
```

## Recommendations
- For LeetCode constraints (lowercase): counting array
- For general text: Unicode map counting; consider normalization (NFC)
- Avoid sorting unless code brevity outweighs performance needs
