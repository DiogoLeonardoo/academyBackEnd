package com.academy.project.rest.Controller;
import com.academy.project.exception.ObjectNotFoundException;

import com.academy.project.model.GoalsModel;
import com.academy.project.model.UserModel;
import com.academy.project.repository.GoalsRepository;
import com.academy.project.repository.UserRepository;
import com.academy.project.service.GoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/goals")
public class GoalsController {

    @Autowired
    private GoalsService goalsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoalsRepository goalsRepository;


    @PostMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<GoalsModel>> createGoal(@PathVariable int userId, @RequestBody GoalsModel goal) {
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User não encontrado com o ID: " + userId));
        goal.setUser(user);
        GoalsModel createdGoal = goalsService.createGoal(goal);
        ApiResponse<GoalsModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "Meta criada com sucesso!", createdGoal);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<GoalsModel>>> getGoalsByUserId(@PathVariable int userId) {
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User não encontrado com o ID: " + userId));
        List<GoalsModel> goals = goalsRepository.findByUser(user);
        ApiResponse<List<GoalsModel>> response = new ApiResponse<>(UUID.randomUUID().toString(), "Metas encontradas com sucesso!", goals);
        return ResponseEntity.ok(response);
    }
}