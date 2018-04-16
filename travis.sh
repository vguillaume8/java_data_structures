#!/usr/bin/env bash
set -e

sudo apt-get install -y python-tk

sudo pip install --upgrade pip

sudo pip install -r requirements.txt --ignore-installed six

