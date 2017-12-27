package structures.trees;

import util.Util;

import static util.Util.max;

/**
 * Self balanced AVL Tree.
 *
 * @author Jabari Dash
 * @param <K> Generic type for keys.
 * @param <V> Generic type for values.
 */
public final class AVLTree<K extends Comparable, V> extends BinarySearchTree<K, V> {

    // Integer codes for rotation
    private static final int LEFT_LEFT_ROTATION   = 0;
    private static final int LEFT_RIGHT_ROTATION  = 1;
    private static final int RIGHT_RIGHT_ROTATION = 2;
    private static final int RIGHT_LEFT_ROTATION  = 3;

    /**
     * Initialize a BinarySearchTree with a specified
     * set of keys.
     *
     * @param keys Specified keys to insert into BinarySearchTree
     */
    @SuppressWarnings("unused")
    public AVLTree(K[] keys) {
        this.root(null);

        for (K key : keys) {
            insert(key);
        }
    }

    /**
     *
     * @param node
     */
    @SuppressWarnings("unused")
    private int balanceFactor(AVLTreeNode<K, V> node) {

        if (node != null)
            return height(node.leftChild) - height(node.rightChild);

        return 0;
    }

    /**
     *
     * @param node
     */
    @SuppressWarnings("unused")
    private int height(AVLTreeNode<K, V> node) {
        if (node != null)
            return node.height;

        return 0;
    }

    /**
     * Inserts a specified key into the tree with an empty value.
     *
     * @param key The specified key to insert
     * @return True if the insertion was successful. An insertion is successful
     * if the specified key is not already in the tree.
     */
    @Override
    public boolean insert(K key) {

        root(insert(root(), key));

        return true;
    }

    /**
     *
     * @param node
     * @param key
     * @return
     */
    @SuppressWarnings("unused")
    private AVLTreeNode insert(AVLTreeNode<K, V> node, K key) {

        System.out.println("inserting " + key);

        // If we are at the bottom
        if (node == null) {
            return new AVLTreeNode<K, V>(key, null, null, null);
        }

        // Compare the keys
        int comparison = node.key().compareTo(key);


        // Less than
        if (comparison > 0) {
            node.leftChild = insert(node.leftChild, key);

            // Greater than
        } else {
            node.rightChild = insert(node.rightChild, key);
        }

        // Set the height to the maximum height of left and right subtrees
        node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;

        // Get the balance factor
        int balance = balanceFactor(node);

        // Compare key to current node's kid's keys then
        // check four cases in which subtrees can be unbalanced

        // Left Left
        if (balance > 1 && key.compareTo(node.leftChild().key()) < 0)
            return rotate(node, LEFT_LEFT_ROTATION);

        // Right Right
        if (balance < -1 && key.compareTo(node.rightChild().key()) > 0)
            return rotate(node, RIGHT_RIGHT_ROTATION);

        // Left Right
        if (balance > 1 && key.compareTo(node.leftChild().key()) > 0)
            return rotate(node, LEFT_RIGHT_ROTATION);

        // Right Left
        if (balance < -1 && key.compareTo(node.rightChild().key()) < 0)
            return rotate(node, RIGHT_LEFT_ROTATION);

        // Return unchanged pointer
        // (it was already balanced)
        return node;
    }

    @Override
    public AVLTreeNode<K, V> root() {
        return (AVLTreeNode<K, V>) super.root();
    }

    /**
     *
     * @param node
     * @param rotationType
     * @return
     */
    @SuppressWarnings("unused")
    private AVLTreeNode<K, V> rotate(AVLTreeNode<K, V> node, int rotationType) {

        switch (rotationType) {
            case LEFT_LEFT_ROTATION:
                node = rotateRight(node);
                break;

            case RIGHT_RIGHT_ROTATION:
                node = rotateLeft(node);
                break;

            case LEFT_RIGHT_ROTATION:
                node.leftChild(rotateLeft(node.leftChild()));
                node = rotateRight(node);
                break;

            case RIGHT_LEFT_ROTATION:
                node.rightChild(rotateRight(node.rightChild()));
                node = rotateLeft(node);
                break;
        }

        return node;
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
        y.leftChild(x);
        x.rightChild(T2);

        //  Update heights
        x.height = max(height(x.leftChild()),
                   height(x.rightChild())) + 1;

        y.height = max(height(y.leftChild()),
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
        AVLTreeNode<K, V> x;
        AVLTreeNode<K, V> T2;

        // Get nodes
        x = y.leftChild();
        T2 = x.rightChild();

        // Perform rotation
        x.rightChild(y);
        y.leftChild(T2);

        // Calculate new heights
        int hy = max(height(y.leftChild()),
                     height(y.rightChild())) + 1;

        int hx = max(height(x.leftChild()),
                     height(x.rightChild())) + 1;

        // Update height
        y.height(hy);
        x.height(hx);

        // Return root
        return x;
    }

    /**
     * Node in AVL Tree.
     *
     * @author Jabari Dash
     * @param <K> Generic type for keys.
     * @param <V> Generic type for values.
     */
    private static class AVLTreeNode<K extends Comparable, V> extends BinarySearchTreeNode<K, V> {

        int height;
        K key;
        V value;
        AVLTreeNode<K, V> leftChild;
        AVLTreeNode<K, V> rightChild;

        public AVLTreeNode(K key, V value, AVLTreeNode<K, V> leftChild, AVLTreeNode<K, V> rightChild) {
            this.key = key;
            this.value = value;
            this.leftChild(leftChild);
            this.rightChild(rightChild);
            height = 1;
        }

        @SuppressWarnings("unused")
        public int height() {
            return this.height;
        }

        @SuppressWarnings("unused")
        public void height(int height) {
            this.height = height;
        }

        public K key() {
            return this.key;
        }

        public void key(K key) {
            this.key = key;
        }

        public V value() {
            return this.value;
        }

        public void value(V value) {
            this.value = value;
        }

        /**
         *
         * @return
         */
        @SuppressWarnings("unused")
        protected AVLTreeNode<K, V> leftChild() {
            return this.leftChild;
        }

        /**
         *
         * @param leftChild
         */
        @SuppressWarnings("unused")
        protected void leftChild(AVLTreeNode<K, V> leftChild) {

            this.leftChild = leftChild;
        }

        /**
         *
         * @return
         */
        @SuppressWarnings("unused")
        protected AVLTreeNode<K, V> rightChild() {
            return this.rightChild;
        }

        /**
         *
         * @param rightChild
         */
        @SuppressWarnings("unused")
        protected void rightChild(AVLTreeNode<K, V>rightChild) {

            this.rightChild = rightChild;
        }

    }
}
