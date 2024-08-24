# Prometheus - Metrics 

If started with the **_-m_** flag, have a look at Prometheus, the system that aggregates metric data from all services (
e.g. our own Quarkus services, databases, Traefik and, yes, Prometheus itself).

> **Path:** [http://localhost/prometheus](http://localhost/prometheus)

<div style="text-align: center;">
    <figure>
        <img src="images/prometheus/prometheus-entry.png" width="80%">
        <figcaption>Prometheus entry by given path.</figcaption>
    </figure>
</div>


For monitoring purposes, Traefik provides metrics that are collected by a Prometheus server and visualized through Grafana dashboards.

Here are some examples which metrics can be provided by Prometheus and can be shown on the Prometheus-Graphs.


<div style="text-align: center;">
    <figure>
        <img src="images/prometheus/metrics-example.png" width="80%">
        <figcaption>Collection of Traefik-Entrypoint Metrics</figcaption>
    </figure>
</div>


<div style="text-align: center;">
    <figure>
        <img src="images/prometheus/metrics-graph-example.png" width="80%">
        <figcaption>Collection of Traefik-Entrypoint Metrics</figcaption>
    </figure>
</div>


# Prometheus - Metrics 





