# Chapter 6: Breadth-First Search (BFS) — Grokking Algorithms

### Graphs 101
- A graph has nodes (vertices) and edges (connections).
- Types:
  - Directed vs undirected
  - Weighted vs unweighted
- Terminology note:
  - Directed graph = "digraph" (edges have direction: u→v is not v→u)
  - Undirected graph (often just called "graph"): edges are bidirectional (u—v means both ways)
- Representations:
  - Adjacency list (common, memory-efficient)
  - Adjacency matrix (dense graphs)

#### Trees as Graphs (brief)
- A tree is a connected, acyclic, undirected graph
- A rooted tree orients edges away from the root (parent → children)
- BFS/DFS on trees are just special cases on graphs (no cycles → simpler)
- Shortest path in a tree is unique (only one simple path between any two nodes)

Example (Family Tree as a Graph):
- Nodes = people (e.g., "Grandparent", "Parent", "Child")
- Edges = parent–child relationships
- As a rooted tree: root at the oldest known ancestor, edges directed to descendants
- Queries:
  - BFS finds relatives by “generations” (level by level)
  - Unique path from a person to an ancestor (no cycles)

### Breadth-First Search (BFS)
- Explores level by level using a queue
- Finds shortest path in unweighted graphs (fewest edges)
- Decides reachability (is there a path from A to B?)

```python
from collections import deque

def bfs_shortest_path(graph, start, goal):
    queue = deque([[start]])
    visited = set([start])
    while queue:
        path = queue.popleft()
        node = path[-1]
        if node == goal:
            return path
        for neighbor in graph.get(node, []):
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(path + [neighbor])
    return None
```

- Complexity: O(V + E) time, O(V) space

#### Example: Find a "mango seller"
In the book, you traverse a social graph to find a person who is a mango seller. The rule used is: “a seller is anyone whose name ends with 'm'”.

```python
from collections import deque

def person_is_seller(name: str) -> bool:
    return name.endswith('m')  # e.g., "thom" → seller

def find_mango_seller(graph, start):
    queue = deque(graph.get(start, []))
    visited = set([start])
    while queue:
        person = queue.popleft()
        if person in visited:
            continue
        if person_is_seller(person):
            return person  # Found!
        visited.add(person)
        queue.extend(graph.get(person, []))
    return None  # No seller reachable

# Example graph (adjacency list)
graph = {
    'you': ['alice', 'bob', 'claire'],
    'bob': ['anuj', 'peggy'],
    'alice': ['peggy'],
    'claire': ['thom', 'jonny'],
    'anuj': [], 'peggy': [], 'thom': [], 'jonny': []
}

seller = find_mango_seller(graph, 'you')  # returns 'thom'
```

### Topological Sort (Intro)
- For Directed Acyclic Graphs (DAGs)
- Orders nodes so every edge u→v puts u before v
- Use cases: task scheduling, build ordering, dependency resolution

Notes (brief):
- Only defined for DAGs (if there’s a cycle, no valid order exists)
- Multiple valid orders may exist

Kahn’s Algorithm (high-level):
1) Compute in-degrees
2) Push nodes with in-degree 0 into a queue
3) Pop, append to order, decrement neighbors’ in-degrees; push new 0s
4) If all nodes output → OK; else → cycle

- Complexity: O(V + E)

### In-Repo References
- Java graph structures and examples:
  - `projects/data-structures/java/datastructures-java/src/graphs/`

### Exercises
1) Use BFS to find a shortest path (by hops) between two nodes.
2) Detect cycle in a directed graph via topological sort attempt.
3) Given course prerequisites, output a valid order (toposort).


### Visual, Step-by-Step Examples (ASCII)

#### BFS — Level-by-level Expansion

Graph (adjacency list):

```
you → alice, bob, claire
bob → anuj, peggy
alice → peggy
claire → thom, jonny
anuj → (none)   peggy → (none)   thom → (none)   jonny → (none)
```

Goal: find a person whose name ends with 'm'.

State evolution (Queue | Visited):
```
Init:   Queue=[alice, bob, claire] | Visited={you}
Step1:  pop alice  → not seller; push neighbors
        Queue=[bob, claire, peggy] | Visited={you, alice}
Step2:  pop bob    → not seller; push neighbors
        Queue=[claire, peggy, anuj, peggy] | Visited={you, alice, bob}
Step3:  pop claire → not seller; push neighbors
        Queue=[peggy, anuj, peggy, thom, jonny] | Visited={you, alice, bob, claire}
Step4:  pop peggy  → not seller
        Queue=[anuj, peggy, thom, jonny] | Visited={you, alice, bob, claire, peggy}
Step5:  pop anuj   → not seller
        Queue=[peggy, thom, jonny] | Visited={..., anuj}
Step6:  pop peggy  → already visited; skip
        Queue=[thom, jonny]
Step7:  pop thom   → ends with 'm' → FOUND
```

…and so on.

#### Topological Sort — Kahn’s Algorithm Snapshot

Suppose a small DAG of tasks:

```
A → C
B → C, D
C → E
D → E
```

In-degrees initially:
```
deg(A)=0, deg(B)=0, deg(C)=2, deg(D)=1, deg(E)=2
Queue=[A, B]
```

Process:
```
1) pop A → order=[A];  decrement C: deg(C)=1
   Queue=[B]
2) pop B → order=[A, B]; decrement C: deg(C)=0, decrement D: deg(D)=0
   Queue=[C, D]
3) pop C → order=[A, B, C]; decrement E: deg(E)=1
   Queue=[D]
4) pop D → order=[A, B, C, D]; decrement E: deg(E)=0
   Queue=[E]
5) pop E → order=[A, B, C, D, E] (done)
```

…and so on.


