package emprestes.ds.domain;

import java.util.List;

/**
 * Defines a generic tree contract with common traversal operations.
 *
 * @param <T> stored element type
 */
public interface Tree<T> {

    /**
     * Inserts a value into the tree.
     *
     * @param value value to insert
     * @return current tree instance
     */
    Tree<T> insert(T value);

    /**
     * Checks whether a value exists in the tree.
     *
     * @param value value to search
     * @return {@code true} when present
     */
    boolean contains(T value);

    /**
     * Returns the number of nodes in the tree.
     *
     * @return tree size
     */
    int size();

    /**
     * Indicates whether the tree is empty.
     *
     * @return {@code true} when empty
     */
    boolean isEmpty();

    /**
     * Performs in-order traversal.
     *
     * @return traversal values
     */
    List<T> inorder();

    /**
     * Performs pre-order traversal.
     *
     * @return traversal values
     */
    List<T> preorder();

    /**
     * Performs post-order traversal.
     *
     * @return traversal values
     */
    List<T> postorder();

    /**
     * Performs breadth-first traversal.
     *
     * @return traversal values
     */
    List<T> bfs();
}
