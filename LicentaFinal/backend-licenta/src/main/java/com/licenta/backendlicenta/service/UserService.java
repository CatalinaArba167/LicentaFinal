package com.licenta.backendlicenta.service;

import com.licenta.backendlicenta.domain.User;
import com.licenta.backendlicenta.repository.UserRepository;
import com.licenta.backendlicenta.utils.ImageUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import static com.licenta.backendlicenta.controller.exception.message.Messages.USER_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    public static final String USER = "USERS";
    public static final String NOT_FOUND = " not found";


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new EntityExistsException(USER_ALREADY_EXISTS);

    }

//    public User updateUser(User newUser) {
//
//        Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());
//        if (existingUser.isPresent()) {
//            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
//            return userRepository.save(newUser);
//        }
//        throw new EntityExistsException(USER + NOT_FOUND);
//    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            return user.get();
        throw new EntityNotFoundException(USER + NOT_FOUND);
    }


    public User findByID(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return user.get();
        throw new EntityNotFoundException(USER + NOT_FOUND);
    }

    public void validateUserId(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new EntityNotFoundException(USER + NOT_FOUND);
    }

    public User updateUser(User user){
        this.validateUserId(user.getId());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.get().getEmail())
                    .authorities(USER)
                    .passwordEncoder(passwordEncoder::encode)
                    .password(user.get().getPassword())
                    .build();
        }
        throw new UsernameNotFoundException(USER + email + NOT_FOUND);
    }




}
