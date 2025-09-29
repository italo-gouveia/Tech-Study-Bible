# Build and Run - Data Structures in Java

## Compile a Specific Demo
```bash
# From this folder
# Example: compile graphs/graphwithadjacencylist/Main.java and its Graph.java
javac -cp . src/graphs/graphwithadjacencylist/Graph.java src/graphs/graphwithadjacencylist/Main.java -d out
```

## Run a Specific Demo
```bash
# Using the compiled classes under out
java -cp out graphs.graphwithadjacencylist.Main
```

## Notes
- Each module has its own `Main.java` entrypoint.
- You may compile entire `src` tree:
```bash
find src -name "*.java" > sources.txt
javac -cp . @sources.txt -d out
```
- Consider migrating to a build tool (Maven/Gradle) and adding JUnit tests.
