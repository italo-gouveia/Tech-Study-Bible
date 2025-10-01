package main

// isAnagram checks whether t is an anagram of s using frequency counting.
// Assumes lowercase English letters a-z.
func isAnagram(s string, t string) bool {
	if len(s) != len(t) {
		return false
	}
	var count [26]int
	for i := 0; i < len(s); i++ {
		count[s[i]-'a']++
		count[t[i]-'a']--
	}
	for _, c := range count {
		if c != 0 {
			return false
		}
	}
	return true
}

// isAnagramUnicode handles general Unicode via rune counting.
func isAnagramUnicode(s, t string) bool {
	runesS := []rune(s)
	runesT := []rune(t)
	if len(runesS) != len(runesT) {
		return false
	}
	m := make(map[rune]int, len(runesS))
	for i := range runesS {
		m[runesS[i]]++
		m[runesT[i]]--
	}
	for _, v := range m {
		if v != 0 {
			return false
		}
	}
	return true
}
