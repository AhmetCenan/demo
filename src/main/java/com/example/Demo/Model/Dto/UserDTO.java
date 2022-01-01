package com.example.Demo.Model.Dto;

import lombok.Data;

@Data
public class UserDTO {

    private String userName;
    private String password;
    private String email;
    private boolean isActive;

}
