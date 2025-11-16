# OTLP Demo

This repository contains a sample SpringBoot application that implements manual
instrumentation with OpenTelemetry to export business specific traces and metrics.

## Running the application

The following command will spin up the application, along with a Postgres database
and a Prometheus and Jaeger instances for capturing metrics and traces respectively:

```shell
docker compose up -d
```

## Traces

Traces can be inspected in the Jaeger UI at http://localhost:16686.

## License

MIT
