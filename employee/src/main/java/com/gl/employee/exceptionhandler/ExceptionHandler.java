package com.gl.employee.exceptionhandler;

import com.gl.employee.exceptionhandler.EmployeeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

   @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeException.class)
    public String handleException(HttpServletRequest request, EmployeeException ex, RedirectAttributes redirectAttrs) {
        log.error("[GlobalExceptionHandler.FastagInventoryException] url: {}, HttpStatus: {}, " +
                "message: {}, exception:  ", request.getRequestURL().toString(), ex.getStatus(), ex.getMessage(), ex);
       redirectAttrs.addFlashAttribute("errorObject", ex);
       return "redirect:/employees/redirectionUrl";
    }
}
