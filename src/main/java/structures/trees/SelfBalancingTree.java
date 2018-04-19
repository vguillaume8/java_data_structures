package structures.trees;

/**
 * Abstract class for Self balancing trees
 * such as AVLTree and Red Black Tree. The
 * rotation function are implemented in
 * this class as they are used by both types
 * of trees.
 *
 * @author Jabari Dash
 * @param <K> Generic type for keys. Must be comparable
 * @param <V> Generic type for values.
 */
public abstract class SelfBalancingTree<K extends Comparable, V> extends BinarySearchTree<K, V> {

    /**
     * Performs a left rotation on a subtree.
     *
     * @param node Node to rotate from
     * @return Root of new subtree
     */
    public BinarySearchTreeNode<K, V> rotateLeft(BinarySearchTreeNode<K, V> node) {
        return null;
    }

    /**
     * Performs a left-left rotation on a subtree.
     *
     * @param node Node to rotate from
     * @return Root of new subtree
     */
    public BinarySearchTreeNode<K, V> rotateLeftLeft(BinarySearchTreeNode<K, V> node) {
        return null;
    }

    /**
     * Performs a right rotation on a subtree.
     *
     * @param node Node to rotate from
     * @return Root of new subtree
     */
    public BinarySearchTreeNode<K, V> rotateRight(BinarySearchTreeNode<K, V> node) {
        return null;
    }

    /**
     * Performs a right-right rotation on a subtree.
     *
     * @param node Node to rotate from
     * @return Root of new subtree
     */
    public BinarySearchTreeNode<K, V> rotateRightRight(BinarySearchTreeNode<K, V> node) {
        return null;
    }
}
