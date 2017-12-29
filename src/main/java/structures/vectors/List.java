package structures.vectors;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Abstract List structure. All vector-like structures (linear structures)
 * will extend this abstract class
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public interface List<T> extends Vector<T> {

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
     * Checks if a specified index is out of bounds of the DataStructure
     *
     * @param index Specified index
     * @return True if and only if the index is out of bound
     */
    /**
     * Determines whether or not a specified index is within the bounds of the list
     *
     * @param index Specified index
     * @return True if and only if the index is less then the length of the list, and positive
     */
    default boolean indexOutOfBounds(int index) {
        return index < 0 || index >= this.size();
    }

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
     * Retrieves and removes a value from a specified index
     *
     * @param index Index to remove value from
     * @return The value at the specified index
     */
    T remove(int index);

//------------------------------------------------------------------------------

    /**
     * Inserts a specified value into the front of the list
     *
     * @param value Specified value to insert
     */
    boolean insertFirst(T value);

//------------------------------------------------------------------------------

    /**
     * Inserts a specified value onto the back of the list
     *
     * @param value Specified value to insert
     */
    boolean insertLast(T value);

//------------------------------------------------------------------------------

    /**
     * Returns iterator (allows use with enhanced forloop)
     *
     * @return Iterator for iterating over Structure by index
     */
    @Override
    default Iterator<T> iterator() {
        return new IndexedDataStructureIterator<>(this);
    }

//------------------------------------------------------------------------------

    /**
     * Removes first value from the List
     *
     * @return First element in DataStructure
     */
    T removeFirst();

//------------------------------------------------------------------------------

    /**
     * Removes the last value from the List
     *
     * @return Last element in DataStructure
     */
    T removeLast();

//------------------------------------------------------------------------------
    /**
     * Verifies if a provided index is within the DataStructure or not
     *
     * @param index Specified index to verify
     * @throws IndexOutOfBoundsException Exception thrown if the index is invalid
     */
    default void verifyIndex(int index) {
        if (this.indexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("size: " + this.size() + " index: " + index);
        }
    }


    /**
     * Static Iterator class so that the List can be iterated on via
     * the enhance for loop
     *
     * @author Jabari Dash
     * @param <T> Generic type
     */
    class IndexedDataStructureIterator<T> implements Iterator<T> {
        private List<T> list;   // List to iterate over
        private int cursor;                     // Cursor to keep track of position in iteration

        /**
         * Constructor, initialize cursor to index 0, and the list
         * variable equal to the passed in list
         *
         * @param list The list to be iterated on
         */
        public IndexedDataStructureIterator(List<T> list) {
            this.list = list;
            this.cursor = 0;
        }

//------------------------------------------------------------------------------

        /**
         * Determines whether or not the iterator still has values left
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

            // If there are no more values left, throw an Exception
            if (!hasNext()) {
                throw new NoSuchElementException("No element");
            }

            int current = this.cursor;    // Store the current cursor value
            this.cursor++;                // Increment the cursor
            return list.get(current);     // Return the value at index cursor (before it was incremented)
        }

    }
}
