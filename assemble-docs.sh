#!/bin/bash

# Begin Copy Customer Information Service Doc
echo "Begin copying Customer Information Service README.md"
mkdir -p docs/customer-information-service
cp -R customer-information-service/README.md docs/customer-information-service/
echo "End copying Customer Information Service README.md"

# Begin Create and Copy Kotlin Doc for Customer Information Service
echo "Begin creating and copying Kotlin doc for Customer Information Service"
mkdir -p docs/customer-information-service-dokka
cd customer-information-service || exit
./gradlew dokkaHtml
cp -R build/dokka/html/* ../docs/customer-information-service-dokka/
cd ..
echo "End creating and copying Kotlin doc for Customer Information Service"

sleep 5
# Begin Copy Login Service Doc
echo "Begin copying Login Service README.md"
mkdir -p docs/login-service
cp -R login-service/README.md docs/login-service/
echo "End copying Login Service README.md"

# Begin Create and Copy Kotlin Doc for Login Service
echo "Begin creating and copying Kotlin doc for Login Service"
mkdir -p docs/login-service-dokka
cd login-service || exit
./gradlew dokkaHtml
cp -R build/dokka/html/* ../docs/login-service-dokka/
cd ..
echo "End creating and copying Kotlin doc for Login Service"

sleep 5
# Begin Copy Depot Service Doc
echo "Begin copying Depot Service README.md"
mkdir -p docs/depot-service
cp -R depot-service/README.md docs/depot-service/
echo "End copying Depot Service README.md"

# Begin Create and Copy Kotlin Doc for Depot Service
echo "Begin creating and copying Kotlin doc for Depot Service"
mkdir -p docs/depot-service-dokka
cd depot-service || exit
./gradlew dokkaHtml
cp -R build/dokka/html/* ../docs/depot-service-dokka/
cd ..
echo "End creating and copying Kotlin doc for Depot Service"

sleep 5
# Begin Copy Transaction Service Doc
echo "Begin copying Transaction Service README.md"
mkdir -p docs/transaction-service
cp -R transaction-service/README.md docs/transaction-service/
echo "End copying Transaction Service README.md"

# Begin Create and Copy Kotlin Doc for Transaction Service
echo "Begin creating and copying Kotlin doc for Transaction Service"
mkdir -p docs/transaction-service-dokka
cd transaction-service || exit
./gradlew dokkaHtml
cp -R build/dokka/html/* ../docs/transaction-service-dokka/
cd ..
echo "End creating and copying Kotlin doc for Transaction Service"

sleep 5
# Begin Copy Support Service Doc
echo "Begin copying Support Service README.md"
mkdir -p docs/support-service
cp -R support-service/README.md docs/support-service/
echo "End copying Support Service README.md"

# Begin Create and Copy Kotlin Doc for Support Service
echo "Begin creating and copying Kotlin doc for Support Service"
mkdir -p docs/support-service-dokka
cd support-service || exit
./gradlew dokkaHtml
cp -R build/dokka/html/* ../docs/support-service-dokka/
cd ..
echo "End creating and copying Kotlin doc for Support Service"
