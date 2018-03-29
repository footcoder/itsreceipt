package com.backend.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping(value = "/hello")
    public String sampleController(){
        return "Hello World good!!!!";
    }

}
