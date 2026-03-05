package emprestes.ds.domain;

/**
 * Sorting contract for predefined element types used in the project.
 */
public interface ISort {

    /**
     * Sorts integer values in ascending order.
     *
     * @param integers values to sort
     * @return sorted array (same reference when mutable operations are used)
     */
    Integer[] sort(Integer... integers);

    /**
     * Sorts character values in ascending order.
     *
     * @param characters values to sort
     * @return sorted array (same reference when mutable operations are used)
     */
    Character[] sort(Character... characters);
}
