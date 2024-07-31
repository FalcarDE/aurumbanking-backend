# AurumBanking-Backend
Backend for the Banking-App.

## Getting started
### DEV-UI 
- http://localhost:8080/q/dev-ui/io.quarkus.quarkus-smallrye-openapi/swagger-ui
- http://localhost:8080/q/dev-ui/io.quarkus.quarkus-kafka-client/topics

### Quarkus-UI in PROD
- http://localhost/dashboard/#/ (Overall execution of all Docker Compose)
- http://localhost:8080/dashboard/#/ (Single execution of the Prod Docker Compose)

### Prometheus & Grafana in PROD 
- Prometheus: http://172.28.0.2:9090/metrics-service (Single execution of the Prod Docker Compose, look for IP address in Quarkus-UI)
- Grafana: http://172.28.0.3:3000/login (Single execution of the Prod Docker Compose, look for IP in Quarkus-UI)
- Prometheus: http://192.168.80.8:9090 (Overall execution of all Docker Compose)
- Grafana:  http://192.168.80.10:3000/login  (Overall execution of all Docker Compose)
- *Only on overall execution of all docker compose file - Jeagger Tracing*: http://localhost/tracing/search

### run mdkocs locally
- python -m pip install mkdocs
- python -m pip install mkdocs-material
- python -m mkdocs serve --dev-addr 127.0.0.1:4242