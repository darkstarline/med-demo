package com.med.demo;

import io.prometheus.metrics.core.metrics.Counter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PrometheusMetricsInterceptor implements HandlerInterceptor {

    static final Counter requestCounter = Counter.builder()
            .name("io_namespace_http_requests_total")
            .help("Total requests.")
            .labelNames("path", "method", "code")
            .register();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                    @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        int status = response.getStatus();

        requestCounter.labelValues(requestURI, method, String.valueOf(status)).inc();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}