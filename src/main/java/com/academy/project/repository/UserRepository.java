package com.academy.project.repository;

import com.academy.project.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    @Query("""
        SELECT u FROM UserModel u
        LEFT JOIN FETCH u.trainings t
        LEFT JOIN FETCH t.exerciseTrainings et
        LEFT JOIN FETCH et.exercise
        WHERE u.id = :userId
    """)
    Optional<UserModel> findUserWithTrainingsAndExercises(@Param("userId") Integer userId);
}

