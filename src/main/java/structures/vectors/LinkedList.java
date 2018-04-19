package structures.vectors;

import structures.commons.LinkedStructure;

import java.util.Collection;

/**
 * Basic implementation of Doubly Linked List
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public final class LinkedList<T> extends LinkedStructure<T> implements List<T> {


  // TODO - Implement removeLast()
  // currently inheriting from List. The problem
  // is that is that it is implemented as a default
  // method, and removes by index. For a linkedlist,
  // removing by index is O(n). But, we have a pointer
  // to the tail, so we can reduce removeLast() to O(1)
  // the same way we are doing in Queue. We can then
  // ultimately use the same function in LinkedQueue and LinkedList
  // to accomplish this action in O(1)

  // Technically the same goes for removeFirst(), but
  // we exit after first iteration of loop. There is
  // slight overhead, but this is still constant time.
  // However, we can remove this overheard, and use the same
  // implementation that LinkedStack uses to minimize
  // overhead and maximize code reuse, all while maintaining
  // O(1) time for remove first / pop().

  /**
   * Constructs empty list
   */
  public LinkedList() {
    ;
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

//------------------------------------------------------------------------------

  /**
   * Construct linked list from Java Collection of values.
   *
   * @param values Collection of values to construct list from.
   * @see java.util.Collection
   */
  public LinkedList(Collection<T> values) {
    insert(values);
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


//------------------------------------------------------------------------------

  /**
   * Insert specified value at specified index in list.
   *
   * @param value Specified value to be inserted into the list
   * @param index Specified index
   * @return True to indicate the insertion was successful.
   */
  @Override
  public boolean insert(T value, int index) {
    Node<T> newNode;

    // Create a new node with
    // the value to be inserted
    newNode = new Node<>(value);

    // If the list is empty,
    // set new node to the head
    if (this.empty()) {
      head = newNode;
      tail = newNode;

      // The list is not empty
    } else if (index == 0) {
      newNode.next = head;
      head.prev    = newNode;
      head         = newNode;

    } else {

      // If the index is somewhere in the middle,
      // Find the ith node, and put
      // the new node right in front of it
      // (essentially, taking its index)
      Node<T> oldNode;

      oldNode      = getNode(index);        // Get the ith node
      newNode.prev = oldNode.prev;          // Set new node points, to that of older node
      newNode.next = oldNode;               // Set new nodes' next to ith node

      // Set the i-1th node's next to the new node
      if (oldNode.prev != null)
        oldNode.prev.next = newNode;

      // Set the old ith node's previous to the new node
      oldNode.prev = newNode;

    }

    this.size++;

    return true;
  }

//------------------------------------------------------------------------------

  /**
   * Removes a node at a specified index from the list.
   *
   * @param index Index of specified node to remove
   * @return Value of node at specified index
   */
  @Override
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
      head  = head.next;

    }

      // TODO - Figure out why I commented this out
//    else if (index == size - 1) {
//      value = tail.value;
//      tail = tail.next;
//
//    }

    else {

      // Get the ith node and its value
      Node<T> node = getNode(index);
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
   * @param value The value to be put in the list
   * @param index Index into which teh value will be inserted
   */
  public void set(T value, int index) {

    getNode(index).value = value;
  }

}
