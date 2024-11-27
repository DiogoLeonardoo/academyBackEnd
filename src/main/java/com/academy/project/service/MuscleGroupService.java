package com.academy.project.service;

import com.academy.project.exception.ObjectNotFoundException;
import com.academy.project.model.MuscleGroupModel;
import com.academy.project.repository.MuscleGroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuscleGroupService {

    @Autowired
    private MuscleGroupRepository muscleGroupRepository;

    public List<MuscleGroupModel> findAll() {
        return muscleGroupRepository.findAll();
    }

    public Optional<MuscleGroupModel> findById(int id) {
        return muscleGroupRepository.findById(id);
    }

    @Transactional
    public MuscleGroupModel save(MuscleGroupModel muscleGroup) {
        return muscleGroupRepository.save(muscleGroup);
    }

    @Transactional
    public MuscleGroupModel update(MuscleGroupModel muscleGroup) {
        if (!muscleGroupRepository.existsById(muscleGroup.getId())) {
            throw new ObjectNotFoundException("Muscle group not found with ID: " + muscleGroup.getId());
        }
        return muscleGroupRepository.save(muscleGroup);
    }

    @Transactional
    public void delete(int id) {
        if (!muscleGroupRepository.existsById(id)) {
            throw new ObjectNotFoundException("Muscle group not found with ID: " + id);
        }
        muscleGroupRepository.deleteById(id);
    }
}