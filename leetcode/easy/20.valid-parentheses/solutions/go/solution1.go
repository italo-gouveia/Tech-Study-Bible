package main

// isValid uses a stack to validate parentheses.
func isValid(s string) bool {
	if len(s)%2 == 1 { // odd length cannot be balanced
		return false
	}
	stack := make([]byte, 0, len(s))
	match := map[byte]byte{')': '(', ']': '[', '}': '{'}
	for i := 0; i < len(s); i++ {
		c := s[i]
		switch c {
		case '(', '[', '{':
			stack = append(stack, c)
		case ')', ']', '}':
			if len(stack) == 0 || stack[len(stack)-1] != match[c] {
				return false
			}
			stack = stack[:len(stack)-1]
		}
	}
	return len(stack) == 0
}