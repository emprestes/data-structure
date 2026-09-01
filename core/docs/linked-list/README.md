# Singly Linked List

## Concept and purpose

A singly linked list represents an ordered sequence as a chain of nodes. Each
node owns a value and a link to the next node. Unlike an array, nodes do not
need to occupy adjacent memory locations, and adding a node does not require
moving the existing values.

The structure is useful when insertions and removals near the head are frequent
and random indexed access is not a priority.

## Mental model

The list stores references to its first and last nodes. Every other node is
reachable by following `next` links from the head.

```text
 head                                      tail
   |                                         |
   v                                         v
+-----+------+     +-----+------+     +-----+------+
|  A  | next | --> |  B  | next | --> |  C  | null |
+-----+------+     +-----+------+     +-----+------+
```

Because links point in only one direction, reaching a node requires starting at
the head. The tail reference makes appending O(1), but removing the tail remains
O(n): its predecessor must be found by traversing the chain.

## Invariants

A correct implementation preserves these facts after every operation:

1. An empty list has a null head and tail.
2. A non-empty list has a head and tail.
3. The tail's `next` link is always null.
4. Following `next` from the head reaches exactly `size` nodes.
5. Nodes do not form a cycle.

## Operations

- **Insert at the head:** create a node, point it to the old head, then replace
  the head reference.
- **Insert at the tail:** link the old tail to a new node, then replace the tail.
- **Insert at an index:** find the predecessor and splice the new node between
  two existing nodes.
- **Remove at the head:** move the head to its successor.
- **Remove elsewhere:** find the predecessor and make it skip the removed node.
- **Search or indexed access:** follow links from the head until the target is
  found or the chain ends.

## Complexity

| Operation | Time | Additional space |
| --- | --- | --- |
| read by index | O(n) | O(1) |
| search by value | O(n) | O(1) |
| insert at head | O(1) | O(1) |
| append with a tail reference | O(1) | O(1) |
| insert at an arbitrary index | O(n) | O(1) |
| remove head | O(1) | O(1) |
| remove tail or arbitrary index | O(n) | O(1) |
| iterate or create a snapshot | O(n) | O(1) for iteration; O(n) for snapshot |

The list itself uses O(n) space. Compared with an array, every element also
requires storage for a link.

## Trade-offs and use cases

Choose a singly linked list when:

- insertions or removals at the head are common;
- stable node identity matters;
- elements should grow without reallocating a contiguous block;
- sequential traversal is the natural access pattern.

Prefer an array-backed structure when indexed access, cache locality, or lower
per-element memory overhead matters more. Prefer a doubly linked list when
backward traversal or O(1) removal through a known node is required.

## Language references

The concept above is language-independent. The following implementations expose
the same mechanics through APIs that follow each language's conventions.

| Language | Implementation | Tests | API documentation | Example |
| --- | --- | --- | --- | --- |
| Java | [`SinglyLinkedList.java`](../../src/main/java/emprestes/ds/linkedlist/java/SinglyLinkedList.java) | [`SinglyLinkedListTest.java`](../../src/test/java/emprestes/ds/linkedlist/java/SinglyLinkedListTest.java) | Run `./gradlew :core:javadoc` | [`Java example`](#java-example) |
| Kotlin | [`SinglyLinkedList.kt`](../../src/main/kotlin/emprestes/ds/linkedlist/kotlin/SinglyLinkedList.kt) | [`SinglyLinkedListTest.kt`](../../src/test/kotlin/emprestes/ds/linkedlist/kotlin/SinglyLinkedListTest.kt) | Run `./gradlew :core:dokkaGeneratePublicationHtml` | [`Kotlin example`](#kotlin-example) |

### Java example

```java
var names = new SinglyLinkedList<String>();
names.addLast("Ada").addLast("Edsger").add(1, "Grace");

System.out.println(names.get(1));  // Grace
System.out.println(names.toList()); // [Ada, Grace, Edsger]
```

### Kotlin example

```kotlin
val names = SinglyLinkedList<String>()
    .addLast("Ada")
    .addLast("Edsger")
    .add(1, "Grace")

println(names[1])       // Grace
println(names.toList()) // [Ada, Grace, Edsger]
```
