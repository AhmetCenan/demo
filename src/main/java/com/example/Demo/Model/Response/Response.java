package com.example.Demo.Model.Response;

import lombok.Data;

@Data
public class Response {
    private Object data;
    private boolean isSuccess;
    public Response(Object data,boolean isSuccess){
        this.data = data;
        this.isSuccess = isSuccess;
    }
}
