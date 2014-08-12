package com.derbysoft.chapaai.adapter.pusher.timer;

public abstract class BaseProviderChangesPushJob implements ProviderChangesPushJob {
    @Override
    public void run() {
        push();
    }
}
