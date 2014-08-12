package com.derbysoft.chapaai.adapter.pusher.domain.model;


import com.derbysoft.ccs.core.MappingEntry;
import com.derbysoft.common.util.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Provider implements MappingEntry {
    private String providerCode;
    private int pushThreadCount;
    private boolean disabled;
    private String pushChangeJobNames;

    public List<String> getPushChangeJobNameList() {
        ArrayList<String> pushJobNameList = new ArrayList<String>();
        if (StringUtils.isBlank(pushChangeJobNames)) {
            return pushJobNameList;
        }
        Collections.addAll(pushJobNameList, StringUtils.split(pushChangeJobNames, Constants.COMMA));
        return pushJobNameList;
    }

    public String getPushChangeJobNames() {
        return pushChangeJobNames;
    }

    public void setPushChangeJobNames(String pushChangeJobNames) {
        this.pushChangeJobNames = pushChangeJobNames;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public int getPushThreadCount() {
        return pushThreadCount;
    }

    public void setPushThreadCount(int pushThreadCount) {
        this.pushThreadCount = pushThreadCount;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
