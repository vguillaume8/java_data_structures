package functional.vectors

import spock.lang.Specification
import spock.lang.Unroll
import structures.vectors.LinkedList

class LinkedListSpec extends Specification {

    @Unroll
    def "Insertion with LinkedList"() {
        setup:
        LinkedList<Integer> l
        int n
        int t
        int j

        when:
        l = new LinkedList<Integer>()

        for (Integer i : input) {
            l.insert(i)
        }

        n = (int) l.size()

        for (int i = 0; i < n; i++) {

            j = i

            while (j > 0 && l.get(j-1) > l.get(j)) {
                t = l.get(j)
                l.update(l.get(j-1), j)
                l.update(t, j-1)
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
    def "Binary Search with LinkedList"() {
        setup:
        LinkedList<Integer> list
        boolean             found
        Integer             length
        Integer             m
        Integer             t
        Integer             l
        Integer             r

        when:
        list = new LinkedList<Integer>()

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
    def "HashMap using chaining with LinkedList"() {
        setup:
        HashMap<Integer, Integer> map

        when:
        map = new HashMap<Integer, Integer>(input)

        println(map.toString())

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
        private LinkedList< LinkedList< Entry<K, V> > > valueLists
        private int size
        private int collisions

        HashMap(List<Map<K, V>> input) {
            Entry<K, V>[] nullArray

            nullArray  = [null] * 16
            size       = 0
            collisions = 0

            valueLists = new LinkedList< LinkedList<Entry<K, V>> >(nullArray)

            for (Map<K, V> map : input) {
                for (Map.Entry<K, V> entry : map) {
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
                valueLists.update(new LinkedList<Entry<K, V>>(), i)

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

            for (LinkedList<Entry<K, V>> list : valueLists) {
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
