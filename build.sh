#!/usr/bin/env bash
set -e

sudo apt-get install -y python-tk

pip install -r requirements.txt

gradle build
