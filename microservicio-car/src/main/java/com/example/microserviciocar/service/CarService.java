package com.example.microserviciocar.service;

import com.example.microserviciocar.entity.Car;
import com.example.microserviciocar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car save(Car car){
        Car carNew;
        carNew = carRepository.save(car);
        return carNew;
    }
    public void delete(Car car){

        carRepository.delete(car);
    }

    public Car findById(Long id){
        return carRepository.findById(id).orElse(null);
    }

    public Car update(Car car){
        return carRepository.save(car);
    }

    public List<Car> byUserId(Long userid){
        return carRepository.findByUserId(userid);
    }
}
