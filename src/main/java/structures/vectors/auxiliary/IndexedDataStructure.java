package structures.vectors.auxiliary;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Abstract IndexedDataStructure structure. All vector-like structures (linear structures)
 * will extend this abstract class
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public interface IndexedDataStructure<T> extends LinearDataStructure<T> {

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
    boolean indexOutOfBounds(int index);

//------------------------------------------------------------------------------

    /**
     * Insert a value at a specified index
     *
     * @param value Value to be inserted
     * @param index Specified index to insert value at
     */
    void insert(T value, int index);

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
    void insertFirst(T value);

//------------------------------------------------------------------------------

    /**
     * Inserts a specified value onto the back of the list
     *
     * @param value Specified value to insert
     */
    void insertLast(T value);

//------------------------------------------------------------------------------

    /**
     * Removes first value from the IndexedDataStructure
     *
     * @return First element in DataStructure
     */
    T removeFirst();

//------------------------------------------------------------------------------

    /**
     * Removes the last value from the IndexedDataStructure
     *
     * @return Last element in DataStructure
     */
    T removeLast();

//------------------------------------------------------------------------------

    /**
     * Verify that an index is valid, if not, and exception
     * is thrown to stop execution
     *
     * @param index Specified index
     */
    void verifyIndex(int index);

//------------------------------------------------------------------------------

    /**
     * Static Iterator class so that the IndexedDataStructure can be iterated on via
     * the enhance for loop
     *
     * @author Jabari Dash
     * @param <T> Generic type
     */
    class IndexedDataStructureIterator<T> implements Iterator<T> {
        private IndexedDataStructure<T> list;   // List to iterate over
        private int cursor;                     // Cursor to keep track of position in iteration

        /**
         * Constructor, initialize cursor to index 0, and the list
         * variable equal to the passed in list
         *
         * @param list The list to be iterated on
         */
        public IndexedDataStructureIterator(IndexedDataStructure<T> list) {
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
