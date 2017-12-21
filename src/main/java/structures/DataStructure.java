package structures;

/**
 * Generic interface for any data structure in this library
 *
 * @author Jabari Dash
 */
public abstract class DataStructure {
    private int size;

    protected void init() {
        this.size = 0;
    }

    /**
     * Determines whether or not the structure is empty
     *
     * @return True if and only if there are no elements in the structure
     */
    public abstract boolean empty();

    /**
     * Decrements the size of the structure by 1
     */
    public void decrementSize() {
        this.size--;
    }

    /**
     * Increments the size of the structure by 1
     */
    public void incrementSize() {
        this.size++;
    }

    /**
     * Returns the size of the structure
     *
     * @return The number of elements in the structure
     */
    public int size() {
        return this.size;
    }
}
