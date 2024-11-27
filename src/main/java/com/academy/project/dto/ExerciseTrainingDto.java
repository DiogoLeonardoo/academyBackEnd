package com.academy.project.dto;

import com.academy.project.model.ExerciseModel;
import lombok.Data;

import java.util.List;

@Data
public class ExerciseTrainingDto {

    private int exerciseId;
    private int sets;
    private int repetitions;
    private int restTime;
    private List<ExerciseModel> exercises;

}
