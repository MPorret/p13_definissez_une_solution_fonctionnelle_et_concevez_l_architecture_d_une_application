package com.openclassrooms.pocwebsocket.Controller;

import com.openclassrooms.pocwebsocket.Model.User;
import com.openclassrooms.pocwebsocket.Repository.UserRepository;
import com.openclassrooms.pocwebsocket.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("")
    public List<User> getAllUsers () {
        return userService.findAll();
    }
}