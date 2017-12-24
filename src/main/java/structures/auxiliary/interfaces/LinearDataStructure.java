package structures.auxiliary.interfaces;

public interface LinearDataStructure<T> extends DataStructure<T>,  Iterable<T> {

    /**
     * Initializes the IndexedDataStructure with a specified array of values
     *
     * @param values Values to be inserted into the IndexedDataStructure
     */
    void init(T[] values);

    /**
     * Initializes the IndexedDataStructure with a specified length and default value
     *
     * @param length Specified length of IndexedDataStructure
     * @param value Specified default value
     */
    void init(int length, T value);

//------------------------------------------------------------------------------

    /**
     * Removes an element from the DataStructure. The order
     * in which the value comes out depends on the implementation
     * of implementing class
     *
     * @return Removed value from DataStructure
     */
    T remove();

    /**
     * Returns the data structure as an array
     *
     * @return Array representation of DataStructure
     */
    T[] toArray();
}
