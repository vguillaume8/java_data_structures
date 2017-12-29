package structures.vectors;

import structures.vectors.LinkedList.Node;

import java.util.Iterator;

/**
 * Basic FIFO Queue implementation
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public final class Queue<T> implements Vector<T> {

    /**
     *
     */
    private int size;

    /**
     * Pointer to the first node in the chain
     */
    private Node<T> head;

    /**
     * Pointer to the last node in the chain
     *
     * TODO - Use the tail in implementing classes such as Linkedlist so insert last is O(1)
     */
    private Node<T> tail;

    /**
     * Constructs empty Queue.
     */
    public Queue() {
        super();
    }

    /**
     * Constructs Queue from array of keys.
     *
     * @param values Array of keys to instatiate Queue from
     */
    public Queue(T[] values) {
        insert(values);
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
    public T peek() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot peek() empty Queue");
        }

        return head.value;
    }

    /**
     * Inserts value at back of Queue.
     *
     * @param value The specified value to insert
     */
    @Override
    public boolean insert(T value) {
        Node<T> node = new Node<>(value);

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
    public T remove() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot remove() from empty Queue");
        }

        T value = head.value;   // Get value from head
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
    public Iterator<T> iterator() {

        return LinkedList.iterator(head);
    }
}