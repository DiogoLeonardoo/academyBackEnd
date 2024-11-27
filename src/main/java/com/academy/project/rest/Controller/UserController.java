package com.academy.project.rest.Controller;
import com.academy.project.exception.ObjectNotFoundException;
import com.academy.project.model.UserModel;
import com.academy.project.repository.UserRepository;
import com.academy.project.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserModel>>> GetAllUsers() {
        List<UserModel> usersList = userRepository.findAll();
        String message = usersList.isEmpty() ? "Nenhum usuário encontrado" : "Users encontrados com sucesso!";
        ApiResponse<List<UserModel>> response = new ApiResponse<>(UUID.randomUUID().toString(),message, usersList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserModel>> GetById(@PathVariable("id") int id) {
        UserModel userModel = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User não encontrado com o ID: " + id));
        ApiResponse<UserModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "User encontrado com sucesso!", userModel);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserModel>> createUser(@RequestBody @Valid UserModel user) {
        try {
            UserModel createdUser = userService.createUser(user);
            ApiResponse<UserModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "User criado com sucesso!", createdUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<UserModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "Falha ao criar user!", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletar (@PathVariable int id) {
        userService.deleteUser(id);
        ApiResponse<Void> response = new ApiResponse<>(UUID.randomUUID().toString(), "User deletado!", null);
        return ResponseEntity.ok(response);
    }
}