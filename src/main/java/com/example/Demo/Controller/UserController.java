package com.example.Demo.Controller;


import com.example.Demo.Model.Request.User.LoginRequest;
import com.example.Demo.Model.Request.User.SignUpRequest;
import com.example.Demo.Model.Response.Response;
import com.example.Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public ResponseEntity<?> allUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        return userService.signUp(signUpRequest);
    }

}
