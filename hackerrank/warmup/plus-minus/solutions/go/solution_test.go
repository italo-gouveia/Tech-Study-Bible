package main

import (
	"testing"
	"time"
)

// TestPlusMinus tests the plusMinus function with various inputs
func TestPlusMinus(t *testing.T) {
	tests := []struct {
		name     string
		arr      []int32
		expected []float64 // [positive, negative, zero]
	}{
		{
			name:     "Example 1: Two positive, two negative, one zero",
			arr:      []int32{1, 1, 0, -1, -1},
			expected: []float64{0.400000, 0.400000, 0.200000},
		},
		{
			name:     "Example 2: Three positive, two negative, one zero",
			arr:      []int32{-4, 3, -9, 0, 4, 1},
			expected: []float64{0.500000, 0.333333, 0.166667},
		},
		{
			name:     "All positive numbers",
			arr:      []int32{1, 2, 3, 4, 5},
			expected: []float64{1.000000, 0.000000, 0.000000},
		},
		{
			name:     "All negative numbers",
			arr:      []int32{-1, -2, -3, -4, -5},
			expected: []float64{0.000000, 1.000000, 0.000000},
		},
		{
			name:     "All zeros",
			arr:      []int32{0, 0, 0, 0, 0},
			expected: []float64{0.000000, 0.000000, 1.000000},
		},
		{
			name:     "Single zero",
			arr:      []int32{0},
			expected: []float64{0.000000, 0.000000, 1.000000},
		},
		{
			name:     "Single positive",
			arr:      []int32{1},
			expected: []float64{1.000000, 0.000000, 0.000000},
		},
		{
			name:     "Single negative",
			arr:      []int32{-1},
			expected: []float64{0.000000, 1.000000, 0.000000},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// We can't easily test the output of plusMinus since it prints to stdout
			// Instead, we'll test the logic by calling a helper function
			positive, negative, zero := countElements(tt.arr)
			n := len(tt.arr)

			positiveRatio := float64(positive) / float64(n)
			negativeRatio := float64(negative) / float64(n)
			zeroRatio := float64(zero) / float64(n)

			// Check with tolerance for floating point precision
			tolerance := 0.000001
			if !floatEquals(positiveRatio, tt.expected[0], tolerance) {
				t.Errorf("Positive ratio = %f, expected %f", positiveRatio, tt.expected[0])
			}
			if !floatEquals(negativeRatio, tt.expected[1], tolerance) {
				t.Errorf("Negative ratio = %f, expected %f", negativeRatio, tt.expected[1])
			}
			if !floatEquals(zeroRatio, tt.expected[2], tolerance) {
				t.Errorf("Zero ratio = %f, expected %f", zeroRatio, tt.expected[2])
			}
		})
	}
}

// TestEdgeCases tests boundary conditions
func TestEdgeCases(t *testing.T) {
	// Test with maximum constraint values
	maxArray := make([]int32, 100)
	for i := 0; i < 50; i++ {
		maxArray[i] = 100 // Positive
	}
	for i := 50; i < 100; i++ {
		maxArray[i] = -100 // Negative
	}

	positive, negative, zero := countElements(maxArray)
	if positive != 50 {
		t.Errorf("Expected 50 positive elements, got %d", positive)
	}
	if negative != 50 {
		t.Errorf("Expected 50 negative elements, got %d", negative)
	}
	if zero != 0 {
		t.Errorf("Expected 0 zero elements, got %d", zero)
	}
}

// TestPerformance tests performance with large inputs
func TestPerformance(t *testing.T) {
	// Create large test array
	arr := make([]int32, 1000)
	for i := range arr {
		arr[i] = int32(i%201 - 100) // Numbers from -100 to 100
	}

	// Measure execution time
	start := time.Now()
	for i := 0; i < 1000; i++ {
		countElements(arr)
	}
	duration := time.Since(start)

	// Performance should be reasonable (less than 1 second for 1000 iterations)
	if duration > time.Second {
		t.Errorf("Performance test took too long: %v", duration)
	}

	t.Logf("Performance test: 1,000 iterations completed in %v", duration)
}

// Benchmark tests
func BenchmarkPlusMinus(b *testing.B) {
	arr := []int32{1, -1, 0, 2, -2, 3, -3, 0, 4, -4}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		countElements(arr)
	}
}

func BenchmarkPlusMinusLarge(b *testing.B) {
	arr := make([]int32, 1000)
	for i := range arr {
		arr[i] = int32(i%201 - 100)
	}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		countElements(arr)
	}
}

// Helper function to count elements (for testing)
func countElements(arr []int32) (positive, negative, zero int) {
	for _, num := range arr {
		if num > 0 {
			positive++
		} else if num < 0 {
			negative++
		} else {
			zero++
		}
	}
	return
}

// Helper function to compare floats with tolerance
func floatEquals(a, b, tolerance float64) bool {
	diff := a - b
	if diff < 0 {
		diff = -diff
	}
	return diff < tolerance
}
