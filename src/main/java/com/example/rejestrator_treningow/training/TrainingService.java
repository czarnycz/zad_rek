package com.example.rejestrator_treningow.training;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingService implements TrainingServicee{

    final TrainingRepository trainingRepository;
    final AverageSpeed averageSpeed;

    @Override
    public List<Training> findAll() {
        return trainingRepository.findAll();
    }

    @Override
    public void add(Training training) {

        Training createdTraining = Training.builder()
                .date(training.getDate())
                .distance(training.getDistance())
                .time(training.getTime())
                .amountOfCaloriesBurned(training.getAmountOfCaloriesBurned())
                .comments(training.getComments())
                .build();
        createdTraining.setAverageSpeed(averageSpeed.calculateAverageOfSpeed(training.getTime(),training.getDistance()));
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
            training.setDate(training.getDate());
            training.setDistance(training.getDistance());
            training.setTime(training.getTime());
            training.setAmountOfCaloriesBurned(training.getAmountOfCaloriesBurned());
            training.setComments(training.getComments());
            training.setAverageSpeed(averageSpeed.calculateAverageOfSpeed(training.getTime(),training.getDistance()));
            trainingRepository.save(training);
        }else{
            throw new EntityNotFoundException("Training with ID: " + id + " not found");
        }

    }

}
