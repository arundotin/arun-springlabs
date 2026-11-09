
package com.arun.springlabs.scopevaluedemo;

import org.springframework.stereotype.Component;

@Component
public class RequestContextScopedValue {

    private final ScopedValue<Tenant> TENANT =
            ScopedValue.newInstance();

    public ScopedValue<Tenant> tenant() {
        return TENANT;
    }
}
