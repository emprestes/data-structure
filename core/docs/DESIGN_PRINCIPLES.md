# SOLID and the Law of Demeter

## Purpose

Design principles are decision tools, not goals by themselves. This repository
uses them to keep educational implementations easy to understand, substitute,
test, and extend without hiding the underlying algorithms behind unnecessary
frameworks or abstraction layers.

## Review method

The production code in `core` and `app` was reviewed by responsibility,
dependency direction, public contract, internal representation, and mutation
boundary. A refactor was accepted only when it improved at least one measurable
property—cohesion, coupling, substitutability, encapsulation, or testability—
without obscuring the data structure or changing its asymptotic complexity.

## Principle map

| Principle | Review question | Applied decision |
| --- | --- | --- |
| Single Responsibility | Does this type have one reason to change? | Stack storage remains in `Stack`; operation history moved to `StackHistory`; rendering remains in JavaFX. |
| Open/Closed | Can a client use another implementation without changing orchestration code? | `StackHistory` accepts stack roles through its constructor and has no dependency on the linked-node implementation. |
| Liskov Substitution | Do implementations honor their contracts for empty, null, and snapshot behavior? | Stack contracts document null handling; tests exercise empty state, immutable snapshots, and repeated observation. |
| Interface Segregation | Must a read-only client depend on mutation methods? | `StackView` and `MutableStack` expose separate roles; `IStack` composes them for clients that genuinely need both. |
| Dependency Inversion | Does high-level behavior construct low-level storage internally? | The application model receives abstractions; only the JavaFX composition root creates `Stack`. |
| Law of Demeter | Does a collaborator navigate another object's internal graph? | Stack nodes are private; the UI asks `StackHistory` for snapshots instead of navigating or rebuilding stack storage. |

## Stack refactor

### Before

- A public `Node` type exposed stack representation and link-navigation methods.
- `Stack.toList()` implemented observation by popping every value and pushing it
  back, coupling a read operation to mutation behavior.
- Size recursively traversed the node chain, making a basic state query O(n).
- The JavaFX class mutated the stack, captured history, stored snapshots, and
  rendered controls and cards.
- The single `IStack` contract forced observers to see destructive operations.

### After

```text
StackVisualizerApp  --->  StackHistory  --->  MutableStack<String>
        |                    |
        |                    +--------->  StackView<String>
        |
        +-- renders immutable StackSnapshot values

Composition root: one Stack<String> supplies both role interfaces.
```

- Nodes are a private implementation detail of `Stack`.
- `StackView` contains observation only; `MutableStack` contains commands only.
- `IStack` remains as a compatibility contract that composes both roles.
- `StackHistory` coordinates commands and immutable snapshots without importing
  JavaFX or constructing a concrete data structure.
- `Stack.toList()` traverses nodes without changing the stack.
- Stack size is maintained as an invariant, so `size()` is O(1).

## Demeter boundaries

The Law of Demeter is applied as “talk to immediate collaborators,” not as a ban
on every chained call. Fluent calls on builders, streams, and JavaFX controls do
not reveal domain internals and are not automatically violations.

The boundaries used here are:

- nodes never cross a data-structure public API;
- collection snapshots are immutable or defensive copies;
- application orchestration asks a model for state instead of reaching through
  the model to storage;
- algorithms receive the minimal input representation they operate on;
- UI objects remain confined to the `app` module.

## Java and Kotlin review findings

| Area | Java | Kotlin | Decision |
| --- | --- | --- | --- |
| Dynamic array | Internal storage is private; snapshots do not expose the backing array. | Same properties; iterator and storage are encapsulated. | No design abstraction added. The array mechanics should remain visible. |
| Singly linked list | Node is private; contract and implementation are separated. | Node is private and the type exposes behavior only. | Existing design already follows SRP and Demeter. |
| Binary search | Stateless, generic, and independent of storage implementation beyond `List`. | Equivalent stateless API. | No injected strategy: it would add ceremony without an alternate policy. |
| Sorting and numeric algorithms | Stateless algorithm objects operate only on supplied values. | No counterpart yet. | Keep algorithms cohesive; split only when a class gains an independent policy. |
| Graph and tree | Public interfaces represent complete educational capabilities. | No counterpart yet. | Do not fragment them until a real client needs a smaller role; speculative ISP would reduce clarity. |
| Map, set, queue, and heap | Mutable state is private and returned collections are snapshots or immutable copies. | No counterpart yet. | Preserve direct implementations for teaching value. |
| URL and hash helpers | Stateless utilities with explicit validation. | No counterpart yet. | Keep utility APIs small; hash formatting and tests were corrected during review. |

## Trade-offs

- `IStack` is retained to avoid a breaking migration for existing clients. New
  observers should prefer `StackView`; command-only clients should prefer
  `MutableStack`.
- `StackHistory` accepts two roles that normally refer to the same object. This
  makes dependency needs explicit and testable, at the cost of one additional
  constructor argument.
- The review intentionally does not create factories, repositories, services,
  or strategies for stateless algorithms. SOLID does not require every class to
  have an interface.
- Existing graph and tree interfaces remain broad because current consumers use
  them as complete data structures. They can be split when a concrete client
  demonstrates a narrower dependency.

## Verification

- Core and application unit tests cover changed contracts and orchestration.
- Javadoc and KDoc/Dokka generation verify public documentation.
- Data-structure asymptotic behavior is preserved, while stack `size()` and
  non-mutating snapshots improve over the previous implementation.
