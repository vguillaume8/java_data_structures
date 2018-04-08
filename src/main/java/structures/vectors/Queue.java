package structures.vectors;

import structures.vectors.LinkedList.Node;
import java.util.Iterator;

/**
 * Basic FIFO Queue implementation.
 *
 * @author Jabari Dash
 * @param <K> Generic type
 */
public final class Queue<K> implements Vector<K> {

    /**
     * Number of elements in the Queue
     */
    private int size;

    /**
     * Reference to the first node in the chain
     */
    private Node<K> head;

    /**
     * Pointer to the last node in the chain
     *
     */
    private Node<K> tail;

    /**
     * Constructs empty Queue.
     */
    public Queue() {
        super();
    }

    /**
     * Constructs Queue from array of keys.
     *
     * @param keys Array of keys to instatiate Queue from
     */
    public Queue(K[] keys) {
        insert(keys);
    }

    /**
     * Determines whether on not the queue is empty.
     *
     * @return True if and only if the queue contains zero elements, otherwise false.
     */
    @Override
    public boolean empty() {

        return size == 0 && head == null;
    }

    /**
     * Determines whether or not this Queue is equal to
     * a provided object.
     *
     * @param object Object to compare this Queue with.
     * @return True if and only if their types are the same,
     * lengths are the same, and the contain all the same elements.
     */
    @Override
    public boolean equals(Object object) {

        // Object must be an Queue, and all elements must be equal, or object
        // must be this Queue itself
        return this == object || (object instanceof Queue && equivalentTo(object));
    }

    /**
     * Retrieves, but does not remove the front-most
     * element in the Queue.
     *
     * @return Front-most value from Queue
     */
    public K peek() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot peek() empty Queue");
        }

        return head.value;
    }

    /**
     * Inserts key at back of Queue.
     *
     * @param key The specified key to insert.
     */
    @Override
    public boolean insert(K key) {
        Node<K> node = new Node<>(key);

        if (empty()) {
            head = node;
            tail = node;

        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }

        this.size++;

        return true;
    }

    /**
     * Retrieve and remove front-most value from Queue.
     *
     * @return Front most value from Queue
     */
    @Override
    public K remove() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot remove() from empty Queue");
        }

        K value = head.value;   // Get value from head
        head    = head.next;    // Set head equal to head's next
        size--;                 // Decrement size of Queue

        return value;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return Number of element in queue.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns a String representation of the queue
     *
     * @return String version of the list
     */
    @Override
    public String toString() {
        return asString();
    }

    /**
     * Returns an Iterator for iterating over the Queue.
     *
     * @return Iterator object
     */
    @Override
    public Iterator<K> iterator() {

        return LinkedList.iterator(head);
    }
}