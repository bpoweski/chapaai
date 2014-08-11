package com.derbysoft.chapaai.adapter.core.commons.exception;

public class ProviderConfigNotFoundException extends RuntimeException {
    public String getMessage() {
        return "provider config not found";
    }
}
