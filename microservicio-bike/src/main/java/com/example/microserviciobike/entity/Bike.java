package com.example.microserviciobike.entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Entity
@Document(collection = "bikes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bike {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private ObjectId id;
    private String brand;
    private String  model;
    private Long userId;

}
