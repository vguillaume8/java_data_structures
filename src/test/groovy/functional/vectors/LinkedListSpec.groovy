package functional.vectors

import spock.lang.Specification
import spock.lang.Unroll
import structures.vectors.LinkedList

class LinkedListSpec extends Specification {

    class HashMap<K, V> {

        // 2-dimensional LinkedList
        private LinkedList< LinkedList< Entry<K, V> > > valueLists
        private int size
        private int collisions

        HashMap(List<Map<K, V>> input) {
            Entry<K, V>[] nullArray = [null] * 16

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

        int size() {
            return this.size
        }

        String toString() {
            StringBuilder stringBuilder = new StringBuilder()

            stringBuilder.append("[")

            for (LinkedList<Entry<K, V>> list : valueLists) {
                if (list != null) {
                    stringBuilder.append(list.toString() + ",")
                }
            }

            stringBuilder.append("]")

            return stringBuilder.toString()
        }

        private class Entry<K, V> {
            K key
            V value

            Entry(K key, V value) {
                this.key   = key
                this.value = value
            }

            String toString() {

                return "[" + key + ", " + value + "]"
            }

            boolean equals(Object object) {
                Entry<K, V> entry = (Entry<K, V>) object

                return this.key   == entry.key &&
                       this.value == entry.value
            }
        }
    }

    @Unroll
    def "HashMap using chaining"() {
        setup:
        HashMap<Integer, Integer> map

        when:
        map = new HashMap<Integer, Integer>(input)

        println(map.toString())

        then:
        map.size() == size

        where:
        size || input
        9    || [ [1:1], [2:1], [3:1], [4:1], [4:2], [5:1], [6:1], [7:1], [7:2], [7:1] ]
        1    || [ [1:1] ]
        0    || []
        1    || [ [1:1], [1:1], [1:1], [1:1], [1:1], [1:1] ]
    }
}
