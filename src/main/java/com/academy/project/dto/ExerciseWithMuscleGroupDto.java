package com.academy.project.dto;

import lombok.*;

import java.util.List;

@Data
public class ExerciseWithMuscleGroupDto {

    private int exerciseId;
    private String nome;
    private String descricao;
    private List<MuscleGroupDto> muscleGroups;
}