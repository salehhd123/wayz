package com.example.wayz.Api.ApiException;

public class ApiException extends RuntimeException{

    public ApiException(String errMessage){
        super(errMessage);
    }
}
