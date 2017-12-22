package structures.auxiliary;

/**
 * Generic interface for any data structure in this library
 *
 * @author Jabari Dash
 */
public interface DataStructure<T> {

    /**
     * Determines whether or not the DataStructure has a contains a specified value
     *
     * @param value Specified value
     * @return True if and only if the specified value is within the Structure
     */
    boolean contains(T value);

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the structure is empty
     *
     * @return True if and only if there are no elements in the structure
     */
    boolean empty();

//------------------------------------------------------------------------------

    /**
     * Initializes the data structure
     */
    void init();

//------------------------------------------------------------------------------

    /**
     * Inserts a specified value into the IndexedDataStructure
     *
     * @param value The specified value to insert
     */
    void insert(T value);

//------------------------------------------------------------------------------

    /**
     * Removes an element from the DataStructure. The order
     * in which the value comes out depends on the implementation
     * of implementing class
     *
     * @return Removed value from DataStructure
     */
    T remove();

//------------------------------------------------------------------------------

    /**
     * Returns the number of values in the data structure
     *
     * @return Number of values in the DataStructure
     */
    int size();

}
