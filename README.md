# Tech Study Bible

A curated, structured personal "study bible" for algorithms, data structures, coding challenges, and certifications. Includes detailed analyses, multiple language implementations (Java, Go), and performance insights.

## 📁 Repository Structure
```
├── leetcode/                  # LeetCode solutions with analyses and tests
│   ├── easy/
│   │   ├── 1071.gcd-of-strings/
│   │   ├── 125.valid-palindrome/
│   │   └── 1768.merge-strings-alternately/
│   └── medium/
│       └── 49.group-anagrams/
├── hackerrank/
│   └── warmup/
│       └── plus-minus/
├── projects/
│   ├── data-structures/java/datastructures-java/
│   └── design-patterns/java/design-patterns-java/
├── certifications/
├── notes/
├── resources/
├── _staging/                  # Intake and in-progress content
└── _docs/                     # Organization and contribution guidelines
```

## ✅ Highlights
- **LeetCode**: Easy and Medium problems with full analysis and multi-language solutions
  - Easy: 1071, 125, 1768
  - Medium: 49 (Group Anagrams)
- **HackerRank**: Warmup problem Plus Minus with Java and Go solutions
- **Projects**:
  - `datastructures-java`: Arrays, linked lists, stacks, queues, heaps, hash tables, tries, graphs, trees
  - `design-patterns-java`: Creational, Structural, Behavioral patterns with runnable examples
- **Docs-first**: `_docs/` with standards for structure, intake, and coding

## 🚀 Quick Start
- Java (per problem or project): see `BUILD_AND_RUN.md` inside each folder
- Go (per problem): run tests/benchmarks in the `solutions/go` folders

Example (Go tests):
```bash
cd leetcode/easy/1768.merge-strings-alternately/solutions/go
go test -v -bench .
```

## 🔎 How to Navigate
- Problem deep-dives live under each problem directory:
  - `README.md`: problem overview and links
  - `analysis/`: approach, complexity, performance
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


