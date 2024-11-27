package com.academy.project.repository;

import com.academy.project.model.ExerciseModel;
import com.academy.project.model.ExerciseTrainingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseTrainingRepository extends JpaRepository<ExerciseTrainingModel, Integer> {

    @Query("SELECT et FROM ExerciseTrainingModel et WHERE et.training.id = :trainingId")
    List<ExerciseTrainingModel> findByTrainingId(@Param("trainingId") Long trainingId);

    List<ExerciseTrainingModel> findByExercise(ExerciseModel exercise);
}