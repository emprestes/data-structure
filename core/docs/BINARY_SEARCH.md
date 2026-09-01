# Binary Search

## Concept and purpose

Binary search finds a target in a sorted, random-access sequence by repeatedly
discarding half of the remaining candidates. Sorting is not an implementation
detail: it is the property that makes an entire half provably irrelevant after
one comparison.

## Mental model

The search keeps a closed interval from `left` through `right`:

```text
target = 17

index:   0   1   2   3   4   5   6
value:   2   5   9  12  17  21  30
             left   mid       right

17 > 12, so indexes 0 through 3 can be discarded.
```

The midpoint is calculated as `left + (right - left) / 2`. This is equivalent
to `(left + right) / 2`, but avoids overflowing when the two bounds are large.

## Invariants and correctness

At the beginning of every step:

1. if the target exists, at least one occurrence lies inside `[left, right]`;
2. every index smaller than `left` and greater than `right` has been ruled out;
3. the interval strictly shrinks after a comparison that does not match.

If the middle value is smaller than the target, sorted order proves that the
middle and every value to its left are also too small. The symmetric argument
holds when the middle value is larger. Eventually the algorithm either finds a
match or produces `left > right`, which means no candidates remain.

## Iterative and recursive forms

Both forms apply the same interval rule. The iterative form updates bounds in a
loop. The recursive form passes the smaller interval to another call. Recursion
can mirror the mathematical definition more closely, while iteration avoids
call-stack overhead.

## Complexity

| Variant | Time | Additional space |
| --- | --- | --- |
| Iterative | O(log n) | O(1) |
| Recursive | O(log n) | O(log n) call stack |

Binary search requires O(1) comparisons for an empty input and one comparison
for a one-element input. It does not include the cost of sorting the data.

## Trade-offs and use cases

Use binary search when data is already sorted, indexed access is efficient, and
many lookups justify maintaining that order. It also underpins lower/upper bound
queries, insertion-point searches, and monotonic “search the answer” problems.

It is a poor fit for an unsorted sequence, a linked list with O(n) indexed
access, or data that changes so often that preserving sort order dominates the
lookup savings. With duplicates, this implementation returns any match; finding
the first or last occurrence requires a boundary-search variant.

## Language references

| Language | Implementation | Tests | API documentation | Example |
| --- | --- | --- | --- | --- |
| Java | [`BinarySearch.java`](../src/main/java/emprestes/ds/binarysearch/java/BinarySearch.java) | [`BinarySearchTest.java`](../src/test/java/emprestes/ds/binarysearch/java/BinarySearchTest.java) | Run `./gradlew :core:javadoc` | [`Java example`](#java-example) |
| Kotlin | [`BinarySearch.kt`](../src/main/kotlin/emprestes/ds/binarysearch/kotlin/BinarySearch.kt) | [`BinarySearchTest.kt`](../src/test/kotlin/emprestes/ds/binarysearch/kotlin/BinarySearchTest.kt) | Run `./gradlew :core:dokkaGeneratePublicationHtml` | [`Kotlin example`](#kotlin-example) |

### Java example

```java
var search = new BinarySearch();
var values = List.of(2, 5, 9, 12, 17, 21, 30);

System.out.println(search.iterativeIndexOf(values, 17)); // 4
System.out.println(search.recursiveIndexOf(values, 7));  // -1
```

### Kotlin example

```kotlin
val search = BinarySearch()
val values = listOf(2, 5, 9, 12, 17, 21, 30)

println(search.iterativeIndexOf(values, 17)) // 4
println(search.recursiveIndexOf(values, 7))  // -1
```
