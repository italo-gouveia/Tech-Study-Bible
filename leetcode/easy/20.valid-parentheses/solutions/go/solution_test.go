package main

import "testing"

func TestIsValid(t *testing.T) {
	if !isValid("()") { t.Fatalf("expected true") }
	if !isValid("()[]{}") { t.Fatalf("expected true") }
	if isValid("(]") { t.Fatalf("expected false") }
	if !isValid("([])") { t.Fatalf("expected true") }
	if isValid("([)]") { t.Fatalf("expected false") }
}

func BenchmarkIsValid(b *testing.B) {
	s := "()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}()[]{}"
	for i := 0; i < b.N; i++ {
		isValid(s)
	}
}