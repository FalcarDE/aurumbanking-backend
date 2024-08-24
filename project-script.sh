#!/bin/bash

# 1. Token anfordern und direkt den String extrahieren
TOKEN=$(curl -s -X GET "http://localhost:80/customer-information-service/customers/createJwtToken" \
-H "Authorization: Basic $(echo -n 'Hoang:admin' | base64)")

# Überprüfung des Tokens
if [ -z "$TOKEN" ]; then
  echo "Kein Token erhalten"
  exit 1
fi
echo "Token wurde generiert."

# 2. Base64-kodiertes Profilbild aus der Datei lesen
if [ -f profile-image.txt ]; then
    PROFILE_IMAGE=$(cat profile-image.txt)
else
    echo "Profilbilddatei nicht gefunden."
    exit 1
fi

# 3. JSON-Daten für die Kundenanfrage in eine temporäre Datei schreiben
TMP_FILE=$(mktemp)
cat <<EOF > $TMP_FILE
{
  "firstname": "John",
  "lastname": "Doe",
  "birthDate": "1990-01-01T00:00:00",
  "created": "2024-05-30T14:00:00",
  "lastestLogin": "2024-05-30T14:00:00",
  "streetName": "Main Street",
  "housenumber": "123",
  "city": "Sample City",
  "country": "Sample Country",
  "zipcode": "DE176381",
  "username": "johndoe",
  "email": "johndoe@example.com",
  "phoneNumber": "+1234567890",
  "password": "123",
  "profileImage": $PROFILE_IMAGE
}
EOF

# Ausgabe der JSON-Daten zur Überprüfung
# cat $TMP_FILE

# 4. POST-Anfrage zur Erstellung eines Kunden mit dem erhaltenen Token und dem Profilbild durchführen
HTTP_STATUS=$(curl -s -o response.json -w "%{http_code}" -X POST "http://localhost:80/customer-information-service/customers" \
-H "Accept: application/json" \
-H "Content-Type: application/json" \
-H "Authorization: Bearer $TOKEN" \
--data-binary @$TMP_FILE)


# Temporäre Datei löschen
rm $TMP_FILE

# 5. POST-Anfrage zur Erstellung eines Depots mit der customerId durchführen
DEPOT_RESPONSE=$(curl -s -o depot_response.json -w "%{http_code}" -X POST "http://localhost:80/depot-service/depot" \
-H "Accept: application/json" \
-H "Content-Type: application/json" \
-d "{
  \"customerId\": 1,
  \"currencyArea\": \"EUR\",
  \"depositAmount\": 5000.00,
  \"fallbackDepositAmount\": 1000.00
}")

# Statuscode der Depot-Anfrage ausgeben
DEPOT_STATUS=$(echo "$DEPOT_RESPONSE" | tail -n 1)
echo "Depot-Anfrage HTTP Status Code: $DEPOT_STATUS"

# Konfiguration
IP="localhost"
PORT="80"
FULLPATH="transactions-service/transactions"

# Ermittlung des heutigen Datums
start_date=$(date +%Y-%m-%d)
echo "Startdatum: $start_date"

# Anzahl der Tage für vergangene und zukünftige Daten
past_days=3
future_days=3

# Schleife für vergangene Daten (heute und 3 Tage in der Vergangenheit)
for i in $(seq -$past_days 0); do
    # Datum berechnen (Vergangenheit oder heute)
    current_date=$(date -d "$start_date $i days" +%Y-%m-%d)

    # Erstellen des JSON-Objekts für die POST-Anfrage
    json_data=$(cat <<EOF
{
  "depotId": 1,
  "country": "Germany",
  "recipient": "John Doe",
  "iban": "DE89370400440532013000",
  "bic": "COBADEFFXXX",
  "moneyValue": 1150.00,
  "purposeOfUse": "Payment for services",
  "standingOrder": false,
  "transactionType": "outcome",
  "transactionClassification": "Standard Überweisung",
  "dateTimeOfFirstExecution": "${current_date}T10:00:00",
  "dateTimeOfLastExecution": "${current_date}T10:00:00"
}
EOF
)

    # POST-Anfrage ausführen und HTTP-Statuscode ermitteln
    http_response=$(curl -i -X POST "http://${IP}:${PORT}/${FULLPATH}" \
        -H "Accept: application/json" \
        -H "Content-Type: application/json" \
        -d "${json_data}")

    # Ausgabe des vollständigen HTTP-Responses
    echo "$http_response"

    # 10 Sekunden Pause
    sleep 10
done

# Schleife für zukünftige Daten (1 bis 3 Tage in der Zukunft)
for i in $(seq 1 $future_days); do
    # Datum berechnen (Zukunft)
    future_date=$(date -d "$start_date + $i days" +%Y-%m-%d)

    # Erstellen des JSON-Objekts für die POST-Anfrage
    json_data=$(cat <<EOF
{
  "depotId": 1,
  "country": "Germany",
  "recipient": "Master 42",
  "iban": "DE89370400440532013000",
  "bic": "COBADEFFXXX",
  "moneyValue": 2000.00,
  "purposeOfUse": "some income",
  "standingOrder": false,
  "transactionType": "income",
  "transactionClassification": "Standard Überweisung",
  "dateTimeOfFirstExecution": "${future_date}T10:00:00",
  "dateTimeOfLastExecution": "${future_date}T10:00:00"
}
EOF
)

    # POST-Anfrage ausführen und HTTP-Statuscode ermitteln
    http_response=$(curl -i -X POST "http://${IP}:${PORT}/${FULLPATH}" \
        -H "Accept: application/json" \
        -H "Content-Type: application/json" \
        -d "${json_data}")

    # Ausgabe des vollständigen HTTP-Responses
    echo "$http_response"

    # 10 Sekunden Pause
    sleep 10
done
