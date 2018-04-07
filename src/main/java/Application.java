import structures.commons.DataStructure;
import structures.trees.AVLTree;
import structures.trees.BinarySearchTree;
import structures.vectors.ArrayList;
import structures.vectors.LinkedList;
import structures.vectors.Queue;
import java.util.Arrays;


/**
 *
 */
public final class Application {

  public static void main(String[] args) throws Exception {

    Integer[] src = {1, 2, 3, 4, 5, 6};
    Integer[] dst = {0, 0, 0, 0, 0, 0, 0};

//    dst = ArrayList.copy(2, 3, 1, src, dst);


    String s = Arrays.toString(dst);

    System.out.println(s);
  }

}
