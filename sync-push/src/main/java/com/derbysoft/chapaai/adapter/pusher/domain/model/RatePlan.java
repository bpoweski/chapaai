package com.derbysoft.chapaai.adapter.pusher.domain.model;

import com.derbysoft.ccs.core.MappingEntry;

public class RatePlan implements ChapaaiMappingEntry {

    private String ratePlanCode;

    private String channelRatePlanCode;

    private boolean disabled;

    public String getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    public String getChannelRatePlanCode() {
        return channelRatePlanCode;
    }

    public void setChannelRatePlanCode(String channelRatePlanCode) {
        this.channelRatePlanCode = channelRatePlanCode;
    }

    @Override
    public boolean isDisabled() {
        return disabled;
    }

    @Override
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatePlan)) return false;

        RatePlan ratePlan = (RatePlan) o;

        if (channelRatePlanCode != null ? !channelRatePlanCode.equals(ratePlan.channelRatePlanCode) : ratePlan.channelRatePlanCode != null)
            return false;
        if (ratePlanCode != null ? !ratePlanCode.equals(ratePlan.ratePlanCode) : ratePlan.ratePlanCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ratePlanCode != null ? ratePlanCode.hashCode() : 0;
        result = 31 * result + (channelRatePlanCode != null ? channelRatePlanCode.hashCode() : 0);
        return result;
    }


}
