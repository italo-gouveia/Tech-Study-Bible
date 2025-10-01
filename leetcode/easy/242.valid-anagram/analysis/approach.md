# 242. Valid Anagram — Approach

## Problem
Given two strings `s` and `t`, return true if `t` is an anagram of `s`, and false otherwise.

## Approaches

1) Counting Array (Lowercase English)
- Use an array of size 26 to count occurrences in `s` and subtract for `t`.
- If all counts end at zero, they are anagrams.
- Time: O(n) | Space: O(1) (fixed 26)

2) HashMap (Unicode-safe)
- Count frequencies using `map[rune]int` (Go) or `Map<Character,Integer>` (Java) and compare.
- Time: O(n) | Space: O(k) where k is alphabet size of inputs

3) Sorting
- Sort both strings and compare for equality.
- Time: O(n log n) | Space: O(1) extra (if in-place) or O(n)

## Trade-offs
- Counting array is fastest and simplest for constraints limited to lowercase a–z.
- HashMap handles Unicode and mixed alphabets at small space overhead.
- Sorting is concise but asymptotically slower than counting.

## Edge Cases
- Different lengths → immediately false
- Empty strings → true
- Repeated characters and skewed distributions
- Unicode combining characters (normalize if required by spec)
