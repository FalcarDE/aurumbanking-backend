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
  
### Setup-Pipeline
token: 
- linux: glrt-xznuGhoqctjSmbVNxpm_
- windows: xxx

docker run --rm -it -v gitlab-runner-config:/etc/gitlab-runner gitlab/gitlab-runner:latest register .\gitlab-runner.exe register --url https://git.ai.fh-erfurt.de --token $token
- Enter the GitLab instance URL (for example, https://gitlab.com/): [https://git.ai.fh-erfurt.de]: [Enter]
- Enter a name for the runner. This is stored only in the local config.toml file: aurumbanking-gitlab-runner
- Enter an executor: parallels, virtualbox, docker, docker-autoscaler, instance, custom, shell, ssh, docker-windows, docker+machine, kubernetes: docker
- Enter the default Docker image (for example, ruby:2.7): jdk:17

docker run -d --name gitlab-runner --restart always -v /var/run/docker.sock:/var/run/docker.sock -v gitlab-runner-config:/etc/gitlab-runner gitlab/gitlab-runner:latest 

docker exec -it gitlab-runner /bin/bash
- apt update 
- apt install nano
- nano /etc/gitlab-runner/config.toml --> set: privileged = true
