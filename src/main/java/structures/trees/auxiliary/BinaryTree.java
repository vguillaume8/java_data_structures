package structures.trees.auxiliary;

import structures.auxiliary.DataStructure;
import structures.auxiliary.Value;
import structures.vectors.ArrayList;
import java.util.Iterator;

/**
 * Interface for Linked BinaryTrees
 *
 * @param <T> Generic type
 */
public interface BinaryTree<T> extends DataStructure<T> {

    // Constants
    int IN_ORDER   = 0;
    int PRE_ORDER  = 1;
    int POST_ORDER = 2;

    /**
     * Displays information about the tree.
     * Size, levels, all 3 traversals
     */
    void display();

    /**
     * Returns the maximum level that the tree spans to
     *
     * @return Max depth of tree
     */
    int height();

    /**
     * Returns pointer to root Node of tree
     *
     * @return Root pointer
     */
    Node<T> root();

    /**
     * Returns ArrayList representation of tree
     * in pre-order
     *
     * @return ArrayList representation to tree
     */
    ArrayList<T> toArrayList();

    /**
     * Returns ArrayList representation of tree
     * in pre-order
     *
     * @return Array representation of tree
     */
    Object[] toArray();

    /**
     * Returns Array representation of tree
     * in specified order
     *
     * @param traversalType Specified order (inorder, preorder, postorder)
     * @return Array representation of tree
     */
    Object[] toArray(int traversalType);

    /**
     * Returns ArrayList representation of tree
     * in specified order
     *
     * @param traversalType Specified order (inorder, preorder, postorder)
     * @return ArrayList representation to tree
     */
    ArrayList<T> toArrayList(int traversalType);

    /**
     * Generic Node class for BinaryTrees
     *
     * @param <T> Generic type
     */
    abstract class Node<T> extends Value<T> {
        private Node<T> leftChild;      // Pointer to left child
        private Node<T> rightChild;     // Pointer to right child

        /**
         *
         *
         * @param value      Specified value of the Node
         * @param leftChild  Pointer to designated left child node
         * @param rightChild Pointer to designated left child node
         */
        public Node(T value, Node<T> leftChild, Node<T> rightChild) {
            this.value(value);
            this.leftChild(leftChild);
            this.rightChild(rightChild);
        }

        public abstract Node<T> insert(Node<T> node, T value);

        /**
         * Determines the maximum depth (number of levels) in the tree
         *
         * @return Number of levels in the Tree
         */
        public static int height(Node node) {
            int leftHeight;
            int rightHeight;

            if (node == null) {
                return 0;

            } else {

                leftHeight = Node.height(node.leftChild());
                rightHeight = Node.height(node.rightChild());
            }

            return Math.max(leftHeight, rightHeight) + 1;
        }

        /**
         * Returns the pointer to tne Node's left child
         *
         * @return Pointer to left child
         */
        public Node<T> leftChild() {
            return this.leftChild;
        }

        /**
         * Sets the leftChild of a Node
         *
         * @param leftChild Pointer to new leftChild
         */
        public void leftChild(Node<T> leftChild) {
            this.leftChild = leftChild;
        }

        /**
         * Returns the pointer to the Node's right child
         *
         * @return Pointer to right child
         */
        public Node<T> rightChild() {
            return this.rightChild;
        }

        /**
         * Sets the rightChild of a Node
         *
         * @param rightChild to new rightChild
         */
        public void rightChild(Node<T> rightChild) {
            this.rightChild = rightChild;
        }

        public abstract ArrayList<T> toArrayList(Node<T> node, ArrayList<T> arrayList, int traversalType);

        /**
         * Flattens the tree to an ArrayList (pre-order)
         *
         * @param node Node to start appending to ArrayList from
         * @param arrayList Growing ArrayList
         * @return ArrayList representation of the BinaryTree
         */
        public abstract ArrayList<T> toArrayList(Node<T> node, ArrayList<T> arrayList);

        public abstract ArrayList<T> toArrayListInOrder(Node<T> node, ArrayList<T> arrayList);
        public abstract ArrayList<T> toArrayListPreOrder(Node<T> node, ArrayList<T> arrayList);
        public abstract ArrayList<T> toArrayListPostOrder(Node<T> node, ArrayList<T> arrayList);
    }

    /**
     * Basic iterator for iterating over BinaryTree in order,
     * essentially, the BinaryTree is converted to an ArrayList,
     * and then traversed with the ArrayList's iterator. This is
     * not space effective, or time effective, but it keeps the
     * code a lot simpler
     *
     * @param <T> Generic type
     */
    class BinaryTreeIterator<T> implements Iterator<T> {
        Iterator<T> iterator;

        /**
         * Constructs for iterating over BinaryTree. By
         * default, traversal is done "pre-order," that is,
         * left, parent, right
         *
         * @param binaryTree BinaryTree to be traversed
         */
        public BinaryTreeIterator(BinaryTree<T> binaryTree) {
            iterator = binaryTree.toArrayList().iterator();
        }

        /**
         * Determines whether or not there are more
         * nodes to be iterated over
         *
         * @return True if and only if there are still
         * unvisited nodes to return
         */
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        /**
         * Returns next Node in in-order traversal
         *
         * @return Next Node
         */
        @Override
        public T next() {
            return iterator.next();
        }
    }
}
