package structures.trees;

/**
 * Self balanced AVL BinarySearchTree.
 *
 * Useful - https://www.youtube.com/watch?v=7m94k2Qhg68
 *
 * @author Jabari Dash
 * @param <K> Generic type for keys.
 * @param <V> Generic type for values.
 * @see structures.trees.BinarySearchTree
 */
public final class AVLTree<K extends Comparable, V> extends BinarySearchTree<K, V> {

    /**
     * Constructs an empty AVLTree.
     */
    @SuppressWarnings("unused")
    public AVLTree() {
        super();
    }

    /**
     * Initialize a BinarySearchTree with a specified
     * set of keys. Note, all values are null.
     *
     * @param keys Specified keys to insert into BinarySearchTree
     */
    @SuppressWarnings("unused")
    public AVLTree(K[] keys) {
        super(keys);
    }

    /**
     * Auxiliary function for calculating the
     * balance factor of the tree. Note, though it
     * does not technically override the balanceFactor() function
     * in the superclass, this function computes balance factor
     * in constant time because nodes are keeping track of their
     * given heights. The balanceFactor() method in the superclass
     * requires tree traversal, and thus takes linear time.
     *
     * @param node Pointer to node to start recursion from.
     * @return Balance factor of tree that begins with the specified
     * node as its root.
     */
    protected int balanceFactor(AVLTreeNode<K, V> node) {

        return node == null ? 0 : height(node.leftChild()) - height(node.rightChild());
    }

    /**
     * Computes the height of the tree. Note, this
     * method is private because it is takes a node
     * as an argument. The method height() in the
     * superclass BinarySearchTree implicitly invokes
     * this method. Provided AVLTreeNodes have to keep track
     * of height (but regular BinarySearchTreeNodes do not),
     * this method is simply an optimization in that it
     * changes the runtime of height() from O(log2(n)) to O(1).
     *
     * @param node Node to start calculating height from.
     */
    @SuppressWarnings("unused")
    protected int height(AVLTreeNode<K, V> node) {

        return node == null ? 0 : node.height;
    }

    /**
     * Insert a key-value pair into the tree.
     *
     * @param key Key
     * @param value Value
     * @return True if and only if the new pair was successfully inserted.
     */
    public boolean insert(K key, V value) {
        // Store size before insertion
        int oldSize = this.size;

        // Insert key
        root = insert(root(), key, value);

        // Return true if the size changed
        return oldSize != this.size;
    }

    /**
     * Inserts a new key-value pair after a specified node.
     * We return the node because insert() has two steps.
     *
     * 1. Normal binary search tree insertion
     * 2. Re-balancing the tree
     *
     * Insertion is recursive, so in order
     * to not lose the pointer to root after subsequent
     * calls to insert(), we return it, so we
     * have the proper node when we proceed
     * to balance the tree in step 2.
     *
     * @param node Node to insert from.
     * @param key Key
     * @param value Value
     * @return Root node
     */
    @SuppressWarnings("unused")
    protected AVLTreeNode<K, V> insert(AVLTreeNode<K, V> node, K key, V value) {

        // If we are at the bottom
        if (node == null) {
            this.size++;
            return new AVLTreeNode<K, V>(key, value);
        }

        // Compare the keys
        int comparison = key.compareTo(node.key);

        // Less than
        if (comparison < 0) {
            node.leftChild = insert(node.leftChild(), key, value);

            // Greater than
        } else if (comparison > 0) {
            node.rightChild = insert(node.rightChild(), key, value);

        // No duplicates
        } else {
            return node;
        }

        // Set the height to the maximum height of left and right subtrees
        node.height = Math.max(height(node.leftChild()), height(node.rightChild())) + 1;

        // Get the balance factor
        int balance = balanceFactor(node);

        // Compare key to current node's kid's keys then
        // check four cases in which subtrees can be unbalanced

        // Left Left
        if (balance > 1 && key.compareTo(node.key) < 0) {
            return rotateRight(node);
        }

        // Left Right
        if (balance > 1 && key.compareTo(node.leftChild.key) > 0) {
            node.leftChild = rotateLeft(node.leftChild());
            return rotateRight(node);
        }

        // Right Right
        if (balance < -1 && key.compareTo(node.rightChild().key) > 0) {
            return rotateLeft(node);
        }

        // Right Left
        if (balance < -1 && key.compareTo(node.rightChild().key) < 0) {
            node.rightChild = rotateRight(node.rightChild());
            return rotateLeft(node);
        }

        // Return unchanged pointer
        // (it was already balanced)
        return node;
    }

    /**
     * Removes a node from thee tree via
     * it's key.
     *
     * @param key The specified key to remove from the tree.
     * @return True if and only if the node with the associated key was removed successfully.
     */
    @Override
    public boolean remove(K key) {

        // TODO - Implement

        return super.remove(key);
    }

    /**
     * Returns the root node of the tree.
     *
     * @return Root node
     */
    protected AVLTreeNode<K, V> root() {
        return (AVLTreeNode<K, V>) root;
    }

    /**
     * Perform left left rotation on specified node.
     *
     * @param x Node to perform rotation on.
     * @return Pointer to new node.
     */
    @SuppressWarnings("unused")
    private AVLTreeNode<K, V> rotateLeft(AVLTreeNode<K, V> x) {
        AVLTreeNode<K, V> y  = x.rightChild();
        AVLTreeNode<K, V> T2 = y.leftChild();

        // Perform rotation
        y.leftChild  = x;
        x.rightChild = T2;

        //  Update heights
        x.height = Math.max(height(x.leftChild()),
                   height(x.rightChild())) + 1;

        y.height = Math.max(height(y.leftChild()),
                       height(y.rightChild())) + 1;

        // Return new root
        return y;
    }

    /**
     * Perform left right rotation on specified node.
     *
     * @param y Node to perform rotation on.
     * @return Pointer to new node.
     */
    @SuppressWarnings("unused")
    private AVLTreeNode<K, V> rotateRight(AVLTreeNode<K, V> y) {
        AVLTreeNode<K, V> x  = y.leftChild();
        AVLTreeNode<K, V> T2 = x.rightChild();

        // Perform rotation
        x.rightChild = y;
        y.leftChild  = T2;

        // Calculate new heights
        // Update height
        y.height = Math.max(height(y.leftChild()),
                height(y.rightChild())) + 1;;

        x.height = Math.max(height(x.leftChild()),
                height(x.rightChild())) + 1;

        // Return root
        return x;
    }

    /**
     * Node in AVL BinarySearchTree.
     *
     * @author Jabari Dash
     * @param <K> Generic type for keys.
     * @param <V> Generic type for keys.
     * @see structures.trees.BinarySearchTree.BinarySearchTreeNode
     */
    private static class AVLTreeNode<K extends Comparable, V> extends BinarySearchTreeNode<K, V> {

        /**
         * Height of subtree where
         * this node is the root.
         */
        private int height;

        /**
         * Constructs a new Node.
         *
         * @param key Key that will be used for inserting and searching for this node.
         * @param value Value of the node.
         */
        private AVLTreeNode(K key, V value) {
            super(key, value);
            this.height = 1;
        }

        /**
         * Returns the left child of this node.
         *
         * @return Left child
         */
        @SuppressWarnings("unused")
        protected AVLTreeNode<K, V> leftChild() {

            return (AVLTreeNode<K, V>) this.leftChild;
        }

        /**
         * Returns the right child of this node.
         *
         * @return Right child
         */
        @SuppressWarnings("unused")
        protected AVLTreeNode<K, V> rightChild() {
            return (AVLTreeNode<K, V>) this.rightChild;
        }

    }
}
