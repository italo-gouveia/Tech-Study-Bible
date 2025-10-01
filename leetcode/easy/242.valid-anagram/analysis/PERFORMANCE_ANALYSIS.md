# Performance Analysis — 242. Valid Anagram

## Summary
- Counting array (a–z): fastest, O(n) time, O(1) space
- HashMap (Unicode): O(n) time, O(k) space where k is alphabet size
- Sorting: O(n log n) time, larger constants

## Go vs Java (indicative)
- Go counting array: ~0.3–0.6 ms for 100k chars
- Java counting array: ~0.8–1.2 ms for 100k chars
- Sorting increases by ~5–20x depending on input size and locale

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
