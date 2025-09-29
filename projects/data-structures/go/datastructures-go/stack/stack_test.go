package stack

import "testing" // import testing package for unit tests

// Test basic push/pop/peek/size behaviors
func TestStackBasic(t *testing.T) { // define a test function with *testing.T
	s := New() // create a new empty stack

	if !s.IsEmpty() { // stack should be empty initially
		t.Fatalf("expected empty stack") // fail test if not empty
	} // end if

	s.Push(1) // push integer 1
	s.Push(2) // push integer 2
	s.Push(3) // push integer 3

	if s.Size() != 3 { // size should be 3 after pushes
		t.Fatalf("expected size 3, got %d", s.Size()) // report mismatch
	} // end if

	top, ok := s.Peek()        // peek top element
	if !ok || top.(int) != 3 { // expect ok true and value 3
		t.Fatalf("expected top 3, ok true; got %v, %v", top, ok) // fail otherwise
	} // end if

	v, ok := s.Pop()         // pop top element
	if !ok || v.(int) != 3 { // expect ok true and popped value 3
		t.Fatalf("expected pop 3, ok true; got %v, %v", v, ok) // fail otherwise
	} // end if

	v, ok = s.Pop()          // pop again
	if !ok || v.(int) != 2 { // expect 2
		t.Fatalf("expected pop 2; got %v, %v", v, ok) // fail otherwise
	} // end if

	v, ok = s.Pop()          // pop last remaining element
	if !ok || v.(int) != 1 { // expect 1
		t.Fatalf("expected pop 1; got %v, %v", v, ok) // fail otherwise
	} // end if

	if !s.IsEmpty() { // after popping all, stack must be empty
		t.Fatalf("expected empty stack after pops") // fail otherwise
	} // end if

	_, ok = s.Pop() // popping empty stack
	if ok {         // should not be ok
		t.Fatalf("expected pop on empty to be not ok") // fail if ok
	} // end if
} // end test
