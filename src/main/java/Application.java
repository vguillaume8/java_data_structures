import structures.trees.AVLTree;
import structures.commons.Pair;
import structures.trees.BinarySearchTree;
import structures.trees.Tree;
import structures.vectors.ArrayList;
import util.Util;

/**
 *
 */
public final class Application {

  public static void main(String[] args) throws Exception {

    Integer[] empty = {};
    Integer[] single = {1};
    Integer[] two = {1, 2};
    Integer[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    Integer[] unbalanced = {1, 2, 3, 4, 5, 6};
    Integer[] balancedIncompleteFull = {7, 3, 1, 5, 4, 6, 11, 9, 13, 8, 10};
    Integer[] balancedIncompleteNotFull = {7, 3, 1, 5, 6, 11, 9, 13, 8, 10};
    Integer[] fullAndIncomplete = {7, 3, 1, 5, 2, 0, 4, 6, 11, 9, 13, 8};
    Integer[] fullAndComplete = {7, 3, 1, 5, 2, 0, 4, 6, 11, 9, 13};
    Integer[] perfect = {7, 3, 1, 5, 0, 2, 4, 6, 11, 9, 13, 8, 10, 12, 14};

    ArrayList<Integer[]> arrayList = new ArrayList<>();

    arrayList.insert(empty);
    arrayList.insert(single);
    arrayList.insert(two);
    arrayList.insert(sorted);
    arrayList.insert(unbalanced);
    arrayList.insert(balancedIncompleteFull);
    arrayList.insert(balancedIncompleteNotFull);
    arrayList.insert(fullAndIncomplete);
    arrayList.insert(fullAndComplete);
    arrayList.insert(perfect);

    for (Integer[] intArray : arrayList) {

      System.out.println("Input array: " + Util.ArrayToString(intArray));

      BinarySearchTree<Integer, Object> tree = new AVLTree<>(intArray);

      tree.display();


    }

  }

}
