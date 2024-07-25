package com.examen.zapata_backend.dtos;

import com.examen.zapata_backend.entities.Person;

import java.util.List;

public class ResponseDto {
    Boolean status;
    String msg;
    List<Person> data;

    public ResponseDto(Boolean status, String msg, List<Person> data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Person> getData() {
        return data;
    }

    public void setData(List<Person> data) {
        this.data = data;
    }
}
