#!/bin/bash

echo "Building service containers..."
#--------------------------------------------
echo "Building customer-information-service..."
cd customer-information-service || exit
./gradlew build -x test -Dquarkus.container-image.build=true
cd ..
echo "Building customer-information-service... done."
#--------------------------------------------
echo "Building depot-service..."
cd depot-service || exit
./gradlew build -x test -Dquarkus.container-image.build=true
cd ..
echo "Building depot-service... done."
#--------------------------------------------
echo "Building support-service..."
cd support-service || exit
./gradlew build -x test -Dquarkus.container-image.build=true
cd ..
echo "Building support-service... done."
#--------------------------------------------
echo "Building login-service..."
cd login-service || exit
./gradlew build -x test -Dquarkus.container-image.build=true
cd ..
echo "Building login-service... done."
#--------------------------------------------
echo "Building transaction-service..."
cd transaction-service || exit
./gradlew build -x test -Dquarkus.container-image.build=true
cd ..
echo "Building transaction-service... done."
#--------------------------------------------
echo "Building service containers... done."
