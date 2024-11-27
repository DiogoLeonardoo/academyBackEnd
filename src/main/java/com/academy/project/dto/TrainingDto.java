package com.academy.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class TrainingDto {
    private Integer id;
    private String nome;
    private String descricao;
    private List<ExerciseTrainingDto> exerciseTrainings;
}
