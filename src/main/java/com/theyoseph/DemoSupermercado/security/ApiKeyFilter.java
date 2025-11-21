package com.theyoseph.DemoSupermercado.security;

import com.theyoseph.DemoSupermercado.exception.ForbiddenException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${app.api-key}")
    private String apiKey;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver hadler;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        String method = request.getMethod();

        // Solo proteger POST, PUT y DELETE
        if (method.equals("POST") || method.equals("PUT") || method.equals("DELETE")){
            String headerKey = request.getHeader("X-API-KEY");
            if (headerKey == null || !headerKey.equals(apiKey)){
                hadler.resolveException(request, response, null, new ForbiddenException("API Key inválida"));
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
