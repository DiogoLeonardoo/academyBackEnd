package com.academy.project.service;

import com.academy.project.model.UserModel;
import com.academy.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserModel> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Transactional
    public UserModel updateUser(UserModel user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public UserModel getUserWithTrainingsAndExercises(Integer userId) {
        return userRepository.findUserWithTrainingsAndExercises(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

}
