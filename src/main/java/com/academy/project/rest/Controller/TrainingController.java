package com.academy.project.rest.Controller;

import com.academy.project.exception.ObjectNotFoundException;
import com.academy.project.model.ExerciseTrainingModel;
import com.academy.project.model.TrainingModel;
import com.academy.project.model.UserModel;
import com.academy.project.repository.TrainingRepository;
import com.academy.project.repository.UserRepository;
import com.academy.project.service.ExerciseTrainingService;
import com.academy.project.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseTrainingService exerciseTrainingService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TrainingModel>>> getAllTrainings() {
        List<TrainingModel> trainingsList = trainingRepository.findAll();
        String message = trainingsList.isEmpty() ? "Nenhum treinamento encontrado" : "Treinamentos encontrados com sucesso!";
        ApiResponse<List<TrainingModel>> response = new ApiResponse<>(UUID.randomUUID().toString(), message, trainingsList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainingModel>> getTrainingById(@PathVariable("id") int id) {
        TrainingModel trainingModel = trainingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Treinamento não encontrado com o ID: " + id));
        ApiResponse<TrainingModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "Treinamento encontrado com sucesso!", trainingModel);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<TrainingModel>> createTraining(@PathVariable int userId, @RequestBody TrainingModel training) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado com o ID: " + userId));

        // Associar o usuário ao treino
        training.setUser(user);

        // Associar o treino aos exercícios (verifique se os exercícios têm o campo 'training' preenchido)
        if (training.getExerciseTrainings() != null) {
            for (ExerciseTrainingModel exerciseTraining : training.getExerciseTrainings()) {
                exerciseTraining.setTraining(training);  // Garantir que o treino seja associado ao exercício
            }
        }

        // Salvar o treino com os exercícios
        TrainingModel createdTraining = trainingService.createTraining(training);

        ApiResponse<TrainingModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "Treinamento criado com sucesso!", createdTraining);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainingModel>> updateTraining(@PathVariable("id") int id, @RequestBody TrainingModel trainingDetails) {
        TrainingModel training = trainingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Treinamento não encontrado com o ID: " + id));
        training.setNome(trainingDetails.getNome());
        training.setDescricao(trainingDetails.getDescricao());
        training.setExerciseTrainings(trainingDetails.getExerciseTrainings());
        TrainingModel updatedTraining = trainingService.updateTraining(training);
        ApiResponse<TrainingModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "Treinamento atualizado com sucesso!", updatedTraining);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTraining(@PathVariable("id") int id) {
        trainingService.deleteTraining(id);
        ApiResponse<Void> response = new ApiResponse<>(UUID.randomUUID().toString(), "Treinamento deletado com sucesso!", null);
        return ResponseEntity.ok(response);
        }

    }

