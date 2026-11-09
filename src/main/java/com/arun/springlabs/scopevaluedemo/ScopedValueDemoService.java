package com.arun.springlabs.scopevaluedemo;

import org.springframework.stereotype.Service;

@Service
public class ScopedValueDemoService {

    private final RequestContextScopedValue requestContextScopedValue;

    public ScopedValueDemoService(
            RequestContextScopedValue requestContextScopedValue) {

        this.requestContextScopedValue = requestContextScopedValue;
    }

    public String getDetailsFromThreadLocal() {
        Tenant tenant = RequestContextThreadLocal.getTenant();
        return switch (tenant) {
            case GE -> "General Electrics Details";
            case FORD -> "Ford Motors Details";
            case AMAZON -> "Amazon Details";
            case GOOGLE -> "Google Details";
            case null -> "Null Details";
        };
    }

    public String getDetailsFromScopedValue() {
        Tenant tenant = requestContextScopedValue.tenant().get();
        return switch (tenant) {
            case GE -> "General Electrics Details";
            case FORD -> "Ford Motors Details";
            case AMAZON -> "Amazon Details";
            case GOOGLE -> "Google Details";
        };
    }

}
