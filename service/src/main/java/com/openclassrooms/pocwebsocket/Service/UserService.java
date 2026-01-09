package com.openclassrooms.pocwebsocket.Service;

import com.openclassrooms.pocwebsocket.Model.User;
import com.openclassrooms.pocwebsocket.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll () {
        return userRepository.findAll();
    }

    public Optional<User> findUserById (Integer id) {
        return userRepository.findById(id);
    }
}
