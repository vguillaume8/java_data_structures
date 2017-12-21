package structures.auxiliary;

/**
 * DoublyLinkedListNode class for chaining together values in a DoublyLinkedList
 */
public class DoublyLinkedListNode<T> extends LinkedListNode<T> {
    private DoublyLinkedListNode<T> prev;   // Pointer to previous node in chain
    private DoublyLinkedListNode<T> next;   // Pointer to next node in chain

//------------------------------------------------------------------------------

    /**
     * Constructs a new node with a specified value, and pointers to its
     * previous and its next nodes
     *
     * @param value The specified value of teh node
     * @param prev Pointer to the node that comes before this node in the chain
     * @param next Pointer to the node that comes after this node in the chain
     */
    public DoublyLinkedListNode(T value, DoublyLinkedListNode<T> prev, DoublyLinkedListNode<T> next) {
        this.value(value);
        this.prev(prev);
        this.next(next);
    }

//------------------------------------------------------------------------------

    /**
     * Inserts a data value into the chained nodes
     *
     * @param value Specified value to be inserted into the chain
     */
    public void insert(T value) {

        // If the following node is empty
        // we have reached the end of the list
        if (this.next() == null) {
            this.next(new DoublyLinkedListNode<T>(value, this, null));

            // Otherwise, we must recurse to the end
        } else {
            this.next().insert(value);
        }
    }

//------------------------------------------------------------------------------

    /**
     * Returns the pointer to the next node that follows this node
     *
     * @return Pointer to the following node
     */
    public DoublyLinkedListNode<T> next() {
        return this.next;
    }

//------------------------------------------------------------------------------

    /**
     * Sets the pointer to the next node
     *
     * @param next Designated node to be the next of this node
     */
    public void next(DoublyLinkedListNode<T> next) {
        this.next = next;
    }

//------------------------------------------------------------------------------

    /**
     * Returns the pointer to the previous node that precedes this node
     *
     * @return Pointer to the previous node
     */
    public DoublyLinkedListNode<T> prev() {
        return this.prev;
    }

//------------------------------------------------------------------------------

    /**
     * Sets the pointer to the prev node
     *
     * @param prev Designated node to be the prev of this node
     */
    public void prev(DoublyLinkedListNode<T> prev) {
        this.prev = prev;
    }
}