# Prometheus - Metrics 

If started with the **_-m_** flag, have a look at Prometheus, the system that aggregates metric data from all services (
e.g. our own Quarkus services, databases, Traefik and, yes, Prometheus itself).

> **Path on PROD:** [http://localhost/prometheus](http://localhost/prometheus)

<div style="text-align: center;">
    <figure>
        <img src="images/prometheus/prometheus-entry.png" width="80%">
        <figcaption>Prometheus entry by given path.</figcaption>
    </figure>
</div>

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


# Grafana - Monitoring

For monitoring purposes, Traefik provides metrics that are collected by a Prometheus server and visualized through Grafana dashboards.

> **Path on PROD:** [http://localhost/metrics-ui-service/login](http://localhost/prometheus)

> username: admin

> password: admin

We have for each of our services a monitoring dashboards. In the following section, we will have a closer look on those dashboards.

## Login-Service

## Customer-Information-Service

## Depot-Service

### Panel 1: Successful Support Requests Per Request Type (Max Duration)

This gauge panel shows the maximum processing time for successful support requests (HTTP 200 status). 
It helps in understanding the performance of the support service, particularly in measuring the longest request times. 
If the max duration consistently exceeds a certain threshold, this could indicate a performance bottleneck.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/" width="70%">
    <figcaption>Screenshot of the max duration for successful depot requests</figcaption>
</figure>

</div>

### Panel 2: Sum of Server Errors last 24h

This bar chart  visualizes the total number of depot request errors handled by the service over time. 
It helps troubleshooting in case of system damages be visualizing timezones with a high increase of error requests.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/" width="70%">
    <figcaption>Screenshot of the sum of server errors last 24h</figcaption>
</figure>

</div>

### Panel 3: Sum of Requests per Hour last 24h

The bar chart diagram displays the sum of server requests per request type per hour. It helps to determine high frequent
interactions in order to have an indicator for improvement areas in case of performance issues.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/" width="70%">
    <figcaption>Screenshot sum of requests per request type per hour last 24h</figcaption>
</figure>

</div>

### Panel 4: Sum of Connection Seconds per Half Hour last 24h

This bar chart displays the count of connention seconds per half hour for the depot service. It helps to define time
zones with high server activity for load handling purposes.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/" width="70%">
    <figcaption>Screenshot sum of connection seconds per half hour last 24h</figcaption>
</figure>

</div>

## Transaction-Service

## Support-Service

This dashboard provides a clear visualization of key performance metrics related to the support service, using Prometheus as the data source. Each panel is designed to monitor a specific aspect of the service, helping ensure reliability and performance optimization.

### Panel 1: Successful Support Requests (Max Duration)

This gauge panel shows the maximum processing time for successful support requests (HTTP 200 status). It helps in understanding the performance of the support service, particularly in measuring the longest request times. If the max duration consistently exceeds a certain threshold, this could indicate a performance bottleneck.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/support-service/grafana-successfull-requests-max.png" width="70%">
    <figcaption>Screenshot of the max duration for successful support requests</figcaption>
</figure>

</div>

### Panel 2: Sum of Support Requests Over Time (Timeseries)

This timeseries panel visualizes the total number of support requests handled by the service over time. Monitoring this data helps detect spikes in request volume, which could indicate periods of high load or unusual activity that might need investigation.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/support-service/grafana-sum-of-requests-timeseries.png" width="70%">
    <figcaption>Screenshot of the sum of support requests over time</figcaption>
</figure>

</div>

### Panel 3: Max Request Time for Status 200 (Timeseries)

This timeseries panel tracks the maximum duration of HTTP 200 status requests over time. By monitoring this metric, you can identify trends in performance for successful requests and potentially spot periods of degraded performance before they escalate into more significant issues.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/support-service/grafana-messaurement-of-max-request-time-200-timeseries.png" width="70%">
    <figcaption>Screenshot of the max request time for HTTP 200 status requests</figcaption>
</figure>

</div>

### Panel 4: Max Request Time for Status 200

This panel measures the maximum request time for HTTP 200 status requests. It provides immediate insight into how quickly requests are being processed and can alert the team to performance issues that may require tuning or scaling.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/support-service/grafana-messaurement-of-max-request-time-200.png" width="70%">
    <figcaption>Screenshot of the max request time for HTTP 200 status requests</figcaption>
</figure>

</div>

### Panel 5: Duration of Server Connections in Seconds

This gauge panel tracks the duration of server connections in seconds. It is useful for identifying potential issues with connection handling in the support service, particularly if connection times begin to increase unexpectedly, potentially leading to timeouts or degraded service quality.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/support-service/grafana-duration-of-server-connections-in-seconds.png" width="70%">
    <figcaption>Screenshot of the duration of server connections</figcaption>
</figure>

</div>

### Conclusion

This dashboard offers essential metrics to monitor the performance and reliability of the support service. By keeping track of request times, connection durations, and overall service load, you can ensure that the support service remains responsive and scalable, identifying and addressing potential bottlenecks or performance degradations in a timely manner.






