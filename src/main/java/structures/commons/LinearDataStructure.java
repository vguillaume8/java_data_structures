package structures.commons;

import structures.commons.DataStructure;

public interface LinearDataStructure<T> extends DataStructure<T>,  Iterable<T> {


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

    T[] toArray(T[] array);
}
