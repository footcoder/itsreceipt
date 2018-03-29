package com.backend.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class SampleController {

    @GetMapping(value = "/hello")
    public String sampleController(){
        return "Hello World last";
    }

    @GetMapping(value = "/receipts")
    public ResponseEntity receipts(){
        return new ResponseEntity(new HashMap<String, String>(), HttpStatus.OK);
    }
}
