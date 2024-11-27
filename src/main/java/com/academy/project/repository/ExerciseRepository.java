// src/main/java/com/academy/project/repository/ExerciseRepository.java
package com.academy.project.repository;

import com.academy.project.model.ExerciseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseModel, Integer> {

    @Query("SELECT e FROM ExerciseModel e LEFT JOIN FETCH e.muscleGroups WHERE e.id = :exerciseId")
    Optional<ExerciseModel> findByIdWithMuscleGroups(Integer exerciseId);

    @Query("SELECT e FROM ExerciseModel e JOIN FETCH e.muscleGroups")
    List<ExerciseModel> findAllWithMuscleGroups();
}