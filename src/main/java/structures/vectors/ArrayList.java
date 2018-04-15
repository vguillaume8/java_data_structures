package structures.vectors;

import structures.commons.DynamicArray;

import java.util.Collection;

/**
 * Basic implementation of a generic ArrayList.
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public final class ArrayList<E> extends DynamicArray<E> implements List<E> {

    /**
     * Constructs empty list. The default capacity
     * of the ArrayList is 10.
     */
    public ArrayList() {

        super();
    }

    /**
     * Instantiates an ArrayList with an internal array of
     * a specified length. This constructor should be used
     * when the number of elements that the ArrayList will
     * contain is known beforehand. This avoids the dynamic memory
     * re-allocations required to continuously make space for
     * new elements. If a length smaller than the default
     * capacity (10) is passed, the ArrayList's capacity will
     * default to 10.
     *
     * @param length Specific initial capacity of ArrayList.
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int length) {

        super(length);
    }

    /**
     * Constructs ArrayList from array of elements.
     *
     * @param values Array of elements to construct the list from
     */
    public ArrayList(E[] values) {

        super(values);
    }

    /**
     * Construct array list from Java Collection of values.
     *
     * @param values Collection of values to construct list from.
     * @see java.util.Collection
     */
    public ArrayList(Collection<E> values) {

        super(values);
    }

    /**
     * Returns the value at a specified index.
     *
     * @param index Specified index.
     * @return Value at specified index.
     */
    @Override
    public E get(int index) {

       return access(index);
    }

    /**
     * Inserts a value at the end of the list.
     *
     * @param value The specified key to insert
     * @return True to indicate the insertion was successful.
     */
    @Override
    public boolean insert(E value) {

        return append(value);
    }

    /**
     * Inserts a value at a specified index.
     *
     * @param value Value to be inserted
     * @param index Specified index to insert value at
     * @return True to indicate the insertion was successful.
     */
    @Override
    public boolean insert(E value, int index) {

       return add(value, index);
    }

    /**
     * Retrieves and removes the value at a specified index.
     *
     * @param index Index to remove value from.
     * @return Value at specified index.
     */
    @Override
    public E remove(int index) {

        return delete(index);
    }

    /**
     * Overwrites a value at a specified index
     * with a new value.
     *
     * @param value New value.
     * @param index Specified index.
     */
    public void set(E value, int index) {

        update(index, value);
    }

    /**
     *
     * @return
     */
    public String toString() {

        return asString();
    }

}