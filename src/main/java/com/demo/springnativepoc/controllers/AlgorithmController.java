package com.demo.springnativepoc.controllers;

import com.demo.springnativepoc.services.AlgorithmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/algorithm")
public class AlgorithmController {

    final private AlgorithmService algorithmService;

    public AlgorithmController(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }


    @GetMapping("/{word}")
    public String getMaximumPalindromeFromAWord(@PathVariable("word") String word){
        return algorithmService.computeMaximumPalindromeFromAWird(word);
    }

}
