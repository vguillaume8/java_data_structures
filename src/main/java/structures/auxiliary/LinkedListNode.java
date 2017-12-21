package structures.auxiliary;

/**
 * Abstract class for use with DoublyLinkedListNodes and
 * SinglyLinkedListNodes
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public abstract class LinkedListNode<T> extends Value<T> {

    /**
     * Inserts a specified value into the chain of nodes
     *
     * @param value Specified value to be inserted
     */
    public abstract void insert(T value);

    /**
     * Returns pointer to next node in chain
     *
     * @return Pointer to next node
     */
    public abstract LinkedListNode<T> next();

    /**
     * Sets next pointer to specified node
     *
     * TODO - Figure out why subclasses complain about
     *  public void next(DoublyLinkedList<T> next) {...}
     *
     * @param next Specified node
     */
//    public abstract void next(LinkedListNode<T> next);
}
