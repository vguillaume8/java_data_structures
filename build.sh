#!/usr/bin/env bash
set -e

# matplotlib depends on this package
sudo apt-get install -y python-tk

# Groovy tests depend on python code
pip install -r requirements.txt

# Build java code and test
gradle build
