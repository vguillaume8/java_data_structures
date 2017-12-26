import structures.trees.BinarySearchTree;
import structures.trees.BinarySearchTree.*;
import structures.commons.Pair;

/**
 *
 */
public final class Application {

  public static void main(String[] args) {

    Integer[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    Integer[] unbalanced = {1, 2, 3, 4, 5, 6};
    Integer[] balancedIncompleteFull = {7, 3, 1, 5, 4, 6, 11, 9, 13, 8, 10};
    Integer[] balancedIncompleteNotFull = {7, 3, 1, 5, 6, 11, 9, 13, 8, 10};
    Integer[] fullAndIncomplete = {7, 3, 1, 5, 2, 0, 4, 6, 11, 9, 13, 8};
    Integer[] fullAndComplete = {7, 3, 1, 5, 2, 0, 4, 6, 11, 9, 13};
    Integer[] perfect = {7, 3, 1, 5, 0, 2, 4, 6, 11, 9, 13, 8, 10, 12, 14};

    Pair<Integer, String>[] pairs = new Pair[6];
    pairs[0] = new Pair<Integer, String>(1, "Jabari");
    pairs[1] = new Pair<Integer, String>(2, "Jalia");
    pairs[2] = new Pair<Integer, String>(3, "Jelani");
    pairs[3] = new Pair<Integer, String>(4, "Vanessa");
    pairs[4] = new Pair<Integer, String>(5, "Leonard");
    pairs[5] = new Pair<Integer, String>(6, "Ceazar");

    BinarySearchTree<Integer, String> tree = BinarySearchTree.balancedBinarySearchTree(pairs);

    tree.display();

    System.out.println(tree.pairs(BinarySearchTree.IN_ORDER));
    System.out.println(tree.pairs(BinarySearchTree.PRE_ORDER));
    System.out.println(tree.pairs(BinarySearchTree.POST_ORDER));
    System.out.println(tree.pairs(BinarySearchTree.LEVEL_ORDER));
  }

}
