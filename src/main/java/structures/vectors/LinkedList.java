package structures.vectors;

import structures.commons.ChainedIndexedDataStructure;

/**
 * Basic implementation of Doubly Linked List
 *
 * Time Complexity:
 * Time will be measured in
 * either direct and indirect recursive
 * method calls or loop iterations (where applicable).
 *
 * create() = O(n)<br>
 * insert() = O(n)<br>
 * search() = O(n)<br>
 * remove() = O(n)<br>
 *
 * Space complexity:
 * Space will be measured in
 * number of auxiliary nodes or variables
 * that are allocated within
 * a given method. Values passed as parameters
 * that are on the call stack are not included.
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public final class LinkedList<T> extends ChainedIndexedDataStructure<T> {

  /**
   * Constructs empty list.
   *
   * <br>
   * <br>
   * <strong>Time Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   *
   * <br>
   * <strong>Space Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   */
  public LinkedList() {
    super();
  }

//------------------------------------------------------------------------------

  /**
   * Constructs DoubleLinkedList from array of values.
   *
   * <br>
   * <br>
   * <strong>Time Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   *
   * <br>
   * <strong>Space Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   *
   * @param values Array of values to construct the list from
   */
  public LinkedList(T[] values) {
    super(values);
  }

//------------------------------------------------------------------------------

  /**
   * Returns the node at a specified index in the list.
   *
   * <br>
   * <br>
   * <strong>Time Complexity:</strong><br>
   * <strong>Best: </strong>&Omega;(1)<br>
   * <strong>Worst: </strong>O(n)<br>
   *
   * <br>
   * <strong>Space Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   *
   * @param index The specified to retrieve the node from
   * @return SinglyLinkedListNode at specified index
   */
  protected Node<T> getNode(int index) {
    Node<T> node;

    // Check that the specified index is a
    // valid index (Non-negative, and less than
    // the length of the list)
    this.verifyIndex(index);

    // Get the head node,
    // and start indexing from 0
    node = this.head();

    // index up until the
    // specified index (inclusive)
    for (int i = 0; i < index; i++) {
      node = node.next();
    }

    // If we got to the ith node
    // and it is null, then the
    // index is not valid
    // TODO - this should never happen!
    if (node == null) {
      throw new RuntimeException("There was an error, wtf");
    }

    // Otherwise, return the ith node
    return node;
  }

//------------------------------------------------------------------------------

  /**
   * Inserts a specified value at the front of the list.
   *
   * <br>
   * <br>
   * <strong>Time Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   *
   * <br>
   * <strong>Space Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   *
   * @param value The specified value to be inserted
   */
  public void insertFirst(T value) {
    this.insert(value, 0);
  }

//------------------------------------------------------------------------------

  /**
   * Inserts a specified value at the back of the list.
   *
   * TODO - Turn this into constant time by keeping track of the tail
   *
   * <br>
   * <br>
   * <strong>Time Complexity:</strong><br>
   * <strong>Best: </strong>&Omega;(1)<br>
   * <strong>Worst: </strong>O(n)<br>
   *
   * <br>
   * <strong>Space Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   *
   * @param value The specified value to be inserted
   */
  public void insertLast(T value) {

    // If the list is empty, insert in the front
    if (this.empty()) {
      this.insertFirst(value);

      // Otherwise insert in the back using the head's
      // insert method
    } else {

      // TODO - abstract this to insert(int, int)
      // TODO - only want 1 method incrementing the length if possible
      this.head().insert(value);
      this.size++;
    }
  }

//------------------------------------------------------------------------------

  /**
   * Insert specified value at specified index in list.
   *
   * <br>
   * <br>
   * <strong>Time Complexity:</strong><br>
   * <strong>Best: </strong>&Omega;(1)<br>
   * <strong>Worst: </strong>O(n)<br>
   *
   * <br>
   * <strong>Space Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   *
   * @param value Specified value to be inserted into the list
   * @param index Specified index
   */
  public void insert(T value, int index) {
    Node<T> node;
    Node<T> newNode;

    // If the list is empty
    if (this.empty()) {
      this.head(new Node<T>(value, null, null));
    }

    // Otherwise if its not empty, but we want to
    // insert at the front of the list
    else if (index == 0) {
      newNode = new Node<T>(value, null, this.head());

      this.head().prev(newNode);
      this.head(newNode);
    }

    // If the index is somewhere in the middle
    else {

      // Find the ith node, and put
      // a node right in front of it
      node = this.getNode(index);                                        // Get the ith node
      newNode = new Node<T>(value, node.prev(), node);  // Create a new node, setting its previous to the ith node's prev, and ith node as its next
      node.prev().next(newNode);                                         // Set the i-1th node's next to the new node
      node.prev(newNode);                                                // Set the old ith node's previous to the new node
    }

    this.size++;

  }

//------------------------------------------------------------------------------

  /**
   * Removes a node at a specified index from the list.
   *
   * <br>
   * <br>
   * <strong>Time Complexity:</strong><br>
   * <strong>Best: </strong>&Omega;(1)<br>
   * <strong>Worst: </strong>O(n)<br>
   *
   * <br>
   * <strong>Space Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   *
   * @param index Index of specified node to remove
   * @return Value of node at specified index
   */
  public T remove(int index) {

    if (this.empty()) {
      throw new EmptyDataStructureException("Cannot remove from an empty LinkedList");
    }

    this.verifyIndex(index);

    T value;

    if (this.size() == 1) {
      value = this.head().value();
      this.head(null);
    }

    // If we are removing from the front of the list
    else if (index == 0) {

      value = this.head().value();
      this.head(this.head().next());

    } else {

      // Get the ith node and its value
      Node<T> node = this.getNode(index);
      value = node.value();

      // Set the node at i-1's next to node at i+1
      // Essentially skipping over the node at i
      node.prev().next(node.next());

      // If node at i is not the end node
      // link the following nodes in the chain
      if (node.next() != null) {

        // Set i+1's previous to i's previous, again
        // skipping right over the node at i itself
        node.next().prev(node.prev());
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
   * <br>
   * <br>
   * <strong>Time Complexity:</strong><br>
   * <strong>Best: </strong>&Omega;(1)<br>
   * <strong>Worst: </strong>O(n)<br>
   *
   * <br>
   * <strong>Space Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   * @return Value of node at specified index
   */
  public T remove() {
    return this.remove(this.size()-1);
  }

//------------------------------------------------------------------------------

  /**
   * Removes the first value in the list.
   *
   * <br>
   * <br>
   * <strong>Time Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   *
   * <br>
   * <strong>Space Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   * @return Value of node at specified index
   */
  public T removeFirst() {
    return this.remove(0);
  }

//------------------------------------------------------------------------------

  /**
   * Removes the last value in the list.
   *
   * <br>
   * <br>
   * <strong>Time Complexity:</strong><br>
   * <strong>Best: </strong>&Omega;(1)<br>
   * <strong>Worst: </strong>O(n)<br>
   *
   * <br>
   * <strong>Space Complexity:</strong><br>
   * <strong>Avg: </strong>&Theta;(1)<br>
   * @return Value of node at specified index
   */
  public T removeLast() {
    return this.remove();
  }
}
