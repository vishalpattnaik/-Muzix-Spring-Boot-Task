package com.stackroute.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

//Global Exception Handler Class
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(TrackNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ErrorMessage handleTracknotFoundException(final TrackNotFoundException e, final HttpServletRequest request) {
        ErrorMessage errorClass = new ErrorMessage();
        errorClass.setErrormessage(e.getMessage());
        errorClass.setRequestedURI(request.getRequestURI());
        return errorClass;
    }


    @ExceptionHandler(TrackAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @ResponseBody
    ErrorMessage handleTrackAlreadyExists(final TrackAlreadyExistsException e, final HttpServletRequest request) {
        ErrorMessage errorClass = new ErrorMessage();
        errorClass.setErrormessage(e.getMessage());
        errorClass.setRequestedURI(request.getRequestURI());
        return errorClass;

    }
}