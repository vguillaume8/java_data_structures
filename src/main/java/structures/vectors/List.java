package structures.vectors;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Abstract List structure. All vector-like (linear) structures
 * will implement this interface.
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public interface List<T> extends Vector<T> {

    /**
     * Returns the value located at a specified index
     *
     * <p>
     * Time: <br>
     *     O(1) for array implementation<br>
     *     O(n) for linked implementations<br>
     *
     * Space: O(1)
     *
     * @param index Specified index
     * @return Item at the specified index
     */
    T get(int index);

//------------------------------------------------------------------------------

    /**
     * Insert a value at a specified index
     *
     * <p>
     * Time: O(n)<br>
     * Space: O(1)
     *
     * @param value Value to be inserted
     * @param index Specified index to insert value at
     * @return True if the insertion is successful.
     */
    boolean insert(T value, int index);

//------------------------------------------------------------------------------

    /**
     * Insert element at front of list.
     *
     * <p>
     * Time: <br>
     *     O(n) for array implementation<br>
     *     O(1) for linked implementations<br>
     *
     * Space: O(1)
     *
     * @param value Value to insert.
     * @return True if the insertion was successful.
     */
    default boolean insertFirst(T value) {
        return insert(value, 0);
    }

//------------------------------------------------------------------------------

    /**
     * Insert element at end of list.
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     * @param value Value to insert.
     * @return True if the insertion was successful.
     */
    default boolean insertLast(T value) {
        return insert(value);
    }

//------------------------------------------------------------------------------

    /**
     * Returns iterator (allows use with enhanced for loop)
     *
     * @return Iterator for iterating over Structure by index
     */
    @Override
    default Iterator<T> iterator() {
        return new ListIterator<>(this);
    }

//------------------------------------------------------------------------------

    /**
     * Retrieves and removes a value from a specified index
     *
     * <p>
     * Time: O(n)<br>
     * Space: O(1)
     *
     * @param index Index to remove value from
     * @return The value at the specified index
     */
    T remove(int index);

//------------------------------------------------------------------------------

    /**
     * Removes and returns the next
     * element from list - provided the
     * natural order of removal in subclasses.
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     * @return Next element in order of removal.
     */
    default T remove() {
        return removeLast();
    }

//------------------------------------------------------------------------------

    /**
     * Removes first value from the List
     *
     * <p>
     * Time: <br>
     *     O(n) for array implementation<br>
     *     O(1) for linked implementations<br>
     *
     * Space: O(1)
     *
     * @return First element in DataStructure
     */
    default T removeFirst() {
        return remove(0);
    }

//------------------------------------------------------------------------------

    /**
     * Removes the last value from the List
     *
     * <p>
     * Time: O(1)<br>
     * Space: O(1)
     *
     * @return Last element in DataStructure
     */
    default T removeLast() {
        return remove(size() - 1);
    }

//------------------------------------------------------------------------------

    /**
     * Overwrites a value at a specified index
     * with a new value.
     *
     * <p>
     * Time: <br>
     *     O(1) for array implementation<br>
     *     O(n) for linked implementations<br>
     *
     * Space: O(1)
     *
     * @param value New value.
     * @param index Specified index.
     */
    @SuppressWarnings("unused")
    void set(T value, int index);


//------------------------------------------------------------------------------

    /**
     * Static Iterator class so that the List can be iterated on via
     * the enhance for loop.
     *
     * @author Jabari Dash
     * @param <T> Generic type
     */
    class ListIterator<T> implements Iterator<T> {
        private List<T> list;   // List to iterate over
        private int     cursor; // Cursor to keep track of position in iteration

        /**
         * Constructor, initialize cursor to index 0, and the list
         * variable equal to the passed in list
         *
         * @param list The list to be iterated on
         */
        private ListIterator(List<T> list) {
            this.list   = list;
            this.cursor = 0;
        }

//------------------------------------------------------------------------------

        /**
         * Determines whether or not the iterator still has keys left
         *
         * @return True if and only if the cursor has not reached the end of the list
         */
        public boolean hasNext() {
            return this.cursor < this.list.size();
        }

//------------------------------------------------------------------------------

        /**
         * Returns the next value in iteration
         *
         * @return The next value in the iteration
         */
        public T next() {

            // If there are no more keys left, throw an Exception
            if (!hasNext()) {
                throw new NoSuchElementException("No next element");
            }

            int current = this.cursor;    // Store the current cursor value
            this.cursor++;                // Increment the cursor
            return list.get(current);     // Return the value at index cursor (before it was incremented)
        }

    }
}
