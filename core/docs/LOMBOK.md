# Lombok in the Java Examples

## Purpose

Lombok is used only in Java source code and only when generated boilerplate has
no educational value. Kotlin already provides concise language features for the
same concerns and does not use Lombok or its annotation processor.

The guiding rule is simple: annotations may remove ceremony around a data
structure, but they must never hide the mechanics that the example is intended
to teach.

## Configuration

Both Java-producing Gradle modules declare Lombok as `compileOnly` and as an
`annotationProcessor`. Lombok is therefore needed during compilation but is not
a runtime dependency of the produced application or library.

The repository-level `lombok.config` stops configuration from leaking in from a
parent directory and marks generated members with `@Generated` metadata.

## Applied annotations

| Annotation | Location | Reason |
| --- | --- | --- |
| `@UtilityClass` | `HashUtils`, `URLHelper` | Expresses a stateless utility type and generates its inaccessible constructor. |
| `@RequiredArgsConstructor` | private stack node | Generates a constructor that only assigns final representation fields. |
| `@RequiredArgsConstructor` + `@NonNull` | `StackHistory` | Generates constructor injection and preserves fail-fast null validation. |

## Intentional exclusions

- **Kotlin sources:** Kotlin constructors, properties, data classes, and null
  safety already cover Lombok's main use cases.
- **Records:** `StackSnapshot` remains a Java record because records are the
  language-native representation for immutable data carriers.
- **Data structures:** fields, links, resizing, traversal, rotations, heap
  operations, and invariants remain explicit.
- **Algorithms:** stateless algorithms do not receive interfaces, builders, or
  generated accessors merely to demonstrate annotations.
- **`@Data`:** deliberately prohibited because it can generate setters,
  equality, and string representations that expose or misrepresent mutable
  internals.
- **Public API models:** Lombok is not used to silently widen visibility or
  create mutability.

## Review checklist

Before adding another Lombok annotation, verify that:

1. the generated code is pure boilerplate;
2. removing the handwritten code does not hide a data-structure concept;
3. visibility and null behavior remain explicit;
4. the annotation does not expose mutable internal state;
5. a modern Java language feature is not clearer;
6. Kotlin code remains unaffected.
