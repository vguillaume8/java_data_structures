# Data Structures

This is project is a tutorial that covers common data structures that are
seen in introductory and intermediate computer science courses. The site contains
explanations, diagrams, and mathematical proofs for each data structure. In addition
to the online tutorials, the data structures can be downloaded as a 
[Java 8 library][jar_file] for
readers to test with their own code.

## What Will Be Covered

### Vectors

Vectors are any data structure that takes a linear form. These are data 
structures that are used to represent lists, sequences, or provide some 
sort of ordering to data.

##### Queue

Queues are what's called a First in First Out (FIFO) type of data structure.
That is, elements that pass through a queue are done sequentially. You can
think of queues like lines in a grocery store, or songs in a playlist.
[Learn more on Queues...][queue]

##### Stack

A Stack is a First in Last Out (FILO) data structure. The name is
self-explanatory. Imagine removing books from a book bag, and stacking
them onto the table. If you were to now, remove each book one by one
from the top down, and place them back into your book bag, the first book
that you placed on the table would be the last book to come out the stack.
[Learn more on Stacks...][stack]

##### LinkedList

The LinkedList is a more general combination of both the Stack and the Queue.
The LinkedList allows for insertion and removal of elements from the
front, the back, and anywhere in between. The idea of a linked-list really
highlights the importance of pointers, and chaining elements. This is a very
different idea of achieving similar results as the Array.
[Learn more of LinkedLists...][linked_list]


##### ArrayList

ArrayLists are very similar to arrays. They are indexed data structures.
The difference is that they grow. Unlike an array, which's length needs to
be declared when the array is created, the ArrayList's length grows as elements
are inserted. [Read more about ArrayLists...][array_list] 


## Implementation

All of the aforementioned data structures are implemented in the Java 
programming language. The code is available on [GitHub][github_repo].


[Setup and Installation][setup_md]

[See Java Documentation][javadoc]


[//]: Links
[jar_file]: http://jabaridash.com:8085/build/java_data_structures.jar
[setup_md]: docs/setup.md
[javadoc]: docs/javadoc/index.html

[queue]: docs/structures/vectors/Queue.md
[stack]: docs/structures/vectors/Stack.md
[linked_list]: docs/structures/vectors/LinkedList.md
[array_list]: docs/structures/vectors/ArrayList.md

[github_repo]: https://github.com/N02870941/java_data_structures
