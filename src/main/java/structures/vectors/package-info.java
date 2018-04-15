/**
 * The core linear (vector-like) data structures in computer science.
 *
 * <ul>
 *   <li>Stack</li>
 *   <li>Queue</li>
 *   <li>List</li>
 * </ul>
 *
 * Each structure has 2 implementations - an array,
 * and linked-list implementation. Ultimately, the
 * the run time for basic operations such as insertion,
 * and deletion has O(1) constant runtime for both implementations.
 * <p>
 * The advantage for array implementations is that
 * accessing and updating the array is generally faster
 * than accessing and updating a linked-list because of
 * the overhead associated with allocating space for a new
 * node object.
 * <p>
 * However, once, the internal array becomes full, we must
 * copy all elements to a new array. This is an expensive operation,
 * but fortunately is does not happen often, as the list size
 * is doubled after each re-allocation. This means we can expect
 * an expensive insert to occur approximately log2(n) times
 * for n elements. So for a list of size 1 Million, we will only
 * have to reallocated about 20 times.
 * <p>
 * The disadvantage to the array approach is implementation. Using
 * nodes allows the implementation to be considerably simpler
 * to implement, and thus less error-prone.
 * <p>
 * From the outside perspective, they behave exactly the same. Therefore,
 * they can be used interchangeably. The may have slightly different
 * public methods as they are inheriting public methods from different
 * places. But, if instantiated with an interface (good practice) like
 * {@code Stack stack = new ArrayStack();} or
 * {@code Stack stack = new LinkedStack();}, the
 * two objects will behave identically.
 */
package structures.vectors;