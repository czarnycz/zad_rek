package com.example.rejestrator_treningow.training;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
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
    public void edit(Long id, Training updatedTraining) {
        final Optional<Training> optionalTraining = trainingRepository.findById(id);
        if(optionalTraining.isPresent()){
            Training training = optionalTraining.get();
            training.setDate(updatedTraining.getDate());
            training.setDistance(updatedTraining.getDistance());
            training.setTime(updatedTraining.getTime());
            training.setAmountOfCaloriesBurned(updatedTraining.getAmountOfCaloriesBurned());
            training.setComments(updatedTraining.getComments());

            trainingRepository.save(training);
        }else{
            throw new EntityNotFoundException("Training with ID: " + id + " not found");
        }
    }

}
