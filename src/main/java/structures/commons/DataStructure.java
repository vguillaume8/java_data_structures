package structures.commons;

/**
 * Generic interface for any data structure in this library.
 *
 * @author Jabari Dash
 * @param <T> Generic type of data structure.
 */
public interface DataStructure<T> {

    /**
     * Determines whether or not the DataStructure has a contains a specified value.
     *
     * @param value Specified value
     * @return True if and only if the specified value is within the Structure
     */
    boolean contains(T value);

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the structure is empty.
     *
     * @return True if and only if there are no elements in the structure.
     */
    boolean empty();

//------------------------------------------------------------------------------

    /**
     * Inserts a specified value into the IndexedDataStructure.
     *
     * @param value The specified value to insert.
     * @return True if and only if the value was successfully inserted.
     */
    boolean insert(T value);

//------------------------------------------------------------------------------

    /**
     * Returns the number of values in the data structure.
     *
     * @return Number of values in the DataStructure.
     */
    int size();

//------------------------------------------------------------------------------

    /**
     * Exception that is throw when an operation takes places that
     * requires the DataStructure to be non-empty, but its empty.
     *
     * @author Jabari Dash
     */
    class EmptyDataStructureException extends RuntimeException {

        /**
         * Call superclass constructor.
         *
         * @param message Message to be printed to Standard Error.
         */
        public EmptyDataStructureException(String message) {
            super(message);
        }
    }

}
