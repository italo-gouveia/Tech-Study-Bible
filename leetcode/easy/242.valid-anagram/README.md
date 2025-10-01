# 242. Valid Anagram

- Problem: https://leetcode.com/problems/valid-anagram/
- Reference: https://neetcode.io/solutions/valid-anagram

## Approaches
- Counting array (26 lowercase letters): O(n) time, O(1) extra space
- HashMap for Unicode (runes in Go): O(n) time, O(k) space
- Sorting both strings and comparing: O(n log n) time

## Solutions
- Go: `solutions/go/solution1.go`
  - `isAnagram` (counting a–z)
  - `isAnagramUnicode` (Unicode map)
  - `isAnagramSort` (sorting)
  - Tests/benchmarks: `solutions/go/solution_test.go`
- Java: `solutions/java/`
  - `solution1.java` (counting a–z)
  - `solution2.java` (sorting)
  - `README.md` (build/run) and `Bench.java` (micro-benchmark)

## Analysis
- Approach: `analysis/approach.md`
- Performance: `analysis/PERFORMANCE_ANALYSIS.md` (Go benches + Java micro-benchmark)

## Follow-up (Unicode)
Use `map[rune]int` in Go and iterate runes; in Java, use `Map<Character,Integer>` or normalize as needed for combining characters.
