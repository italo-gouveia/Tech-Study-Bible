package main

import (
	"bufio"
	"fmt"
	"os"
)

// firstUniqChar returns the 0-based index of the first character that
// appears exactly once. If none exists, returns -1.
// Optimized for LeetCode 387 constraints: lowercase English letters only.
func firstUniqChar(s string) int {
	// 'a'..'z' are contiguous in ASCII. Subtracting 'a' normalizes:
	// 'a'-'a'=0, 'b'-'a'=1, ..., 'z'-'a'=25. This lets us index a [26]int.
	var freq [26]int

	// First pass: count frequencies using byte indexing
	for i := 0; i < len(s); i++ {
		// Map byte 'a'..'z' to indices 0..25 using s[i]-'a'
		freq[s[i]-'a']++
	}

	// Second pass: find first index with count == 1
	for i := 0; i < len(s); i++ {
		// Check the normalized bucket; 1 means unique
		if freq[s[i]-'a'] == 1 {
			return i
		}
	}
	return -1
}

func main() {
	in := bufio.NewReader(os.Stdin)
	fmt.Print("Enter a string: ")
	line, _ := in.ReadString('\n')
	for len(line) > 0 && (line[len(line)-1] == '\n' || line[len(line)-1] == '\r') {
		line = line[:len(line)-1]
	}

	idx := firstUniqChar(line)
	fmt.Println(idx)
}
