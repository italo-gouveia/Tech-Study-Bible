package strategy

// We demonstrate the Strategy pattern with a simple payment example
// where different strategies implement the same behavior.

import "strconv" // import strconv to convert int to string using Itoa

// PaymentStrategy defines the behavior interface
type PaymentStrategy interface { // declare interface for strategies
	Pay(amount int) string // Pay executes payment and returns a message
} // end interface

// CreditCardStrategy is one concrete strategy
type CreditCardStrategy struct { // struct holding card details
	Cardholder string // name on the card
	Number     string // card number (masked in real apps)
} // end struct

// Pay implements PaymentStrategy for CreditCardStrategy
func (c CreditCardStrategy) Pay(amount int) string { // method on value receiver
	return "Paid " + strconv.Itoa(amount) + " using Credit Card of " + c.Cardholder // return message using strconv.Itoa
} // end method

// PixStrategy is another concrete strategy (popular instant payment in BR)
type PixStrategy struct { // struct holding pix key id
	Key string // pix key identifier
} // end struct

// Pay implements PaymentStrategy for PixStrategy
func (p PixStrategy) Pay(amount int) string { // method on value receiver
	return "Paid " + strconv.Itoa(amount) + " using Pix key " + p.Key // return message using strconv.Itoa
} // end method

// Checkout is the Context that uses a strategy
type Checkout struct { // context struct
	Strategy PaymentStrategy // selected strategy to use
} // end struct

// SetStrategy allows changing behavior at runtime
func (c *Checkout) SetStrategy(s PaymentStrategy) { // method on pointer receiver
	c.Strategy = s // assign the provided strategy to the context
} // end method

// Process performs payment using current strategy
func (c Checkout) Process(amount int) string { // method on value receiver
	if c.Strategy == nil { // ensure a strategy is set
		return "No payment strategy set" // return error message if nil
	} // end if
	return c.Strategy.Pay(amount) // delegate to strategy implementation
} // end method
