package com.academy.project.repository;

import com.academy.project.model.GoalsModel;
import com.academy.project.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalsRepository extends JpaRepository<GoalsModel, Integer> {
    List<GoalsModel> findByUser(UserModel user);
}