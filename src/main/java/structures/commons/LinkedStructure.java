package structures.commons;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Abstract class that all linked
 * structures such as linked lists,
 * linked queues, etc will extend.
 *
 * @author Jabari Dash
 * @param <E> Generic type
 */
public abstract class LinkedStructure<E> implements DataStructure<E>, Iterable<E> {

    /**
     * Number of elements in linked list
     */
    protected int size;

    /**
     * Pointer to first node in list
     */
    private Node<E> head;

    /**
     * Pointer to last node in list
     */
    private Node<E> tail;

    /**
     * Constructs empty LinkedStructure.
     */
    protected LinkedStructure() {
        ;
    }

    /**
     * Constructs LinkedStructure from an
     * array of values.
     *
     * @param values Array of values to insert.
     */
    protected LinkedStructure(E[] values) {
        insert(values);
    }

    /**
     * Constructs a LinkedStructure from a
     * collection of values.
     *
     * @param values Collection of values to insert.
     */
    protected LinkedStructure(Collection<E> values) {
        insert(values);
    }

    /**
     * Removes and returns the value of
     * a node at a specified index.
     *
     * @param index Specified index
     * @return Value of node at that index.
     */
    protected E delete(int index) {

        E value;

        if (this.empty()) {
            throw new EmptyDataStructureException("Cannot remove from an empty LinkedList");
        }

        // Verify that the index is
        // within the bounds of the list
        verifyIndex(index);

        // Removing form the front
        if (index == 0 || size == 1) {
            value = head.value;
            head  = head.next;

        }

        // TODO - Figure out why I commented this out
        // Delete last should be O(1), not O(n)
//    else if (index == size - 1) {
//      value = tail.value;
//      tail = tail.next;
//
//    }

        else {

            // Get the ith node and its value
            Node<E> node = getNode(index);
            value        = node.value;

            // Set the node at i-1's next to node at i+1
            // Essentially skipping over the node at i
            node.prev.next = node.next;

            // If node at i is not the end node
            // link the following nodes in the chain
            if (node.next != null) {

                // Set i+1's previous to i's previous, again
                // skipping right over the node at i itself
                node.next.prev = node.prev;
            }

        }

        // After removing a node,
        // decrement length of list
        this.size--;

        return value;
    }

    /**
     * Determines whether or not this LinkedList is equal to
     * a provided object.
     *
     * @param object Object to compare this LinkedList with.
     * @return True if and only if their types are the same,
     * lengths are the same, and the contain all the same elements.
     */
    @Override
    public boolean equals(Object object) {

        // Object must be an LinkedList, and all elements must be equal, or object
        // must be this LinkedList itself
        return equivalentTo(object);
    }

    /**
     * Returns the node at a specified index in the list.
     *
     * @param index The specified to retrieve the node from
     * @return SinglyLinkedListNode at specified index
     */
    protected Node<E> getNode(int index) {
        Node<E> node;

        verifyIndex(index);

        // Search from left to right
        if (index < size / 2) {

            node = head;

            for (int i = 0; i < index; i++) {
                node = node.next;
            }

        // Search from left to right
        } else {
            node = tail;

            // Search from right to left
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }

        }

        return node;
    }


    /**
     * Append a specified value  to the back of the list
     *
     * @param value Specified value to be inserted into the list
     * @return True to indicate the insertion was successful.
     */
    @Override
    public boolean insert(E value) {

        return insertTail(value);
    }

    /**
     * Prepends a new value to the front
     * of the structure.
     *
     * @param value New head value
     * @return True if insertion is successful.
     */
    protected boolean insertHead(E value) {
        Node<E> node = new Node<E>(value);

        if (empty()) {

            head = node;
            tail = node;

        } else {

            node.next = head;
            head.prev = node;
            head      = node;
        }

        size++;

        return true;
    }

    /**
     * Inserts a new value at a specified
     * index.
     *
     * @param value New value
     * @param index Specified index
     * @return True if the insertion is successful
     */
    protected boolean insertMiddle(E value, int index) {
        Node<E> newNode = new Node<>(value);

        if (this.empty()) {

            head = newNode;
            tail = newNode;

            // If removing from the front
        } else if (index == 0) {

            newNode.next = head;
            head.prev    = newNode;
            head         = newNode;

        } else {

            // If the index is somewhere in the middle,
            // Find the ith node, and put
            // the new node right in front of it
            // (essentially, taking its index)
            Node<E> oldNode;

            oldNode      = getNode(index);        // Get the ith node
            newNode.prev = oldNode.prev;          // Set new node points, to that of older node
            newNode.next = oldNode;               // Set new nodes' next to ith node

            // Set the i-1th node's next to the new node
            if (oldNode.prev != null) {
                oldNode.prev.next = newNode;
            }

            // Set the old ith node's
            // previous to the new node
            oldNode.prev = newNode;

        }

        size++;

        return true;
    }

    /**
     * Appends a new value to the back
     * of the structure.
     *
     * @param value New tail value
     * @return True if the insertion is successful.
     */
    protected boolean insertTail(E value) {
        Node<E> node = new Node<E>(value);

        if (empty()) {

            head = node;
            tail = node;

        } else {

            node.prev = tail;
            tail.next = node;
            tail      = node;
        }

        size++;

        return true;
    }

    /**
     * Returns an Iterator to iterate over the list.
     *
     * @return Iterator object.
     */
    @Override
    public Iterator<E> iterator() {

        return iterator(head);
    }

    /**
     * Provided the head node of any LinkedList,
     * this method returns an Iterator. This method is used
     * by instances of linked objects such as Queues, and Stacks.
     * That a similar function does not need to be implemented
     * multiple times.
     *
     * @param head The first node in the list.
     * @return Iterator object.
     */
    public static <E> Iterator<E> iterator(Node<E> head) {

        return new Iterator<E>() {

            Node<E> node = head;

            /**
             * Determines whether or not there are more Nodes
             * to iterate over
             *
             * @return True if and only if there are more nodes in the chain
             */
            @Override
            public boolean hasNext() {

                // If there is a node left to print, return false
                // if the node is null, we have traversed off the list
                return this.node != null;
            }

            /**
             * Returns the value of the next Node in the chain
             *
             * @return Value of the next Node
             */
            @Override
            public E next() {
                // If there are no more keys left, throw an Exception
                if (!hasNext()) {
                    throw new NoSuchElementException("No element");
                }

                E value = node.value;
                node = node.next;

                return value;
            }
        };
    }

//------------------------------------------------------------------------------

    /**
     * Updates the value of a node
     * at a specified index.
     *
     * @param value New value
     * @param index Specified index
     */
    protected void setNode(E value, int index) {

        getNode(index).value = value;
    }

//------------------------------------------------------------------------------

    /**
     * Returns the number of elements in the list.
     *
     * @return Number of elements in list
     */
    @Override
    public int size() {
        return size;
    }

//------------------------------------------------------------------------------

    /**
     * Returns a String representation
     * of the structure.
     *
     * @return String representation
     */
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");

        for (E value : this) {
            sj.add(value.toString());
        }

        return sj.toString();
    }

//------------------------------------------------------------------------------

    /**
     * Node class for chaining together keys in a LinkedList
     *
     * @author Jabari Dash
     * @param <T> Generic type
     */
    public static class Node<T> {
        public T       value;  // Value of the node
        public Node<T> prev;   // Pointer to previous node in chain
        public Node<T> next;   // Pointer to next node in chain

        /**
         * Constructs a new {@code Node} with a specified value.
         *
         * @param value Specified value of Node
         */
        public Node(T value) {

            this.value = value;
            this.prev  = null;
            this.next  = null;
        }

        /**
         * Returns a String representation of the node
         *
         * @return String version of the node's value
         */
        @Override
        public String toString() {

            return value == null ? "null" : value.toString();
        }

    }
}
