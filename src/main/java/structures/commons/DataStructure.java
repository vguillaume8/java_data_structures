package structures.commons;

import java.util.Collection;

/**
 * Generic interface for any data structure in this library.
 *
 * @author Jabari Dash
 * @param <K> Generic type of data structure.
 */
public interface DataStructure<K> {

    // TODO - https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html
    // Check out making methods synchronized to avoid
    // concurrent modification issues if the objects
    // are used across multiple threads

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

    /**
     * Determines whether or not an Object is equal to
     * this {@code Vector}. Implementing classes can
     * override the java.lang.Object.equals() method, and
     * call this.
     *
     * @param object Object to compare this object with.
     * @return True if the objects are of the same type, their
     * sizes are the same, and they contain all the same keys.
     */
    default boolean equivalentTo(Object object) {


        // If we made it to the bottom,
        // the objects are equal
        return sameType(object) && sameContents(object);
    }

//------------------------------------------------------------------------------

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
     * Checks that two DataStructures have
     * the same contents.
     *
     * @param object Object to compare.
     * @return True if the two objects have the same contents.
     */
    default boolean sameContents(Object object) {

        // TODO - Placeholder
//
//        if (true) {
//            throw new RuntimeException("Method not implemented");
//        }

        return true;
    }

//------------------------------------------------------------------------------

    /**
     * Determines at runtime via reflection whether
     * or not two implementing classes are of the
     * same type. Example, two arraylists that implement
     * DataStructure will return true for this. But
     * a LinkedList and an ArrayList will return false,
     * even though they both implement the interface List.
     *
     * @param object Object to compare to.
     * @return True if and only if the objects are of the exact same type.
     */
    default boolean sameType(Object object) {

        // If object is compared to itself
        if (this == object)
            return true;

        // Check that the object is an
        // instance of the same type of
        // object of the object calling
        // this method
        try {

            if (!Class.forName(this.getClass().getName()).isInstance(object)) {
                return false;
            }

        } catch (ClassNotFoundException e) {
            return false;
        }

        return true;
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
     * Verifies if a provided index is within the DataStructure or not
     *
     * @param index Specified index to verify
     * @throws IndexOutOfBoundsException Exception thrown if the index is invalid
     */
    default void verifyIndex(int index) {
        if (empty()) {
            throw new EmptyDataStructureException("Cannot verify index on empty data structure");
        }

        if (indexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("size: " + this.size() + " index: " + index);
        }
    }

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
