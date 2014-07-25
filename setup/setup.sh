#!/bin/bash

# clean previous installations
rm -rf bin
mkdir bin

# install playframework
cd bin
wget http://downloads.typesafe.com/typesafe-activator/1.2.3/typesafe-activator-1.2.3.zip
unzip typesafe-activator-1.2.3.zip
rm typesafe-activator-1.2.3.zip

echo "export PATH=\$PATH:/$PWD/typesafe-activator-1.2.3" >> ~/.bashrc
