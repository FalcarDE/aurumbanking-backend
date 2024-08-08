#!/bin/bash

# Konfiguration
IP="192.168.2.116"
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
