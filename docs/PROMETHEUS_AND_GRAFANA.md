# Metrics - Service  


## Prometheus 
<details>
<summary> Prometheus </summary>

<p> Traefik provides metrics that are collected by a Prometheus server and visualized through Grafana dashboards. Follow this Link on PROD to Web-UI of Prometheus: </p>

<code> <a href="http://localhost/prometheus" target="_blank"> http://localhost/prometheus </a> </code>


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

</details>


## Grafana - Monitoring

<details>
<summary> Grafana </summary>

<p> For monitoring purposes, Traefik provides metrics that are collected by a Prometheus server and visualized through Grafana dashboards. Follow this Link on PROD to Web-UI of Grafana: </p>

<code> <a href="http://localhost/metrics-ui-service/login" target="_blank"> http://localhost/metrics-ui-service/login </a> </code>

<code> username: admin </code>

<code>  password: admin </code>

<p> We have for each of our services a monitoring dashboards. In the following section, we will have a closer look on those dashboards. </p>

<h3> Connect Prometheus with Grafana </h3>

<p> To enable Grafana to capture metrics from Prometheus, Prometheus first needs to be connected. To achieve this, we need to run all Docker containers in the PROD environment. </p>

<p> After executing this, you need to access <a href="localhost/dashboards/ " target="_blank">localhost/dashboards/ </a> to retrieve the IP address of the <strong<metrics-service@docker</strong> service. </p>

<div style="text-align: center;">
<figure>
    <img src="images/prometheus/connect-grafa-prom.png" width="80%">
    <figcaption>IP-Address of smetrics-service@docker </figcaption>
</figure>
</div>

<p> Now, we need to connect the data source in Grafana by creating a new Prometheus connection and linking it using the retrieved IP address. </p>

<div style="text-align: center;">
<figure>
    <img src="images/prometheus/connect-grafa-prom-1.png" width="80%">
    <figcaption>Using the IP-Address for the Prometheus Backend Connection </figcaption>
</figure>
</div>

<div style="text-align: center;">
<figure>
    <img src="images/prometheus/connect-grafa-prom-3.png" width="80%">
    <figcaption>Successful Connection </figcaption>
</figure>
</div>

<p> Now, we are able to create dashboards by using the provided metrics from the prometheus server. </p>


<div style="text-align: center;">
<figure>
    <img src="images/prometheus/connect-grafa-prom-4.png" width="80%">
    <figcaption>Add new Dashbaord </figcaption>
</figure>
</div>

<div style="text-align: center;">
<figure>
    <img src="images/prometheus/connect-grafa-prom-5.png" width="80%">
    <figcaption> Connect to the establish prometheus server </figcaption>
</figure>
</div>

<div style="text-align: center;">
<figure>
    <img src="images/prometheus/connect-grafa-prom-6.png" width="80%">
    <figcaption> Using services metrics from Prometheus Server </figcaption>
</figure>
</div>

</details>


### Login-Service

<details>
<summary> Login-Service </summary>

</details>

### Customer-Information-Service

<details>
<summary> Customer-Information-Service </summary>

</details>

### Depot-Service

<details>
<summary> Depot-Service </summary>

</details>

### Transaction-Service

<details>
<summary> Transaction-Service </summary>

</details>

### Support-Service

<details>
<summary> Support-Service </summary>

<p> This dashboard provides a clear visualization of key performance metrics related to the support service, 
using Prometheus as the data source. 
Each panel is designed to monitor a specific aspect of the service, helping ensure reliability and performance optimization. </p>

<h3> Panel 1: Successful Support Requests (Max Duration) </h3>

This gauge panel shows the maximum processing time for successful support requests (HTTP 200 status). It helps in understanding the performance of the support service, particularly in measuring the longest request times. If the max duration consistently exceeds a certain threshold, this could indicate a performance bottleneck.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/support-service/grafana-successfull-requests-max.png" width="70%">
    <figcaption>Screenshot of the max duration for successful support requests</figcaption>
</figure>

</div>

<h3> Panel 2: Sum of Support Requests Over Time (Timeseries) </h3>

This timeseries panel visualizes the total number of support requests handled by the service over time. Monitoring this data helps detect spikes in request volume, which could indicate periods of high load or unusual activity that might need investigation.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/support-service/grafana-sum-of-requests-timeseries.png" width="70%">
    <figcaption>Screenshot of the sum of support requests over time</figcaption>
</figure>

</div>

<h3> Panel 3: Max Request Time for Status 200 (Timeseries) </h3>

This timeseries panel tracks the maximum duration of HTTP 200 status requests over time. By monitoring this metric, you can identify trends in performance for successful requests and potentially spot periods of degraded performance before they escalate into more significant issues.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/support-service/grafana-messaurement-of-max-request-time-200-timeseries.png" width="70%">
    <figcaption>Screenshot of the max request time for HTTP 200 status requests</figcaption>
</figure>

</div>

<h3> Panel 4: Max Request Time for Status 200 </h3>

This panel measures the maximum request time for HTTP 200 status requests. It provides immediate insight into how quickly requests are being processed and can alert the team to performance issues that may require tuning or scaling.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/support-service/grafana-messaurement-of-max-request-time-200.png" width="70%">
    <figcaption>Screenshot of the max request time for HTTP 200 status requests</figcaption>
</figure>

</div>

<h3> Panel 5: Duration of Server Connections in Seconds </h3>

This gauge panel tracks the duration of server connections in seconds. It is useful for identifying potential issues with connection handling in the support service, particularly if connection times begin to increase unexpectedly, potentially leading to timeouts or degraded service quality.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/support-service/grafana-duration-of-server-connections-in-seconds.png" width="70%">
    <figcaption>Screenshot of the duration of server connections</figcaption>
</figure>

</div>

<h3> Conclusion </h3>
This dashboard offers essential metrics to monitor the performance and reliability of the support service. By keeping track of request times, connection durations, and overall service load, you can ensure that the support service remains responsive and scalable, identifying and addressing potential bottlenecks or performance degradations in a timely manner.

</details>








