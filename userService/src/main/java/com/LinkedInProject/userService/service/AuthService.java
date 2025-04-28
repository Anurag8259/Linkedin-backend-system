package com.LinkedInProject.userService.service;

import com.LinkedInProject.userService.dto.LoginRequestDto;
import com.LinkedInProject.userService.dto.SignupRequestDto;
import com.LinkedInProject.userService.dto.UserDto;
import com.LinkedInProject.userService.entity.User;
import com.LinkedInProject.userService.exception.BadRequestException;
import com.LinkedInProject.userService.exception.ResourceNotFoundException;
import com.LinkedInProject.userService.repository.UserRepository;
import com.LinkedInProject.userService.utils.BCrypt;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;

    public AuthService(UserRepository userRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    public UserDto signUp(SignupRequestDto signUpRequestDto) {

        log.info("Signup a user with email :{}",signUpRequestDto.getEmail());

        boolean exists=userRepository.existsByEmail(signUpRequestDto.getEmail());
        if(exists){
            throw new BadRequestException("User already exists");
        }

        User user=modelMapper.map(signUpRequestDto, User.class);
        user.setPassword(BCrypt.hash(signUpRequestDto.getPassword()));

        user=userRepository.save(user);
        return modelMapper.map(user,UserDto.class);

    }

    public String login(LoginRequestDto loginRequestDto) {

        log.info("Login request for user with email :{}",loginRequestDto.getEmail());

        User user=userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()->new BadRequestException("Incorrect email or password"));

        boolean isPasswordMatch = BCrypt.match(loginRequestDto.getPassword(),user.getPassword());

        if(!isPasswordMatch){
            throw new BadRequestException("Incorrect email or password");
        }

        return jwtService.generateAccessToken(user);
    }

}
