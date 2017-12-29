import structures.commons.DataStructure;
import structures.trees.AVLTree;
import structures.trees.BinarySearchTree;
import structures.vectors.ArrayList;
import structures.vectors.LinkedList;
import structures.vectors.Queue;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 */
public final class Application {

  public static void main(String[] args) throws Exception {

    BinarySearchTree<Integer, Object> tree;
    BinarySearchTree<Integer, Object> avlTree;
    BinarySearchTree<Integer, Object> bstTree;

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
    Integer[] halfHalf = {5, 4, 3, 2, 1, 6, 7, 8, 9};
    Integer[] example = {10, 20, 30, 40, 50, 25};

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
    arrayList.insert(halfHalf);
    arrayList.insert(example);

    char[] letters = {'a', 'b', 'c', 'd', 'e'};

    System.out.println(Arrays.toString(letters));    // Display array first
    letters = shiftRight(letters);                   // Shift right by one
    System.out.println(Arrays.toString(letters));    // Display it again

  }

  public static char[] shiftRight(char[] array) {
    int n = array.length;

    // Move everything up
    for (int i = n-1; i >= 1; i--) {
      array[i] = array[i-1];
    }

    // Overwrite value at first index (optional)
    array[0] = ' ';

    return array;
  }

}
