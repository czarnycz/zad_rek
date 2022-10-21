package com.example.rejestrator_treningow.training;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServicee trainingServicee;

    @GetMapping("/list")
    public List<Training> list(){
        return trainingServicee.findAll();

    }

    @PostMapping("/add")
    public void add(@RequestBody Training training){
        trainingServicee.add(training);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        trainingServicee.delete(id);
    }

    @PutMapping("/edit/{id}")
    public void edit(@PathVariable Long id, @RequestBody Training training){
        trainingServicee.edit(id,training);
    }
}
