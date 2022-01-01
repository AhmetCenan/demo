package com.example.Demo.Model.Entity;


import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "Users",schema ="public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String password;
    private String email;
    private boolean isActive;
}
