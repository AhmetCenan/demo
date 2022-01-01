package com.example.Demo.Service;

import com.example.Demo.Comman.EntityDTOConverter;
import com.example.Demo.Model.Dto.UserDTO;
import com.example.Demo.Model.Entity.User;
import com.example.Demo.Model.Request.User.LoginRequest;
import com.example.Demo.Model.Request.User.SignUpRequest;
import com.example.Demo.Model.Response.JwtResponse;
import com.example.Demo.Model.Response.Response;
import com.example.Demo.Repository.UserRepository;
import com.example.Demo.Security.Jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    private static final EntityDTOConverter<User, UserDTO> entityToDtoConverter = new EntityDTOConverter(UserDTO.class);

    public ResponseEntity<?> getAllUsers(){
        try {
            List<User> users = userRepository.findAll();
            return new ResponseEntity<>(new Response(users.stream().map(entityToDtoConverter::convert)
                    .collect(Collectors.toCollection(ArrayList::new)), true), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> login(LoginRequest loginRequest){
        try {
            User user = userRepository.findByUserName(loginRequest.getUserName());
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
            }
            if (!user.isActive()) {
                return new ResponseEntity<>("User is passive", HttpStatus.UNAUTHORIZED);
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
            if (!authentication.isAuthenticated()) {
                return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
            }
            JwtResponse jwt = new JwtResponse();
            jwt.setJwt(jwtUtils.generateJwtToken(user));
            return new ResponseEntity<>(new Response(jwt,true), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> signUp(SignUpRequest signUpRequest){
        try {
            User newUser = new User();
            newUser.setUserName(signUpRequest.getUserName());
            newUser.setPassword(encoder.encode(signUpRequest.getPassword()));
            newUser.setEmail(signUpRequest.getEmail());
            newUser.setActive(true);
            userRepository.save(newUser);
            return new ResponseEntity<>(new Response(entityToDtoConverter.convert(newUser),true),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }

}
