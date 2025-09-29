# Build and Run - Design Patterns in Java

## Compile a Specific Pattern
```bash
# From this folder
# Example: compile structural/adapter and run Main
javac -cp . src/structural/adapter/*.java -d out
```

## Run a Specific Pattern
```bash
java -cp out structural.adapter.Main
```

## Compile All Patterns
```bash
find src -name "*.java" > sources.txt
javac -cp . @sources.txt -d out
```

## Notes
- Each pattern folder typically contains a `Main.java` for demonstration.
- Consider adopting Maven/Gradle and adding JUnit tests.
