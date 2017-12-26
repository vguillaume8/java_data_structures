import structures.trees.BinarySearchTree;
import java.util.ArrayList;

import static structures.trees.BinarySearchTree.IN_ORDER;
import static structures.trees.BinarySearchTree.POST_ORDER;
import static structures.trees.BinarySearchTree.PRE_ORDER;

public final class Application {

  public static void main(String[] args) {
    int size = 10;

    Character[] letters  = {'f', 'i', 'h', 'g', 'k', 'j', 'b', 'a', 'd', 'c', 'e'};
    Character[] letters2 = {'f', 'i', 'h', 'g'};
    Integer[] numbers = {1, 2, 3, 4, 5, 6, 7};
    Double[] doubles = {1.1, 3.1, 10.1, 0.0001, 19.0, 2.11};

    BinarySearchTree<Comparable> tree = new BinarySearchTree<>(numbers);
    display(tree);
    System.out.println("\n");

    for (Comparable object : numbers) {
      display(tree);
      System.out.println();
      tree.remove(object);
    }
  }

  /**
   * Display tree's important information to verify that
   * it was build correctly. This method is for development purposes
   */
  @SuppressWarnings("unchecked")
  public static <T extends Comparable> void display(BinarySearchTree<T> tree) {
    int size   = tree.size();
    int height = tree.height();

    System.out.println(tree.getClass().getSimpleName());
    System.out.println("Size: "      + size);
    System.out.println("Height: "    + height);
    System.out.println("inorder: "   + tree.toString(IN_ORDER));
    System.out.println("preorder: "  + tree.toString(PRE_ORDER));
    System.out.println("postorder: " + tree.toString(POST_ORDER));
  }

}
