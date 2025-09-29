# Intake and Integration Process

## Goal
Ensure smooth, iterative integration of new items staged under `_staging/` into the main repository structure.

## Steps
1. Drop the new item into `_staging/` with a short README.
2. Create `_staging/analysis/<item>.md` including:
   - Purpose and scope
   - Suggested classification (see PROJECT_TAXONOMY)
   - Proposed final path
   - Dependencies and build/run requirements
   - Any risks or open questions
3. Prepare using templates in `_docs/templates/`.
4. Validate against MASTER_GUIDELINES checklists.
5. Move to final location; update indexes and cross-links.
6. Remove temporary staging artifacts when done.

## Checklists
- Naming follows conventions
- Executable demo or main present (if applicable)
- Unit tests added or planned
- README complete (setup, run, test, structure)
- Related links and references included

## Example Analysis Skeleton
```
# Analysis: <item>
- Type: project | challenge | note | certification | resource
- Proposed path: <final/location>
- Why here: <reasoning>
- Structure to create: <folders>
- Templates used: <which>
- Open questions: <list>
```
