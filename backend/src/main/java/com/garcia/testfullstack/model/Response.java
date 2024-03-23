package com.garcia.testfullstack.model;

import java.util.List;

public class Response<T> {

    public Response(boolean b, String m, List<PersonEntity> persons) {
        status = b;
        msg = m;
        data = (List<T>) persons;
    }

    public boolean status;
    public String msg;
    public List<T> data;
}
