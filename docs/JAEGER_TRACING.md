# Jaeger - Tracing

## Jaeger - Tracing - UI
<details>
<summary> Jaeger - Tracing - UI </summary>

<p> Use this URL to call the Jaeger UI on PROD via a web-browser. </p>
<code> <a href="http://localhost/tracing" target="_blank"> http://localhost/tracing</a> </code>

<p>Jaeger is our OpenTelemetry compliant service that collects tracing information from all services comprising our system.
With that data, one is able to examine how requests move through the system, gather insights about processing times as
well as errors that happen along the way.
</p>


<p> Here is an example of the jaeger-tracing on the transaction-service. <p>
<div style="text-align: center;">

<figure>
    <img src="images/jaeger-tracing/Jaeger-Tracing-1.png" width="100%">
    <figcaption>Jaeger main screen showing captured traces</figcaption>
</figure>

<figure>
    <img src="images/jaeger-tracing/Jaeger-Tracing-2.png" width="100%">
    <figcaption>Jaeger showing a trace graph of the transaction-service. </figcaption>
</figure>

<figure>
    <img src="images/jaeger-tracing/Jeager-Tracing-3.png" width="100%">
    <figcaption>Jaeger showing detail of a trace & its spans.</figcaption>
</figure>

</div>

</details>