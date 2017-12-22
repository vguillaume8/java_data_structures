package structures.vectors.auxiliary;

import structures.auxiliary.DataStructure;

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
}