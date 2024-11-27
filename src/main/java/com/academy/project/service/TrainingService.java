package com.academy.project.service;

import com.academy.project.exception.ObjectNotFoundException;
import com.academy.project.model.ExerciseModel;
import com.academy.project.model.TrainingModel;
import com.academy.project.repository.ExerciseRepository;
import com.academy.project.repository.ExerciseTrainingRepository;
import com.academy.project.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academy.project.dto.TrainingDto;
import com.academy.project.dto.ExerciseDto;


import java.util.List;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseTrainingRepository exerciseTrainingRepository;

    public TrainingModel createTraining(TrainingModel training) {
        return trainingRepository.save(training);
    }

    public TrainingModel updateTraining(TrainingModel training) {
        return trainingRepository.save(training);
    }

    public void deleteTraining(int id) {
        TrainingModel training = trainingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Treinamento não encontrado com o ID: " + id));
        trainingRepository.delete(training);
    }

    public List<TrainingModel> getAllTrainings() {
        return trainingRepository.findAll();
    }

    public TrainingModel getTrainingById(int id) {
        return trainingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Treinamento não encontrado com o ID: " + id));
    }






}