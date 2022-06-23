package com.example.rejestrator_treningow.training;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingService implements TrainingServicee{

    final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public List<Training> findAll() {
        return trainingRepository.findAll();
    }

    @Override
    public void add(Training training) {
        Training createdTraining = Training.builder()
                .data(training.getData())
                .distance(training.getDistance())
                .time(training.getTime())
                .amountOfCaloriesBurned(training.getAmountOfCaloriesBurned())
                .comments(training.getComments())
                .build();
        trainingRepository.save(createdTraining);
    }

    @Override
    public void delete(Long id) {
        trainingRepository.deleteById(id);
    }

    @Override
    public void edit(Long id) {
        Optional<Training> trainingOptional = trainingRepository.findById(id);
        if(trainingOptional.isPresent()){
            Training training = trainingOptional.get();
            training.setData(training.getData());
            training.setDistance(training.getDistance());
            training.setTime(training.getTime());
            training.setAmountOfCaloriesBurned(training.getAmountOfCaloriesBurned());
            training.setComments(training.getComments());
            trainingRepository.save(training);
        }else{
            throw new EntityNotFoundException("Training with ID: " + id + " not found");
        }

    }


}
