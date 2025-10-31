#!/bin/bash

javac -cp ".:sqlite-jdbc-3.45.3.0.jar:slf4j-api-2.0.13.jar:slf4j-simple-2.0.13.jar" Main.java
java -cp ".:sqlite-jdbc-3.45.3.0.jar:slf4j-api-2.0.13.jar:slf4j-simple-2.0.13.jar" Main
