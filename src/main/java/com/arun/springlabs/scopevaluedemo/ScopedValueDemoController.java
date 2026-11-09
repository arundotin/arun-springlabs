package com.arun.springlabs.scopevaluedemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ScopedValueDemoController {

    private final ScopedValueDemoService scopedValueDemoService;
    private final RequestContextScopedValue requestContextScopedValue;

    public ScopedValueDemoController(
            ScopedValueDemoService scopedValueDemoService,
            RequestContextScopedValue requestContextScopedValue) {

        this.scopedValueDemoService = scopedValueDemoService;
        this.requestContextScopedValue = requestContextScopedValue;
    }

    @GetMapping("/details")
    public String getDetailsFromThreadLocal() {
        Tenant tenant = randomlyPickSomeTenantForThisDemo();
        RequestContextThreadLocal.setTenant(tenant);
        try {
            return scopedValueDemoService.getDetailsFromThreadLocal();
        } finally {
            RequestContextThreadLocal.clear();
        }
    }

    @GetMapping("/details-scope")
    public String getDetailsFromScopedValue() {
        Tenant tenant = randomlyPickSomeTenantForThisDemo();

        var carrier = ScopedValue.where(requestContextScopedValue.tenant(), tenant);

        return carrier.call(scopedValueDemoService::getDetailsFromScopedValue);

    }

    private Tenant randomlyPickSomeTenantForThisDemo() {
        Tenant[] tenants = Tenant.values();

        int randomIndex = new Random().nextInt(tenants.length);

        return tenants[randomIndex];
    }
}