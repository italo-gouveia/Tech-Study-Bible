# Go Language Basics - Study Q&A

## Language Fundamentals

### Q: What makes Go different from other languages?
**A:** Key characteristics:
- **Compiled**: Fast compilation and execution
- **Statically typed**: Type safety at compile time
- **Garbage collected**: Automatic memory management
- **Concurrent by design**: Built-in goroutines and channels
- **Simple syntax**: Minimal keywords, clear structure
- **Cross-platform**: Single binary deployment

### Q: What are the basic data types in Go?
**A:** Go has several built-in types:

```go
// Numeric types
var i int = 42           // int (32 or 64 bits depending on platform)
var i8 int8 = 127        // 8-bit signed integer
var ui uint = 42         // unsigned integer
var f float64 = 3.14     // 64-bit floating point
var c complex128 = 1+2i  // complex number

// String and boolean
var s string = "Hello"   // string (immutable)
var b bool = true        // boolean

// Derived types
var arr [5]int           // array of 5 integers
var slice []int          // slice (dynamic array)
var m map[string]int     // map (hash table)
var ch chan int          // channel for communication
```

### Q: What's the difference between arrays and slices?
**A:**
- **Array**: Fixed size, value type, passed by value
- **Slice**: Dynamic size, reference type, passed by reference

```go
// Array - fixed size
var arr [3]int = [3]int{1, 2, 3}
fmt.Println(len(arr)) // 3 (always)

// Slice - dynamic size
var slice []int = []int{1, 2, 3}
slice = append(slice, 4, 5) // can grow
fmt.Println(len(slice)) // 5

// Slice from array
arr := [5]int{1, 2, 3, 4, 5}
slice := arr[1:4] // slice from index 1 to 3 (exclusive)
```

## Functions and Methods

### Q: How do functions work in Go?
**A:** Functions are first-class citizens:

```go
// Basic function
func add(a, b int) int {
    return a + b
}

// Multiple return values
func divide(a, b int) (int, error) {
    if b == 0 {
        return 0, errors.New("division by zero")
    }
    return a / b, nil
}

// Named return values
func calculate(x, y int) (sum, product int) {
    sum = x + y
    product = x * y
    return // naked return
}

// Variadic function
func sum(numbers ...int) int {
    total := 0
    for _, num := range numbers {
        total += num
    }
    return total
}

// Function as parameter
func apply(fn func(int) int, x int) int {
    return fn(x)
}
```

### Q: What's the difference between functions and methods?
**A:**
- **Function**: Standalone, not associated with any type
- **Method**: Associated with a type (receiver)

```go
// Function
func add(a, b int) int {
    return a + b
}

// Method with value receiver
type Rectangle struct {
    width, height float64
}

func (r Rectangle) area() float64 {
    return r.width * r.height
}

// Method with pointer receiver
func (r *Rectangle) scale(factor float64) {
    r.width *= factor
    r.height *= factor
}

// Usage
rect := Rectangle{width: 10, height: 5}
fmt.Println(rect.area()) // 50
rect.scale(2)            // modifies the original
fmt.Println(rect.area()) // 200
```

## Interfaces

### Q: How do interfaces work in Go?
**A:** Go uses implicit interface implementation:

```go
// Interface definition
type Writer interface {
    Write([]byte) (int, error)
}

// Any type that implements Write method satisfies Writer interface
type File struct {
    name string
}

func (f File) Write(data []byte) (int, error) {
    fmt.Printf("Writing to %s: %s\n", f.name, string(data))
    return len(data), nil
}

// Interface can be used polymorphically
func processData(w Writer, data []byte) {
    w.Write(data)
}

// Usage
file := File{name: "output.txt"}
processData(file, []byte("Hello World"))
```

### Q: What's the empty interface?
**A:** `interface{}` can hold any type:

```go
// Empty interface
var i interface{}

i = 42
i = "hello"
i = []int{1, 2, 3}

// Type assertion
if str, ok := i.(string); ok {
    fmt.Println("It's a string:", str)
}

// Type switch
switch v := i.(type) {
case int:
    fmt.Println("Integer:", v)
case string:
    fmt.Println("String:", v)
default:
    fmt.Println("Unknown type")
}
```

## Error Handling

### Q: How does error handling work in Go?
**A:** Go uses explicit error returns:

```go
// Error is a built-in interface
type error interface {
    Error() string
}

// Custom error type
type CustomError struct {
    Code    int
    Message string
}

func (e CustomError) Error() string {
    return fmt.Sprintf("Error %d: %s", e.Code, e.Message)
}

// Function that returns error
func divide(a, b int) (int, error) {
    if b == 0 {
        return 0, CustomError{Code: 400, Message: "division by zero"}
    }
    return a / b, nil
}

// Error handling
result, err := divide(10, 0)
if err != nil {
    fmt.Println("Error:", err)
    return
}
fmt.Println("Result:", result)
```

### Q: What are some error handling best practices?
**A:**
1. **Always check errors** - don't ignore them
2. **Wrap errors** with context using `fmt.Errorf`
3. **Use sentinel errors** for specific conditions
4. **Create custom error types** for complex error information

```go
// Wrapping errors
func readConfig(filename string) (*Config, error) {
    data, err := ioutil.ReadFile(filename)
    if err != nil {
        return nil, fmt.Errorf("failed to read config file %s: %w", filename, err)
    }
    // process data...
}

// Sentinel errors
var ErrNotFound = errors.New("not found")

func findUser(id int) (*User, error) {
    // search logic...
    return nil, ErrNotFound
}
```

## Pointers and Memory

### Q: How do pointers work in Go?
**A:** Pointers store memory addresses:

```go
// Basic pointer usage
x := 42
p := &x        // p is a pointer to x
fmt.Println(*p) // dereference p to get value (42)
*p = 100       // change value through pointer
fmt.Println(x)  // x is now 100

// Pointer to struct
type Person struct {
    Name string
    Age  int
}

person := Person{Name: "Alice", Age: 30}
ptr := &person
ptr.Age = 31 // same as (*ptr).Age = 31
fmt.Println(person.Age) // 31

// Pointer receiver vs value receiver
func (p Person) getName() string {    // value receiver - copies struct
    return p.Name
}

func (p *Person) setAge(age int) {    // pointer receiver - modifies original
    p.Age = age
}
```

### Q: When to use pointers vs values?
**A:**
- **Use pointers when**:
  - You need to modify the original value
  - Working with large structs (avoid copying)
  - The value can be nil
- **Use values when**:
  - The value is small and simple
  - You want to avoid accidental modifications
  - Working with basic types (int, string, bool)

```go
// Large struct - use pointer to avoid copying
type LargeStruct struct {
    data [1000]int
}

func processLarge(ls *LargeStruct) { // pointer - efficient
    // modify ls.data
}

// Small struct - value is fine
type Point struct {
    x, y int
}

func distance(p Point) float64 { // value - simple and safe
    return math.Sqrt(float64(p.x*p.x + p.y*p.y))
}
```
