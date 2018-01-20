# Java Data Structures

[![Build Status](https://travis-ci.org/N02870941/java_data_structures.svg?branch=vectors)](https://travis-ci.org/N02870941/java_data_structures)

This is a code repository that contains implementations of
common computer science data structures.

## Dependencies
This project aims to have as few third-party dependencies as possible.

#### Languages

The API is implemented with the Java Standard Edition Development Kit 8
(Java SDK 8), and the tests are written in Groovy. Graphs for used for tutorials
are generated with Python and not pertinent to development in this project.

- [Java SDK 8][java]
- [Groovy 2.4][groovy]
- [Python 2.7][python]

#### Build Tools

Build automation is handled by Gradle.

- [Gradle 4.2.1][gradle]

#### External APIs / JARs

The core API does not have any external dependencies. However, the
tests and graph generator require the following frameworks / libraries:

* [OpenCSV][open_csv]
* [Spock Framework][spock_framework]
* [Matplotlib][matplotlib]
* [NumPy][numpy]
* [SciPy Library][scipy]

**Note:** *OpenCSV and Spock will be downloaded automatically upon building
this project - no extra work is required.*

## Installation

Getting started is as simple as cloning the repository,
opening it with your favorite IDE or text editor,
and building the code.

To do this via command line, execute the following:

```bash
git clone https://github.com/N02870941/java_data_structures.git

cd java_data_structures

gradle build

gradle run -q
```

## Un-installation

You can simply delete the `java_data_structures` folder, as this is a self-contained
project. All external API dependencies will be deleted as well.

**Note:** *Be careful, as the following line is irreversible.*

```bash
rm -rf java_data_structures
```

## Contributing

If you would like to contribute or provide a bug fix,
please see the [documentation on contributing][contributing].

[java]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[groovy]: http://groovy-lang.org/download.html
[gradle]: https://gradle.org/releases/
[python]: https://www.python.org/download/releases/2.7/

[open_csv]: http://opencsv.sourceforge.net/
[spock_framework]: http://spockframework.org/spock/docs/1.1/all_in_one.html
[matplotlib]: https://matplotlib.org/index.html
[numpy]: https://pypi.python.org/pypi/numpy
[scipy]: https://www.scipy.org/scipylib/index.html

[contributing]: CONTRIBUTING.md
