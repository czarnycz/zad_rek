package com.example.rejestrator_treningow.training;

import java.util.List;

public interface TrainingServicee {
    List<Training> findAll();
    void add(Training training);
    void delete(Long id);
    void edit(Long id, Training updatedTraining);
}
