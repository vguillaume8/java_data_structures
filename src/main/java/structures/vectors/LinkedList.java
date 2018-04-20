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

    super(values);
  }

//------------------------------------------------------------------------------

  /**
   * Construct linked list from Java Collection of values.
   *
   * @param values Collection of values to construct list from.
   * @see java.util.Collection
   */
  public LinkedList(Collection<T> values) {

    super(values);
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
   * Insert specified value at specified index in list.
   *
   * @param value Specified value to be inserted into the list
   * @param index Specified index
   * @return True to indicate the insertion was successful.
   */
  @Override
  public boolean insert(T value, int index) {

    return insertMiddle(value, index);
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

    return delete(index);
  }

  /**
   * Overwrites a value at a specified index
   * with a new value.
   *
   * @param value The value to be put in the list
   * @param index Index into which teh value will be inserted
   */
  public void set(T value, int index) {

    setNode(value, index);
  }

}
