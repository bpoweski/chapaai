package com.derbysoft.chapaai.adapter.core.commons.log;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LogRecorder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long time;
    private String eventName;
    private String errorMessage;

    public LogRecorder() {
    }

    public LogRecorder(String eventName) {
        this.time = System.currentTimeMillis();
        this.eventName = eventName;
    }

    public LogRecorder(String eventName, String errorMessage) {
        this.time = System.currentTimeMillis();
        this.eventName = eventName;
        this.errorMessage = errorMessage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
