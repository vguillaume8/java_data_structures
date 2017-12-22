package structures.auxiliary;

/**
 * Generic interface for any data structure in this library
 *
 * @author Jabari Dash
 */
public interface DataStructure<T> {

    /**
     *
     * @param value
     * @return
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
     * Inserts a specified value into the IndexedDataStructure
     *
     * @param value The specified value to insert
     */
    void insert(T value);

//------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    int size();

}
