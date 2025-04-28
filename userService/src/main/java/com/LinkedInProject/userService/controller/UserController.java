package com.LinkedInProject.userService.controller;

import com.LinkedInProject.userService.dto.LoginRequestDto;
import com.LinkedInProject.userService.dto.SignupRequestDto;
import com.LinkedInProject.userService.dto.UserDto;
import com.LinkedInProject.userService.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class UserController {

    private final AuthService userService;

    public UserController(AuthService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signUpRequestDto){
        return new ResponseEntity<>(userService.signUp(signUpRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto){
        String token=userService.login(loginRequestDto);
        return ResponseEntity.ok(token);
    }


}
