package com.inventoryManagement.exception;

import com.inventoryManagement.enums.EnumStatus;
import com.inventoryManagement.model.InventoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Component
public class ProductExceptionHandler {


    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public InventoryResponse methodArgHandlerForEmptyBody(HttpMessageNotReadableException ex) {
        InventoryResponse error = new InventoryResponse();
        error.setErrorCode("97");
        error.setErrorDescription("input error");
        error.setStatus(EnumStatus.FAIL);
        return error;
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public InventoryResponse badRequestHandler(ConstraintViolationException ex) {
        InventoryResponse error = new InventoryResponse();
        error.setErrorCode("97");
        error.setErrorDescription("input error");
        error.setStatus(EnumStatus.FAIL);
        return error;
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public InventoryResponse methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        InventoryResponse error = new InventoryResponse();
        error.setErrorCode("97");
        error.setErrorDescription("input error");
        error.setStatus(EnumStatus.FAIL);
        return error;
    }
}
