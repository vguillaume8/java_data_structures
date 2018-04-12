package structures.vectors;

import structures.vectors.LinkedList.Node;

import java.util.Collection;
import java.util.Iterator;

/**
 * Basic FIFO LinkedQueue implementation.
 *
 * @author Jabari Dash
 * @param <K> Generic type
 */
public final class LinkedQueue<K> implements Vector<K> {

    /**
     * Number of elements in the LinkedQueue
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
     * Constructs empty LinkedQueue.
     */
    public LinkedQueue() {
        super();
    }

    /**
     * Constructs LinkedQueue from array of keys.
     *
     * @param keys Array of keys to instatiate LinkedQueue from
     */
    public LinkedQueue(K[] keys) {
        insert(keys);
    }

    //------------------------------------------------------------------------------

    /**
     * Construct queue from Java Collection of values.
     *
     * @param values Collection of values to construct LinkedQueue from.
     * @see java.util.Collection
     */
    public LinkedQueue(Collection<K> values) {
        insert(values);
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
     * Determines whether or not this LinkedQueue is equal to
     * a provided object.
     *
     * @param object Object to compare this LinkedQueue with.
     * @return True if and only if their types are the same,
     * lengths are the same, and the contain all the same elements.
     */
    @Override
    public boolean equals(Object object) {

        // Object must be an LinkedQueue, and all elements must be equal, or object
        // must be this LinkedQueue itself
        return this == object || (object instanceof LinkedQueue && equivalentTo(object));
    }

    /**
     * Retrieves, but does not remove the front-most
     * element in the LinkedQueue.
     *
     * @return Front-most value from LinkedQueue
     */
    public K peek() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot peek() empty LinkedQueue");
        }

        return head.value;
    }

    /**
     * Inserts key at back of LinkedQueue.
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
            tail      = node;
        }

        this.size++;

        return true;
    }

    /**
     * Retrieve and remove front-most value from LinkedQueue.
     *
     * @return Front most value from LinkedQueue
     */
    @Override
    public K remove() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot remove() from empty LinkedQueue");
        }

        K value = head.value;   // Get value from head
        head    = head.next;    // Set head equal to head's next
        size--;                 // Decrement size of LinkedQueue

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
     * Returns an Iterator for iterating over the LinkedQueue.
     *
     * @return Iterator object
     */
    @Override
    public Iterator<K> iterator() {

        return LinkedList.iterator(head);
    }
}