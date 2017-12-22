package structures.vectors;

import structures.vectors.auxiliary.DynamicallySizedArray;

/**
 *
 * @param <T> Generic type
 */
public class ArrayList<T> extends DynamicallySizedArray<T> {

    public ArrayList() {
        this.init();
    }

    public ArrayList(T[] values) {
        this.init(values);
    }

    public ArrayList(int length, T value) {
        this.init(length, value);
    }

    /**
     *
     * @param index Specified index
     * @return
     */
    @Override
    public T get(int index) {
       this.verifyIndex(index);
       return this.values[index];
    }

    /**
     *
     * @param index Index to remove value from
     * @return
     */
    @Override
    public T remove(int index) {
        T value;

        this.verifyIndex(index);        // Verify that the index is valid
        value = this.values[index];     // Store the value are the given index
        this.shiftLeft(index);          // Shift all values up from the right of index over one to the left
        this.decrementSize();           // Decrement size of array
        return value;                   // Return the stored value
    }

    /**
     *
     * @param value The specified value to insert
     */
    @Override
    public void insert(T value) {
        this.insertLast(value);
    }

    /**
     *
     *
     * @param value Value to be inserted
     * @param index Specified index to insert value at
     */
    @Override
    public void insert(T value, int index) {
        this.verifyIndex(index);        // Verify that the index is a valid index
        this.grow();                    // Potentially grow the internal array before insertion
        this.shiftRight(index);         // Shift all values up one index, starting at designated index
        this.values[index] = value;     // Insert the new value into the designated index
        this.incrementSize();           // Increment size of list
    }

    private void shiftRight(int index) {
        this.verifyIndex(index);

        for (int i = values.length-2; i >= index; i--) {
            values[i+1] = values[i];
        }
    }

    private void shiftLeft(int index) {
        this.verifyIndex(index);

        for (int i = index; i < this.size(); i++) {
            values[i] = values[i+1];
        }
    }

    /**
     *
     * @param value Specified value to insert
     */
    @Override
    public void insertFirst(T value) {
        if (this.empty()) {
            this.insertLast(value);
        } else {
            this.insert(value, 0);
        }
    }

    /**
     *
     * @param value Specified value to insert
     */
    @Override
    public void insertLast(T value) {
        this.grow();                        // Potentially grow the internal array if we need space
        this.values[this.size()] = value;   // Append the value to the back of the array
        this.incrementSize();               // Increment the size of the list

    }

    /**
     *
     * @return
     */
    @Override
    public T removeFirst() {
        return this.remove(0);
    }

    /**
     *
     * @return
     */
    @Override
    public T removeLast() {
        return this.remove(this.size() - 1);
    }

    /**
     *
     * @return
     */
    @Override
    public T remove() {
        return this.removeLast();
    }
}