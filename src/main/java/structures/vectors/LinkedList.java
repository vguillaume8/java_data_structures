package structures.vectors;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Basic implementation of Doubly Linked List
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public final class LinkedList<T> implements List<T> {

  private int size;
  private Node<T> head;
  private Node<T> tail;

  /**
   * Constructs empty list.
   */
  public LinkedList() {

  }

//------------------------------------------------------------------------------

  /**
   * Constructs {@code LinkedList} from array of keys.
   *
   * @param values Array of keys to construct the list from
   */
  public LinkedList(T[] values) {
    insert(values);
  }

  /**
   * Determines whether or not the list is empty
   *
   * @return True if and only if there are no elements in the list, otherwise false
   */
  public boolean empty() {

    return size == 0 && head == null;
  }

//------------------------------------------------------------------------------

  /**
   * Returns the value at a specified index in the list
   *
   * @param index The specified to retrieve the value from
   * @return The value of the node at the specified index
   */
  @Override
  public T get(int index) {
    return getNode(index).value;
  }

//------------------------------------------------------------------------------

  /**
   * Returns the node at a specified index in the list.
   *
   * @param index The specified to retrieve the node from
   * @return SinglyLinkedListNode at specified index
   */
  private Node<T> getNode(int index) {
    Node<T> node;

    verifyIndex(index);

    node = head;

    // index up until the
    // specified index (inclusive)
    for (int i = 0; i < index; i++) {
      node = node.next;
    }

    // Otherwise, return the ith node
    return node;
  }

  /**
   * Append a specified value  to the back of the list
   *
   * @param value Specified value to be inserted into the list
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

//------------------------------------------------------------------------------

  /**
   * Inserts a specified value at the front of the list.
   *
   * @param value The specified value to be inserted
   */
  public boolean prepend(T value) {
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
   * Inserts a specified value at the back of the list.
   *
   * @param value The specified value to be inserted
   */
  public boolean append(T value) {
    Node<T> node = new Node<>(value);

    if (empty()) {
      head = node;

    } else {

      node.prev = tail;
      tail.next = node;
      tail = node;
    }

    size++;

    return true;
  }

//------------------------------------------------------------------------------

  /**
   * Insert specified value at specified index in list.
   *
   * @param value Specified value to be inserted into the list
   * @param index Specified index
   */
  public boolean insert(T value, int index) {
    Node<T> newNode;

    // Create a new node with
    // the value to be inserted
    newNode = new Node<>(value);

    // If the list is empty,
    // set new node to the head
    if (this.empty()) {
      head = newNode;

      // The list is not empty
    } else if (index == 0) {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;

    } else {

      // If the index is somewhere in the middle,
      // Find the ith node, and put
      // the new node right in front of it
      // (essentially, taking its index)
      Node<T> oldNode;

      oldNode = getNode(index);             // Get the ith node
      newNode.prev = oldNode.prev;          // Set new node points, to that of older node
      newNode.next = oldNode;               // Set new nodes' next to ith node

      // Set the i-1th node's next to the new node
      if (oldNode.prev != null)
        oldNode.prev.next = newNode;

      oldNode.prev = newNode;               // Set the old ith node's previous to the new node

//      node = getNode(index-1);

    }


    this.size++;

    return true;
  }

  @Override
  public Iterator<T> iterator() {

    return iterator(head);
  }

  /**
   *
   * @param head
   * @return
   */
  protected static <T> Iterator<T> iterator(Node<T> head) {

    return new Iterator<T>() {

      Node<T> node = head;

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
      public T next() {
        // If there are no more keys left, throw an Exception
        if (!hasNext()) {
          throw new NoSuchElementException("No element");
        }

        T value = node.value;
        node = node.next;

        return value;
      }
    };
  }

//------------------------------------------------------------------------------

  /**
   * Removes a node at a specified index from the list.
   *
   * @param index Index of specified node to remove
   * @return Value of node at specified index
   */
  public T remove(int index) {
    T value;

    if (this.empty()) {
      throw new EmptyDataStructureException("Cannot remove from an empty LinkedList");
    }

    // Verify that the index is
    // within the bounds of the list
    verifyIndex(index);

    // Removing form the front
    if (index == 0 || size == 1) {
      value = head.value;
      head = head.next;

    }

//    else if (index == size - 1) {
//      value = tail.value;
//      tail = tail.next;
//
//    }

    else {

      // Get the ith node and its value
      Node<T> node = getNode(index);
      value = node.value;

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

//------------------------------------------------------------------------------

  /**
   * Removes the last value in the list.
   *
   * @return Value of node at specified index
   */
  public T remove() {
    return remove(size-1);
  }

//------------------------------------------------------------------------------

  /**
   * Removes the first value in the list.
   *
   * @return Value of node at specified index
   */
  public T removeFirst() {
    if (empty()) {

    }

    return remove(0);
  }

//------------------------------------------------------------------------------

  /**
   * Removes the last value in the list.
   *
   * @return Value of node at specified index
   */
  public T removeLast() {
    return remove();
  }

  /**
   *
   * @return
   */
  @Override
  public int size() {
    return size;
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

  /**
   * Overwrites a value at a specified index
   * with a new value.
   *
   * @param value
   * @param index
   */
  public void update(T value, int index) {

    getNode(index).value = value;
  }

  /**
   * Node class for chaining together keys in a LinkedList
   *
   * @author Jabari Dash
   * @param <T> Generic type
   */
  public static class Node<T> {
    protected T value;        // Value of the node
    protected Node<T> prev;   // Pointer to previous node in chain
    protected Node<T> next;   // Pointer to next node in chain

    /**
     * Constructs a new {@code Node} with a specified value.
     *
     * @param value Specified value of Node
     */
    protected Node(T value) {
      this.value = value;
      this.prev = null;
      this.next = null;
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
