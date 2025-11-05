# Chapter 8: Greedy Algorithms — Grokking Algorithms

### Introduction
- **Greedy algorithms** make the locally optimal choice at each step
- They hope that local optima lead to a global optimum
- Simple and intuitive approach to problem-solving
- Not always optimal, but often provide good solutions efficiently

## 🎯 What Are Greedy Algorithms?

**Definition**: A greedy algorithm is an algorithmic paradigm that follows the problem-solving heuristic of making the locally optimal choice at each stage with the hope of finding a global optimum.

### Key Characteristics:
- **Local Optimization**: Makes the best choice at each step
- **No Backtracking**: Once a choice is made, it's never reconsidered
- **Hope for Global Optimum**: Assumes local optima lead to global optimum
- **Simple Implementation**: Usually straightforward to code

### When Greedy Works:
- **Optimal Substructure**: Optimal solution contains optimal solutions to subproblems
- **Greedy Choice Property**: A global optimum can be arrived at by making locally optimal choices

## 📚 Classic Example: Classroom Scheduling Problem

### Problem Statement:
You have a classroom and want to schedule as many classes as possible. Each class has a start time and end time. You want to maximize the number of non-overlapping classes.

### Example Classes:
```
Class A: 9:00 AM - 10:00 AM
Class B: 9:30 AM - 10:30 AM  
Class C: 10:00 AM - 11:00 AM
Class D: 10:30 AM - 11:30 AM
Class E: 11:00 AM - 12:00 PM
```

### Greedy Approach: "Earliest Finish Time"
1. **Sort classes by finish time** (earliest first)
2. **Select the first class** (earliest finishing)
3. **Remove all conflicting classes** (overlapping with selected)
4. **Repeat** with remaining classes

### Step-by-Step Execution:
```
Sorted by finish time:
1. Class A: 9:00-10:00 (finishes at 10:00)
2. Class C: 10:00-11:00 (finishes at 11:00)  
3. Class E: 11:00-12:00 (finishes at 12:00)
4. Class B: 9:30-10:30 (finishes at 10:30)
5. Class D: 10:30-11:30 (finishes at 11:30)

Greedy Selection:
✅ Select A (9:00-10:00) - earliest finish
❌ Skip B (9:30-10:30) - conflicts with A
✅ Select C (10:00-11:00) - next earliest finish
❌ Skip D (10:30-11:30) - conflicts with C  
✅ Select E (11:00-12:00) - next earliest finish

Result: 3 classes scheduled (A, C, E)
```

### Why This Works:
- **Optimal Substructure**: If we have optimal solution for remaining classes after selecting one, we can build optimal solution for all classes
- **Greedy Choice Property**: Selecting earliest finishing class doesn't prevent us from finding optimal solution

## 🧠 Key Insights

### Greedy vs Other Approaches:
- **Greedy**: Make locally optimal choice, hope for global optimum
- **Dynamic Programming**: Consider all possibilities, choose optimal
- **Brute Force**: Try all possible combinations

### Common Greedy Strategies:
1. **Sort by some criteria** (finish time, weight, etc.)
2. **Make greedy choice** at each step
3. **Update problem state** after each choice
4. **Repeat** until problem solved

### When Greedy Fails:
- **Coin Change Problem**: Greedy doesn't work for all coin systems
- **Knapsack Problem**: Greedy by value/weight ratio isn't always optimal
- **Traveling Salesman**: Greedy nearest neighbor isn't optimal
