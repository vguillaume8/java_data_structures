# ArrayList

An array list is a common data structure used in many programming languages.
In other programming languages, it may be called something else, but most
programming languages should have them. When working with arrays, we although
they have very fast lookup, and update functions, we realize their limitations.

Arrays are statically size. This means, the size of the array does not change.
This is why we must pass the length of an array as a parameter upon instantiating
it. Otherwise, the computer does not know how much space to allocate.

Allocating an array before using is great if we know much space out program
is going to use. But often we do not. This is where array-lists come in.


Array-lists are dynamically sized arrays. They are arrays that grow with
respect to the input. For example, if I need a list of 10 people, my array-list
will be of size 10. If I would like to list 1,000 people, then my array-list
wil be of size 1,000. In fact, for any n inputs, where n is an integer that 
is greater than or equal to zero, our array list will be of size n. Let's see how.

