package com.derbysoft.chapaai.adapter.pusher.timer;

import com.derbysoft.chapaai.adapter.pusher.domain.model.Provider;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component("pushLOSRateJob")
public class PushLOSRateJob extends BaseProviderChangesPushJob {
    private static final AtomicBoolean IS_RUNNING = new AtomicBoolean(true);

    @Override
    public boolean isRunning() {
        return IS_RUNNING.get();
    }

    @Override
    public void stop() {
        //cancel child task
        IS_RUNNING.set(false);
    }

    @Override
    public void push(Provider provider) {

    }
}
