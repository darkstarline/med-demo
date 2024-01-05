package com.med.demo;

import io.prometheus.metrics.core.metrics.Counter;
import io.prometheus.metrics.exporter.servlet.jakarta.PrometheusMetricsServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
//    use interceptor instead
//    private static final Counter requestCount = Counter.builder()
//            .name("request_total")
//            .help("count request total")
//            .register();


    @GetMapping("/")
    public String sayHello() throws InterruptedException {
//        requestCount.inc();
        return "Hello, World!\n";
    }


    @Bean
    public ServletRegistrationBean<PrometheusMetricsServlet> createPrometheusMetricsEndpoint() {
        return new ServletRegistrationBean<>(new PrometheusMetricsServlet(), "/metrics/*");
    }

}
