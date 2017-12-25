package structures.trees;

import structures.util.interfaces.ComparableValue;
import structures.vectors.ArrayList;
import structures.vectors.classes.ChainedDataStructure;

import static structures.trees.BinarySearchTree.IN_ORDER;
import static structures.trees.BinarySearchTree.POST_ORDER;
import static structures.trees.BinarySearchTree.PRE_ORDER;

/**
 * Node for use in Binary SearchTree
 *
 * @author Jabari Dash
 * @param <T> Generic type
 */
public final class BinarySearchTreeNode<T extends Comparable> extends ChainedDataStructure.Node<T> implements ComparableValue<T> {

    /**
     * Initializes a new tree node with its value
     * and pointers to both its left and right child
     *
     * @param value Values of the node
     * @param leftChild Left child of node
     * @param rightChild Right child of node
     */
    public BinarySearchTreeNode(T value, BinarySearchTreeNode<T> leftChild, BinarySearchTreeNode<T> rightChild) {
        super(value, leftChild, rightChild);
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param value
     * @return
     */
    @Override
    public boolean contains(T value) {
        return this.contains(this, value);
    }

//------------------------------------------------------------------------------

    protected boolean contains(BinarySearchTreeNode<T> node, T value) {
        if (node == null) {
            return false;
        }

        // TODO - Turn this into a binary search, because we dont have to check both right and left

        // See if its in left subtree, right subtree, or in parent
        boolean parent = node.value() == value;
        boolean left = contains(node.leftChild(), value);
        boolean right = contains(node.rightChild(), value);

        // If its in any of the three, we found it
        return left || right || parent;
    }

//------------------------------------------------------------------------------

    /**
     * Determines the maximum depth (number of levels) in the tree
     *
     * @return Number of levels in the Tree
     */
    public static int height(BinarySearchTreeNode node) {
        int leftHeight;
        int rightHeight;

        if (node == null) {
            return 0;

        } else {

            leftHeight  = BinarySearchTreeNode.height(node.leftChild());
            rightHeight = BinarySearchTreeNode.height(node.rightChild());
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

//------------------------------------------------------------------------------

    /**
     * Insert a value into the tree
     *
     * @param value Value to insert into the tree
     * @return True if the values was inserted, false otherwise
     */
    @Override
    public boolean insert(T value) {
        boolean result = false;

        // value < this.value
        if (value.compareTo(this.value()) < 0) {

            if (this.leftChild() == null) {
                this.leftChild(new BinarySearchTreeNode<T>(value, null, null));
                result = true;
            } else {
                return this.leftChild().insert(value);
            }

            // value > this.value
        } else if (value.compareTo(this.value()) > 0) {

            if (this.rightChild() == null) {
                this.rightChild(new BinarySearchTreeNode<T>(value, null, null));
                result = true;
            } else {
                return this.rightChild().insert(value);
            }
        }

        return result;
    }

//------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    public BinarySearchTreeNode<T> leftChild() {
        return (BinarySearchTreeNode<T>) this.prev();
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param leftChild
     */
    public void leftChild(BinarySearchTreeNode<T> leftChild) {
        this.prev(leftChild);
    }

//------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    private T minValue() {
        if (this.leftChild() == null) {
            return this.value();
        } else {
            return this.leftChild().minValue();
        }
    }

//------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    private T maxValue() {
        if (this.rightChild() == null) {
            return this.value();
        } else {
            return this.rightChild().maxValue();
        }
    }

//------------------------------------------------------------------------------

    public boolean remove(BinarySearchTreeNode<T> node, T value) {
        if (value.compareTo(this.value()) < 0) {

            if (this.leftChild() != null)
                return this.leftChild().remove(this, value);

            else
                return false;

        } else if (value.compareTo(this.value()) > 0) {

            if (this.rightChild() != null)
                return this.rightChild().remove(this, value);

            else
                return false;

        } else {

            if (this.leftChild() != null && this.rightChild() != null) {

                this.value(this.rightChild().minValue());
                this.rightChild().remove(this, this.value());

            } else if (node.leftChild() == this) {

                BinarySearchTreeNode<T> child = leftChild() != null ? leftChild() : rightChild();

                node.leftChild(child);

            } else if (node.rightChild() == this) {

                BinarySearchTreeNode<T> child = (leftChild() != null) ? leftChild() : rightChild();
                node.rightChild(child);
            }

            return true;

        }
    }

//------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    public BinarySearchTreeNode<T> rightChild() {
        return (BinarySearchTreeNode<T>) this.next();
    }

//------------------------------------------------------------------------------

    /**
     *
     * @param rightChild
     */
    public void rightChild(BinarySearchTreeNode<T> rightChild) {
        this.next(rightChild);
    }

    public ArrayList<T> toArrayList() {
        return this.toArrayList(this, new ArrayList<T>(), PRE_ORDER);
    }

    public ArrayList<T> toArrayList(int traversalType) {
        return this.toArrayList(this, new ArrayList<>(), traversalType);
    }

    /**
     *
     * @param node
     * @param arrayList
     * @param traversalType
     * @return
     */
    private ArrayList<T> toArrayList(BinarySearchTreeNode<T> node, ArrayList<T> arrayList, int traversalType) {
        ArrayList<T> arrayList1;

        switch (traversalType) {
            case IN_ORDER:   arrayList1 = this.toArrayListInOrder(node, arrayList);     break;
            case PRE_ORDER:  arrayList1 = this.toArrayListPreOrder(node, arrayList);    break;
            case POST_ORDER: arrayList1 = this.toArrayListPostOrder(node, arrayList);   break;
            default: throw new IllegalArgumentException("traversalType: " + traversalType + " unrecognized");
        }

        return arrayList1;
    }

//------------------------------------------------------------------------------

    private ArrayList<T> toArrayListInOrder(BinarySearchTreeNode<T> node, ArrayList<T> arrayList) {
        if (node == null) {
            return arrayList;
        }

        arrayList = node.toArrayListInOrder(node.leftChild(), arrayList);
        arrayList.insert(node.value());
        arrayList = node.toArrayListInOrder(node.rightChild(), arrayList);

        return arrayList;
    }

//------------------------------------------------------------------------------

    private ArrayList<T> toArrayListPreOrder(BinarySearchTreeNode<T> node, ArrayList<T> arrayList) {
        if (node == null) {
            return arrayList;
        }

        arrayList.insert(node.value());
        arrayList = node.toArrayListPreOrder(node.leftChild(), arrayList);
        arrayList = node.toArrayListPreOrder(node.rightChild(), arrayList);

        return arrayList;
    }

//------------------------------------------------------------------------------

    private ArrayList<T> toArrayListPostOrder(BinarySearchTreeNode<T> node, ArrayList<T> arrayList) {
        if (node == null) {
            return arrayList;
        }

        arrayList = node.toArrayListPostOrder(node.leftChild(), arrayList);
        arrayList = node.toArrayListPostOrder(node.rightChild(), arrayList);
        arrayList.insert(node.value());

        return arrayList;
    }

    /**
     *
     * @param comparableValue
     * @return
     */
    public int compareTo(ComparableValue<T> comparableValue) {
        return this.value().compareTo(comparableValue.value());
    }

    public boolean lessThan(ComparableValue<T> value) {
        return this.compareTo(value) < 0 ? true : false;
    }

    public boolean lessThanOrEqualTo(ComparableValue<T> value) {
        return this.compareTo(value) <= 0 ? true : false;
    }

    public boolean greaterThan(ComparableValue<T> value) {
        return this.compareTo(value) > 0 ? true : false;
    }

    public boolean greaterThanOrEqualTo(ComparableValue<T> value) {
        return this.compareTo(value) >= 0 ? true : false;
    }
}
