package com.derbysoft.chapaai.adapter.core.commons.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PerformanceLog {
    private static final Log LOG = LogFactory.getLog("performanceLog");

    private StringBuilder logContent = new StringBuilder();

    public static PerformanceLog create(String process) {
        PerformanceLog performanceLog = new PerformanceLog();
        performanceLog.appendItem("process", process);
        return performanceLog;
    }

    private PerformanceLog() {
    }

    public void appendItem(String key, Object value) {
        logContent.append("<").append(key).append("=").append(value == null ? "-" : value.toString().trim()).append(">");
    }

    public void addError(Throwable e) {
        if (e != null) {
            appendItem("error.type", PerformanceLogError.getType(e));
            appendItem("error.code", PerformanceLogError.getCode(e));
            appendItem("error.message", PerformanceLogError.getErrorMessage(e));
        }
    }

    public void save() {

        LOG.info(logContent.toString());
    }
}
