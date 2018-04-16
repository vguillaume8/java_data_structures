package structures.vectors;

import structures.vectors.LinkedList.Node;

import java.util.Collection;
import java.util.Iterator;

/**
 *  Basic implementation of a LinkedStack class using a Linked List.
 *
 *  All basic operations are O(1), except for
 *  contains() O(n), which we shouldn't be using with
 *  a Stack anyways.
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public final class LinkedStack<T> implements Stack<T>, Vector<T> {

    /**
     * Number of elements on the LinkedStack
     */
    protected int size;

    /**
     * Pointer to the first node in the chain
     */
    private Node<T> head;

//------------------------------------------------------------------------------

    /**
     * Constructs empty LinkedStack
     */
    public LinkedStack() {
        super();
    }

//------------------------------------------------------------------------------

    /**
     * Constructs {@code LinkedStack} from array of values.
     *
     * @param values Array of values to instantiate LinkedStack from.
     */
    public LinkedStack(T[] values) {
        insert(values);
    }

//------------------------------------------------------------------------------

    /**
     * Construct stack from Java Collection of values.
     *
     * @param values Collection of values to construct LinkedStack from.
     * @see java.util.Collection
     */
    public LinkedStack(Collection<T> values) {
        insert(values);
    }

//------------------------------------------------------------------------------

    /**
     * Determines whether or not this LinkedStack is equal to
     * a provided object.
     *
     * @param object Object to compare this LinkedStack with.
     * @return True if and only if their types are the same,
     * lengths are the same, and the contain all the same elements.
     */
    public boolean equals(Object object) {
        return equivalentTo(object);
    }

//------------------------------------------------------------------------------


    /**
     * Returns an Iterator for iterating over the LinkedStack.
     *
     * @return Iterator object
     */
    @Override
    public Iterator<T> iterator() {

        // Need to get the head node
        return LinkedList.iterator(head);
    }
//------------------------------------------------------------------------------

    /**
     * Inserts value at top of LinkedStack.
     *
     * @param value The specified value to insert
     * @return True if the insertion was successful.
     */
    @Override
    public boolean insert(T value) {
        Node<T> node = new Node<>(value);

        if (this.empty()) {
            head = node;

        } else {
            node.next = head;
            head.prev = node;
            head      = node;
        }

        this.size++;

        return true;
    }


//------------------------------------------------------------------------------

    /**
     * Retrieves and removes top of LinkedStack.
     *
     * @return Value form top of LinkedStack
     */
    @Override
    public T remove() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot remove from an empty LinkedStack");
        }

        T value   = this.head.value;  // Get value from head
        this.head = this.head.next;   // Set head equal to head's next
        this.size--;                  // Decrement size of LinkedQueue

        return value;
    }

    /**
     * Returns number of elements on the stack.
     *
     * @return Number of elements on stack.
     */
    @Override
    public int size() {
        return size;
    }

//------------------------------------------------------------------------------


    /**
     * Retrieves but does not remove the topmost value from the LinkedStack.
     *
     * @return The topmost value on the LinkedStack.
     */
    @Override
    public T top() {
        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot top() and empty LinkedStack");
        }

        return this.head.value;
    }

//------------------------------------------------------------------------------

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