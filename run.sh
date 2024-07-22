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

# Funktion zum Starten eines Docker Compose Setups
start_docker_compose() {
    compose_file=$1
    echo "Starting Docker Compose setup: $compose_file..."
    docker-compose -f $compose_file up -d
    echo "Starting Docker Compose setup: $compose_file... done."
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
    "docker-compose-test.yml"
    "monitoring/docker-compose-prometheus"
    "monitoring/docker-compose-grafana.yml"
    "monitoring/docker-compose-tracing.yml"
)

echo "Which Docker Compose setups would you like to start?"
echo "Enter the numbers corresponding to your choices, separated by spaces (e.g., 1 3):"
for i in "${!options[@]}"; do
    echo "$((i+1)). ${options[$i]}"
done
echo "$(( ${#options[@]} + 1 )). All Docker Compose setups"

# Benutzerauswahl einlesen
read -p "Your choices: " choices

# Auswahl ausführen
for choice in $choices; do
    case $choice in
        1)
            start_docker_compose "docker-compose-test.yml"
            ;;
        2)
            start_docker_compose "monitoring/docker-compose-metrics.yml"
            ;;
        3)
            start_docker_compose "monitoring/docker-compose-metrics-ui.yml"
            ;;
        4)
            start_docker_compose "monitoring/docker-compose-tracing.yml"
            ;;
        5)
            for i in "${!options[@]}"; do
                start_docker_compose "${options[$i]}"
            done
            ;;
        *)
            echo "Invalid choice: $choice"
            ;;
    esac
done

echo "All selected services started successfully."
