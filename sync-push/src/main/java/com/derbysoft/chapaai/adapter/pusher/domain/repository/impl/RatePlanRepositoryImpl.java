package com.derbysoft.chapaai.adapter.pusher.domain.repository.impl;

import com.derbysoft.chapaai.adapter.pusher.domain.model.RatePlan;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.RatePlanRepository;

public class RatePlanRepositoryImpl extends ChapaaiEntryRepositoryImpl<RatePlan>
                                   implements RatePlanRepository {
    @Override
    public RatePlan getRatePlan(String RatePlanCode) {
        return this.getChapaaiMappingEntry(RatePlanCode);
    }
}
