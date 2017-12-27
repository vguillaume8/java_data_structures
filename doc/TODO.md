# Things to Implement
List of things to implement, so that I don't forget

## Build
- Implement deploy script to upload the `java_data_structures.jar`
to a remote server or artifactory
- Change `build.gradle` to only do `javadoc` for `structures` package

## General
- Make `DataStructure` objects `extend Clonable` (if not too expensive of a change)

## Trees
- Finish unit testing `BinarySearchTree`

## Vectors
- Unit test `toArray()` functions in vectors
- Perhaps chains `ChainedDataStructure` or one of the other `abstract` 
classes to be an `interface` now that we have realized that in Java 8
interfaces support method implementations
- Optimize `insert(T value)` for `ChainedDataStructure` by adding `Node tail` pointer
