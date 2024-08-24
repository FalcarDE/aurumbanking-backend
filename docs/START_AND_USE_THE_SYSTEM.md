# How to start and use the system

## How to run

To start the system run the run.sh file on:

- windows: ./run.sh
- linux: sh ./run.sh

<div style="text-align: center;">

<figure>
    <img src="./images/run-sh-1%20(1).png" width="80%">
    <figcaption>First Question of the run.sh</figcaption>
</figure>


<figure>
    <img src="./images/run-sh-1%20(2).png" width="80%">
    <figcaption>Second Question of the run.sh</figcaption>
</figure>

After the Docker-Compose runs you can move on to step 1.





### 2. Jaeger UI / Tracing

If started with the **_-t_** flag, have a look at the Jaeger UI.

> **Path:** [http://localhost/tracing](http://localhost/tracing)

Jaeger is our OpenTelemetry compliant service that collects tracing information from all services comprising our system.
With that data, one is able to examine how requests _move_ through the system, gather insights about processing times as
well as errors that happen along the way.

<div style="text-align: center;">

<figure>
    <img src="assets/images/screen_jaeger_trace_list.png" width="80%">
    <figcaption>Jaeger main screen showing captured traces</figcaption>
</figure>

<figure>
    <img src="assets/images/screen_jaeger_trace_graph.png" width="80%">
    <figcaption>Jaeger showing a trace graph, e.g. the dependencies between different spans</figcaption>
</figure>

<figure>
    <img src="assets/images/screen_jaeger_span_info.png" width="80%">
    <figcaption>Jaeger showing detail of a trace & its spans</figcaption>
</figure>

</div>

### 3. Prometheus / Metrics

If started with the **_-m_** flag, have a look at Prometheus, the system that aggregates metric data from all services (
e.g. our own Quarkus services, databases, Traefik and, yes, Prometheus itself).

> **Path:** [http://localhost/prometheus](http://localhost/prometheus)

<div style="text-align: center;">
    <figure>
        <img src="assets/images/screen_prometheus_graph.png" width="80%">
        <figcaption>Prometheus showing bytes used by the JVM</figcaption>
    </figure>
</div>

### 4. Grafana / Metrics Dashboards

If started with the **_-mui_** flag, Grafana is available.

> **Path:** [http://localhost/metrics-ui-service](http://localhost/metrics-ui-service)

Upon first access, login with *admin:admin*, you are then asked to provide a new password.

Grafana needs to connect to a *data source*; this will be Prometheus in our case. Select the appropriate option at the
main page and enter `http://metrics-service:9090/metrics-service` under *Connection*. Afterward, you are able to build
dashboards showing the data imported from Prometheus. This can be really time-consuming; luckily, on can import
pre-configured dashboards. Some useful ones are the following:

-
Quarkus: [https://grafana.com/grafana/dashboards/14370-jvm-quarkus-micrometer-metrics/](https://grafana.com/grafana/dashboards/14370-jvm-quarkus-micrometer-metrics/)
- Traefik: [https://grafana.com/grafana/dashboards/4475-traefik/](https://grafana.com/grafana/dashboards/4475-traefik/)
-
Jaeger: [https://grafana.com/grafana/dashboards/12535-jaeger-all-in-one/](https://grafana.com/grafana/dashboards/12535-jaeger-all-in-one/)
-
Prometheus: [https://grafana.com/grafana/dashboards/3662-prometheus-2-0-overview/](https://grafana.com/grafana/dashboards/3662-prometheus-2-0-overview/)
-
cAdvisor: [https://grafana.com/grafana/dashboards/11600-docker-container/](https://grafana.com/grafana/dashboards/11600-docker-container/)

Beside metric data from Prometheus, Grafana can also connect to your databases to create dashboards that show the nature
of your data.

<div style="text-align: center;">

<figure>
    <img src="assets/images/screen_grafana_metrics_explorer.png" width="80%">
    <figcaption>Grafana Metrics Explorer</figcaption>
</figure>

<figure>
    <img src="assets/images/screen_grafana_quarkus_dashboard.png" width="80%">
    <figcaption>Grafana Quarkus Dashboard</figcaption>
</figure>

<figure>
    <img src="assets/images/screen_grafana_jaeger_dashboard.png" width="80%">
    <figcaption>Grafana Jaeger Dashboard</figcaption>
</figure>

</div>
</div>

## References

- Quarkus & Gradle: [https://quarkus.io/guides/gradle-tooling](https://quarkus.io/guides/gradle-tooling)
- Quarkus Path
  Resolution: [https://quarkus.io/blog/path-resolution-in-quarkus/](https://quarkus.io/blog/path-resolution-in-quarkus/)
- GitLab CI/CD
  Variables: [https://docs.gitlab.com/ee/ci/variables/predefined_variables.html](https://docs.gitlab.com/ee/ci/variables/predefined_variables.html)
- Build Docker in Docker in GitLab
  CI/CD: [https://docs.gitlab.com/ee/ci/docker/using_docker_build.html](https://docs.gitlab.com/ee/ci/docker/using_docker_build.html)
- TestContainers in GitLab
  CI/CD: [https://www.atomicjar.com/2023/01/running-testcontainers-tests-on-gitlab-ci/](https://www.atomicjar.com/2023/01/running-testcontainers-tests-on-gitlab-ci/)
- Traefik Examples: [https://github.com/frigi83/traefik-examples](https://github.com/frigi83/traefik-examples)
- GitLab CI/CD
  Intro: [https://about.gitlab.com/blog/2020/12/10/basics-of-gitlab-ci-updated/](https://about.gitlab.com/blog/2020/12/10/basics-of-gitlab-ci-updated/)
- GitLab CI/CD Gradle Build
  Cache: [https://blog.jdriven.com/2021/11/reuse-gradle-build-cache-on-gitlab/](https://blog.jdriven.com/2021/11/reuse-gradle-build-cache-on-gitlab/)
