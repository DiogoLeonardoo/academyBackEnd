package com.academy.project.service;

import com.academy.project.model.GoalsModel;
import com.academy.project.repository.GoalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalsService {

    @Autowired
    private GoalsRepository goalsRepository;

    public GoalsModel createGoal(GoalsModel goal) {
        return goalsRepository.save(goal);
    }
}