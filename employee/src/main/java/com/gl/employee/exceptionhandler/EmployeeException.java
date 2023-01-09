package com.gl.employee.exceptionhandler;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
public class EmployeeException extends Exception {

    protected String code;
    protected HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * Just provide a message String
     *
     * @param p_message
     */
    public EmployeeException(String p_message) {
        super(p_message);
    }

    public EmployeeException(String p_message, HttpStatus status) {
        super(p_message);
        this.status = status;
    }
}