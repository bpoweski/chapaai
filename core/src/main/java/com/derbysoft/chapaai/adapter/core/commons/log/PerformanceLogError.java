package com.derbysoft.chapaai.adapter.core.commons.log;

public abstract class PerformanceLogError {
    public static String getType(Throwable e) {
        return "errorType";
    }
    public static String getCode(Throwable e){
        return e.getClass().getSimpleName();
    }
    public static String getErrorMessage(Throwable e){
        return e.getMessage();
    }
}
