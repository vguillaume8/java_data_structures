package structures.vectors;

/**
 * Concrete implementations of Queue
 * data structures will implement this
 * interface.
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public interface Queue<E> extends Vector<E> {

    /**
     *
     * @param element
     * @return
     */
    default boolean enqueue(E element) {
        return insert(element);
    }

    /**
     *
     * @return
     */
    default E dequeue() {
        return remove();
    }

    /**
     *
     * @return
     */
    E peek();
}
