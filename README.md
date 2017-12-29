# Data Structures

This is project is a tutorial that covers common data structures that are
seen in introductory and intermediate computer science courses. The site contains
explanations, diagrams, and mathematical proofs for each data structure. In addition
to the online tutorials, the data structures can be downloaded as a 
[Java 8 library][jar_file] for
readers to test with their own code.

## Prerequisites

This tutorial assumes that the reader has either taken or is taking an introduction to
programming course or has the equivalent knowledge. Knowing a the Java 
programming language would be useful for reading code examples, but the
idea behind the data structures are language agnostic.

The core programming concepts that the reader should know include:

* Arrays
* Variables
* Methods / Functions
* Loops
* Recursion

## What Will Be Covered

The following categories will be covered by this project.

### Vectors

Vectors are any data structure that takes a linear form. These are data 
structures that are used to represent lists, sequences, or provide some 
sort of ordering to data. [Learn more about Vectors...][vector]

### Trees

Trees are non-linear data structures often used for keep data organized.
We typically use trees when the data that we are working with can be 
sorted / ordered. [Learn more about Trees...][tree]

### Sets

A Set is a dynamically sized group of unordered unique elements.
Finding an application for a set may seem more challenging, 
when we can just put them into some sort of list. The advantage 
comes in the speed of lookup and insertion.
[Learn more about Sets...][set]

### Maps

Maps are like dictionaries. They are unordered, dynamically sized
groups of unique key to non-unique value mappings - hence the name, 
Map. Maps have many applications, such as implementing dictionaries 
(key is to word, as value is to definition).
[Learn more about Maps][map]

### Graphs

Graphs are an abstract data structure with tons of applications.
In essence, a graph is a set of objects that are (potentially)
connected to other objects in the set. A good application of a graph
is to represent a highway system between cities.
[Learn more about Maps...][map]

## The Code

All of the aforementioned data structures are implemented in the Java 
programming language. The code is available as a library in the following formats:

 * [Source code GitHub][github_repo].
 * [Java Executable Binary][jar_file]
 
 For more information on about the API, please
 see the following links.

[Setup and Installation][setup_md]

[API Documentation][javadoc]


[jar_file]: http://jabaridash.com:8085/build/java_data_structures.jar
[setup_md]: docs/dev/setup/SETUP.md
[javadoc]: docs/javadoc/index.html

[vector]: docs/structures/vectors/Vector.md
[tree]: docs/structures/trees/Tree.md
[graph]: docs/structures/graphs/Graph.md
[map]: docs/structures/maps/Map.md
[set]: docs/structures/sets/Set.md

[github_repo]: https://github.com/N02870941/java_data_structures

