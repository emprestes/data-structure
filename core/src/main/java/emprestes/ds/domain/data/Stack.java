package emprestes.ds.domain.data;

import emprestes.ds.domain.IStack;
import emprestes.ds.domain.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

/**
 * Linked-node stack implementation.
 *
 * @param <T> stored value type
 */
public class Stack<T> implements IStack<T> {

    private Node<T> head;

    /**
     * Returns the current top value as an optional.
     *
     * @return optional top value
     */
    public Optional<T> getHead() {
        return ofNullable(head).map(Node::value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Indicates whether the stack has at least one element.
     *
     * @return {@code true} when not empty
     */
    public boolean nonEmpty() {
        return !isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SafeVarargs
    public final IStack<T> push(T... values) {
        for (var value : values) {
            push(value);
        }

        return this;
    }

    /**
     * Pushes a single non-null value to the stack top.
     *
     * @param value value to push
     */
    private void push(T value) {
        head = nonNull(value)
                ? ofNullable(head)
                  .map(h -> h.next(value))
                  .orElseGet(() -> new Node<>(value))
                : head;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T pop() {
        var value = ofNullable(head).map(Node::value);

        if (value.isPresent()) {
            head = head.previous();
            return value.get();
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> popAll() {
        var all = new ArrayList<T>();

        while (nonEmpty()) {
            all.add(pop());
        }

        return all;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return ofNullable(head).map(Node::count).orElse(0);
    }

    /**
     * Returns a snapshot of the current stack from top to bottom without mutating it.
     */
    public List<T> toList() {
        var popped = popAll();
        for (int i = popped.size() - 1; i >= 0; i--) {
            push(popped.get(i));
        }
        return List.copyOf(popped);
    }
}
