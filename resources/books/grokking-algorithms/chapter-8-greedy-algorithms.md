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

## 📊 Complexity Analysis

### Classroom Scheduling:
- **Time**: O(n log n) for sorting + O(n) for selection = O(n log n)
- **Space**: O(1) additional space (if sorting in-place)

### General Greedy Pattern:
- Often dominated by sorting step: O(n log n)
- Selection step usually O(n)
- Overall: O(n log n) typically

## 🎯 Real-World Applications

### Scheduling Problems:
- **Classroom scheduling** (this example)
- **Meeting room booking**
- **CPU task scheduling**
- **Resource allocation**

### Optimization Problems:
- **Minimum Spanning Tree** (Kruskal's, Prim's algorithms)
- **Shortest Path** (Dijkstra's algorithm)
- **Huffman Coding** (data compression)
- **Fractional Knapsack**

## 🔍 Proof Techniques

### Proving Greedy Correctness:
1. **Greedy Choice Property**: Show greedy choice is safe
2. **Optimal Substructure**: Show optimal solution contains optimal solutions to subproblems
3. **Exchange Argument**: Show greedy choice doesn't prevent optimal solution

### Example Proof Sketch (Classroom Scheduling):
- **Greedy Choice**: Selecting earliest finishing class is safe
- **Optimal Substructure**: Optimal solution for remaining classes after selection
- **Exchange**: If optimal solution doesn't start with earliest finishing class, we can exchange it without losing optimality

## 📝 Key Takeaways

1. **Greedy algorithms** make locally optimal choices
2. **Not always optimal** but often provide good solutions efficiently
3. **Classroom scheduling** is a classic example using "earliest finish time"
4. **Sorting is often key** to greedy algorithms
5. **Proof techniques** help verify correctness
6. **Many real-world problems** can be solved greedily

## 🚀 Next Steps

In upcoming sections, we'll explore:
- More complex greedy problems
- When greedy algorithms fail
- Advanced proof techniques
- Implementation details and optimizations

## 🧳 Classic Example: Knapsack Problem (The Thief)

### Problem Statement
You are a thief with a knapsack that can carry at most W weight. There are items, each with a value v and a weight w. Your goal is to maximize total value without exceeding the weight capacity.

### Two Variants

**0/1 Knapsack**: You either take an item whole or leave it (no fractions). Greedy does **not always work** here.

**Fractional Knapsack**: You can take fractions of items. Greedy **is optimal** here.

### Greedy Strategy (Fractional Knapsack)

The key insight: **value per unit weight** (density) tells us which items are most valuable.

**Steps:**
1. **Calculate density** for each item: `density = value / weight`
2. **Sort items by density** in descending order (highest density first)
3. **Take as much as possible** from the highest-density item, then move to the next

### Example: Fractional Knapsack (Greedy Works!)

**Capacity W = 50**

**Items:**
- Item A: value = 60, weight = 10 → density = 6.0
- Item B: value = 100, weight = 20 → density = 5.0
- Item C: value = 120, weight = 30 → density = 4.0

**Greedy selection order:** A → B → C (by density)

**Step-by-step:**
1. Take A completely (10/50 used, value = 60)
2. Take B completely (30/50 used, value = 160 total)
3. Take 20/30 of C (fills remaining 20 weight)
   - Value from C: 20/30 × 120 = 80
   - Total value = 60 + 100 + 80 = **240** ✅ (optimal!)

**Why it works:** Since we can take fractions, we always want the "best bang for your buck" first, then fill the rest.

### Counterexample: Why Greedy Fails for 0/1 Knapsack

**Capacity W = 15**

**Items:**
- A: value = 10, weight = 5 → density = 2.0
- B: value = 10, weight = 5 → density = 2.0
- C: value = 12, weight = 10 → density = 1.2
- D: value = 18, weight = 12 → density = 1.5

**Greedy by density picks:**
1. A (density 2.0) → 5/15 used, value = 10
2. B (density 2.0) → 10/15 used, value = 20 total
3. Remaining 5 weight cannot fit C (needs 10) or D (needs 12)
4. **Total value = 20** ❌

**Optimal solution:**
- C + A (weight 10+5 = 15, value 12+10 = **22**)
- **22 > 20** ✅

**Why greedy fails:** By taking A and B first (high density but small items), we waste space and can't fit the better combination (C + A). With 0/1 knapsack, you can't take fractions, so you need to consider combinations, not just individual item densities.

### Complexity Analysis

**Fractional Knapsack (Greedy):**
- **Time:** O(n log n) for sorting by density + O(n) for selection = **O(n log n)**
- **Space:** O(1) additional space
- **Result:** Optimal solution

**0/1 Knapsack (Dynamic Programming):**
- **Time:** O(nW) where n = items, W = capacity (pseudo-polynomial)
- **Space:** O(nW) or O(W) with optimization
- **Result:** Optimal solution (but much slower than greedy)

### Takeaways

✅ **Use greedy (by value/weight) for Fractional Knapsack** — optimal and fast (O(n log n))

❌ **Do NOT use greedy for 0/1 Knapsack** — use dynamic programming instead

**Key insight:** The ability to take fractions makes greedy optimal for fractional knapsack, but integer constraints require considering all combinations.

## 🧪 Exercises

### 8.1 Truck Loading (Maximize Used Space)
Problem: You must load a truck with boxes of different sizes. Goal: maximize the used space in a single truck (fixed capacity).

- Greedy proposal: Sort boxes by size (volume/weight) descending and place each box using First-Fit Decreasing (FFD) until no more fits.
- Optimal? Not guaranteed. This resembles bin packing/0/1 knapsack. FFD is efficient and often good in practice, but optimality is not guaranteed; exact optimal solutions need DP/branch-and-bound (for 0/1) or ILP.

### 8.2 7-Day Europe Trip (Maximize Points)
Problem: Each attraction has a value (how much you want to see it) and a time cost. You have 7 days. Maximize total value.

- Greedy proposal: Sort attractions by value-per-time (density) descending and pick while time remains.
- Optimal? Not for 0/1 selection. This is 0/1 knapsack with time as weight; greedy by density is not always optimal. Use DP for optimality. Greedy is optimal only for the fractional variant (if partial visits were possible, which is unrealistic here).

## Set Operations Refresher (Fruit Example)

Let two sets represent fruit collections:

- A = {apple, banana, cherry}
- B = {banana, cherry, date}

- Union (A ∪ B): elements in A or B (or both)
  - Result: {apple, banana, cherry, date}
- Intersection (A ∩ B): elements in both A and B
  - Result: {banana, cherry}
- Difference (A − B): elements in A but not in B
  - Result: {apple}
- Difference (B − A): elements in B but not in A
  - Result: {date}

These operations are used in Set Cover to compute how many new elements a station covers (intersection with the uncovered set) and to remove covered elements from the uncovered set (difference).

## Set Cover — Radio Stations Example

### Problem
You have a universe of elements to cover (e.g., the 50 U.S. states) and a collection of subsets (each radio station covers some states). The goal is to pick the fewest stations that together cover all states.

### Exhaustive search: infeasible
The power set has \(2^n\) possible station combinations — impractical for large \(n\).

### Practical strategy: greedy algorithm
- At each step, choose the station that covers the LARGEST number of yet-uncovered states.
- Repeat until the entire universe is covered.

### Algorithm outline
```pseudo
result = {}
uncoveredStates = {all states}

while uncoveredStates is not empty:
    bestStation = null
    bestCoverage = {}
    for each station in stations:
        newCoverage = stationStates ∩ uncoveredStates
        if |newCoverage| > |bestCoverage|:
            bestStation = station
            bestCoverage = newCoverage
    result.add(bestStation)
    uncoveredStates.removeAll(bestCoverage)

return result
```

### Mini example
- Target states: `{mt, wa, or, id, nv, ut, ca, az}`
- Stations:
  - `kone`: `{id, nv, ut}`
  - `ktwo`: `{wa, id, mt}`
  - `kthree`: `{or, nv, ca}`
  - `kfour`: `{nv, ut}`
  - `kfive`: `{ca, az}`

Typical greedy selection: `ktwo` → `kthree` → `kone` → `kfive`.

### Complexity and quality
- Time: typically \(O(nm)\) (n = stations, m = elements), with optimizations possible.
- Approximation: guarantees a solution within a factor of \(\ln m\) of optimal; in practice, it performs very well.

### When to use
- Coverage planning (towers, radios, sensors), minimal test selection, feature curation, replication/cache.

## 🔴 NP-Complete Problems

### The Connection: Set Cover and Traveling Salesman

Both **Set Cover** and the **Traveling Salesman Problem (TSP)** share a common characteristic: to find the optimal solution, you must calculate **every possible solution** and choose the best one. These problems are **NP-complete** — notoriously difficult to solve efficiently.

### 🧳 Traveling Salesman Problem (TSP)

**Problem:** A traveling salesman must visit five cities: Marin, San Francisco, Palo Alto, Berkeley, Fremont. Find the shortest route that visits each city exactly once and returns to the starting point.

**Challenge:** Calculate all possible routes and find the shortest one.

**Example distances:**
- Some route: 10 miles
- Another route: 103 miles
- Yet another: 133 miles
- And many more...

**Question:** How many routes must be calculated for five cities?

### 📊 Counting Routes Step-by-Step

#### **Two Cities**

Suppose you want to visit only **two cities**.

**Starting in Marin:**
- Marin → San Francisco

**Starting in San Francisco:**
- San Francisco → Marin

**Total: 2 routes**

**Important note:** These are **not necessarily the same route**! In cities like San Francisco with many one-way streets, you can't always return the same way. You might need to go several miles in the wrong direction to access a highway. So two routes are not necessarily equivalent.

**Question:** "In TSP, is there a specific starting city?"

**Answer:** Sometimes yes, sometimes no.

- **Fixed start:** If you're a traveling salesman living in San Francisco, SF is your starting point.
- **No fixed start:** If you're FedEx trying to deliver a package in the Bay Area coming from Chicago, you need to find both the optimal route AND the optimal starting point.

For simplicity, we'll use the version **without a fixed starting point** (the runtime is the same, but examples are easier).

**Two cities = 2 possible routes**

#### **Three Cities**

Now suppose you add **one more city**. How many routes exist?

If you start in **Berkeley**, you still need to visit two more cities:

**Starting in Berkeley:**
- Berkeley → Marin → San Francisco
- Berkeley → San Francisco → Marin

**Pattern:** For each starting city, there are routes for the remaining cities.

**All routes:**
- Starting in Berkeley: 2 routes
- Starting in Marin: 2 routes
- Starting in San Francisco: 2 routes

**Total: 3 starting cities × 2 routes each = 6 routes**

**Three cities = 6 possible routes**

#### **Four Cities**

Let's add another city: **Fremont**. Suppose you start there.

If the second city is Berkeley, you still have two cities left (Marin and SF), which gives you 2 routes from that point.

**Starting in Fremont:**
- Fremont → Berkeley → Marin → SF
- Fremont → Berkeley → SF → Marin
- Fremont → Marin → Berkeley → SF
- Fremont → Marin → SF → Berkeley
- Fremont → SF → Berkeley → Marin
- Fremont → SF → Marin → Berkeley

**Look!** These look very similar to the 6 routes calculated when you had only **three cities**, except now all routes have an additional city: **Fremont**!

**The pattern:**
- Starting in Fremont = 6 routes
- Starting in Marin = 6 routes
- Starting in San Francisco = 6 routes
- Starting in Berkeley = 6 routes

**Four cities = 4 starting cities × 6 routes each = 24 routes**

#### **The Pattern Emerges**

| Number of Cities | Calculation | Total Routes |
|------------------|-------------|--------------|
| 1 | 1 | 1 |
| 2 | 2 × 1 | 2 |
| 3 | 3 × 2 | 6 |
| 4 | 4 × 6 | 24 |
| 5 | 5 × 24 | **120** |
| 6 | 6 × 120 | **720** |
| 7 | 7 × 720 | **5,040** |
| 8 | 8 × 5,040 | **40,320** |
| 9 | 9 × 40,320 | 362,880 |
| 10 | 10 × 362,880 | **3,628,800** |

**This is the factorial function!** (Remember from Chapter 3?)

- 5! = 5 × 4 × 3 × 2 × 1 = **120**
- 6! = **720**
- 7! = **5,040**
- 8! = **40,320**
- 10! = **3,628,800** (nearly 3.6 million routes!)

### 📈 Exponential Growth Visualization

```
Number of Routes (log scale)
│
│                                    ● 10! = 3.6M
│                              ● 9! = 363K
│                        ● 8! = 40K
│                  ● 7! = 5K
│            ● 6! = 720
│      ● 5! = 120
│  ● 4! = 24
│● 3! = 6
│● 2! = 2
│● 1! = 1
└─────────────────────────────────────────
  1  2  3  4  5  6  7  8  9  10
        Number of Cities
```

**Key insight:** The number of possible routes grows **extremely rapidly**. This is why it's **impossible** to calculate the "correct" solution for the Traveling Salesman Problem when the number of cities is large.

### 🔗 What TSP and Set Cover Have in Common

Both problems share these characteristics:

1. **Calculate every possible solution** and choose the best
2. **Exponential search space** — number of possibilities grows factorially/exponentially
3. **NP-complete** — no known efficient (polynomial-time) algorithm exists
4. **Brute force is impractical** for large inputs

**Set Cover:** \(2^n\) possible station combinations  
**TSP:** \(n!\) possible routes

Both require exploring massive solution spaces!

### 🎯 Approximating TSP: Nearest Neighbor Heuristic

**Question:** What's a good approximation algorithm for TSP? Something simple that finds a short path.

**Answer:** **Nearest Neighbor** (greedy approach)

**Algorithm:**
1. Randomly choose a starting city
2. At each step, choose the **nearest unvisited city**
3. Continue until all cities are visited
4. Return to the starting city

**Example:** Starting in Marin

```
Marin (start)
  ↓ 10 miles
San Francisco
  ↓ 14 miles
Berkeley
  ↓ 31 miles
Fremont
  ↓ 16 miles
Palo Alto
  ↓ (return to Marin)

Total distance: 71 miles
```

**Result:** Maybe not the shortest path, but still quite short! ✅

**Note:** This is a **heuristic** — it doesn't guarantee optimality, but it's fast and often produces good results.

### 📝 Brief Explanation of NP-Complete

Some problems are **notoriously difficult** to solve:

- **Traveling Salesman Problem** (TSP)
- **Set Cover Problem**
- Many others...

**NP-complete** means:
- No known **efficient** (polynomial-time) algorithm exists
- Best known algorithms require **exponential time** in the worst case
- Many people doubt it's possible to create a fast algorithm for these problems
- If you solve one NP-complete problem efficiently, you can solve them all

**Practical approach:** Use **approximation algorithms** or **heuristics** to get "good enough" solutions quickly.

## Approximation Algorithms

### Why approximation?
Some optimization problems (including Set Cover) are NP-hard. Exact solutions require exploring an exponential search space in the worst case. Approximation algorithms trade exactness for guaranteed, efficiently-computable solutions.

### Guarantees you can expect
- For Set Cover, the greedy algorithm is an \(H_m\)-approximation, where \(H_m \approx \ln m + \gamma\) and \(m\) is the number of elements to cover. In short: within a factor of about \(\ln m\) from optimal.
- Many approximation algorithms provide a constant or logarithmic factor bound relative to the optimal solution; the bound is proven mathematically.

### How the greedy achieves the bound (intuition)
- Each step covers as many uncovered elements as possible per chosen set.
- The number of steps needed grows like the harmonic series because each choice reduces the remaining uncovered portion by a multiplicative factor on average, yielding the \(\ln m\) bound.

### When to use approximation
- Exact optimality is infeasible (time/memory constraints), but you need a provable quality bar.
- Large input sizes where heuristics are acceptable but you still want worst-case guarantees.

### Other classic approximation examples
- Vertex Cover: 2-approximation via maximal matching.
- Metric TSP: 1.5-approximation (Christofides). Greedy nearest-neighbor is a heuristic (no tight guarantee).
- Facility Location and k-Center: logarithmic or constant-factor approximations under common metric assumptions.

### Practical tip
Start with greedy or simple rounding-based approximations. If solution quality is insufficient, consider problem-specific improvements (local search, LP/ILP relaxations with rounding) while keeping an eye on provable bounds.

## 🔍 How to Identify NP-Complete Problems

### The Real-World Example: Jonah's Football Team

**Problem:** Jonah is choosing players for his fantasy football team. He has a list of characteristics he wants his team to have:
- A good quarterback
- A good running back
- Players who play well in youth leagues
- Players who play well under pressure
- And other skills...

He has a list of players, and each player has some of these skills:

| Player | Skills |
|--------|--------|
| Matt Forte | RB |
| Brendan Marshall | WR / Good under pressure |
| Aaron Rodgers | QB / Good under pressure |
| ... | ... |

**Challenge:** Jonah needs a team that covers all desired characteristics, but the team size is limited.

**"Wait a second,"** Jonah thinks, **"this is a Set Cover problem!"**

```
Desired Skills: {QB, RB, WR, Youth, Under Pressure, ...}
Players: Each player covers some skills
Goal: Minimum team that covers all skills
```

**Solution:** Jonah can use the same approximation algorithm (greedy Set Cover) to create his team!

**Key insight:** NP-complete problems appear everywhere! It's always good to know if the problem you're trying to solve is NP-complete, because in this situation you can stop trying to solve it perfectly and instead solve it using an approximation algorithm.

### ⚠️ The Challenge: Recognizing NP-Complete Problems

However, it's **difficult to tell** if the problem you're working on is NP-complete, because often the difference is **very small**.

**Example:** In previous chapters, we talked a lot about **shortest paths**. You know how to calculate the shortest path from point A to point B.

**Shortest Path (A → B):** ✅ **Easy!** Use Dijkstra's algorithm — O((V + E) log V)

**Visualization:**
```
Point A (Twin Peaks)
  ↓ Walking
Bus Stop #4.4
  ↓ Bus #28
Golden Gate Bridge (Point B)

Alternative route:
Point A (Twin Peaks)
  ↓ Walking
Bus Stop
  ↓ Bus #33 → Bus #38L → Bus #28
  OR
  ↓ Bus #33 → Bus #5L → Bus #28
Golden Gate Bridge (Point B)
```

**However**, if you want to find the shortest path that **connects multiple points**, you fall into the **Traveling Salesman Problem**, which is **NP-complete**!

**TSP (A → B → C → D → A):** ❌ **NP-complete!** Requires checking n! routes

### 📋 Indicators: How to Tell if a Problem is NP-Complete

**Simple answer:** There's **no easy way** to tell if the problem you're working on is NP-complete. Here are some indicators:

#### ✅ **Indicator 1: Exponential Slowdown**
- Your algorithm runs **fast for small inputs**, but becomes **very slow** as the number of items increases
- Example: Works fine for 5 cities, but takes forever for 20 cities

#### ✅ **Indicator 2: "All Combinations"**
- **"All combinations of X"** usually means an NP-complete problem
- Example: "Calculate all possible routes" → TSP

#### ✅ **Indicator 3: Can't Divide into Subproblems**
- You have to calculate **"every possible version"** of X because you **can't divide it into smaller subproblems**?
- Maybe it's an NP-complete problem
- Example: Can't solve TSP by solving smaller TSPs independently

#### ✅ **Indicator 4: Sequences**
- If your problem involves a **sequence** (like a sequence of cities, as in TSP) and is **hard to solve**, it might be NP-complete
- Example: Finding optimal order to visit cities

#### ✅ **Indicator 5: Sets**
- If your problem involves a **set** (like a set of radio stations) and is **hard to solve**, it might be an NP-complete problem
- Example: Set Cover — choosing minimum stations to cover all states

#### ✅ **Indicator 6: Can Rewrite as Known NP-Complete Problem**
- Can you **rewrite your problem** as the **Set Cover problem** or the **Traveling Salesman Problem**?
- Then your problem is **definitely NP-complete**!
- Example: Jonah's team selection = Set Cover

### 🧪 Exercises: Is It NP-Complete?

#### **8.6 Mail Delivery Route**
A mail carrier must deliver mail to twenty houses. He must find the shortest route that passes through all twenty houses. Is this an NP-complete problem?

**Answer:** ✅ **Yes!** This is the **Traveling Salesman Problem (TSP)**. The mail carrier must visit each house exactly once and return to the starting point, finding the shortest route. This requires checking all possible routes (factorial growth).

#### **8.7 Maximum Clique**
Find the largest clique in a set of people. (In graph theory, a clique in an undirected graph is a subset of vertices such that every two vertices in the subset are connected by an edge. For this example, a clique is a set of people where everyone knows everyone else.) Is this an NP-complete problem?

**Answer:** ✅ **Yes!** Finding the **maximum clique** is a classic NP-complete problem. You need to check all possible subsets of people to find the largest group where everyone knows everyone else. This requires exponential time in the worst case.

#### **8.8 Graph Coloring**
You're making a map of the United States and need to color adjacent states different colors. You must find the minimum number of colors so that no two adjacent states have the same color. Is this an NP-complete problem?

**Answer:** ✅ **Yes!** This is the **Graph Coloring Problem**, specifically finding the **chromatic number** (minimum colors needed). Determining if a graph can be colored with k colors is NP-complete. Finding the minimum k is even harder.

### Exercises — Greedy or Not?

- 8.3 Quicksort: Not greedy. It's divide-and-conquer (partition + recursive sorts).
- 8.4 Breadth-First Search: Not greedy. It's a level-order traversal using a queue; finds shortest paths only in unweighted graphs.
- 8.5 Dijkstra's Algorithm: Greedy. Repeatedly selects the nearest unvisited node to finalize shortest paths in graphs with nonnegative weights.

## 📚 Chapter Summary

### Key Takeaways

#### ✅ **Greedy Algorithms**
- **Optimize locally** in the hope of ending up with a global optimization
- Make the **best choice at each step** without reconsidering
- **Simple to write** and have **low execution time**
- **Not always optimal**, but often provide good solutions efficiently
- Examples: Classroom scheduling, Fractional Knapsack, Dijkstra's algorithm

#### ❌ **NP-Complete Problems**
- **No fast solution** exists (no polynomial-time algorithm known)
- Require checking **exponential** or **factorial** number of possibilities
- Examples: Traveling Salesman Problem, Set Cover, Graph Coloring
- **Brute force is impractical** for large inputs

#### 🎯 **Approximation Algorithms**
- If you're trying to solve an **NP-complete problem**, the best thing to do is use an **approximation algorithm**
- **Greedy algorithms** are easy to write and have low execution time, so they make **good approximation algorithms**
- Trade exactness for **guaranteed, efficiently-computable solutions**
- Examples: Greedy Set Cover (ln m approximation), Nearest Neighbor for TSP

### When to Use Greedy

✅ **Use greedy when:**
- Problem has **optimal substructure**
- **Greedy choice property** holds (local optimum leads to global optimum)
- You need a **fast, simple solution**
- Approximation is acceptable (for NP-complete problems)

❌ **Don't use greedy when:**
- Problem requires **considering all combinations** (0/1 Knapsack)
- **Backtracking** is necessary
- **Exact optimality** is required and greedy doesn't guarantee it

### Common Patterns

1. **Sorting + Greedy Selection**: Classroom scheduling, Fractional Knapsack
2. **Priority Queue + Greedy**: Dijkstra's algorithm
3. **Greedy Set Cover**: Radio stations, team selection
4. **Nearest Neighbor**: TSP approximation

### Final Thoughts

Greedy algorithms are a powerful tool in your algorithmic toolkit. They're:
- **Simple** to understand and implement
- **Fast** (often O(n log n) or O(n))
- **Good approximations** for hard problems
- **Widely applicable** in real-world scenarios

Remember: When facing an NP-complete problem, don't try to solve it perfectly — use a greedy approximation algorithm instead!