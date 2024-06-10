package com.example.microserviciobike.controller;


import com.example.microserviciobike.entity.Bike;
import com.example.microserviciobike.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping("/")
    public ResponseEntity<List<Bike>> getAllBikes() {
        List<Bike> bikes = bikeService.getAll();
        if (bikes.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable("id") String id) {
        Bike bike = bikeService.findById(id);
        if (bike == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @PostMapping("")
    public ResponseEntity<Bike> save(@RequestBody Bike bike) {
        Bike bikeNew = bikeService.save(bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Bike>> getCarsByUserId(@PathVariable("userId") Long userId) {
        List<Bike> bikes = bikeService.byUserId(userId);
        return ResponseEntity.ok(bikes);
    }
}
