package com.academy.project.repository;

import com.academy.project.model.MuscleGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuscleGroupRepository extends JpaRepository<MuscleGroupModel, Integer> {

    List<MuscleGroupModel> findAll();
}
