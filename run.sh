#!/bin/sh

./gradlew clean build
java -jar build/libs/a-star-0.0.1.jar
