package com.leanpay.loancalculator.controller;

import com.leanpay.loancalculator.exceptions.IncompleteDataException;
import com.leanpay.loancalculator.exceptions.IncorrectDataException;
import com.leanpay.loancalculator.exceptions.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IncorrectDataException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResponse handleIncorrectDataExceptionException() {
        return new JsonResponse("Data provided is not in the right format. Please check the data entered and try again.");
    }

    @ExceptionHandler({IncompleteDataException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResponse handleIncompleteDataExceptionException() {
        return new JsonResponse("Data provided is incomplete. You need to have loanAmount, interestRate and loanTerm. \n Please check the data entered and try again.");
    }
}
