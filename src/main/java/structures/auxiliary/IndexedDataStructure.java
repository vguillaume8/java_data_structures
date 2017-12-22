package structures.auxiliary;

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
     * @return
     */
    T removeFirst();

//------------------------------------------------------------------------------

    /**
     * Removes the last value from the IndexedDataStructure
     *
     * @return
     */
    T removeLast();

//------------------------------------------------------------------------------

    /**
     * Static Iterator class so that the IndexedDataStructure can be iterated on via
     * the enhance for loop
     *
     * @author Jabari Dash
     * @param <T> Generic type
     */
    class VectorIterator<T> implements Iterator<T> {
        private IndexedDataStructure<T> list;   // List to iterate over
        private int cursor;           // Cursor to keep track of position in iteration

        /**
         * Constructor, initialize cursor to index 0, and the list
         * variable equal to the passed in list
         *
         * @param list The list to be iterated on
         */
        public VectorIterator(IndexedDataStructure<T> list) {
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
