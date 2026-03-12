package com.examen.backend.dto;

public class ResponseDTO {
    public boolean status;
    public String msg;
    public Object data;

    public ResponseDTO(boolean status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
