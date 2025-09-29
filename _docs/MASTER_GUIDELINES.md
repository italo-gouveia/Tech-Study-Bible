# Master Guidelines: Personal IT Study Bible

## Purpose
Centralize all study materials, projects, notes, and exercises into a single, coherent repository with consistent organization and executable, testable artifacts.

## Core Principles
- Projects → Languages → Variations (keep this hierarchy whenever it makes sense)
- Executable by default (main/CLI or demo) + unit tests
- Documentation-first: every item has minimal README + links
- Iterative intake: stage in `_staging/`, then integrate
- DRY templates: reuse `_docs/templates` across all areas

## Top-Level Structure
```
/docs/                # rendered docs site (optional, future)
_docs/                # authoring docs and governance
_staging/             # staging area for new items
leetcode/             # coding challenges (platform → difficulty → problems)
hackerrank/           # coding challenges (domain → tracks)
projects/             # personal or course projects (topic → project)
notes/                # knowledge notes (topic → subtopic)
certifications/       # exam prep (provider → exam)
resources/            # books, links, articles
scripts/              # utilities to build/test/generate
README.md
```

## Universal Folder Contract
Each leaf folder (a concrete problem/project/note module) should include:
- README.md (what, why, how)
- solutions/ or src/ (code)
- tests/ (unit tests) or solution_test.* files
- analysis/ (approach, complexity, benchmarks) when applicable
- BUILD_AND_RUN.md (how to run/build/test)

## Governance
- Intake: add to `_staging`, open an "analysis" note, propose placement
- Review: ensure naming, templates, and tests match standards
- Integrate: move to final location, update indexes and README
- Iterate: optimizations and refactors welcome; keep changelog entries brief

## Naming Rules
- kebab-case for directories, except platform folders that mirror external naming
- `[number].[slug]` for enumerated problems (e.g., `1768.merge-strings-alternately`)
- `language/variation/` for multiple implementations

## Executable + Test Policy
- Provide main/demo entrypoints where meaningful
- Unit tests are mandatory for algorithms and libraries
- Prefer language idiomatic testing frameworks (JUnit, Go `testing`, pytest, etc.)

## Documentation Policy
- Keep READMEs concise: goal, setup, run, tests, structure
- Link to deeper docs under `analysis/` if needed
- Cross-link related topics and problems

## Intake Workflow (Short)
1) Drop into `_staging/`
2) Create `_staging/analysis/<name>.md` with context
3) Classify using taxonomy in `_docs/PROJECT_TAXONOMY.md`
4) Prepare using templates in `_docs/templates/`
5) Move into place; update indexes/TOCs

## Indexing
- Maintain index README files at key levels (e.g., `leetcode/README.md`, `projects/README.md`)
- Use simple tables of contents grouped by topic/difficulty

## CI (Future Optional)
- Lint and test runners per language
- Docs build checks
- Link checkers

## Security & Licensing
- Attribute original sources when copying descriptions
- Keep solutions educational; no licensing conflicts

## Versioning
- Semantic, when applicable, for reusable libs; otherwise keep simple CHANGELOGs


