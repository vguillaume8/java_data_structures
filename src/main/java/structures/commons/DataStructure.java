package structures.commons;

import java.util.Collection;

/**
 * Generic interface for any data structure in this library.
 *
 * @author Jabari Dash
 * @param <K> Generic type of data structure.
 */
public interface DataStructure<K> {

    /**
     * Determines whether or not the DataStructure contains a specified key.
     *
     * @param key Specified key
     * @return True if and only if the specified key is within the Structure
     */
    boolean contains(K key);

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the structure is empty.
     *
     * @return True if and only if there are no elements in the structure.
     */
    default boolean empty() {
        return size() == 0;
    }

//------------------------------------------------------------------------------

    /**
     * Inserts a specified key into the List.
     *
     * @param key The specified key to insert.
     * @return True if and only if the key was successfully inserted.
     */
    boolean insert(K key);

//------------------------------------------------------------------------------

    /**
     * Inserts an array into the data structure.
     *
     * @param keys keys to insert.
     * @return Returns true if and only if all keys were successfully inserted.
     */
    default boolean insert(K[] keys) {
        boolean all = true;

        for (K key : keys)
            all = all && insert(key);

        return all;
    }

//------------------------------------------------------------------------------

    /**
     * Inserts a Java Collection into the data structure.
     *
     * @param values values to insert.
     * @return Returns true if and only if all values were successfully inserted.
     */
    default boolean insert(Collection<K> values) {
        boolean all = true;

        for (K value : values)
            all = all && insert(value);

        return all;
    }

//------------------------------------------------------------------------------

    /**
     * Returns the number of keys in the data structure.
     *
     * @return Number of keys in the DataStructure.
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
