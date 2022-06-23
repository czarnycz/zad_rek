package com.example.rejestrator_treningow.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainingRepository extends JpaRepository<Training,Long> {

}
