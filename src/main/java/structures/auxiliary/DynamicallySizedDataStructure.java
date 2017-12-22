package structures.auxiliary;

/**
 * All dynamically sized data types should extend this class
 *
 * @param <T> Generic type
 */
public abstract class DynamicallySizedDataStructure<T> implements DataStructure<T> {

    private int size;   // Tracks the number of values in the structure

//------------------------------------------------------------------------------

    /**
     *
     * @return
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

    /**
     *
     * @param size
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
