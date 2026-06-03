package com.test.backend.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private Boolean status;
    private String msg;
    private Object data;
}
