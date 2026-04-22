package com.praktikumDB.deploy.service;

import com.praktikumDB.deploy.model.User;
import com.praktikumDB.deploy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User request){
        request.setId(UUID.randomUUID().toString());
        return userRepository.save(request);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByID(String id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

    public User updateUser(String id, User requests){
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser != null){
            existingUser.setName(requests.getName());
            existingUser.setNim(requests.getNim());
            return userRepository.save(existingUser);
        }
        return null;

    }


}
