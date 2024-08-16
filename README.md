# AurumBanking-Backend
Backend for the Banking-App.

## Getting started
## DEV-UI
- http://localhost:8080/q/dev-ui/io.quarkus.quarkus-smallrye-openapi/swagger-ui
- http://localhost:8080/q/dev-ui/io.quarkus.quarkus-kafka-client/topics

## Quarkus-UI in PROD
- http://localhost/dashboard/#/ (Overall execution of all Docker Compose)
- http://localhost:8080/dashboard/#/ (Single execution of the Prod Docker Compose)

## Prometheus & Grafana in PROD
- Prometheus: http://172.28.0.2:9090/metrics-service (Single execution of the Prod Docker Compose, look for IP address in Quarkus-UI)
- Grafana: http://172.28.0.3:3000/login (Single execution of the Prod Docker Compose, look for IP in Quarkus-UI)
- Prometheus: http://192.168.80.8:9090 (Overall execution of all Docker Compose)
- Grafana:  http://192.168.80.10:3000/login  (Overall execution of all Docker Compose)
- *Only on overall execution of all docker compose file - Jeagger Tracing*: http://localhost/tracing/search

### run mdkocs locally
- python -m pip install mkdocs
- python -m pip install mkdocs-material
- python -m mkdocs serve --dev-addr 127.0.0.1:4242

## Setup-Pipeline
**_token:_**
- linux: **_glrt-xznuGhoqctjSmbVNxpm_**
- windows-hoang: **_glrt-xznuGhoqctjSmbVNxpm_** / **_glrt-aHawSL4WALWi1s6BXdVi_**
- steffan-gitlab-runner: **_glrt-Fzfyj9euFsuo1f_szyUo_**
- milena-gitlab-runner: **_glrt-pzYxkWTn55mxxy4S4hXA_**
- salma-gitlab-runner: **_glrt-sQu4HSKd7RgotJkPHwCn_**

`docker run --rm -it -v gitlab-runner-config:/etc/gitlab-runner gitlab/gitlab-runner:latest register .\gitlab-runner.exe register --url https://git.ai.fh-erfurt.de --token [$token einfügen]`
- Enter the GitLab instance URL (for example, https://gitlab.com/): [https://git.ai.fh-erfurt.de]: **_[Enter]_**
- Enter a name for the runner. This is stored only in the local config.toml file: _**aurumbanking-gitlab-runner**_
- Enter an executor: parallels, virtualbox, docker, docker-autoscaler, instance, custom, shell, ssh, docker-windows, docker+machine, kubernetes: **_docker_**
- Enter the default Docker image (for example, ruby:2.7): **_jdk:17_**

`docker run -d --name gitlab-runner --restart always -v /var/run/docker.sock:/var/run/docker.sock -v gitlab-runner-config:/etc/gitlab-runner gitlab/gitlab-runner:latest`

`docker exec -it gitlab-runner /bin/bash`
- apt update
- apt install nano
- nano /etc/gitlab-runner/config.toml --> set: privileged = true

## Pipeline Running Rules

### Customer Information Service
- **Prepare Build Cache**
    - Runs for branches matching: `feature/customer-information-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build**
    - Runs for branches matching: `feature/customer-information-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build Image**
    - Runs for branches matching: `feature/customer-information-service`
    - Also runs for the branch: `test/permant` and `main`

### Depot Service
- **Prepare Build Cache**
    - Runs for branches matching: `feature/depot-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build**
    - Runs for branches matching: `feature/depot-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build Image**
    - Runs for branches matching: `feature/depot-service`
    - Also runs for the branch: `test/permant` and `main`

### Login Service
- **Prepare Build Cache**
    - Runs for branches matching: `feature/login-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build**
    - Runs for branches matching: `feature/login-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build Image**
    - Runs for branches matching: `feature/login-service`
    - Also runs for the branch: `test/permant` and `main`

### Support Service
- **Prepare Build Cache**
    - Runs for branches matching: `feature/support-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build**
    - Runs for branches matching: `feature/support-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build Image**
    - Runs for branches matching: `feature/support-service`
    - Also runs for the branch: `test/permant` and `main`

### Transaction Service
- **Prepare Build Cache**
    - Runs for branches matching: `feature/transaction-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build**
    - Runs for branches matching: `feature/transaction-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build Image**
    - Runs for branches matching: `feature/transaction-service`
    - Also runs for the branch: `test/permant` and `main`

### Documentation
- **Prepare Documentation Pages**
    - Runs for branches matching: `docs/`
    - Also runs for the branch: `test/permant` and `main`

- **Build Documentation Pages**
    - Runs for branches matching: `docs/`
    - Also runs for the branch: `test/permant` and `main`
