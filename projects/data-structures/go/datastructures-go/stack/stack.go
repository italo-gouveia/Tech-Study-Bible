package stack

// We implement a generic-like stack using empty interface and type parameters are avoided
// because we want to keep compatibility with older Go versions. Each line is commented.

// Stack represents a simple LIFO stack of interface{} values
type Stack struct { // define a struct named Stack
	items []interface{} // items holds the elements of the stack in a slice
}

// New creates and returns an empty stack
func New() *Stack { // constructor function returning pointer to Stack
	return &Stack{ // allocate a new Stack struct
		items: make([]interface{}, 0), // initialize items slice with length 0
	} // close struct literal
} // end function

// Push adds an element to the top of the stack
func (s *Stack) Push(value interface{}) { // method with receiver *Stack
	s.items = append(s.items, value) // append value to the items slice
} // end method

// Pop removes and returns the top element of the stack
// Second return value indicates whether a value was present
func (s *Stack) Pop() (interface{}, bool) { // method returning value and ok flag
	if len(s.items) == 0 { // check if stack is empty
		return nil, false // return nil and false if empty
	} // end if
	lastIndex := len(s.items) - 1 // compute index of last element
	value := s.items[lastIndex]   // get the element at last index
	s.items = s.items[:lastIndex] // shrink slice to remove last element
	return value, true            // return value and true indicating success
} // end method

// Peek returns the top element without removing it
// Second return value indicates whether a value was present
func (s *Stack) Peek() (interface{}, bool) { // method returning value and ok flag
	if len(s.items) == 0 { // check if stack is empty
		return nil, false // return nil and false if empty
	} // end if
	return s.items[len(s.items)-1], true // return last element and true
} // end method

// Size returns the number of elements in the stack
func (s *Stack) Size() int { // method returning int size
	return len(s.items) // return length of items slice
} // end method

// IsEmpty reports whether the stack has no elements
func (s *Stack) IsEmpty() bool { // method returning bool
	return len(s.items) == 0 // true when items length is zero
} // end method
