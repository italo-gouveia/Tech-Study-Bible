# 20. Valid Parentheses

- Problem: https://leetcode.com/problems/valid-parentheses/
- Reference: https://neetcode.io/solutions/valid-parentheses

## Approaches
- Stack simulation with closing→opening lookup
- Early guards: odd length → false; closing with empty stack → false
- Time: O(n), Space: O(n)