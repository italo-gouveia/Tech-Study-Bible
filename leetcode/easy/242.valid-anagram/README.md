# 242. Valid Anagram

- Problem: https://leetcode.com/problems/valid-anagram/
- Reference: https://neetcode.io/solutions/valid-anagram

## Approaches
- Counting array (26 lowercase letters): O(n) time, O(1) extra space
- HashMap for Unicode (runes in Go): O(n) time, O(k) space
- Sorting both strings and comparing: O(n log n) time, O(1) extra space (if in-place)

## Follow-up (Unicode)
Use `map[rune]int` in Go and iterate over runes (not bytes) to handle multi-byte characters.
