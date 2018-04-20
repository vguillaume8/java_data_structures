package structures.vectors;

import structures.commons.DataStructure;
import structures.commons.LinkedStructure;
import structures.commons.LinkedStructure.Node;

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
public final class LinkedStack<T> extends LinkedStructure<T> implements Stack<T>, Vector<T> {

    /**
     * Constructs empty stack
     */
    public LinkedStack() {
        ;
    }

    /**
     * Construct stack from array of values
     *
     * @param values Array of values
     */
    public LinkedStack(T[] values) {
        super(values);
    }

    /**
     * Construct stack from collection of values
     *
     * @param values Collection of values
     */
    public LinkedStack(Collection<T> values) {
        super(values);
    }

    /**
     * Inserts a value at the top of stack.
     *
     * @param value Specified value to be inserted into the list
     * @return True if insertion was successful
     */
    @Override
    public boolean insert(T value) {

        // NOTE - This method is overridden because
        // because default methods in the interface
        // calls the insert method, which by default
        // inserts into the back of the LinkedStructure.
        // To reverse the order, we overwrite and insert
        // in the front.

        return insertHead(value);
    }

    /**
     * Retrieves but does not remove the topmost value from the LinkedStack.
     *
     * @return The topmost value on the LinkedStack.
     */
    @Override
    public T top() {

        return getNode(0).value;
    }

    /**
     * Removes an element from the DataStructure. The order
     * in which the value comes out depends on the implementation
     * of implementing class
     *
     * @return Removed value from DataStructure
     * @see DataStructure
     */
    @Override
    public T remove() {
        return delete(0);
    }
}