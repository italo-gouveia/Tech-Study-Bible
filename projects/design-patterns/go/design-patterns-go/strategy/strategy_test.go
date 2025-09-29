package strategy

import "testing" // import testing for unit tests

// Test that different strategies produce different outputs and context switches work
func TestStrategyPattern(t *testing.T) { // define test function
	checkout := Checkout{} // create a new Checkout context with no strategy

	if msg := checkout.Process(100); msg != "No payment strategy set" { // process without strategy
		t.Fatalf("expected no strategy message, got %q", msg) // ensure error message shown
	} // end if

	cc := CreditCardStrategy{Cardholder: "Italo", Number: "****-****-****-1234"}    // define credit card strategy
	checkout.SetStrategy(cc)                                                        // set credit card as strategy
	if msg := checkout.Process(150); msg != "Paid 150 using Credit Card of Italo" { // process payment
		t.Fatalf("unexpected message for credit card: %q", msg) // ensure expected message
	} // end if

	pix := PixStrategy{Key: "italo@example.com"}                                         // define pix strategy
	checkout.SetStrategy(pix)                                                            // switch strategy at runtime
	if msg := checkout.Process(200); msg != "Paid 200 using Pix key italo@example.com" { // process payment
		t.Fatalf("unexpected message for pix: %q", msg) // ensure expected message
	} // end if
} // end test
