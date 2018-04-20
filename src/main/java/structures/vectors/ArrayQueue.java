package structures.vectors;

import structures.commons.DataStructure;
import structures.commons.DynamicArray;
import java.util.Collection;

/**
 * Implementation of Queue using
 * an internal array to hold data elements.
 *
 * TODO - https://www.geeksforgeeks.org/queue-set-1introduction-and-array-implementation/
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public final class ArrayQueue<E> extends DynamicArray<E> implements Queue<E>, Vector<E> {

    // TODO - Complete implementation
    // a lot of the code from dynamic array probably
    // cannot be used. We mostly care about the copy()
    // function for when are queue becomes too large.
    // but provided that the front will always be moving
    // when we dequeue, a lot of the looping functions
    // do not work. Unless, we change the way that dynamic
    // array works, and all loops start from a variable front.
    // For non-queues, front just always remains 0.
    // However, this requires lots of reworking.

    /**
     * Constructs empty LinkedQueue.
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     */
    public ArrayQueue() {
        super();
    }

    /**
     * Constructs LinkedQueue from array of keys.
     *
     * <p>
     * Time: O(n)<br>
     * Space: O(1)
     *
     * @param values Array of keys to instatiate LinkedQueue from
     */
    public ArrayQueue(E[] values) {
        super(values);
    }

    /**
     * Construct queue from Java Collection of values.
     *
     * <p>
     * Time: O(n)<br>
     * Space: O(1)
     *
     * @param values Collection of values to construct LinkedQueue from.
     * @see java.util.Collection
     */
    public ArrayQueue(Collection<E> values) {
        super(values);
    }

    /**
     * Returns but does not remove
     * the value at the front of the queue.
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     * @return Value at front of queue.
     */
    @Override
    public E peek() {
        return access(0);
    }

    /**
     * Removes an element from the DataStructure. The order
     * in which the value comes out depends on the implementation
     * of implementing class
     *
     * TODO - Optimize to O(1)
     *
     * <p>
     * Time: O(n)<br>
     * Space: O(1)
     *
     * @return Removed value from DataStructure
     * @see DataStructure
     */
    @Override
    public E remove() {
        E value;

        // TODO - Finish implementation

        // Get first element
        // in the queue
        value = delete(0);

        // Push front pointer
        // further back in
        // internal array
        if (!empty()) {
            ;
//            front++;
        }

        return value;
    }

    /**
     * Inserts a specified key into the List.
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     * @param element The specified key to insert.
     * @return True if and only if the key was successfully inserted.
     */
    @Override
    public boolean insert(E element) {
        return append(element);
    }

    /**
     * Returns String representation of queue.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return asString();
    }
}
