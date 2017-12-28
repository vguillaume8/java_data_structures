package structures.trees

import spock.lang.Shared
import structures.commons.Pair
import spock.lang.Specification
import spock.lang.Unroll
import structures.vectors.ArrayList
import structures.trees.BinarySearchTree.BinarySearchTreeNode
import util.Util;

class BinarySearchTreeSpec extends Specification {

    @Shared Integer[] sorted = [1, 2, 3, 4, 5, 6, 7, 8, 9];
    @Shared Integer[] unbalanced = [1, 2, 3, 4, 5, 6];
    @Shared Integer[] balancedIncompleteFull = [7, 3, 1, 5, 4, 6, 11, 9, 13, 8, 10];
    @Shared Integer[] balancedIncompleteNotFull = [7, 3, 1, 5, 6, 11, 9, 13, 8, 10];
    @Shared Integer[] fullAndIncomplete = [7, 3, 1, 5, 2, 0, 4, 6, 11, 9, 13, 8];
    @Shared Integer[] fullAndComplete = [7, 3, 1, 5, 2, 0, 4, 6, 11, 9, 13];
    @Shared Integer[] perfect = [7, 3, 1, 5, 0, 2, 4, 6, 11, 9, 13, 8, 10, 12, 14];

    @Unroll
    def "Construct empty BinarySearchTree"() {
        when:
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();

        then:
        tree.size() == 0
        tree.empty()
    }

    @Unroll
    def "Construct BinarySearchTree from array of keys"() {
        when:
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>(keys);

        then:
        tree.size() == size
        tree.empty() == empty

        where:
        keys                         | size | empty
        [1, 2, 3, 4, 5] as Integer[] | 5    | false
        [] as Integer[]              | 0    | true
        [1] as Integer[]             | 1    | false
    }

    @Unroll
    def "Construct BinarySearchTree from array of pairs"() {
        setup:
        BinarySearchTree<Integer, String> tree;

        Pair<Integer, String>[] pairs = new BinarySearchTreeNode[6];
        pairs[0] = new BinarySearchTreeNode<Integer, String>(1, "Jabari");
        pairs[1] = new BinarySearchTreeNode<Integer, String>(2, "Jalia");
        pairs[2] = new BinarySearchTreeNode<Integer, String>(3, "Jelani");
        pairs[3] = new BinarySearchTreeNode<Integer, String>(4, "Vanessa");
        pairs[4] = new BinarySearchTreeNode<Integer, String>(5, "Leonard");
        pairs[5] = new BinarySearchTreeNode<Integer, String>(6, "Ceazar");

        when:
        tree = new BinarySearchTree<>(pairs);

        then:
        tree.size() == 6
        !tree.empty()
    }

    @Unroll
    def "Check if tree contains a key"() {
        when:
        BinarySearchTree<Comparable, String> tree = new BinarySearchTree<>(array)

        then:
        tree.contains(value) == contains

        where:
        array                                    | value         | contains
        [1, 2, 3, 4, 5, 6, 7] as Integer[]       | 7             | true
        ['a', 'b', 'c', 'd', 'e'] as Character[] | 'm' as char   | false
        [] as Double[]                           | 0.0           | false
        [1.0] as Float[]                         | 1.0 as float  | true
    }

    @Unroll
    def "Check if tree contains a key value pair"() {
        when:
        BinarySearchTree<Integer, String> tree;

        Pair<Integer, String>[] pairs = new BinarySearchTreeNode[8];
        pairs[0] = new BinarySearchTreeNode<Integer, String>(1, "Jabari");
        pairs[1] = new BinarySearchTreeNode<Integer, String>(2, "Jalia");
        pairs[2] = new BinarySearchTreeNode<Integer, String>(3, "Jelani");
        pairs[3] = new BinarySearchTreeNode<Integer, String>(4, "Vanessa");
        pairs[4] = new BinarySearchTreeNode<Integer, String>(5, "Leonard");
        pairs[5] = new BinarySearchTreeNode<Integer, String>(6, "Ceazar");
        pairs[6] = new BinarySearchTreeNode<Integer, String>(7, "Jendaya");
        pairs[7] = new BinarySearchTreeNode<Integer, String>(8, "Elijah");

        tree = new BinarySearchTree<>(pairs);

        then:
        tree.contains(key, value) == contains

        where:
        key | value     | contains
        1   | "Jabari"  | true
        2   | "Jalia"   | true
        3   | "Vanessa" | false
        4   | "Vanessa" | true
    }

    @Unroll
    def "Check if tree is empty"() {
        setup:
        BinarySearchTree<Comparable, Object> tree

        when:
        if (array != null)
            tree = new BinarySearchTree<>(array)

        else
            tree = new BinarySearchTree<>();

        then:
        tree.empty() == empty

        where:
        array                                    | empty
        [1, 2, 3, 4, 5, 6, 7] as Integer[]       | false
        ['a', 'b', 'c', 'd', 'e'] as Character[] | false
        [] as Double[]                           | true
        [1.0] as Float[]                         | false
        null                                     | true
    }

    @Unroll
    def "Get a value from tree by key"() {
        setup:
        BinarySearchTree<Integer, String> tree;

        Pair<Integer, String>[] pairs = new BinarySearchTreeNode[8];
        pairs[0] = new BinarySearchTreeNode<Integer, String>(1, "Jabari");
        pairs[1] = new BinarySearchTreeNode<Integer, String>(2, "Jalia");
        pairs[2] = new BinarySearchTreeNode<Integer, String>(3, "Jelani");
        pairs[3] = new BinarySearchTreeNode<Integer, String>(4, "Vanessa");
        pairs[4] = new BinarySearchTreeNode<Integer, String>(5, "Leonard");
        pairs[5] = new BinarySearchTreeNode<Integer, String>(6, "Ceazar");
        pairs[6] = new BinarySearchTreeNode<Integer, String>(7, "Jendaya");
        pairs[7] = new BinarySearchTreeNode<Integer, String>(8, "Elijah");

        when:
        tree = new BinarySearchTree<>(pairs);

        then:
        tree.get(key) == value

        where:
        value     | key
        "Jabari"  | 1
        "Jalia"   | 2
        "Jelani"  | 3
        "Vanessa" | 4
        "Leonard" | 5
        "Ceazar"  | 6
        "Jendaya" | 7
        "Elijah"  | 8
        null      | 9
    }

    @Unroll
    def "Get a key from tree by value"() {
        setup:
        BinarySearchTree<Integer, String> tree;

        Pair<Integer, String>[] pairs = new BinarySearchTreeNode[8];
        pairs[0] = new BinarySearchTreeNode<Integer, String>(1, "Jabari");
        pairs[1] = new BinarySearchTreeNode<Integer, String>(2, "Jalia");
        pairs[2] = new BinarySearchTreeNode<Integer, String>(3, "Jelani");
        pairs[3] = new BinarySearchTreeNode<Integer, String>(4, "Vanessa");
        pairs[4] = new BinarySearchTreeNode<Integer, String>(5, "Leonard");
        pairs[5] = new BinarySearchTreeNode<Integer, String>(6, "Ceazar");
        pairs[6] = new BinarySearchTreeNode<Integer, String>(7, "Jendaya");
        pairs[7] = new BinarySearchTreeNode<Integer, String>(8, "Elijah");

        when:
        tree = new BinarySearchTree<>(pairs);

        then:
        tree.getKey(value) == key

        where:
        value     | key
        "Jabari"  | 1
        "Jalia"   | 2
        "Jelani"  | 3
        "Vanessa" | 4
        "Leonard" | 5
        "Ceazar"  | 6
        "Jendaya" | 7
        "Elijah"  | 8
        "Jamaal"  | null;
    }

    @Unroll
    def "Check height of tree"() {
        when:
        BinarySearchTree<Comparable, Object> tree = new BinarySearchTree<>(values)

        then:
        tree.height() == height

        where:
        values                                   | height
        [1, 2, 3, 4, 5, 6] as Integer[]          | 6
        ['c', 'a', 'b', 'd', 'e'] as Character[] | 3
        [1] as Double[]                          | 1
        [] as Float[]                            | 0
    }

    @Unroll
    def "Insert a value into tree"() {
        setup:
        BinarySearchTree<Comparable, Object> tree = new BinarySearchTree<>(values)

        when:
        tree.insert(value)

        then:
        tree.contains(check) == contains

        where:
        values                                   | value         | check         | contains
        [1, 2, 3, 4, 5, 6] as Integer[]          | 7 as int      | 8 as int      | false
        ['c', 'a', 'b', 'd', 'e'] as Character[] | 'm' as char   | 'm' as char   | true
        [1] as Double[]                          | 2.0 as double | 3.0 as double | false
        [] as Float[]                            | 1 as float    | 1 as float    | true
    }

    @Unroll
    def "Insert pair into tree"() {
        setup:
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();

        Pair<Integer, String>[] pairs = new BinarySearchTreeNode[8];
        pairs[0] = new BinarySearchTreeNode<Integer, String>(1, "Jabari");
        pairs[1] = new BinarySearchTreeNode<Integer, String>(2, "Jalia");
        pairs[2] = new BinarySearchTreeNode<Integer, String>(3, "Jelani");
        pairs[3] = new BinarySearchTreeNode<Integer, String>(4, "Vanessa");
        pairs[4] = new BinarySearchTreeNode<Integer, String>(5, "Leonard");
        pairs[5] = new BinarySearchTreeNode<Integer, String>(6, "Ceazar");
        pairs[6] = new BinarySearchTreeNode<Integer, String>(7, "Jendaya");
        pairs[7] = new BinarySearchTreeNode<Integer, String>(8, "Elijah");

        when:
        tree.insert(pairs[0].key(), pairs[0].value());  // Jabari, 1
        tree.insert(pairs[2].key(), pairs[0].value());  // Jelani, 3
        tree.insert(pairs[4].key(), pairs[0].value());  // Leonard, 5
        tree.insert(pairs[6].key(), pairs[0].value());  // Jendaya, 7

        then:
        tree.contains(key, value) == contains

        where:
        key | value     | contains
        1   | "Jabari"  | true
        2   | "Jalia"   | false
        3   | "Vanessa" | false
        4   | "Vanessa" | false
    }

    @Unroll
    def "Check that tree is balanced"() {
        when:
        BinarySearchTree<Integer, Object> tree = new BinarySearchTree<>(values)

        then:
        tree.isBalanced() == balanced

        where:
        values                    | balanced
        sorted                    | false
        unbalanced                | false
        balancedIncompleteFull    | true
        balancedIncompleteNotFull | true
        fullAndIncomplete         | true
        fullAndComplete           | true
        perfect                   | true
    }

    @Unroll
    def "Check that tree is complete"() {
        when:
        BinarySearchTree<Integer, Object> tree = new BinarySearchTree<>(values)

        then:
        tree.isComplete() == complete

        where:
        values                    | complete
        sorted                    | false
        unbalanced                | false
        balancedIncompleteFull    | false
        balancedIncompleteNotFull | false
        fullAndIncomplete         | true
        fullAndComplete           | true
        perfect                   | true
    }

    @Unroll
    def "Check that tree is full"() {
        when:
        BinarySearchTree<Integer, Object> tree = new BinarySearchTree<>(values)

        then:
        tree.isFull() == full

        where:
        values                    | full
        sorted                    | false
        unbalanced                | false
        balancedIncompleteFull    | true
        balancedIncompleteNotFull | false
        fullAndIncomplete         | false
        fullAndComplete           | true
        perfect                   | true
    }

    @Unroll
    def "Check that tree is perfect"() {
        when:
        BinarySearchTree<Integer, Object> tree = new BinarySearchTree<>(values)

        then:
        tree.isPerfect() == isPerfect

        where:
        values                    | isPerfect
        sorted                    | false
        unbalanced                | false
        balancedIncompleteFull    | false
        balancedIncompleteNotFull | false
        fullAndIncomplete         | false
        fullAndComplete           | false
        perfect                   | true
    }

    @Unroll
    def "Get array of keys without specifying array type, or order"() {
        setup:
        BinarySearchTree<Integer, String> tree;

        Pair<Integer, String>[] pairs = new BinarySearchTreeNode[8];
        pairs[0] = new BinarySearchTreeNode<Integer, String>(1, "Jabari");
        pairs[1] = new BinarySearchTreeNode<Integer, String>(2, "Jalia");
        pairs[2] = new BinarySearchTreeNode<Integer, String>(3, "Jelani");
        pairs[3] = new BinarySearchTreeNode<Integer, String>(4, "Vanessa");
        pairs[4] = new BinarySearchTreeNode<Integer, String>(5, "Leonard");
        pairs[5] = new BinarySearchTreeNode<Integer, String>(6, "Ceazar");
        pairs[6] = new BinarySearchTreeNode<Integer, String>(7, "Jendaya");
        pairs[7] = new BinarySearchTreeNode<Integer, String>(8, "Elijah");

        when:
        tree = new BinarySearchTree<>(pairs);

        then:
        Util.equals(tree.keys(), keys);

        where:
        keys                                     | _
        [1, 2, 3, 4, 5, 6, 7, 8] as Comparable[] | _
    }

    @Unroll
    def "Get array of keys and specify order"() {
        when:
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>(perfect);

        then:
        Util.equals(tree.keys(order), output);

        where:
        output                                                             | order
        [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14] as Comparable[] | BinarySearchTree.IN_ORDER
        [7, 3, 1, 0, 2, 5, 4, 6, 11, 9, 8, 10, 13, 12, 14] as Comparable[] | BinarySearchTree.PRE_ORDER
        [0, 2, 1, 4, 6, 5, 3, 8, 10, 9, 12, 14, 13, 11, 7] as Comparable[] | BinarySearchTree.POST_ORDER
        [7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14] as Comparable[] | BinarySearchTree.LEVEL_ORDER
    }

    @Unroll
    def "Get array of pairs specifying or order"() {
        setup:
        BinarySearchTree<Integer, String> tree
        structures.vectors.ArrayList<Pair<Integer, String>> arrayList

        Pair<Integer, String>[] pairs = new BinarySearchTreeNode[3];
        pairs[0] = new BinarySearchTreeNode<Integer, String>(2, "Jalia")
        pairs[1] = new BinarySearchTreeNode<Integer, String>(1, "Jabari")
        pairs[2] = new BinarySearchTreeNode<Integer, String>(3, "Jelani")

        when:
        tree = new BinarySearchTree<>(pairs)
        arrayList = new structures.vectors.ArrayList<Pair<Integer, String>>(pairs)
        def p = tree.pairs(BinarySearchTree.IN_ORDER)

        then:
        p.equals(arrayList);
    }

    @Unroll
    def "Remove by key from tree"() {
        when:
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>(keys);

        then:
        tree.remove(key) == removed

        where:
        keys                                                               | key | removed
        [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14] as Comparable[] | 15  | false
        [7, 3, 1, 0, 2, 5, 4, 6, 11, 9, 8, 10, 13, 12, 14] as Comparable[] | -1  | false
        [0, 2, 1, 4, 6, 5, 3, 8, 10, 9, 12, 14, 13, 11, 7] as Comparable[] | 0   | true
        [7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14] as Comparable[] | 1   | true
    }

    @Unroll
    def "Remove by value from tree"() {
        setup:
        BinarySearchTree<Integer, String> tree;

        Pair<Integer, String>[] pairs = new BinarySearchTreeNode[8];
        pairs[0] = new BinarySearchTreeNode<Integer, String>(1, "Jabari");
        pairs[1] = new BinarySearchTreeNode<Integer, String>(2, "Jalia");
        pairs[2] = new BinarySearchTreeNode<Integer, String>(3, "Jelani");
        pairs[3] = new BinarySearchTreeNode<Integer, String>(4, "Vanessa");
        pairs[4] = new BinarySearchTreeNode<Integer, String>(5, "Leonard");
        pairs[5] = new BinarySearchTreeNode<Integer, String>(6, "Ceazar");
        pairs[6] = new BinarySearchTreeNode<Integer, String>(7, "Jendaya");
        pairs[7] = new BinarySearchTreeNode<Integer, String>(8, "Elijah");

        tree = new BinarySearchTree<>(pairs);

        expect:
        tree.removeValue(value) == removed

        where:
        value    | removed
        "Jabari" | true
        "Jalia"  | true
        "Jelani" | true
        "Bob"    | false
    }

    @Unroll
    def "Sort an array of keys"() {
        setup:
        Comparable[] array = BinarySearchTree.sort(keys);

        expect:
        Util.isSorted(array)

        where:
        keys                                                               | _
        [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14] as Comparable[] | _
        [7, 3, 1, 0, 2, 5, 4, 6, 11, 9, 8, 10, 13, 12, 14] as Comparable[] | _
        [0, 2, 1, 4, 6, 5, 3, 8, 10, 9, 12, 14, 13, 11, 7] as Comparable[] | _
        [7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14] as Comparable[] | _
        [1] as Comparable[]                                                | _
        [] as Comparable[]                                                 | _
    }

    @Unroll
    def "Sort an Arraylist of pairs"() {
        setup:
        BinarySearchTree<Integer, String> tree
        boolean ascendingOrder = true

        Pair<Integer, String>[] pairs = new BinarySearchTreeNode[8];
        pairs[0] = new BinarySearchTreeNode<Integer, String>(1, "Jabari");
        pairs[1] = new BinarySearchTreeNode<Integer, String>(2, "Jalia");
        pairs[2] = new BinarySearchTreeNode<Integer, String>(3, "Jelani");
        pairs[3] = new BinarySearchTreeNode<Integer, String>(4, "Vanessa");
        pairs[4] = new BinarySearchTreeNode<Integer, String>(5, "Leonard");
        pairs[5] = new BinarySearchTreeNode<Integer, String>(6, "Ceazar");
        pairs[6] = new BinarySearchTreeNode<Integer, String>(7, "Jendaya");
        pairs[7] = new BinarySearchTreeNode<Integer, String>(8, "Elijah");

        when:
        tree = new BinarySearchTree<>(pairs)
        ArrayList<Pair<Comparable, Object>> arrayList = BinarySearchTree.sort(tree.pairs());

        int size = arrayList.size();

        // Check that ith value is less than value at i+1
        for (int i = 0; i < size-1; i++) {

            if (arrayList.get(i).key().compareTo(arrayList.get(i+1).key()) > 0) {
                ascendingOrder = false;
                break;
            }
        }

        then:
        ascendingOrder

    }

    @Unroll
    def "Test toString(traversalType) method"() {
        setup:
        BinarySearchTree<Comparable, Object> tree = new BinarySearchTree<>(values)

        expect:
        tree.toString(traversalType) == string

        where:
        values                 | traversalType                | string
        balancedIncompleteFull | BinarySearchTree.IN_ORDER    | "[1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13]"
        balancedIncompleteFull | BinarySearchTree.PRE_ORDER   | "[7, 3, 1, 5, 4, 6, 11, 9, 8, 10, 13]"
        balancedIncompleteFull | BinarySearchTree.POST_ORDER  | "[1, 4, 6, 5, 3, 8, 10, 9, 13, 11, 7]"
        balancedIncompleteFull | BinarySearchTree.LEVEL_ORDER | "[7, 3, 11, 1, 5, 9, 13, 4, 6, 8, 10]"
    }

    @Unroll
    def "Get value[] from tree"() {
        setup:
        BinarySearchTree<Integer, String> tree;

        Pair<Integer, String>[] pairs = new BinarySearchTreeNode[8];
        pairs[0] = new BinarySearchTreeNode<Integer, String>(4, "Jabari");
        pairs[1] = new BinarySearchTreeNode<Integer, String>(7, "Jalia");
        pairs[2] = new BinarySearchTreeNode<Integer, String>(1, "Jelani");
        pairs[3] = new BinarySearchTreeNode<Integer, String>(3, "Vanessa");
        pairs[4] = new BinarySearchTreeNode<Integer, String>(5, "Leonard");
        pairs[5] = new BinarySearchTreeNode<Integer, String>(6, "Ceazar");
        pairs[6] = new BinarySearchTreeNode<Integer, String>(2, "Jendaya");
        pairs[7] = new BinarySearchTreeNode<Integer, String>(8, "Elijah");

        when:
        tree = new BinarySearchTree<>(pairs);

        then:
        Util.equals(tree.values(traversalType), values);

        where:
        traversalType                | values
        BinarySearchTree.IN_ORDER    | ["Jelani", "Jendaya", "Vanessa", "Jabari", "Leonard", "Ceazar", "Jalia", "Elijah"] as String[]
        BinarySearchTree.PRE_ORDER   | ["Jabari", "Jelani", "Vanessa", "Jendaya", "Jalia", "Leonard", "Ceazar", "Elijah"] as String[]
        BinarySearchTree.POST_ORDER  | ["Jendaya", "Vanessa", "Jelani", "Ceazar", "Leonard", "Elijah", "Jalia", "Jabari"] as String[]
        BinarySearchTree.LEVEL_ORDER | ["Jabari", "Jelani", "Jalia", "Vanessa", "Leonard", "Elijah", "Jendaya", "Ceazar"] as String[]
    }
}
