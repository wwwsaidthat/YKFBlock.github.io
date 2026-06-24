# Submission Checklist

## Files to submit

- `pom.xml`
- `README.md`
- `src/main/`
- `src/test/`
- `outputs/logs/`
- `outputs/diagrams/`
- `outputs/json/`
- `outputs/reports/final-report.md`
- `docs/superpowers/specs/`
- `docs/superpowers/plans/`

## Items to fill in before submission

- Update the contribution table in `outputs/reports/final-report.md`.
- Confirm the final report still contains the real interaction metrics from the successful run.
- Recheck that the final report content matches the latest generated outputs.

## Sensitive information rules

- Do not submit any real `ARK_API_KEY`.
- Keep `.env.example` as placeholders only.
- Do not create or submit a `.env` file with real secrets.
- Recheck copied screenshots or notes before submission to avoid leaking credentials.

## Final self-check

- Run `mvn -q test`.
- Confirm that `outputs/` contains four logs, four diagrams, four JSON files, and one final report.
- Open `outputs/reports/final-report.md` and verify the contribution table has been filled.
- Verify `.env.example` still uses placeholder values only.
