package com.joya.testfullstack.model;

import java.util.List;

public class ApiResponse<T> {

    public boolean status;
    public String msg;
    public List<T> data;

    public ApiResponse() {
    }

    public ApiResponse(boolean status, String msg, List<T> data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(String msg, List<T> data) {
        return new ApiResponse<>(true, msg, data);
    }

    public static <T> ApiResponse<T> error(String msg) {
        return new ApiResponse<>(false, msg, null);
    }
}
