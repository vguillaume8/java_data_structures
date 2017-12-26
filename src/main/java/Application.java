import structures.trees.BinarySearchTree;

import java.util.Iterator;

import static structures.trees.BinarySearchTree.IN_ORDER;
import static structures.trees.BinarySearchTree.LEVEL_ORDER;

public final class Application {

  public static void main(String[] args) {

    Integer[] oneElement = {1};
    Integer[] unbalanced = {1, 2, 3, 4, 5, 6};
    Integer[] balancedIncomplete = {7, 3, 1, 5, 4, 6, 11, 9, 13, 8, 10};
    Integer[] Complete = {7, 3, 1, 5, 2, 0, 4, 6, 11, 9, 13, 8};
    Integer[] perfect = {7, 3, 1, 5, 0, 2, 4, 6, 11, 9, 13, 8, 10, 12, 14};

    BinarySearchTree<Comparable> tree = new BinarySearchTree<Comparable>(balancedIncomplete);

    tree.display();
  }

}
