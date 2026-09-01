# Dynamic Array

## Concept and purpose

A regular array reserves a fixed-size block. A dynamic array preserves its main
advantage—direct access by index—but replaces the block with a larger one when
it becomes full.

## Mental model

There are two independent measurements:

- **size**: how many elements belong to the collection;
- **capacity**: how many slots the current block can hold.

When inserting into a full array, the implementation creates a block with twice
the capacity and copies the elements. Geometric growth avoids copying everything
on every insertion.

```text
size = 3, capacity = 4

index:       0      1      2      3
stored:    [ A ]  [ B ]  [ C ]  [   ]
```

## Invariants

1. `size` is never negative and never exceeds `capacity`.
2. Elements occupy the logical range from index zero through `size - 1`.
3. Slots from `size` through `capacity - 1` do not belong to the collection.
4. Growing storage preserves element order and every existing value.

## Operations and complexity

| Operation | Time | Reason |
| --- | --- | --- |
| read or replace by index | O(1) | the address is calculated directly |
| append | amortized O(1) | most insertions do not copy the block |
| insert or remove in the middle | O(n) | elements must be shifted |
| search by value | O(n) | every element may need to be visited |
| copy to a list | O(n) | each element enters the new snapshot |

Space usage is O(n). Immediately after growth, some slots are reserved but still
empty; this is the cost paid for fast appends.

## Trade-offs and use cases

Dynamic arrays are a strong default for indexed sequences because they combine
O(1) access, compact storage, and cache-friendly traversal. They are less
suitable when insertions and removals frequently occur near the beginning,
because every successor must move. A linked structure may be preferable when
stable nodes and frequent structural changes matter more than indexed access.

## Language references

| Language | Implementation | Tests | API documentation | Example |
| --- | --- | --- | --- | --- |
| Java | [`DynamicArray.java`](../src/main/java/emprestes/ds/array/java/DynamicArray.java) | [`DynamicArrayTest.java`](../src/test/java/emprestes/ds/array/java/DynamicArrayTest.java) | Run `./gradlew :core:javadoc` | [`Java example`](#java-example) |
| Kotlin | [`DynamicArray.kt`](../src/main/kotlin/emprestes/ds/array/kotlin/DynamicArray.kt) | [`DynamicArrayTest.kt`](../src/test/kotlin/emprestes/ds/array/kotlin/DynamicArrayTest.kt) | Run `./gradlew :core:dokkaGeneratePublicationHtml` | [`Kotlin example`](#kotlin-example) |

### Java example

```java
var names = new DynamicArray<String>();
names.add("Ada").add("Grace").add("Edsger");

System.out.println(names.get(1));  // Grace
System.out.println(names.toList()); // [Ada, Grace, Edsger]
```

### Kotlin example

```kotlin
val names = DynamicArray<String>(initialCapacity = 2)
names.add("Ada")
names.add("Edsger")
names.add(1, "Grace")

println(names[1])       // Grace
println(names.toList()) // [Ada, Grace, Edsger]
```
