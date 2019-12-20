package com.alti.college_search.controllers;

import com.alti.college_search.service.CollegeSearchService;
import com.alti.college_search.service.MapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CollegeController {

    @Autowired
    CollegeSearchService collegeSearchService;

    @Autowired
    MapsService mapsService;

    @GetMapping("/colleges")
    public ResponseEntity<?> getColleges(@RequestParam(required = true) String zip,
                                         @RequestParam(required = true) Integer year,
                                         @RequestParam(required = false) Integer distance,
                                         @RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) String predominantDegrees){

        return ResponseEntity.status(201).body(collegeSearchService.getFilteredSchools(zip, year, distance, page, predominantDegrees));
    }

    /*@GetMapping("/findlocationbyzip/{zip}")
    public ResponseEntity<?> getLocationByZip(@PathVariable String zip){


    }*/
}
