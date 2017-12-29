package structures.vectors;

import structures.vectors.LinkedList.Node;
import java.util.Iterator;

/**
 *  Basic implementation of a Stack class using a Linked List.
 *
 *  All basic operations are O(n), except for
 *  contains() O(n), which we shouldn't be using with
 *  a Stack anyways.
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public final class Stack<T>  implements Vector<T> {

    /**
     *
     */
    private int size;

    /**
     * Pointer to the first node in the chain
     */
    private Node<T> head;

    /**
     * Constructs empty Stack
     */
    public Stack() {
        super();
    }

//------------------------------------------------------------------------------

    /**
     * Constructs Queue from array of values.
     *
     * @param values Array of values to instatiate Queue from
     */
    public Stack(T[] values) {
        insert(values);
    }


    @Override
    public Iterator<T> iterator() {

        // Need to get the head node

        return LinkedList.iterator(head);
    }
//------------------------------------------------------------------------------

    /**
     * Inserts value at top of Stack.
     *
     * @param value The specified value to insert
     */
    @Override
    public boolean insert(T value) {
        Node<T> node = new Node<>(value);

        if (this.empty()) {
            head = node;

        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }

        this.size++;

        return true;
    }

//------------------------------------------------------------------------------

    /**
     * Retrieves and removes top of Stack.
     *
     * @return The topmost value on the Stack
     *
     * @return Value form top of Stack
     */
    @Override
    public T remove() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot remove from an empty Stack");
        }

        T value = this.head.value;  // Get value from head
        this.head = this.head.next;  // Set head equal to head's next
        this.size--;           // Decrement size of Queue

        return value;
    }

//------------------------------------------------------------------------------

    /**
     * Pushes a specified value onto the Stack.
     *
     * @param value Specified value to be pushed onto the Stack
     */
    public void push(T value) {
        this.insert(value);
    }

//------------------------------------------------------------------------------

    /**
     * Retrieves and removes the topmost value from the Stack.
     *
     * @return The topmost value on the Stack
     */
    public T pop() {
        return this.remove();
    }

//------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Retrieves but does not remove the topmost value from the Stack.
     *
     *
     * @return The topmost value on the Stack
     */
    public T top() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot top() and empty Stack");
        }

        return this.head.value;
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
}