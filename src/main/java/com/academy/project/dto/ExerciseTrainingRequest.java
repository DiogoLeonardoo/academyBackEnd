package com.academy.project.dto;

import lombok.Data;

@Data
public class ExerciseTrainingRequest {

    private int exerciseId;
    private int sets;
    private int repetitions;
    private int restTime;

}
