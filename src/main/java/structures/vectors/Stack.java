package structures.vectors;

/**
 * Concrete implementations of Stack
 * data structures will implement this
 * interface.
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public interface Stack<E> extends Vector<E> {

    /**
     * Pushes a specified value onto the LinkedStack.
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     * @param value Specified value to be pushed onto the LinkedStack
     */
    default void push(E value) {

        insert(value);
    }

    /**
     * Retrieves and removes the topmost value from the LinkedStack.
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     * @return The topmost value on the LinkedStack.
     */
    default E pop() {

        return remove();
    }

    /**
     * Retrieves but does not remove the topmost value from the LinkedStack.
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     * @return The topmost value on the LinkedStack.
     */
    E top();

}
