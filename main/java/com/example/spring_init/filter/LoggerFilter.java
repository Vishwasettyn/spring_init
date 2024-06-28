package com.example.spring_init.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class LoggerFilter extends OncePerRequestFilter {
    public static final String TRACKING_ID_MDC = "trackingId";
    public static final String TRACKING_ID = "trackingId";

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String trackingId = extractTrackingId(httpServletRequest);
        if (StringUtils.isEmpty(trackingId)) {
            trackingId = generateTrackingId();
            LOGGER.warn("Request received with null trackingId, generated trackingId is: {}", trackingId);
        }
        trackingId = trackingId.replaceAll("[\n|\r|\t]", "_");
        LOGGER.debug("trackingId for the incoming request {}",trackingId);
        LOGGER.debug("Request URI:{}", httpServletRequest.getRequestURI());

        httpServletRequest.setAttribute(TRACKING_ID_MDC, trackingId);

        try {
            MDC.put(TRACKING_ID_MDC, trackingId);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            LOGGER.debug("Response status:{}", httpServletResponse.getStatus());
            MDC.remove(TRACKING_ID_MDC);
        }
    }

    private String generateTrackingId() {
        return UUID.randomUUID().toString();
    }

    private String extractTrackingId(HttpServletRequest httpRequest) {
        // (case-sensitive)
        final String param = httpRequest.getParameter(TRACKING_ID);

        // (headers are case-insensitive)
        final String header = httpRequest.getHeader(TRACKING_ID);
        return null == param ? header : param;
    }
}
