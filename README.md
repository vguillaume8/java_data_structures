# Java Data Structures

[![Build Status](https://travis-ci.org/N02870941/java_data_structures.svg?branch=vectors)](https://travis-ci.org/N02870941/java_data_structures)

This is a code repository that contains implementations of
common computer science data structures. However, if you are only
interested using the data structures in another project, you may
simply download the runnable binary `java_data_structures.jar` from the `/bin` directory and include it in your project. By default, a test application
that tests the data structures is run. However, simply including the `.jar`
file will allow you to import any data structure that the API offers.

## Dependencies
This project aims to have as few third-party dependencies as possible. However,
we are assuming that you have Java 8, Gradle, and Python 2.7 installed on
your development machine. With this, you can build and run the code.

**Note:** *Python and Groovy are only necessary to run the tests, but Java 8
and Gradle are required to build and run the API.*

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

- [OpenCSV][open_csv]
- [Spock Framework][spock_framework]
- [Matplotlib][matplotlib]
- [NumPy][numpy]
- [SciPy Library][scipy]

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

## Uninstallation
You can simply delete the `java_data_structures` folder, as this is a self-contained
project. All external API dependencies will be deleted as well.

**Note:** *Be careful, as the following line is irreversible.*

```bash
rm -rf java_data_structures
```

## Development
This project was developed using [IntelliJ][intellij], so it is already an IntelliJ project. Eclipse would work well too. However, note, provided that
this project runs several languages, consider using an IDE that supports
each of them.

## Contributing
If you would like to contribute or provide a bug fix,
please see the [documentation for contributing][contributing].

[java]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[groovy]: http://groovy-lang.org/download.html
[gradle]: https://gradle.org/releases/
[python]: https://www.python.org/download/releases/2.7/

[open_csv]: http://opencsv.sourceforge.net/
[spock_framework]: http://spockframework.org/spock/docs/1.1/all_in_one.html
[matplotlib]: https://matplotlib.org/index.html
[numpy]: https://pypi.python.org/pypi/numpy
[scipy]: https://www.scipy.org/scipylib/index.html

[intellij]: https://www.jetbrains.com/idea/
[contributing]: CONTRIBUTING.md
