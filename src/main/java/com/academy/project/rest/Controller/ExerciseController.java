package com.academy.project.rest.Controller;

import com.academy.project.dto.ExerciseWithMuscleGroupDto;
import com.academy.project.exception.ObjectNotFoundException;
import com.academy.project.model.ExerciseModel;
import com.academy.project.model.MuscleGroupModel;
import com.academy.project.repository.MuscleGroupRepository;
import com.academy.project.repository.ExerciseRepository;
import com.academy.project.rest.Controller.ApiResponse;
import com.academy.project.service.ExerciseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private MuscleGroupRepository muscleGroupRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ExerciseWithMuscleGroupDto>>> getAllExercises() {
        List<ExerciseWithMuscleGroupDto> exercises = exerciseService.getAllExercisesWithMuscleGroups();
        ApiResponse<List<ExerciseWithMuscleGroupDto>> response = new ApiResponse<>(UUID.randomUUID().toString(), "Exercises found successfully!", exercises);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseModel> getExercise(@PathVariable Integer id) {
        ExerciseModel exercise = exerciseService.getExerciseWithMuscleGroups(id);
        return ResponseEntity.ok(exercise);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ExerciseModel>> createExercise(@RequestBody ExerciseModel newExercise) {
        ExerciseModel exercise = exerciseService.save(newExercise);
        ApiResponse<ExerciseModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "Exercise created successfully!", exercise);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExerciseModel>> updateExercise(@PathVariable int id, @RequestBody ExerciseModel updatedExercise) {
        updatedExercise.setId(id);
        ExerciseModel exercise = exerciseService.update(updatedExercise);
        ApiResponse<ExerciseModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "Exercise updated successfully!", exercise);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteExercise(@PathVariable int id) {
        exerciseService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(UUID.randomUUID().toString(), "Exercise deleted successfully!", null);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/add-exercise-musclegroup")
    public void addExercisesToMuscleGroup(Integer muscleGroupId, List<Integer> exerciseIds) {
        MuscleGroupModel muscleGroup = muscleGroupRepository.findById(muscleGroupId)
                .orElseThrow(() -> new RuntimeException("Grupo muscular não encontrado"));

        List<ExerciseModel> exercises = exerciseRepository.findAllById(exerciseIds);

        muscleGroup.getExercises().addAll(exercises);
        muscleGroupRepository.save(muscleGroup); // Persiste a associação
    }
}