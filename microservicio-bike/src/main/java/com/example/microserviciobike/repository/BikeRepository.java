package com.example.microserviciobike.repository;

import com.example.microserviciobike.entity.Bike;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//public interface BikeRepository extends JpaRepository<Bike,Long>{
public interface BikeRepository extends MongoRepository<Bike,Long> {

    List<Bike> findByUserId(Long userId);
}
