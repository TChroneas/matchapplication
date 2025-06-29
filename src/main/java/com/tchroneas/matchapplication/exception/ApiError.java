package com.tchroneas.matchapplication.exception;

import java.time.LocalDateTime;

class ApiError {

    private LocalDateTime time = LocalDateTime.now();
    private String status;
    private String message;

    public ApiError(){};

    public ApiError(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
