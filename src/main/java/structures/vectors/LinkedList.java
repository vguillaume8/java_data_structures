package structures.vectors;

import structures.auxiliary.LinkedListNode;

/**
 * Abstract class to be implemented by all types
 * of LinkedLists
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public abstract class LinkedList<T> extends Vector<T> {

    /**
     * Get Node at a specified index
     *
     * @param index Specified index
     * @return Node pointer at specified index
     */
    protected abstract LinkedListNode<T> getNode(int index);

//------------------------------------------------------------------------------

    /**
     * Inserts a specified value into the front of the list
     *
     * @param value Specified value to insert
     */
    public abstract void insertFirst(T value);

//------------------------------------------------------------------------------

    /**
     * Inserts a specified value onto the back of the list
     *
     * @param value Specified value to insert
     */
    public abstract void insertLast(T value);

//------------------------------------------------------------------------------

    /**
     * Returns the value at a specified index in the list
     *
     * <br>
     * <br>
     * <strong>Time Complexity:</strong><br>
     * <strong>Best: </strong>&Omega;(1)<br>
     * <strong>Worst: </strong>O(n)<br>
     *
     * <br>
     * <strong>Space Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * @param index The specified to retrieve the value from
     * @return The value of the node at the specified index
     */
    public T get(int index) {
        return this.getNode(index).value();
    }

//------------------------------------------------------------------------------

    /**
     * Sets head node pointer to null
     */
    protected abstract void nullHead();

//------------------------------------------------------------------------------

    /**
     * Initializes an empty LinkedList
     */
    @Override
    protected void init() {
        this.nullHead();
        super.init();
    }

//------------------------------------------------------------------------------

    /**
     * Initializes the LinkedList with a specified
     * array of values
     *
     * @param values Values to be inserted into the LinkedList
     */
    @Override
    protected void init(T[] values) {
        this.nullHead();
        super.init(values);
    }

//------------------------------------------------------------------------------

    /**
     * Initializes the LinkedListed to a specified length
     * with a provided default value
     *
     * @param length Specified lent of LinkedList
     * @param value Specified default value
     */
    @Override
    protected void init(int length, T value) {
        this.nullHead();
        super.init(length, value);
    }

//------------------------------------------------------------------------------

    /**
     * Append a specified value  to the back of the list
     *
     * <br>
     * <br>
     * <strong>Time Complexity:</strong><br>
     * <strong>Best: </strong>&Omega;(1)<br>
     * <strong>Worst: </strong>O(n)<br>
     *
     * <br>
     * <strong>Space Complexity:</strong><br>
     * <strong>Avg: </strong>&Theta;(1)<br>
     *
     * @param value Specified value to be inserted into the list
     */
    public void insert(T value) {

        // If the list is empty, insert in the front
        if (this.empty()) {
            this.insertFirst(value);

            // Otherwise insert in the back
        } else {
            this.insertLast(value);
        }
    }

}


