package com.derbysoft.chapaai.adapter.pusher.domain.model;


public class Provider {

    private String id;
    private String providerCode;
    private int pushThreadPoolSize;
    private boolean disabled;
    private boolean pushStayAvailability;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public int getPushThreadPoolSize() {
        return pushThreadPoolSize;
    }

    public void setPushThreadPoolSize(int pushThreadPoolSize) {
        this.pushThreadPoolSize = pushThreadPoolSize;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isPushStayAvailability() {
        return pushStayAvailability;
    }

    public void setPushStayAvailability(boolean pushStayAvailability) {
        this.pushStayAvailability = pushStayAvailability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provider)) return false;

        Provider provider = (Provider) o;

        if (providerCode != null ? !providerCode.equals(provider.providerCode) : provider.providerCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return providerCode != null ? providerCode.hashCode() : 0;
    }
}
