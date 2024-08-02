package com.licenta.backendlicenta.controller;

import com.licenta.backendlicenta.dto.UserDto;
import com.licenta.backendlicenta.mapper.UserMapper;
import com.licenta.backendlicenta.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody @NonNull UserDto userDto) {
        return new ResponseEntity<>(userMapper.toDto(userService.createUser(userMapper.toEntity(userDto))), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID userId) {
        return new ResponseEntity<>(userMapper.toDto(userService.findByID(userId)), HttpStatus.OK);
    }


    @PutMapping()
    public ResponseEntity<UserDto> updateUser(@RequestBody @NonNull UserDto userDto) {
        return new ResponseEntity<>(userMapper.toDto(userService.updateUser(userMapper.toEntity(userDto))), HttpStatus.OK);
    }


}