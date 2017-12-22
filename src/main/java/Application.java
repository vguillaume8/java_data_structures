import structures.trees.BinarySearchTree;
import structures.trees.auxiliary.BinaryTree;

import java.util.Arrays;

public class Application<T> {

  public static void main(String[] args) {
    int size = 10;

    Character[] array = {
            'f',
            'i', 'h', 'g', 'k', 'j',
            'b', 'a', 'd', 'c', 'e',

    };

    BinarySearchTree<Character> tree = new BinarySearchTree<Character>(array);


//    tree.display();

    Object[] characters = tree.toArray(BinaryTree.IN_ORDER);

    for (Object c : characters) {
      System.out.println(c);
    }

  }


  public static Integer[] initArray(int size) {
    Integer[] array = new Integer[size];

    for (int i = 0; i < size; i++) {
      array[i] = i+1;
    }

    return array;
  }

}
