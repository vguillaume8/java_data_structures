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
     * Inserts a specified value onto the back of the list
     *
     * @param value Specified value to insert
     */
//    boolean append(T value);

//------------------------------------------------------------------------------

    /**
     * Returns the value located at a specified index
     *
     * @param index Specified index
     * @return Item at the specified index
     */
    T get(int index);

//------------------------------------------------------------------------------

    /**
     * Insert a value at a specified index
     *
     * @param value Value to be inserted
     * @param index Specified index to insert value at
     */
    boolean insert(T value, int index);

//------------------------------------------------------------------------------

    /**
     * Inserts a specified value into the front of the list
     *
     * @param value Specified value to insert
     */
//    boolean prepend(T value);

//------------------------------------------------------------------------------

    /**
     * Returns iterator (allows use with enhanced forloop)
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
     * @param index Index to remove value from
     * @return The value at the specified index
     */
    T remove(int index);

//------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    default T remove() {
        return removeLast();
    }

//------------------------------------------------------------------------------

    /**
     * Removes first value from the List
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
     * @param value New value.
     * @param index Specified index.
     */
    @SuppressWarnings("unused")
    void update(T value, int index);

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
                throw new NoSuchElementException("No element");
            }

            int current = this.cursor;    // Store the current cursor value
            this.cursor++;                // Increment the cursor
            return list.get(current);     // Return the value at index cursor (before it was incremented)
        }

    }
}
