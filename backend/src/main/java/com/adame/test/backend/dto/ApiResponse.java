package com.adame.test.backend.dto;

import java.util.List;

public class ApiResponse<T> {
    private boolean status;
    private String msg;
    private List<T> data;

    public ApiResponse(boolean status, String msg, List<T> data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiResponse(boolean status, String msg, T singleData) {

        this.status = status;

        this.msg = msg;

        this.data = List.of(singleData);

    }

    // Getters y Setters
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
