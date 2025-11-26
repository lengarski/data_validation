# Repository Guidelines

## Project Structure & Module Organization
Source code sits beneath `src/main/java/com/example/datavalidation` with the usual Spring slices: controllers in `web`, services in `service`, repositories in `repository`, and JPA entities in `model`. Configuration, SQL seeds, and other shared resources belong in `src/main/resources` alongside `application.properties`, which already holds the H2 profile. Tests mirror the package tree under `src/test/java`, while Maven artifacts land in `target/`. Keep everything referenced from the repo root so Maven and IDE caches resolve correctly.

## Build, Test, and Development Commands
- `mvn clean install` – refreshes dependencies, compiles the Java 17 sources, runs tests, and assembles the jar.
- `mvn spring-boot:run` – boots the API against the in-memory H2 DB; use `/h2-console` if you need to inspect state.
- `mvn test` – fastest loop for validation/persistence checks; run this before every push.
Leverage your IDE for hot reload, but treat the CLI commands above as the source of truth when verifying changes.

## Coding Style & Naming Conventions
Indent with four spaces, wrap at ~120 chars, and rely on constructor injection (see `DataEntryController` for the pattern). REST endpoints should use kebab-case (`/api/data-entries`). DTOs and controllers should annotate request payloads with `@Valid`, and exceptions flow through `RestExceptionHandler`. Classes use PascalCase, methods/fields camelCase, and configuration keys remain snake-case in `application.properties`.

## Testing Guidelines
JUnit 5 with `@SpringBootTest` is configured; add targeted service tests with mocked repositories and repository tests backed by H2. Name test files `*Tests.java` and use descriptive method names such as `shouldPersistEntryWhenPayloadValid`. Ensure each feature includes at least one regression test beyond `contextLoads`, and run `mvn test` locally before review.

## Commit & Pull Request Guidelines
Write imperative commit subjects (`feat: validate numeric ranges`) and cite issues (`#42`) when applicable. Every PR should summarize behavior changes, call out API contract updates, and list manual/automated test evidence. Keep branches rebased onto `main` and avoid pushing failing builds.

## Security & Configuration Tips
Never commit credentials; lean on Spring profiles and environment overrides. When exposing the H2 console for debugging, restrict it to localhost and disable it before deploying elsewhere. Document any new configuration keys in `application.properties` comments or the PR description so other contributors can replicate the setup.
