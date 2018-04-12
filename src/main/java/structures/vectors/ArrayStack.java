package structures.vectors;

import structures.commons.DataStructure;
import structures.commons.DynamicArray;

import java.util.Iterator;

/**
 * @author Jabari Dash
 * @param <E>
 */
public class ArrayStack<E> extends DynamicArray<E> implements Vector<E> {

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
        return delete(size - 1);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * Inserts a specified key into the List.
     *
     * @param value The specified value to insert.
     * @return True if and only if the key was successfully inserted.
     */
    @Override
    public boolean insert(E value) {
        return append(value);
    }
}
