#!/bin/bash

#docker_compose_command="-f docker-compose-test.yml"
#compose_action="up"  # Standardaktion setzen

# parse flags
#while (( $# )); do
#  case "$1" in
#    -b)
#      echo "Building service containers..."
#      #--------------------------------------------
#      echo "Building customer-information-service..."
#      cd customer-information-service || exit
#      ./gradlew build -x test -Dquarkus.container-image.build=true
#      cd ..
#      echo "Building customer-information-service... done."
#      #--------------------------------------------
#      echo "Building depot-service..."
#      cd depot-service || exit
#      ./gradlew build -x test -Dquarkus.container-image.build=true
#      cd ..
#      echo "Building depot-service... done."
#      #--------------------------------------------
#      echo "Building support-service..."
#      cd support-service || exit
#      ./gradlew build -x test -Dquarkus.container-image.build=true
#      cd ..
#      echo "Building support-service... done."
#      #--------------------------------------------
#      echo "Building transaction-service..."
#      cd transaction-service || exit
#      ./gradlew build -x test -Dquarkus.container-image.build=true
#      cd ..
#      echo "Building transaction-service... done."
#      #--------------------------------------------
#      echo "Building service containers... done."
#  esac
#done

## Den gewünschten Docker Compose Befehl am Ende hinzufügen
#if [[ -n "$1" ]]; then
#  compose_action="$1"
#  shift
#fi
#
## Echo the final command for debugging
#echo "docker compose $docker_compose_command $compose_action"
#
## Execute the final command
#docker compose $docker_compose_command $compose_action

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