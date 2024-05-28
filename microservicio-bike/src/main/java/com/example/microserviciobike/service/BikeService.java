package com.example.microserviciobike.service;


import com.example.microserviciobike.entity.Bike;
import com.example.microserviciobike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll(){
        return bikeRepository.findAll();
    }

    public Bike save(Bike bike){
        Bike carNew;
        carNew = bikeRepository.save(bike);
        return carNew;
    }
    public void delete(Bike bike){

        bikeRepository.delete(bike);
    }

    public Bike findById(Long id){
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike update(Bike bike){
        return bikeRepository.save(bike);
    }

    public List<Bike> byUserId(Long userid){
        return bikeRepository.findByUserId(userid);
    }
}
