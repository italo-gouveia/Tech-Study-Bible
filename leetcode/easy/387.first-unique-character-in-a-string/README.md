# 387. First Unique Character in a String

- Problem: https://leetcode.com/problems/first-unique-character-in-a-string/
- Reference: https://neetcode.io/problems/first-unique-character-in-a-string?list=neetcode250

## Problem Description

Given a string `s`, find the first non-repeating character in it and return its index. If it does not exist, return `-1`.

## Examples

```
Input: s = "leetcode"
Output: 0
```

```
Input: s = "loveleetcode"
Output: 2
```

```
Input: s = "aabb"
Output: -1
```

## Constraints

- `1 <= s.length <= 1e5`
- `s` consists of only lowercase English letters.

## Approaches

### Solution 1: Two-Pass Frequency Array (Optimal)
- Time: O(n)
- Space: O(1) — fixed alphabet size 26
- Idea: Count with `int[26]` (map `'a'..'z'` to `0..25` via `c - 'a'`), then scan to find the first index with count 1.

### Solution 2: Two-Pass Hash Map (Unicode-safe / general)
- Time: O(n)
- Space: O(k) — distinct characters
- Idea: Count chars in a map, then scan to return the first with count 1. Slower than array given constraints.

### Solution 3: IndexOf/LastIndexOf (Concise)
- Time: O(26·n)
- Space: O(1)
- Idea: For each letter `'a'..'z'`, check `indexOf` and `lastIndexOf`; if equal and not -1, track the minimum index.

## Solutions

- Go: [solutions/go/main.go](solutions/go/main.go)
- Java: [solutions/java/Solution.java](solutions/java/Solution.java)

## Key Notes

- Use `'a'..'z'` normalization: `'a'-'a'=0`, `'b'-'a'=1`, …, `'z'-'a'=25` to index a 26-sized array efficiently.
- Prefer array counting for this problem’s constraints; map/runes only if you must support arbitrary Unicode.

