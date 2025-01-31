package com.stefanini.personal.domain.dto;

public class ApiResponse<T> {
    private boolean status;
    private String msg;
    private T data;

    // Constructor
    public ApiResponse(boolean status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    // Getters y setters
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}