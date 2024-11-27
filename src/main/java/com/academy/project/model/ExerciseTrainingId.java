package com.academy.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
public class ExerciseTrainingId  implements Serializable {

    @Column(name = "exercise_id")
    private int exerciseId;

    @Column(name = "training_id")
    private int trainingId;
}
