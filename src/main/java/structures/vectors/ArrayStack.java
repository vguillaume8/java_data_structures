package structures.vectors;

import structures.commons.DataStructure;
import structures.commons.DynamicArray;
import java.util.Collection;
import java.util.Iterator;

/**
 * Implementation of Stack using
 * an internal array to hold data elements.
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public final class ArrayStack<E> extends DynamicArray<E> implements Stack<E> {


    /**
     * Constructs empty stack
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     */
    public ArrayStack() {

        super();
    }

//------------------------------------------------------------------------------

    /**
     * Constructs stack from array of values.
     *
     * <p>
     * Time: O(n)<br>
     * Space: O(1)
     *
     * @param values Array of values to instantiate LinkedStack from.
     */
    public ArrayStack(E[] values) {

        super(values);
    }

//------------------------------------------------------------------------------

    /**
     * Construct stack from Java Collection of values.
     *
     * <p>
     * Time: O(n)<br>
     * Space: O(1)
     *
     * @param values Collection of values to construct LinkedStack from.
     * @see java.util.Collection
     */
    public ArrayStack(Collection<E> values) {

        super(values);
    }

//------------------------------------------------------------------------------

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

        return delete(size - 1);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {

        return new DynamicArrayIterator<E>(false);
    }

    /**
     * Inserts a specified key into the List.
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     * @param value The specified value to insert.
     * @return True if and only if the key was successfully inserted.
     */
    @Override
    public boolean insert(E value) {

        return append(value);
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
    @Override
    public E top() {
        return access(size - 1);
    }

    /**
     * Returns String representation of stack.
     *
     * @return String representation.
     */
    @Override
    public String toString() {

        return asString();
    }

}
