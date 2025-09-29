# Build and Run Instructions - Java

## Prerequisites
- Java 8 or higher
- JUnit 5 (for running tests)

## Project Structure
```
java/
├── Solution1.java          # StringBuilder approach
├── Solution2.java          # Character array approach
├── Main.java              # Executable with sample usage
├── SolutionTest.java      # Unit tests
└── BUILD_AND_RUN.md       # This file
```

## Compilation

### Compile All Files
```bash
# Navigate to the java directory
cd leetcode/easy/1768.merge-strings-alternately/solutions/java

# Compile all Java files
javac -cp . *.java
```

### Compile Individual Files
```bash
# Compile main executable
javac -cp . Main.java

# Compile tests (requires JUnit 5)
javac -cp .:junit-platform-console-standalone-1.9.3.jar SolutionTest.java
```

## Running the Code

### Run Main Executable
```bash
# Run the main program with sample test cases
java -cp . leetcode.easy.mergeStringsAlternately.Main
```

### Expected Output
```
=== Merge Strings Alternately - Java Solutions ===

--- Solution 1: StringBuilder with Two Pointers ---
Test: Example 1: Equal length strings
Input: word1="abc", word2="pqr"
Output: "apbqcr"
Expected: "apbqcr"
Result: ✅ PASS

Test: Example 2: word2 longer
Input: word1="ab", word2="pqrs"
Output: "apbqrs"
Expected: "apbqrs"
Result: ✅ PASS

... (more test cases)

--- Performance Comparison ---
Test data: word1="aaaaaaaaaa..." (1000 chars), word2="bbbbbbbbbb..." (1000 chars)
Solution 1 (StringBuilder): 45.23 ms for 10,000 iterations
Solution 2 (Character Array): 32.15 ms for 10,000 iterations
Performance ratio: 1.41x

--- Edge Cases Testing ---
... (edge case results)
```

## Running Tests

### Using JUnit 5
```bash
# Download JUnit 5 console launcher
wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar

# Run all tests
java -jar junit-platform-console-standalone-1.9.3.jar -cp . --scan-classpath

# Run specific test class
java -jar junit-platform-console-standalone-1.9.3.jar -cp . -c leetcode.easy.mergeStringsAlternately.SolutionTest
```

### Using Maven (if using Maven project)
```bash
# Run tests
mvn test

# Run with verbose output
mvn test -Dtest=SolutionTest
```

### Using Gradle (if using Gradle project)
```bash
# Run tests
./gradlew test

# Run specific test class
./gradlew test --tests SolutionTest
```

## Test Results

### Expected Test Output
```
Test run finished after 1234 ms
[         3 containers found      ]
[         0 containers skipped    ]
[         3 containers started    ]
[         0 containers aborted    ]
[         3 containers successful ]
[         0 containers failed     ]
[        25 tests found           ]
[         0 tests skipped         ]
[        25 tests started         ]
[         0 tests aborted         ]
[        25 tests successful      ]
[         0 tests failed          ]
```

## Performance Testing

### Manual Performance Test
```bash
# Run the main program to see performance comparison
java -cp . leetcode.easy.mergeStringsAlternately.Main
```

### Memory Usage Analysis
```bash
# Run with memory monitoring
java -cp . -XX:+PrintGCDetails leetcode.easy.mergeStringsAlternately.Main
```

## Troubleshooting

### Common Issues

#### 1. ClassNotFoundException
```
Error: Could not find or load main class leetcode.easy.mergeStringsAlternately.Main
```
**Solution**: Make sure you're in the correct directory and all files are compiled.

#### 2. Package Declaration Issues
```
Error: package leetcode.easy.mergeStringsAlternately does not exist
```
**Solution**: Ensure the directory structure matches the package declaration.

#### 3. JUnit Not Found
```
Error: Could not find or load class org.junit.jupiter.api.Test
```
**Solution**: Download JUnit 5 JAR file or use an IDE with JUnit support.

### IDE Setup

#### IntelliJ IDEA
1. Open the project folder
2. Mark `java` folder as Sources Root
3. Add JUnit 5 to classpath
4. Run tests using IDE test runner

#### Eclipse
1. Import project as Java project
2. Add JUnit 5 to build path
3. Run tests using JUnit test runner

#### VS Code
1. Install Java Extension Pack
2. Install Test Runner for Java extension
3. Run tests using the test explorer

## Code Quality

### Checkstyle
```bash
# Run Checkstyle (if configured)
checkstyle -c checkstyle.xml *.java
```

### SpotBugs
```bash
# Run SpotBugs (if configured)
spotbugs *.class
```

## Continuous Integration

### GitHub Actions Example
```yaml
name: Java Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 11
      uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'
    - name: Run tests
      run: |
        cd leetcode/easy/1768.merge-strings-alternately/solutions/java
        javac -cp . *.java
        java -cp . leetcode.easy.mergeStringsAlternately.Main
```

## Additional Tools

### Profiling
```bash
# Run with profiling
java -cp . -XX:+UnlockDiagnosticVMOptions -XX:+DebugNonSafepoints leetcode.easy.mergeStringsAlternately.Main
```

### Debugging
```bash
# Run with debug information
java -cp . -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 leetcode.easy.mergeStringsAlternately.Main
```

## Performance Benchmarks

### Expected Performance (approximate)
- **Solution 1 (StringBuilder)**: ~45ms for 10,000 iterations with 1000+1000 char inputs
- **Solution 2 (Character Array)**: ~32ms for 10,000 iterations with 1000+1000 char inputs
- **Memory Usage**: ~2KB for 1000+1000 char inputs

### Optimization Tips
1. Use Solution 2 for maximum performance
2. Pre-allocate StringBuilder capacity
3. Use bulk copy operations when possible
4. Avoid string concatenation in loops
