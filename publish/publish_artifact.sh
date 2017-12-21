#!/usr/bin/env bash

# Creates a folder with a specified name if it does not exist already
create_folder() {

  # if the bin folder does not exist
  if [ ! -d $1 ]; then
    echo "Creating $1 directory"
    mkdir $1
    echo "$1 directory successfull created"

  else
    echo "$1 directory already exists"
  fi
}

ftp -inv $SCP_HOST << EOF
user $SCP_USERNAME $SCP_PASSWORD
create_folder $SCP_BUILD_DIRECTORY
cd $SCP_BUILD_DIRECTORY
mput $JAR_PATH
bye
EOF
