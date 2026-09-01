# Dynamic Array in Kotlin

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

## Example

```kotlin
val names = DynamicArray<String>(initialCapacity = 2)
names.add("Ada")
names.add("Edsger")
names.add(1, "Grace")

println(names[1])       // Grace
println(names.toList()) // [Ada, Grace, Edsger]
```

## Teaching decisions

- Storage is an `Array<Any?>` because the JVM cannot directly create an array of
  a generic type that is erased at runtime.
- `MutableList` is not used internally: delegating to it would hide the
  reallocation and shifting behavior that this implementation teaches.
- Null values are supported. A `null` outside the logical range is unobservable
  and also releases removed references for garbage collection.

## Code locations

- Implementation: `core/src/main/kotlin/emprestes/ds/kotlin/array/DynamicArray.kt`
- Tests: `core/src/test/kotlin/emprestes/ds/kotlin/array/DynamicArrayTest.kt`
- HTML API documentation: run `./gradlew :core:dokkaGeneratePublicationHtml`
