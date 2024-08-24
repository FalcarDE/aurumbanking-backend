# How to start and use the system 

## How to run stuff

<details>
<summary>Run Backend on PROD </summary>

- start in the root dir of this project
- run this shell scrip:
  - linix/mac: `sh run.sh`
  - windows: `./run.sh`
- press y/yes to build the entire project:

![build-image](images/deployment/run-sh-1.png)

- after build finish, press number 5 to run all docker-compose files:

![build-docker](images/deployment/run-sh-2.png)

- now we can see the services running

![running-docker](images/deployment/docker-services.png)

- go to browser: `http://localhost/dashboard/`
- insert this credetials:
  - `user`
  - `123`

- here is the dashboard on prod:

![traefik-dashboard](images/deployment/traefik-dashboard.png)



<p align="right">(<a href="#top">back to top</a>)</p>
</details>


<details>
<summary>DEV-UI</summary>

- `http://localhost:8080/q/dev-ui/io.quarkus.quarkus-smallrye-openapi/swagger-ui`
- `http://localhost:8080/q/dev-ui/io.quarkus.quarkus-kafka-client/topics`
- `http://localhost:8080/dashboard/#/`

<p align="right">(<a href="#top">back to top</a>)</p>
</details>

<details>
<summary>Quarkus-UI in PROD</summary>

- http://localhost/dashboard/#/

<p align="right">(<a href="#top">back to top</a>)</p>
</details>

<details>
<summary>Prometheus & Grafana in PROD</summary>

- Traefk-Dashboard: http://localhost/dashboard/
- Prometheus: http://localhost/prometheus
- Grafana: http://localhost/metrics-ui-service/login
- Jaeger-Tracing: http://localhost/tracing/search

<p align="right">(<a href="#top">back to top</a>)</p>
</details>

<details>
<summary>run mkdocs locally</summary>

- `python -m pip install mkdocs`
- `python -m pip install mkdocs-material`
- `python -m mkdocs serve --dev-addr 127.0.0.1:4242`

<p align="right">(<a href="#top">back to top</a>)</p>
</details>


