package com.derbysoft.chapaai.adapter.pusher.domain.exception;


import com.derbysoft.dswitch.dto.common.ErrorDTO;

public class GetChangeException extends RuntimeException{

    private String code;
    private String source;

    public GetChangeException() {
    }

    public GetChangeException(String message) {
        super(message);
    }

    public GetChangeException(ErrorDTO error) {
        super(error.getMessage());
        this.code = error.getCode();
        this.source= error.getSource();
    }

    public GetChangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetChangeException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
