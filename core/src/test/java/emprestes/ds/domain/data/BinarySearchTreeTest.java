package emprestes.ds.domain.data;

import emprestes.ds.domain.Tree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeTest {

    private Tree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree<>();
    }

    @Test
    void shouldInsertAndSearchValues() {
        tree.insert(10).insert(5).insert(15).insert(7);

        assertTrue(tree.contains(7));
        assertFalse(tree.contains(8));
        assertEquals(4, tree.size());
    }

    @Test
    void shouldIgnoreDuplicates() {
        tree.insert(10).insert(10).insert(10);

        assertEquals(1, tree.size());
    }

    @Test
    void shouldTraverseInDifferentOrders() {
        tree.insert(10).insert(5).insert(15).insert(3).insert(7);

        assertEquals(java.util.List.of(3, 5, 7, 10, 15), tree.inorder());
        assertEquals(java.util.List.of(10, 5, 3, 7, 15), tree.preorder());
        assertEquals(java.util.List.of(3, 7, 5, 15, 10), tree.postorder());
        assertEquals(java.util.List.of(10, 5, 15, 3, 7), tree.bfs());
    }
}
