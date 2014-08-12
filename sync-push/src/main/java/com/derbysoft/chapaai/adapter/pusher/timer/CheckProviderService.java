package com.derbysoft.chapaai.adapter.pusher.timer;

import com.derbysoft.chapaai.adapter.pusher.domain.model.Provider;
import com.derbysoft.chapaai.adapter.pusher.repository.ProviderRepository;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class CheckProviderService implements InitializingBean {
    private static final int MINUTE_TO_MILLISECOND = 60 * 1000;

    private ProviderRepository providerRepository;

    private Map<String, ProviderChangesPushJob> providerChangesPushJobMap;

    private int checkIntervalMinutes;

    private static final Timer TIMER = new Timer();

    @Override
    public void afterPropertiesSet() throws Exception {
        TIMER.schedule(checkProviderTimeTask(), 0, checkIntervalMinutes * MINUTE_TO_MILLISECOND);
    }

    private TimerTask checkProviderTimeTask() {
        return new TimerTask() {
            @Override
            public void run() {
                for (Provider provider : providerRepository.list()) {
                    ProviderChangesPushJob changesPushJob = providerChangesPushJobMap.get(provider.getProviderCode());
                    if (changesPushJob == null) {
                        throw new IllegalStateException(String.format("Not Found ChangesPushJob By Provider:%s !!", provider.getProviderCode()));
                    }
                    if (provider.isDisabled() && changesPushJob.isRunning()) {
                        changesPushJob.stop();
                        continue;
                    }
                    if (!provider.isDisabled() && !changesPushJob.isRunning()) {
                        changesPushJob.push();
                    }
                }
            }
        };
    }

    public void setProviderChangesPushJobMap(Map<String, ProviderChangesPushJob> providerChangesPushJobMap) {
        this.providerChangesPushJobMap = providerChangesPushJobMap;
    }

    public void setCheckIntervalMinutes(int checkIntervalMinutes) {
        this.checkIntervalMinutes = checkIntervalMinutes;
    }

    public void setProviderRepository(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }
}
