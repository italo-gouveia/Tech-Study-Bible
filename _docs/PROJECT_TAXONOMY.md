# Project Taxonomy and Classification

## High-Level Domains
- Coding Challenges (platform-based: LeetCode, HackerRank, CodeWars)
- Projects (apps, libraries, experiments)
- Notes (concepts, patterns, cheatsheets)
- Certifications (providers: AWS, Azure, GCP, etc.)
- Resources (books, articles, courses)

## Coding Challenges
```
leetcode/
  easy|medium|hard/
    [id].[slug]/
      solutions/<language>/<variation>
      analysis/
      README.md
hackerrank/
  algorithms|data-structures|mathematics|.../
    [track]/[challenge]/
```

## Projects
```
projects/
  topic/
    project-name/
      src/
      tests/
      docs/
      README.md
      BUILD_AND_RUN.md
```

## Notes
```
notes/
  topic/
    subtopic/
      README.md           # summary + links
      references.md       # citations, links
      examples/           # short runnable examples
```

## Certifications
```
certifications/
  provider/
    exam-code-name/
      syllabus.md
      objectives.md
      questions/         # practice questions
      labs/              # hands-on labs
      README.md
```

## Resources
```
resources/
  books/
  articles/
  courses/
  links.md
```

## Classification Decision Tree
1. Is it a platform problem? → Coding Challenges
2. Is it a standalone app/library? → Projects
3. Is it conceptual knowledge? → Notes
4. Is it exam prep? → Certifications
5. Is it external material? → Resources

## Variations by Language
Keep `language/variation` when:
- Multiple approaches exist (e.g., brute-force vs optimized)
- Performance benchmarking is relevant
- Language-specific idioms are worth demonstrating
