
package com.arun.springlabs.scopevaluedemo;

public class RequestContextThreadLocal {

    private static final ThreadLocal<Tenant> TENANT =
            new ThreadLocal<>();

    public static void setTenant(Tenant tenant) {
        TENANT.set(tenant);
    }

    public static Tenant getTenant() {
        return TENANT.get();
    }

    public static void clear() {
        TENANT.remove();
    }
}
