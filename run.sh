#!/bin/bash

docker run --rm -d   --network host nginxdemos/hello

./gradlew clean build run