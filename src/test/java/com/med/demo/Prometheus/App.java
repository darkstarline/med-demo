package com.med.demo.Prometheus;

import io.prometheus.metrics.core.metrics.Counter;
import io.prometheus.metrics.core.metrics.Gauge;
import io.prometheus.metrics.exporter.httpserver.HTTPServer;
import io.prometheus.metrics.instrumentation.jvm.JvmMetrics;
import io.prometheus.metrics.model.snapshots.Unit;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        JvmMetrics.builder().register();

        Counter counter = Counter.builder()
                .name("my_count_total")
                .help("example counter")
                .labelNames("status")
                .register();

        counter.labelValues("ok").inc();
        counter.labelValues("ok").inc();
        counter.labelValues("error").inc();

        Counter serviceTimeSeconds = Counter.builder()
                .name("service_time_seconds_total")
                .help("total time spent serving requests")
                .unit(Unit.SECONDS)
                .register();

        serviceTimeSeconds.inc(Unit.millisToSeconds(200));

        Gauge temperature = Gauge.builder()
                .name("temperature_celsius")
                .help("current temperature")
                .labelNames("location")
                .unit(Unit.CELSIUS)
                .register();

        temperature.labelValues("Berlin").set(22.3);

        HTTPServer server = HTTPServer.builder()
                .port(9400)
                .buildAndStart();

        System.out.println("HTTPServer listening on port http://localhost:" + server.getPort() + "/metrics?debug=openmetrics");
        Thread.currentThread().join();
    }
}
