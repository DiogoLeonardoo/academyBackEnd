package com.academy.project.service;

import com.academy.project.exception.ObjectNotFoundException;
import com.academy.project.model.ExerciseModel;
import com.academy.project.model.ExerciseTrainingModel;
import com.academy.project.model.TrainingModel;
import com.academy.project.repository.ExerciseRepository;
import com.academy.project.repository.ExerciseTrainingRepository;
import com.academy.project.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseTrainingService {

    @Autowired
    private ExerciseTrainingRepository exerciseTrainingRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    // Obter todos os exercícios de treinamento
    public List<ExerciseTrainingModel> getAllExerciseTrainings() {
        return exerciseTrainingRepository.findAll();  // Retorna todos os registros da tabela intermediária
    }

    // Obter exercício de treinamento por ID
    public ExerciseTrainingModel getExerciseTrainingById(Integer id) {
        return exerciseTrainingRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Exercise Training not found with ID: " + id));
    }

    // Criar novo exercício de treinamento
    public ExerciseTrainingModel createExerciseTraining(ExerciseTrainingModel exerciseTraining) {
        return exerciseTrainingRepository.save(exerciseTraining);  // Salva o novo exercício de treinamento
    }

    // Atualizar exercício de treinamento
    public ExerciseTrainingModel updateExerciseTraining(Integer id, ExerciseTrainingModel updatedExerciseTraining) {
        ExerciseTrainingModel existingExerciseTraining = exerciseTrainingRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Exercise Training not found with ID: " + id));

        existingExerciseTraining.setExercise(updatedExerciseTraining.getExercise());  // Atualiza os campos necessários
        existingExerciseTraining.setTraining(updatedExerciseTraining.getTraining());  // Atualiza os campos necessários
        existingExerciseTraining.setSets(updatedExerciseTraining.getSets());
        existingExerciseTraining.setRepetitions(updatedExerciseTraining.getRepetitions());
        existingExerciseTraining.setRestTime(updatedExerciseTraining.getRestTime());

        return exerciseTrainingRepository.save(existingExerciseTraining);  // Salva as alterações
    }

    // Deletar exercício de treinamento
    public void deleteExerciseTraining(Integer id) {
        ExerciseTrainingModel exerciseTraining = exerciseTrainingRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Exercise Training not found with ID: " + id));
        exerciseTrainingRepository.delete(exerciseTraining);  // Deleta o exercício de treinamento
    }

    // Adicionar exercício ao treino
    public void addExerciseToTraining(Integer trainingId, Integer exerciseId, Integer sets, Integer repetitions, Integer restTime) {
        TrainingModel training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Treino não encontrado."));
        ExerciseModel exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercício não encontrado."));

        ExerciseTrainingModel exerciseTraining = new ExerciseTrainingModel();
        exerciseTraining.setTraining(training);
        exerciseTraining.setExercise(exercise);
        exerciseTraining.setSets(sets);
        exerciseTraining.setRepetitions(repetitions);
        exerciseTraining.setRestTime(restTime);

        exerciseTrainingRepository.save(exerciseTraining);  // Salva a associação
    }
}
