package emprestes.ds.binarysearch.kotlin

/**
 * Binary search over sorted, random-access values using natural ordering.
 *
 * The algorithm maintains a closed candidate interval `[left, right]`. A
 * comparison with the middle value either finds [target] or proves that one
 * half cannot contain it, so each step reduces the remaining interval by half.
 *
 * Input values must be sorted in ascending order and must not contain nulls. A
 * null list or target is treated as absent and returns `-1`. With duplicates,
 * any matching index may be returned.
 *
 * Time complexity is O(log n). [iterativeIndexOf] uses O(1) additional space;
 * [recursiveIndexOf] uses O(log n) call-stack space.
 */
class BinarySearch {
    /** Finds [target] iteratively, returning a matching index or `-1`. */
    fun <E : Comparable<E>> iterativeIndexOf(sortedValues: List<E>?, target: E?): Int {
        if (sortedValues == null || target == null) return -1

        var left = 0
        var right = sortedValues.lastIndex
        while (left <= right) {
            val middle = midpoint(left, right)
            val comparison = sortedValues[middle].compareTo(target)
            when {
                comparison == 0 -> return middle
                comparison < 0 -> left = middle + 1
                else -> right = middle - 1
            }
        }
        return -1
    }

    /** Finds [target] recursively, returning a matching index or `-1`. */
    fun <E : Comparable<E>> recursiveIndexOf(sortedValues: List<E>?, target: E?): Int {
        if (sortedValues == null || target == null) return -1
        return recursiveIndexOf(sortedValues, target, 0, sortedValues.lastIndex)
    }

    private fun <E : Comparable<E>> recursiveIndexOf(
        sortedValues: List<E>,
        target: E,
        left: Int,
        right: Int,
    ): Int {
        if (left > right) return -1
        val middle = midpoint(left, right)
        val comparison = sortedValues[middle].compareTo(target)
        return when {
            comparison == 0 -> middle
            comparison < 0 -> recursiveIndexOf(sortedValues, target, middle + 1, right)
            else -> recursiveIndexOf(sortedValues, target, left, middle - 1)
        }
    }

    private fun midpoint(left: Int, right: Int): Int = left + (right - left) / 2
}
