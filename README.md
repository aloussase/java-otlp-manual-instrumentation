# OTLP Demo

This repository contains a sample SpringBoot application that implements manual
instrumentation with OpenTelemetry to export business specific traces and metrics.

## Configure the OTEL agent

To run the application and see the traces in Jaeger UI, you need to tell the OTEL agent
to export traces to the Zipking endpoint. You can do this by configuring the following
environment variables:

```shell
OTEL_EXPORTER_ZIPKIN_ENDPOINT=http://localhost:9411/api/v2/spans
OTEL_TRACES_EXPORTER=zipkin
OTEL_LOGS_EXPORTER=none
OTEL_METRICS_EXPORTER=none
```

We are also disabling logs and metrics because we are only interested in traces right now.

## Running the application

The following command will spin up the application and a Prometheus and Jaeger instances for capturing metrics and
traces respectively:

```shell
docker compose up -d
```

## Traces

Traces can be inspected in the Jaeger UI at http://localhost:16686.

## License

MIT
