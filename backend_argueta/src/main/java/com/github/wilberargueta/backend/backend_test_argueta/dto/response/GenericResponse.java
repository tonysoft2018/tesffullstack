package com.github.wilberargueta.backend.backend_test_argueta.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponse<D> {
    private Boolean status;
    private String message;
    private D data;

    public static <D> GenericResponse<D> ok(D data) {

        return GenericResponse.<D>builder().data(data).status(true).message("Petición exitosa").build();
    }

}
