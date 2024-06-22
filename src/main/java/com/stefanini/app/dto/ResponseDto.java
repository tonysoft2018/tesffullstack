package com.stefanini.app.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> implements Serializable{/**
     * 
     */
    private static final long serialVersionUID = 9132423421417133138L;
    private List<T> data;
    private String message;
}
