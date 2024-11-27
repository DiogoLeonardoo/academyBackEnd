package com.academy.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exercise_training")
public class ExerciseTrainingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sets", nullable = false)
    private int sets;

    @Column(name = "repetitions", nullable = false)
    private int repetitions;

    @Column(name = "restTime", nullable = false)
    private int restTime;

    //Relacionamentos ---

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "training_id")
    private TrainingModel training;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseModel exercise;

}
