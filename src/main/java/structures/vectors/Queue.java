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
     * Inserts a specified element
     * to the end of the queue.
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     * @param element Specified element to insert.
     * @return True if the insertion was successful.
     */
    default boolean enqueue(E element) {
        return insert(element);
    }

    /**
     * Removes and returns the
     * value at the front of the queue.
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     * @return Value at front of queue.
     */
    default E dequeue() {
        return remove();
    }

    /**
     * Returns but does not remove the
     * value at the front of the queue.
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     * @return Value at front of queue.
     */
    E peek();
}
