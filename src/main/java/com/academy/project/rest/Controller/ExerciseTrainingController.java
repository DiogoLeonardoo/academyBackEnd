package com.academy.project.rest.Controller;

import com.academy.project.model.ExerciseTrainingModel;
import com.academy.project.service.ExerciseTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercise-trainings")
public class ExerciseTrainingController {

    @Autowired
    private ExerciseTrainingService exerciseTrainingService;

    // Obter todos os exercícios de treinamento
    @GetMapping
    public ResponseEntity<ApiResponse<List<ExerciseTrainingModel>>> getAllExerciseTrainings() {
        List<ExerciseTrainingModel> exerciseTrainings = exerciseTrainingService.getAllExerciseTrainings();
        ApiResponse<List<ExerciseTrainingModel>> response = new ApiResponse<>(UUID.randomUUID().toString(), "Exercise Trainings found successfully!", exerciseTrainings);
        return ResponseEntity.ok(response);
    }

    // Obter exercício de treinamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseTrainingModel> getExerciseTrainingById(@PathVariable Integer id) {
        ExerciseTrainingModel exerciseTraining = exerciseTrainingService.getExerciseTrainingById(id);
        return ResponseEntity.ok(exerciseTraining);
    }

    // Criar novo exercício de treinamento
    @PostMapping
    public ResponseEntity<ApiResponse<ExerciseTrainingModel>> createExerciseTraining(@RequestBody ExerciseTrainingModel exerciseTraining) {
        ExerciseTrainingModel createdExerciseTraining = exerciseTrainingService.createExerciseTraining(exerciseTraining);
        ApiResponse<ExerciseTrainingModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "Exercise Training created successfully!", createdExerciseTraining);
        return ResponseEntity.ok(response);
    }

    // Atualizar exercício de treinamento
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExerciseTrainingModel>> updateExerciseTraining(@PathVariable Integer id, @RequestBody ExerciseTrainingModel updatedExerciseTraining) {
        ExerciseTrainingModel updated = exerciseTrainingService.updateExerciseTraining(id, updatedExerciseTraining);
        ApiResponse<ExerciseTrainingModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "Exercise Training updated successfully!", updated);
        return ResponseEntity.ok(response);
    }

    // Deletar exercício de treinamento
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteExerciseTraining(@PathVariable Integer id) {
        exerciseTrainingService.deleteExerciseTraining(id);
        ApiResponse<Void> response = new ApiResponse<>(UUID.randomUUID().toString(), "Exercise Training deleted successfully!", null);
        return ResponseEntity.ok(response);
    }

    // Adicionar exercício a um treino
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addExerciseToTraining(@RequestParam Integer trainingId,
                                                                     @RequestParam Integer exerciseId,
                                                                     @RequestParam Integer sets,
                                                                     @RequestParam Integer repetitions,
                                                                     @RequestParam Integer restTime) {
        exerciseTrainingService.addExerciseToTraining(trainingId, exerciseId, sets, repetitions, restTime);
        ApiResponse<String> response = new ApiResponse<>(UUID.randomUUID().toString(), "Exercise added to training successfully!", "Exercise added.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/training/{trainingId}")
    public ResponseEntity<ApiResponse<List<ExerciseTrainingModel>>> getExercisesByTrainingId(@PathVariable Integer trainingId) {
        List<ExerciseTrainingModel> exerciseTrainings = exerciseTrainingService.getExercisesByTrainingId(trainingId);
        ApiResponse<List<ExerciseTrainingModel>> response = new ApiResponse<>(UUID.randomUUID().toString(), "Exercises found successfully!", exerciseTrainings);
        return ResponseEntity.ok(response);
    }

}
