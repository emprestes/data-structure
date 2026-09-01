package emprestes.ds.domain;

/**
 * Complete LIFO stack contract composed from independent mutation and view roles.
 *
 * @param <T> stored element type
 */
public interface IStack<T> extends MutableStack<T>, StackView<T> {

    /**
     * Pushes one or more values to the top of the stack.
     *
     * @param values values to push
     * @return current stack instance
     */
    @Override
    @SuppressWarnings("unchecked")
    IStack<T> push(T... values);
}
