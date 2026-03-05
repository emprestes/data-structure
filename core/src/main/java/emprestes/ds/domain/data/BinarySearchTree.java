package emprestes.ds.domain.data;

import emprestes.ds.domain.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Binary search tree implementation without self-balancing.
 *
 * @param <T> comparable value type
 */
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

    /**
     * Node structure used by the tree.
     *
     * @param <T> node value type
     */
    private static class Entry<T> {
        private final T value;
        private Entry<T> left;
        private Entry<T> right;

        /**
         * Creates a node for the given value.
         *
         * @param value node value
         */
        private Entry(T value) {
            this.value = value;
        }
    }

    private Entry<T> root;
    private int size;

    /**
     * {@inheritDoc}
     */
    @Override
    public Tree<T> insert(T value) {
        if (value == null) {
            return this;
        }

        if (root == null) {
            root = new Entry<>(value);
            size++;
            return this;
        }

        var current = root;
        while (true) {
            int cmp = value.compareTo(current.value);
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new Entry<>(value);
                    size++;
                    return this;
                }
                current = current.left;
            } else if (cmp > 0) {
                if (current.right == null) {
                    current.right = new Entry<>(value);
                    size++;
                    return this;
                }
                current = current.right;
            } else {
                return this;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T value) {
        if (value == null || root == null) {
            return false;
        }

        var current = root;
        while (current != null) {
            int cmp = value.compareTo(current.value);
            if (cmp == 0) {
                return true;
            }
            current = cmp < 0 ? current.left : current.right;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> inorder() {
        var result = new ArrayList<T>(size);
        inorder(root, result);
        return result;
    }

    /**
     * Internal in-order traversal helper.
     *
     * @param node current node
     * @param result accumulator
     */
    private void inorder(Entry<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> preorder() {
        var result = new ArrayList<T>(size);
        preorder(root, result);
        return result;
    }

    /**
     * Internal pre-order traversal helper.
     *
     * @param node current node
     * @param result accumulator
     */
    private void preorder(Entry<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        result.add(node.value);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> postorder() {
        var result = new ArrayList<T>(size);
        postorder(root, result);
        return result;
    }

    /**
     * Internal post-order traversal helper.
     *
     * @param node current node
     * @param result accumulator
     */
    private void postorder(Entry<T> node, List<T> result) {
        if (node == null) {
            return;
        }
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> bfs() {
        if (root == null) {
            return List.of();
        }

        var result = new ArrayList<T>(size);
        var queue = new ArrayDeque<Entry<T>>();
        queue.add(root);

        while (!queue.isEmpty()) {
            var node = queue.removeFirst();
            result.add(node.value);
            if (Objects.nonNull(node.left)) {
                queue.addLast(node.left);
            }
            if (Objects.nonNull(node.right)) {
                queue.addLast(node.right);
            }
        }

        return result;
    }
}
