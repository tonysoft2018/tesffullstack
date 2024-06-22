package com.stefanini.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGeneralDto<T> implements Serializable{/**
     * 
     */
    private static final long serialVersionUID = 8266385392184524838L;
    private T data;
    private String message;
}
