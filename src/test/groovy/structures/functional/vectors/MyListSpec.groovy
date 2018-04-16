package structures.functional.vectors

import spock.lang.Shared
import spock.lang.Unroll;
import structures.vectors.List
import structures.vectors.ArrayList;
import structures.vectors.LinkedList;
import util.Spec;

abstract class MyListSpec extends Spec {
    @Shared List<Integer> list

    @Unroll
    def "Rotate matrix 90 degrees clockwise"() {
        given:
        List< List<Integer> > matrix
        int n
        String temp
        String before
        String after
        String m1 = "[" +
                "[1, 2, 3, 4, 5], " +
                "[1, 2, 3, 4, 5], " +
                "[1, 2, 3, 4, 5], " +
                "[1, 2, 3, 4, 5], " +
                "[5, 4, 3, 2, 1]"   +
                "]"

        String m2 = "[" +
                "[5, 1, 1, 1, 1], " +
                "[4, 2, 2, 2, 2], " +
                "[3, 3, 3, 3, 3], " +
                "[2, 4, 4, 4, 4], " +
                "[1, 5, 5, 5, 5]"   +
                "]"

        when:
        matrix = constructor()
        n = 5

        for (int i = 0; i < n; i++) {

            matrix.insert( (List<Integer>) constructor())

            for (int j = 0; j < n; j++) {

                if (i == n-1){
                    matrix.get(i).insert(i - j + 1)
                } else {
                    matrix.get(i).insert(j + 1)
                }

            }
        }

        before = matrix.toString()

        // Transpose matrix while
        // simultaneously inverting rows
        for (int i = 0; i < n; i++) {

            // Transpose
            for (int j = i; j < n; j++) {
                temp = matrix.get(i).get(j)
                matrix.get(i).set(matrix.get(j).get(i), j)
                matrix.get(j).set(temp, i)
            }

            // Invert row
            for (int j = 0; j < n / 2; j++) {
                temp = matrix.get(i).get(j)
                matrix.get(i).set(matrix.get(i).get(n-1-j), j)
                matrix.get(i).set(temp, n-1-j)
            }
        }

        after = matrix.toString()

        then:
        before == m1
        after  == m2
    }

    @Unroll
    def "Vector addition, subtraction, and dot product"() {
        given:
        List<Integer> v1
        List<Integer> v2
        List<Integer> vsum
        List<Integer> vdif
        int vprod
        int op1
        int op2
        int len

        when:
        v1    = (List<Integer>) constructor()
        v2    = (List<Integer>) constructor()
        vsum  = (List<Integer>) constructor()
        vdif  = (List<Integer>) constructor()
        len   = sum.size()
        vprod = 0

        for (int i = 0; i < len; i++) {
            v1.insert(input1[i])
            v2.insert(input2[i])
        }

        for (int i = 0; i < len; i++) {
            op1 = v1.get(i)
            op2 = v2.get(i)

            vsum.insert(op1 + op2) // v1 + v2
            vdif.insert(op2 - op1) // v2 - v1
            vprod += op1 * op2     // v1 * v2
        }

        then:
        vprod           == product
        vsum.toString() == sum.toString()
        vdif.toString() == difference.toString()

        where:
        product || sum                || difference      || input1          || input2
        130     || [7, 9, 11, 13, 15] || [5, 5, 5, 5, 5] || [1, 2, 3, 4, 5] || [6, 7, 8, 9, 10]
        1       || [2]                || [0]             || [1]             || [1]
    }

    @Unroll
    def "Selection sort with list"() {
        when:
        int len
        int min
        int temp
        list = (List<Integer>) constructor()

        for (Integer i : input) {
            list.insert(i)
        }

        len = list.size()

        for (int i = 0; i < len; i++) {
            min = i

            for (int j = i; j < len; j++) {
                if (list.get(j) < list.get(min)) {
                    min = j
                }
            }

            if (min != i) {
                temp = list.get(i)
                list.set(list.get(min), i)
                list.set(temp, min)
            }
        }

        then:
        list.toString() == string

        where:
        string               || input
        "[1, 2, 3, 4, 5, 6]" || [2, 1, 3, 5, 6, 4]
        "[1, 2]"             || [2, 1]
        "[1, 2, 3]"          || [1, 2, 3]
        "[1]"                || [1]
        "[]"                 || []
    }

    @Unroll
    def "Insertion sort with List"() {
        given:
        List<Integer> l
        int n
        int t
        int j

        when:
        l = (List<Integer>) constructor()

        for (Integer i : input) {
            l.insert(i)
        }

        n = (int) l.size()

        for (int i = 0; i < n; i++) {

            j = i

            while (j > 0 && l.get(j-1) > l.get(j)) {
                t = l.get(j)
                l.set(l.get(j-1), j)
                l.set(t, j-1)
                j--
            }
        }

        then:
        l.toString() == string

        where:
        string               || input
        "[1, 2, 3, 4, 5, 6]" || [2, 1, 3, 5, 6, 4]
        "[1, 2]"             || [2, 1]
        "[1, 2, 3]"          || [1, 2, 3]
        "[1]"                || [1]
        "[]"                 || []
    }

    @Unroll
    def "Binary Search with List"() {
        given:
        List<Integer> list
        boolean             found
        Integer             length
        Integer             m
        Integer             t
        Integer             l
        Integer             r

        when:
        list = (List<Integer>) constructor()

        for (int i : input) {
            list.insert(i)
        }

        found  = false
        length = list.size()
        l      = 0
        r      = length - 1

        while (r >= l) {
            m = (int) (l + r) / 2

            t = list.get(m)

            if (value == t) {
                found = true
                break

            } else if (value < t) {
                r = m - 1

            } else {
                l = m + 1
            }
        }

        then:
        found == contains

        where:
        contains || value ||  input
        true     || 1     || [1, 2, 3, 4, 5, 6, 7]
        true     || 7     || [1, 2, 3, 4, 5, 6, 7]
        true     || 4     || [1, 2, 3, 4, 5, 6, 7]
        false    || 0     || [1, 2, 3, 4, 5, 6, 7]
        false    || 8     || [1, 2, 3, 4, 5, 6, 7]

    }

    @Unroll
    def "HashMap using chaining with Lists"() {
        setup:
        HashMap<Integer, Integer> map

        when:
        map = new HashMap<Integer, Integer>(input)

        then:
        map.size()       == size
        map.toString()   == string
        map.collisions() == collisions

        where:
        size || collisions || string      || input
        1    || 0          || "[[[1:1]]]" || [ [1:1] ]
        0    || 0          || "[]"        || []
        1    || 5          || "[[[1:1]]]" ||[ [1:1], [1:1], [1:1], [1:1], [1:1], [1:1] ]
        9    || 3          || "[[[1:1]], [[2:1]], [[3:1]], [[4:1], [4:2]], [[5:1]], [[6:1]], [[7:1], [7:2]]]" || [ [1:1], [2:1], [3:1], [4:1], [4:2], [5:1], [6:1], [7:1], [7:2], [7:1] ]
    }

    class HashMap<K, V> {

        // 2-dimensional LinkedList
        private List< List< Entry<K, V> > > valueLists
        private int size
        private int collisions

        HashMap(java.util.List<Map<K, V>> input) {
            Entry<K, V>[] nullArray

            nullArray  = [null] * 16
            size       = 0
            collisions = 0

            valueLists = (List< List< Entry<K, V> > >) constructor(nullArray)

            for (java.util.Map<K, V> map : input) {
                for (java.util.Map.Entry<K, V> entry : map) {
                    this.put(entry.key, entry.value)
                }
            }
        }

        void put(K key, V value) {
            Entry<K, V> entry
            int i

            entry = new Entry<K, V>(key, value)
            i = key.hashCode() % valueLists.size()

            if (valueLists.get(i) == null) {

                valueLists.set((List<Entry<K, V>>) constructor(), i)

            } else {
                collisions++
            }

            if (!valueLists.get(i).contains(entry)) {
                valueLists.get(i).insert(entry)
                size++
            }
        }

        String toString() {
            String prefix
            StringBuilder stringBuilder

            stringBuilder = new StringBuilder()
            prefix = ""

            stringBuilder.append("[")

            for (List<Entry<K, V>> list : valueLists) {
                if (list != null) {
                    stringBuilder.append(prefix)
                    prefix = ", "
                    stringBuilder.append(list.toString())
                }
            }

            stringBuilder.append("]")

            return stringBuilder.toString()
        }

        int size() {
            return this.size
        }

        int collisions() {
            return this.collisions
        }

        private class Entry<K, V> {
            K key
            V value

            Entry(K key, V value) {
                this.key   = key
                this.value = value
            }

            boolean equals(Object object) {
                Entry<K, V> entry = (Entry<K, V>) object

                return this.key   == entry.key &&
                        this.value == entry.value
            }

            String toString() {

                return "[" + key + ":" + value + "]"
            }

        }
    }
}

class MyListSpec_ArrayList extends MyListSpec {

    def setup() {
        myClass = ArrayList
    }
}

class MyListSpec_LinkedList extends MyListSpec {

    def setup() {
        myClass = LinkedList
    }
}
