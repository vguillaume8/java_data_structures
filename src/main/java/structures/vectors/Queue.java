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
     *
     * @return
     */
    @Override
    public boolean empty() {

        return size == 0 && head == null;
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
     * @param key The specified key to insert
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
        head = head.next;       // Set head equal to head's next
        size--;                 // Decrement size of Queue

        return value;
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns a String representation of the list
     *
     * @return String version of the list
     */
    @Override
    public String toString() {
        return asString();
    }

    @Override
    public Iterator<K> iterator() {

        return LinkedList.iterator(head);
    }
}