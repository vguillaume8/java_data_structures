package structures.vectors;

/**
 *
 * @author Jabari Dash
 * @param <E>
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

    E peek();
}
