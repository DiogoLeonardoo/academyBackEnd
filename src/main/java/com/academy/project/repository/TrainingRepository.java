package com.academy.project.repository;

import com.academy.project.model.TrainingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingModel, Integer> {

    @Query("SELECT t FROM TrainingModel t LEFT JOIN FETCH t.exerciseTrainings et LEFT JOIN FETCH et.exercise WHERE t.id = :trainingId")
    Optional<TrainingModel> findByIdWithExercises(Long trainingId);


}
