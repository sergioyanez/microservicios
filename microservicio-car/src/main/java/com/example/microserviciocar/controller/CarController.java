package com.example.microserviciocar.controller;


import com.example.microserviciocar.entity.Car;
import com.example.microserviciocar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAll();
        if (cars.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id) {
        Car car = carService.findById(id);
        if (car == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping("")
    public ResponseEntity<Car> save(@RequestBody Car car) {
        Car userNew = carService.save(car);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable("userId") Long userId) {
        List<Car> cars = carService.byUserId(userId);
        return ResponseEntity.ok(cars);
    }
}
