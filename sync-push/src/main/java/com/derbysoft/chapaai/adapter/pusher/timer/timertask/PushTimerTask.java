package com.derbysoft.chapaai.adapter.pusher.timer.timertask;

import com.derbysoft.chapaai.adapter.pusher.timer.ProviderPushTimer;
import com.derbysoft.chapaai.adapter.pusher.domain.model.Provider;
import com.derbysoft.chapaai.adapter.pusher.domain.model.constant.SyncConstant;
import com.derbysoft.chapaai.adapter.pusher.repository.ProviderRepository;

import java.util.*;

public abstract class PushTimerTask extends TimerTask {

    private final Map<String, ProviderPushTimer> providerPullTimerMap
            = new HashMap<String, ProviderPushTimer>();

    private long intervalSeconds;

    private ProviderRepository providerRepository;


    public PushTimerTask() {
        this(SyncConstant.DEFAULT_INTERVAL_SECONDS);
    }


    public PushTimerTask(int intervalSeconds) {
        this.intervalSeconds = intervalSeconds;
    }


    @Override
    public void run() {
        checkChange();
    }

    public void checkChange() {
        List<Provider> providers = getProviderList();
        startPushTimers(providers);
    }

    public List<Provider> getProviderList() {
        return providerRepository.listEnable();
    }

    public void startPushTimers(List<Provider> providers) {

        ProviderPushTimer providerPushTimer;
        for (Provider provider : providers) {
            String providerCode = provider.getProviderCode();
            if (!providerPullTimerMap.containsKey(providerCode)) {
                providerPushTimer = createProviderPushTimer();
                providerPushTimer.start(providerCode, intervalSeconds, provider.getPushThreadPoolSize());
                providerPullTimerMap.put(providerCode, providerPushTimer);
            }
        }
    }

    public void stop() {
        for (ProviderPushTimer providerPullTimer : providerPullTimerMap.values()) {
            providerPullTimer.stop();
        }
    }

    public abstract ProviderPushTimer createProviderPushTimer();

    public long getIntervalSeconds() {
        return intervalSeconds;
    }

    public void setIntervalSeconds(long intervalSeconds) {
        this.intervalSeconds = intervalSeconds;
    }

    public ProviderRepository getProviderRepository() {
        return providerRepository;
    }

    public void setProviderRepository(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public Map<String, ProviderPushTimer> getProviderPullTimerMap() {
        return providerPullTimerMap;
    }
}
