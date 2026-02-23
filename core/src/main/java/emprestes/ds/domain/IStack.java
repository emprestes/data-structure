package emprestes.ds.domain;

import java.util.List;

public interface IStack<T> {

    IStack<T> push(T... value);

    T pop();

    List<T> popAll();

    boolean isEmpty();

    int size();

    /**
     * Returns the current elements from top to bottom without mutating the stack.
     */
    List<T> toList();
}
