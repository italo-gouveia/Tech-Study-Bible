# Tech Study Bible

A curated, structured personal "study bible" for algorithms, data structures, coding challenges, and certifications. Includes detailed analyses, multiple language implementations (Java, Go), and performance insights.

## 📁 Repository Structure
```
├── leetcode/                  # LeetCode solutions with analyses and tests
│   ├── easy/
│   │   ├── 1071.gcd-of-strings/
│   │   ├── 125.valid-palindrome/
│   │   ├── 1768.merge-strings-alternately/
│   │   └── 242.valid-anagram/
│   └── medium/
│       └── 49.group-anagrams/
├── hackerrank/
│   └── warmup/
│       └── plus-minus/
├── projects/
│   ├── data-structures/java/datastructures-java/
│   ├── design-patterns/java/design-patterns-java/
│   ├── data-structures/go/datastructures-go/         # Go module (scaffold + examples)
│   └── design-patterns/go/design-patterns-go/        # Go module (strategy + more)
├── _staging/                  # Intake and in-progress content
│   ├── study-qa/              # Q&A knowledge base (Java/Go/General)
│   └── study-tracks/          # System design, architecture, security, DB, etc.
├── certifications/
├── notes/
├── resources/
├── _staging/                  # Intake and in-progress content
└── _docs/                     # Organization and contribution guidelines
```

## ✅ Highlights
- **LeetCode**: Easy and Medium problems with full analysis and multi-language solutions
  - Easy: 1071, 125, 1768, 242
  - Medium: 49 (Group Anagrams)
- **HackerRank**: Warmup problem Plus Minus with Java and Go solutions
- **Projects**:
  - `datastructures-java`: Arrays, linked lists, stacks, queues, heaps, hash tables, tries, graphs, trees
  - `design-patterns-java`: Creational, Structural, Behavioral patterns with runnable examples
  - `datastructures-go`: Stack (tests), roadmap for queue/heap/trie/union-find
  - `design-patterns-go`: Strategy pattern (tests), roadmap for factory/observer/etc.
- **Study Tracks**: `_staging/study-tracks/` com System Design, Architecture (DDD/Hex), Security (OWASP/Auth), Databases, Networking, Messaging, IaC, Observability
- **Q&A Prep**: `_staging/study-qa/`
- **Docs-first**: `_docs/` com padrões de organização, intake e coding

## 🚀 Quick Start
- Java (per problem or project): see `BUILD_AND_RUN.md` inside each folder
- Go (per problem): run tests/benchmarks in the `solutions/go` folders

Example (Go tests):
```bash
cd leetcode/easy/1768.merge-strings-alternately/solutions/go
go test -v -bench .
```

Example (Java, run simple Main):
```bash
cd leetcode/easy/242.valid-anagram/solutions/java
javac Main.java solution1.java solution2.java
java Main
```

## 🔎 How to Navigate
- Problem deep-dives live under each problem directory:
  - `README.md`: problem overview and links (LeetCode + NeetCode)
  - `analysis/`: approach, complexity, performance (with benches)
  - `solutions/<lang>/`: 1+ implementations, tests, and run instructions
- Projects contain `README.md` and `BUILD_AND_RUN.md` with instructions

## 🧭 Roadmap
- Add more LeetCode Medium/Hard problems
- Expand HackerRank coverage beyond warmup
- Add CI (Go/Java), badges, and LICENSE
- Improve consolidated indexing across `notes/`, `resources/`, `certifications/`

## 📚 Key Docs
- `_docs/MASTER_GUIDELINES.md`
- `_docs/PROJECT_TAXONOMY.md`
- `_docs/INTAKE_PROCESS.md`
- `_docs/CODING_STANDARDS.md`

---
Happy studying! If this helps you, consider starring the repo and opening issues for suggestions.


