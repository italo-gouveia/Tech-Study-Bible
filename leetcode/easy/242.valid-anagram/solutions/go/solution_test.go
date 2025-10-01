package main

import (
	"strings"
	"testing"
)

func TestIsAnagram_Basic(t *testing.T) {
	if !isAnagram("anagram", "nagaram") {
		t.Fatalf("expected true")
	}
	if isAnagram("rat", "car") {
		t.Fatalf("expected false")
	}
	if !isAnagramSort("anagram", "nagaram") {
		t.Fatalf("expected true (sort)")
	}
	if isAnagramSort("rat", "car") {
		t.Fatalf("expected false (sort)")
	}
}

func TestIsAnagram_Unicode(t *testing.T) {
	if !isAnagramUnicode("ábç", "çbá") {
		t.Fatalf("expected true for unicode anagram")
	}
	if isAnagramUnicode("äa", "aäa") {
		t.Fatalf("expected false due to different counts")
	}
}

func BenchmarkIsAnagram_ASCII(b *testing.B) {
	s := strings.Repeat("anagram", 1000)
	t := strings.Repeat("nagaram", 1000)
	for i := 0; i < b.N; i++ {
		_ = isAnagram(s, t)
	}
}

func BenchmarkIsAnagram_Unicode(b *testing.B) {
	s := strings.Repeat("ábç", 2000)
	t := strings.Repeat("çbá", 2000)
	for i := 0; i < b.N; i++ {
		_ = isAnagramUnicode(s, t)
	}
}
