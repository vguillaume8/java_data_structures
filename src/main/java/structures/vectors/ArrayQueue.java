package structures.vectors;

import structures.commons.DataStructure;
import structures.commons.DynamicArray;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @param <E>
 */
public final class ArrayQueue<E> extends DynamicArray<E> implements Queue<E>, Vector<E> {

    // TODO - Complete implementation
    // a lot of the code from dynamic array probably
    // cannot be used. We mostly care about the copy()
    // function for when are queue becomes too large.
    // but provided that the front will always be moving
    // when we dequeue, a lot of the looping functions
    // do not work. Unless, we change the way that dynamic
    // array works, and all loops start from this.front,
    // and front just always remains 0, for all other structures.
    // However, this requires lots of reworking.

    /**
     * Constructs empty LinkedQueue.
     */
    public ArrayQueue() {
        super();
    }

    /**
     * Constructs LinkedQueue from array of keys.
     *
     * @param keys Array of keys to instatiate LinkedQueue from
     */
    public ArrayQueue(E[] keys) {
        insert(keys);
    }

    /**
     * Construct queue from Java Collection of values.
     *
     * @param values Collection of values to construct LinkedQueue from.
     * @see java.util.Collection
     */
    public ArrayQueue(Collection<E> values) {
        insert(values);
    }

    /**
     *
     * @return
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
     * @return Removed value from DataStructure
     * @see DataStructure
     */
    @Override
    public E remove() {
        return delete(0);
    }

    /**
     * Inserts a specified key into the List.
     *
     * @param element The specified key to insert.
     * @return True if and only if the key was successfully inserted.
     */
    @Override
    public boolean insert(E element) {
        return append(element);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return asString();
    }
}
