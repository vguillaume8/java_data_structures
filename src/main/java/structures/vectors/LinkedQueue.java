package structures.vectors;

import structures.commons.DataStructure;
import structures.commons.LinkedStructure;
import structures.commons.LinkedStructure.Node;

import java.util.Collection;
import java.util.Iterator;

/**
 * Basic FIFO LinkedQueue implementation.
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public final class LinkedQueue<E> extends LinkedStructure<E> implements Queue<E> {

    /**
     * Constructs empty queue
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     */
    public LinkedQueue() {
        ;
    }

    /**
     * Construct queue from array of values.
     *
     * <p>
     * Time: O(n)<br>
     * Space: O(1)
     *
     * @param values Array of values
     */
    public LinkedQueue(E[] values) {
        super(values);
    }

    /**
     * Construct queue from collection of values.
     *
     * <p>
     * Time: O(n)<br>
     * Space: O(1)
     *
     * @param values Collection of values
     */
    public LinkedQueue(Collection<E> values) {
        super(values);
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
    @Override
    public E peek() {
        return getNode(0).value;
    }

    /**
     * Removes an element from the DataStructure. The order
     * in which the value comes out depends on the implementation
     * of implementing class
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     * @return Removed value from DataStructure
     * @see DataStructure
     */
    @Override
    public E remove() {
        return delete(0);
    }
}