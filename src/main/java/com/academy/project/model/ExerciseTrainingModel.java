package com.academy.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exercise_training")
public class ExerciseTrainingModel {

    @EmbeddedId
    private ExerciseTrainingId id = new ExerciseTrainingId();

    @Column(name = "sets", nullable = false)
    private int sets;

    @Column(name = "repetitions", nullable = false)
    private int repetitions;

    @Column(name = "restTime", nullable = false)
    private int restTime;

    @ManyToOne
    @MapsId("exerciseId")
    @JsonIgnore
    @JoinColumn(name = "exercise_id")
    private ExerciseModel exercise;

    @ManyToOne
    @MapsId("trainingId")
    @JoinColumn(name = "training_id")
    private TrainingModel training;
}
