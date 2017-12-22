package structures.auxiliary;

/**
 * All dynamically sized data types should extend this class
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public abstract class DynamicallySizedDataStructure<T> implements DataStructure<T> {

    private int size;   // Tracks the number of values in the structure

//------------------------------------------------------------------------------

    /**
     * Determines whether or not the data structure is empty
     *
     * @return True if and only if the size of the structure is 0
     */
    public boolean empty() {
        return this.size() == 0 ? true : false;
    }

//------------------------------------------------------------------------------

    /**
     * Initializes the structure to size 0
     */
    public void init() {
        this.size = 0;
    }

//------------------------------------------------------------------------------

    /**
     * Initialies the structure to a specified size
     *
     * @param size Specified size
     */
    public void init(int size) {
        this.incrementSize(size);
    }

//------------------------------------------------------------------------------

    /**
     * Decrements the size of the structure by 1
     */
    protected void decrementSize() {
        this.decrementSize(1);
    }

//------------------------------------------------------------------------------

    /**
     * Decrements the size of the structure by a specified amount
     *
     * @param decrements Number of times to decrement
     */
    protected void decrementSize(int decrements) {
        while (decrements > 0) {
            this.size--;
            decrements--;
        }
    }

//------------------------------------------------------------------------------

    /**
     * Increments the size of the structure by 1
     */
    protected void incrementSize() {
        this.incrementSize(1);
    }

//------------------------------------------------------------------------------

    /**
     * Increments the size of the structure by a specified amount
     *
     * @param increments Number of times to increment
     */
    protected void incrementSize(int increments) {
        while (increments > 0) {
            this.size++;
            increments--;
        }
    }

//------------------------------------------------------------------------------

    /**
     * Returns the size of the structure
     *
     * @return The number of elements in the structure
     */
    public int size() {
        return this.size;
    }
}
