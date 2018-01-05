package analytics.trees

import commons.Java8Util
import commons.Util
import spock.lang.Shared
import spock.lang.Specification
import structures.trees.AVLTree
import structures.trees.BinarySearchTree


class Trees extends Specification {

    @Shared numberOfKeys = 10000;

    def "Height of AVL Tree vs. n sorted keys"() {
        setup:
        BinarySearchTree<Integer, String> tree = new AVLTree<>();
        java.util.ArrayList<Number[]> points = new ArrayList<>();
        Number[] point

        when:
        for (int i = 0; i < length; i++) {
            tree.insert(i, "");


            point = new Integer[2]
            point[0] = tree.size();
            point[1] = tree.height();

            points.add(point);
        }

        then:
        Java8Util.generateCSV(experimentName, points)
        Util.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "n",
                "L(n)",
                false,
                false,
                false,
                false,
                true,
                true
        )

        where:

        length        | experimentName                        | plotTitle
        numberOfKeys  | "levels_in_avl_tree_vs_n_sorted_keys" | "levels_in_avl_tree_vs_n_sorted_keys"

    }

    def "Height of BST Tree vs. n sorted keys"() {
        setup:
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        java.util.ArrayList<Number[]> points = new ArrayList<>();
        Number[] point

        when:
        for (int i = 0; i < length; i++) {
            tree.insert(i, "");


            point = new Integer[2]
            point[0] = tree.size();
            point[1] = tree.height();

            points.add(point);
        }

        then:
        Java8Util.generateCSV(experimentName, points)
        Util.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "n",
                "L(n)",
                false,
                false,
                false,
                false,
                true,
                true
        )

        where:

        length        | experimentName                        | plotTitle
        numberOfKeys  | "levels_in_bst_tree_vs_n_sorted_keys" | "levels_in_bst_tree_vs_n_sored_keys"

    }

    def "Height of BST Tree vs. n random keys"() {
        setup:
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        java.util.ArrayList<Number[]> points = new ArrayList<>();
        Number[] point

        when:
        for (int i = 0; i < length; i++) {
            tree.insert(Java8Util.rand(0, length), "");


            point = new Integer[2]
            point[0] = tree.size();
            point[1] = tree.height();

            points.add(point);
        }

        then:
        Java8Util.generateCSV(experimentName, points)
        Util.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "n",
                "L(n)",
                false,
                false,
                false,
                false,
                true,
                true
        )

        where:

        length        | experimentName                        | plotTitle
        numberOfKeys  | "levels_in_bst_tree_vs_n_random_keys" | "levels_in_bst_tree_vs_n_random_keys"

    }

    def "Height of AVL Tree vs. Number of nodes"() {
        setup:
        BinarySearchTree<Integer, String> tree = new AVLTree<>();
        java.util.ArrayList<Number[]> points = new ArrayList<>();
        Number[] point

        when:
        for (int i = 0; i < length; i++) {
            tree.insert(Java8Util.rand(0, length), "");


            point = new Integer[2]
            point[0] = tree.size();
            point[1] = tree.height();

            points.add(point);
        }

        then:
        Java8Util.generateCSV(experimentName, points)
        Util.generatePlot(
                experimentName,
                plotTitle,
                "Input_data",
                "n",
                "L(n)",
                false,
                false,
                false,
                false,
                true,
                true
        )

        where:

        length        | experimentName                        | plotTitle
        numberOfKeys  | "levels_in_avl_tree_vs_n_random_keys" | "levels_in_avl_tree_vs_n_random_keys"

    }
}
