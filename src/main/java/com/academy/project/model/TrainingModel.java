package com.academy.project.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="training")
public class TrainingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private UserModel user;

    @JsonManagedReference
    @OneToMany(mappedBy = "training",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExerciseTrainingModel> exerciseTrainings;


}
