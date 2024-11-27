package com.academy.project.dto;

import com.academy.project.model.MuscleGroupModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Data
public class ExerciseDto {

    private Long id;
    private String nome;
    private String descricao;
    private List<MuscleGroupModel> muscleGroups;

}