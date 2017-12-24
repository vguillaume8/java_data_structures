import structures.trees.BinarySearchTree;

public class Application<T> {

  public static void main(String[] args) {
    int size = 10;

    Character[] letters = {
            'f',
            'i', 'h', 'g', 'k', 'j',
            'b', 'a', 'd', 'c', 'e',

    };

    Integer[] numbers = {1, 2, 3, 4, 5, 6, 7};

    Double[] doubles = {1.1, 3.1, 10.1, 0.0001, 19.0, 2.11};

    BinarySearchTree<Comparable> tree = new BinarySearchTree<>(letters);

    tree.display();

  }


}
