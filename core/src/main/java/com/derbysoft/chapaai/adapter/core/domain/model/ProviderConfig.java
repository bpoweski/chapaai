package com.derbysoft.chapaai.adapter.core.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProviderConfig {
    @Id
    @Column(length = 20,nullable = false)
    private String providerCode;
    @Column(nullable = false)
    private boolean hasRate;
    @Column(nullable = false)
    private boolean hasAvail;
    @Column(nullable = false)
    private boolean hasInventory;

    public boolean isHasInventory() {
        return hasInventory;
    }

    public void setHasInventory(boolean hasInventory) {
        this.hasInventory = hasInventory;
    }

    public boolean isHasAvail() {
        return hasAvail;
    }

    public void setHasAvail(boolean hasAvail) {
        this.hasAvail = hasAvail;
    }

    public boolean isHasRate() {
        return hasRate;
    }

    public void setHasRate(boolean hasRate) {
        this.hasRate = hasRate;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }
}
