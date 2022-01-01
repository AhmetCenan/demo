package com.example.Demo.Model.Request.User;

import lombok.Data;

@Data
public class SignUpRequest {

    private String userName;
    private String password;
    private String email;

}
