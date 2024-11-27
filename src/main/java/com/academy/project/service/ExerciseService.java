package com.academy.project.service;

import com.academy.project.dto.ExerciseDto;
import com.academy.project.dto.ExerciseWithMuscleGroupDto;
import com.academy.project.dto.MuscleGroupDto;
import com.academy.project.exception.ObjectNotFoundException;
import com.academy.project.model.ExerciseModel;
import com.academy.project.model.ExerciseTrainingModel;
import com.academy.project.model.MuscleGroupModel;
import com.academy.project.repository.ExerciseRepository;
import com.academy.project.repository.ExerciseTrainingRepository;
import com.academy.project.repository.MuscleGroupRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseTrainingRepository exerciseTrainingRepository;

    @Autowired
    private MuscleGroupRepository muscleGroupRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void addExercisesToMuscleGroup(Integer muscleGroupId, List<Integer> exerciseIds) {
        MuscleGroupModel muscleGroup = muscleGroupRepository.findById(muscleGroupId)
                .orElseThrow(() -> new RuntimeException("Grupo Muscular não encontrado"));

        List<ExerciseModel> exercises = exerciseRepository.findAllById(exerciseIds);

        muscleGroup.getExercises().addAll(exercises);

        muscleGroupRepository.save(muscleGroup);
        exerciseRepository.saveAll(exercises);
    }

    public ExerciseModel save(ExerciseModel exercise) {
        return exerciseRepository.save(exercise);
    }

    public ExerciseModel update(ExerciseModel exercise) {
        Optional<ExerciseModel> existingExercise = exerciseRepository.findById(exercise.getId());
        if (existingExercise.isPresent()) {
            return exerciseRepository.save(exercise);
        } else {
            throw new ObjectNotFoundException("Exercise not found with ID: " + exercise.getId());
        }
    }

    public void delete(int id) {
        ExerciseModel exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Exercise not found with ID: " + id));
        List<ExerciseTrainingModel> exerciseTrainings = exerciseTrainingRepository.findByExercise(exercise);
        if (!exerciseTrainings.isEmpty()) {
            throw new IllegalStateException("Cannot delete exercise as it is part of one or more trainings");
        }
        exerciseRepository.delete(exercise);
    }

    public ExerciseModel getExerciseWithMuscleGroups(Integer exerciseId) {
        return exerciseRepository.findByIdWithMuscleGroups(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercício não encontrado"));
    }

    public List<ExerciseWithMuscleGroupDto> getAllExercisesWithMuscleGroups() {
        List<ExerciseModel> exercises = exerciseRepository.findAllWithMuscleGroups();
        return exercises.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ExerciseWithMuscleGroupDto convertToDto(ExerciseModel exercise) {
        ExerciseWithMuscleGroupDto dto = modelMapper.map(exercise, ExerciseWithMuscleGroupDto.class);
        List<MuscleGroupDto> muscleGroupDtos = exercise.getMuscleGroups().stream()
                .map(muscleGroup -> modelMapper.map(muscleGroup, MuscleGroupDto.class))
                .collect(Collectors.toList());
        dto.setMuscleGroups(muscleGroupDtos);
        return dto;
    }
}