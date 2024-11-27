package com.academy.project.rest.Controller;

import com.academy.project.exception.ObjectNotFoundException;
import com.academy.project.model.MuscleGroupModel;
import com.academy.project.rest.Controller.ApiResponse;
import com.academy.project.service.MuscleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/muscle-group")
public class MuscleGroupController {

    @Autowired
    private MuscleGroupService muscleGroupService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MuscleGroupModel>>> getAllMuscleGroups() {
        List<MuscleGroupModel> muscleGroups = muscleGroupService.findAll();
        ApiResponse<List<MuscleGroupModel>> response = new ApiResponse<>(UUID.randomUUID().toString(), "Muscle groups found successfully!", muscleGroups);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MuscleGroupModel>> getMuscleGroupById(@PathVariable int id) {
        MuscleGroupModel muscleGroup = muscleGroupService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Muscle group not found with ID: " + id));
        ApiResponse<MuscleGroupModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "Muscle group found successfully!", muscleGroup);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MuscleGroupModel>> createMuscleGroup(@RequestBody MuscleGroupModel newMuscleGroup) {
        MuscleGroupModel muscleGroup = muscleGroupService.save(newMuscleGroup);
        ApiResponse<MuscleGroupModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "Muscle group created successfully!", muscleGroup);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MuscleGroupModel>> updateMuscleGroup(@PathVariable int id, @RequestBody MuscleGroupModel updatedMuscleGroup) {
        updatedMuscleGroup.setId(id);
        MuscleGroupModel muscleGroup = muscleGroupService.update(updatedMuscleGroup);
        ApiResponse<MuscleGroupModel> response = new ApiResponse<>(UUID.randomUUID().toString(), "Muscle group updated successfully!", muscleGroup);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMuscleGroup(@PathVariable int id) {
        muscleGroupService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(UUID.randomUUID().toString(), "Muscle group deleted successfully!", null);
        return ResponseEntity.ok(response);
    }
}