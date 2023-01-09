package com.gl.employee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private String status;
    private String message;
    private T response;
    private String statusCode;

    public BaseResponse(T response){
        this.response=response;
    }
}