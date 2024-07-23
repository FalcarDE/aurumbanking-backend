#!/bin/bash

# Funktion zum Bauen eines Services
build_service() {
    service_name=$1
    echo "Building $service_name..."
    cd $service_name || exit
    ./gradlew build -x test -Dquarkus.container-image.build=true
    cd ..
    echo "Building $service_name... done."
}

# Frage, ob die Services gebaut werden sollen
read -p "Do you want to build the service containers? (yes/y or no/n): " build_services_choice
if [[ $build_services_choice == "yes" || $build_services_choice == "y" ]]; then
    echo "Building service containers..."
    build_service "customer-information-service"
    build_service "depot-service"
    build_service "support-service"
    build_service "login-service"
    build_service "transaction-service"
    echo "Building service containers... done."
else
    echo "Skipping the build of service containers."
fi

# Optionen für Docker Compose Setups
options=(
    "monitoring/docker-compose-prometheus.yml"
    "monitoring/docker-compose-grafana.yml"
    "monitoring/docker-compose-tracing.yml"
)

echo "Docker Compose setup 'docker-compose-prod.yml' will always be started."
echo "Which additional Docker Compose setups would you like to start?"
echo "Enter the numbers corresponding to your choices, separated by spaces (e.g., 1 3):"
for i in "${!options[@]}"; do
    echo "$((i+1)). ${options[$i]}"
done
echo "$(( ${#options[@]} + 1 )). All Docker Compose setups"

# Benutzerauswahl einlesen
read -p "Your choices: " choices

# Initiales Docker Compose Kommando
docker_compose_command="docker-compose -f docker-compose-prod.yml"

# Auswahl ausführen und Kommandos hinzufügen
all_selected=false
for choice in $choices; do
    case $choice in
        1)
            docker_compose_command+=" -f monitoring/docker-compose-prometheus.yml"
            ;;
        2)
            docker_compose_command+=" -f monitoring/docker-compose-grafana.yml"
            ;;
        3)
            docker_compose_command+=" -f monitoring/docker-compose-tracing.yml"
            ;;
        4)
            all_selected=true
            break
            ;;
        *)
            echo "Invalid choice: $choice"
            ;;
    esac
done

# Wenn alle ausgewählt wurden, alle Docker Compose Dateien hinzufügen
if [ "$all_selected" = true ]; then
    docker_compose_command="docker-compose -f docker-compose-prod.yml -f monitoring/docker-compose-prometheus.yml -f monitoring/docker-compose-grafana.yml -f monitoring/docker-compose-tracing.yml"
fi

# Starte die Docker Compose Setups
$docker_compose_command up -d

echo "All selected services started successfully."
