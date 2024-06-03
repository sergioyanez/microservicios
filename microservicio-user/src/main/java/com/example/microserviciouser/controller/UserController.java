package com.example.microserviciouser.controller;

import com.example.microserviciouser.entity.User;
import com.example.microserviciouser.models.Bike;
import com.example.microserviciouser.models.Car;
import com.example.microserviciouser.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<User> save(@RequestBody User user) {
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }
    @CircuitBreaker(name = "carsCB",fallbackMethod = "fallBackGetCars")
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId);

        if(user == null){
            return  ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }
    @CircuitBreaker(name = "carsCB",fallbackMethod = "fallBackSaveCar")
    @PostMapping("/saveCar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") Long userId, @RequestBody Car car) {
        if(userService.getUserById(userId) == null){
            return  ResponseEntity.notFound().build();
        }
        Car carNew = userService.saveCar(userId, car);
        return ResponseEntity.ok(car);
    }

    @CircuitBreaker(name = "bikesCB",fallbackMethod = "fallBackGetBikes")
    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId);

        if(user == null){
            return  ResponseEntity.notFound().build();
        }
        List<Bike> bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }
    @CircuitBreaker(name = "bikesCB",fallbackMethod = "fallBackSaveBike")
    @PostMapping("/saveBike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") Long userId, @RequestBody Bike bike) {
        if(userService.getUserById(userId) == null){
            return  ResponseEntity.notFound().build();
        }
        Bike bikeNew = userService.saveBike(userId, bike);
        return ResponseEntity.ok(bike);
    }
    @CircuitBreaker(name = "allCB",fallbackMethod = "fallBackGetAll")
    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getUserAndVehicles(@PathVariable("userId") Long userId) {
        Map<String, Object> result = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }

///////////////////////////////////////////////////////////////////
////////////   métodos para circuit breaker     ///////////////////
///////////////////////////////////////////////////////////////////

    private ResponseEntity<List<Car>> fallBackGetCars(@PathVariable("userId") Long userId, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " tiene los coches en el taller", HttpStatus.OK);
    }
    private ResponseEntity<Car> fallBackSaveCar(@PathVariable("userId") Long userId, @RequestBody Car car, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " no tiene dinero para autos", HttpStatus.OK);
    }

    private ResponseEntity<List<Bike>> fallBackGetBikes(@PathVariable("userId") Long userId, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " tiene las motos en el taller", HttpStatus.OK);
    }
    private ResponseEntity<Bike> fallBackSaveBike(@PathVariable("userId") Long userId, @RequestBody Bike bike, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " no tiene dinero para motos", HttpStatus.OK);
    }
    public ResponseEntity<Map<String, Object>> fallBackGetAll(@PathVariable("userId") Long userId, RuntimeException e) {
        return new ResponseEntity("El usuario " + userId + " tiene todos sus vehículos en el taller", HttpStatus.OK);
    }



}
