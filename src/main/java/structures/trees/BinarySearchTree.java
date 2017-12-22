package structures.trees;

import structures.auxiliary.DynamicallySizedDataStructure;
import structures.trees.auxiliary.BinaryTree;
import structures.vectors.ArrayList;

public class BinarySearchTree<T extends Comparable> extends DynamicallySizedDataStructure<T> implements BinaryTree<T> {

    private Node<T> root;

    public BinarySearchTree() {
        this.init();
    }

    public BinarySearchTree(T[] values) {
        this.init();

        for (T value : values) {
            this.insert(value);
        }
    }

    public void display() {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("Size: " + this.size());
        System.out.println("Height: " + this.height());
        System.out.println("inorder: " + this.toArrayList(BinaryTree.IN_ORDER));
        System.out.println("preorder: " + this.toArrayList(BinarySearchTree.PRE_ORDER));
        System.out.println("postorder: " + this.toArrayList(BinaryTree.POST_ORDER));
    }

    @Override
    public Node<T> root() {
        return this.root;
    }

    @Override
    public int height() {
        return Node.height(root);
    }

    @Override
    public ArrayList<T> toArrayList() {
        return this.root.toArrayList(this.root, new ArrayList<T>());
    }

    /**
     * Returns ArrayList representation of tree
     * in pre-order
     *
     * @return Array representation of tree
     */
    @Override
    public Object[] toArray() {
        ArrayList<?> arrayList = this.toArrayList();

        return arrayList.toArray();
    }

    /**
     * Returns Array representation of tree
     * in specified order
     *
     * @param traversalType Specified order (inorder, preorder, postorder)
     * @return Array representation of tree
     */
    @Override
    public Object[] toArray(int traversalType) {
        return this.toArrayList(traversalType).toArray();
    }

    @Override
    public ArrayList<T> toArrayList(int traversalType) {
        return this.root.toArrayList(this.root, new ArrayList<T>(), traversalType);
    }

    /**
     * Determines whether or not a specified value is present in the tree
     *
     * @param value Specified value
     * @return True if and nly if the specified value is in the tree
     */
    @Override
    public boolean contains(T value) {
        return contains(root, value);
    }

    /**
     * Determines whether or not a specified value is present in the tree.
     * This is an auxiliary method that helps traverse the list via pointers
     *
     * @param node Node to start searching from
     * @param value Specified value
     * @return True if and nly if the specified value is in the tree
     */
    private boolean contains(Node<T> node, T value) {
        if (node == null) {
            return false;
        }

        // See if its in left subtree, right subtree, or in parent
        boolean parent = node.value() == value ? true : false;
        boolean left = contains(node.leftChild(), value);
        boolean right = contains(node.rightChild(), value);

        // If its in any of the three, we found it
        return left || right || parent;
    }

    /**
     * Initializes an empty tree
     */
    @Override
    public void init() {
        super.init();
        this.root = null;
    }

    /**
     * Inserts a specified value into the tree
     *
     * @param value The specified value to insert
     */
    @Override
    public void insert(T value) {
        if (this.empty()) {
            this.root = new BinarySearchTreeNode<T>(value, null, null);
        } else {
                this.root = this.root.insert(this.root, value);
        }

        this.incrementSize();
    }

    /**
     * Removes the root value from the tree
     *
     * @return Value at root
     */
    @Override
    public T remove() {
        return null;
    }

    /**
     * Node for use in Binary SearchTree
     *
     * @author Jabari Dash
     * @param <T> Generic type
     */
    public static class BinarySearchTreeNode<T extends Comparable> extends Node<T> implements Comparable<BinarySearchTreeNode<T>> {

        /**
         *
         *
         * @param value
         * @param leftChild
         * @param rightChild
         */
        public BinarySearchTreeNode(T value, Node<T> leftChild, Node<T> rightChild) {
            super(value, leftChild, rightChild);
        }

        @Override
        public Node<T> insert(Node<T> node, T value) {
            if (node == null) {
                return new BinarySearchTreeNode<T>(value, null, null);

            } else {
                int comparison = value.compareTo(node.value());

                if (comparison < 0) {

                    node.leftChild(node.insert(node.leftChild(), value));

                } else if (comparison > 0) {

                    node.rightChild(node.insert(node.rightChild(), value));
                }
            }

            return node;
        }

        @Override
        public ArrayList<T> toArrayList(Node<T> node, ArrayList<T> arrayList, int traversalType) {
            switch (traversalType) {
                case IN_ORDER: return this.toArrayListInOrder(node, arrayList);
                case PRE_ORDER: return this.toArrayListPreOrder(node, arrayList);
                case POST_ORDER: return this.toArrayListPostOrder(node, arrayList);
                default: throw new IllegalArgumentException("traversalType: " + traversalType + " unrecognized");
            }
        }

        /**
         *
         * @param node Node to start appending to ArrayList from
         * @param arrayList Growing ArrayList
         * @return
         */
        @Override
        public ArrayList<T> toArrayList(Node<T> node, ArrayList<T> arrayList) {
            return this.toArrayListPreOrder(node, arrayList);
        }

        @Override
        public ArrayList<T> toArrayListInOrder(Node<T> node, ArrayList<T> arrayList) {
            if (node == null) {
                return arrayList;
            }

            arrayList = node.toArrayListInOrder(node.leftChild(), arrayList);
            arrayList.insert(node.value());
            arrayList = node.toArrayListInOrder(node.rightChild(), arrayList);

            return arrayList;
        }

        @Override
        public ArrayList<T> toArrayListPreOrder(Node<T> node, ArrayList<T> arrayList) {
            if (node == null) {
                return arrayList;
            }

            arrayList.insert(node.value());
            arrayList = node.toArrayListPreOrder(node.leftChild(), arrayList);
            arrayList = node.toArrayListPreOrder(node.rightChild(), arrayList);

            return arrayList;
        }

        @Override
        public ArrayList<T> toArrayListPostOrder(Node<T> node, ArrayList<T> arrayList) {
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
         * @param node
         * @return
         */
        @Override
        public int compareTo(BinarySearchTreeNode<T> node) {
            return this.value().compareTo(node.value());
        }
    }
}