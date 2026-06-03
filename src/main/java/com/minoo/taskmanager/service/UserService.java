package com.minoo.taskmanager.service;

import com.minoo.taskmanager.dto.UserDto;
import com.minoo.taskmanager.entity.User;
import com.minoo.taskmanager.exception.DuplicateResourceException;
import com.minoo.taskmanager.exception.ResourceNotFoundException;
import com.minoo.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User createUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        return userRepository.save(user);
    }
}