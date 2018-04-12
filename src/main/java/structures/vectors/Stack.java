package structures.vectors;

public interface Stack<E> extends Vector<E> {

    /**
     * Pushes a specified value onto the LinkedStack.
     *
     * @param value Specified value to be pushed onto the LinkedStack
     */
    default void push(E value) {
        insert(value);
    }

    /**
     * Retrieves and removes the topmost value from the LinkedStack.
     *
     * @return The topmost value on the LinkedStack.
     */
    default E pop() {
        return remove();
    }

    /**
     * Retrieves but does not remove the topmost value from the LinkedStack.
     *
     * @return The topmost value on the LinkedStack.
     */
    public abstract E top();

}
