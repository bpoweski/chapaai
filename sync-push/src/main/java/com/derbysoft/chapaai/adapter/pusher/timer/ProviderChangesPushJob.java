package com.derbysoft.chapaai.adapter.pusher.timer;

import com.derbysoft.chapaai.adapter.pusher.domain.model.Provider;

public interface ProviderChangesPushJob extends Runnable {
    boolean isRunning();

    void stop();

    void push(Provider provider);
}
