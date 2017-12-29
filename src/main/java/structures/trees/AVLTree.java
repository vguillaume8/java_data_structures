package structures.trees;

/**
 * Self balanced AVL BinaryTree.
 *
 * Useful - https://www.youtube.com/watch?v=7m94k2Qhg68
 *
 * @author Jabari Dash
 * @param <K> Generic type for keys.
 * @param <V> Generic type for keys.
 */
public final class AVLTree<K extends Comparable, V> extends BinarySearchTree<K, V> {

    @SuppressWarnings("unused")
    public AVLTree() {
        super();
    }

    /**
     * Initialize a BinarySearchTree with a specified
     * set of keys.
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
     *
     * @param node
     */
    @SuppressWarnings("unused")
    protected int height(AVLTreeNode<K, V> node) {

        return node == null ? 0 : node.height;
    }

    public boolean insert(K key, V value) {
        // Store size before insertion
        int oldSize = this.size;

        // Insert key
        root = insert(root(), key, value);

        // Return true if the size changed
        return oldSize != this.size;
    }

    /**
     *
     * @param node
     * @param key
     * @return
     */
    @SuppressWarnings("unused")
    protected AVLTreeNode<K, V> insert(AVLTreeNode<K, V> node, K key, V value) {

        // If we are at the bottom
        if (node == null) {
            this.size++;
            return new AVLTreeNode<>(key, value);
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
     *
     * @return
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
        AVLTreeNode<K, V> y = x.rightChild();
        AVLTreeNode<K, V> T2 = y.leftChild();

        // Perform rotation
        y.leftChild = x;
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
        AVLTreeNode<K, V> x = y.leftChild();
        AVLTreeNode<K, V> T2 = x.rightChild();

        // Perform rotation
        x.rightChild = y;
        y.leftChild = T2;

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
     * Node in AVL BinaryTree.
     *
     * @author Jabari Dash
     * @param <K> Generic type for keys.
     * @param <V> Generic type for keys.
     */
    private static class AVLTreeNode<K extends Comparable, V> extends BinarySearchTreeNode<K, V> {

        private int height;

        /**
         *
         * @param key
         * @param value
         */
        private AVLTreeNode(K key, V value) {
            super(key, value);
            this.height = 1;
        }


        /**
         *
         * @return
         */
        @SuppressWarnings("unused")
        protected AVLTreeNode<K, V> leftChild() {

            return (AVLTreeNode<K, V>) this.leftChild;
        }


        /**
         *
         * @return
         */
        @SuppressWarnings("unused")
        protected AVLTreeNode<K, V> rightChild() {
            return (AVLTreeNode<K, V>) this.rightChild;
        }

    }
}
