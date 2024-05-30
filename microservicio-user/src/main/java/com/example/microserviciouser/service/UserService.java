package com.example.microserviciouser.service;

import com.example.microserviciouser.entity.User;
import com.example.microserviciouser.feignClients.BikeFeignClient;
import com.example.microserviciouser.feignClients.CarFeignClient;
import com.example.microserviciouser.models.Bike;
import com.example.microserviciouser.models.Car;
import com.example.microserviciouser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        User userNew;
        userNew = userRepository.save(user);
        return userNew;
    }
    public void delete(User user){

        userRepository.delete(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User update(User user){
        return userRepository.save(user);
    }

    public List<Car> getCars(Long userId) {
        List<Car> cars = restTemplate.getForObject("http://microservicio-car/cars/byUser/" + userId, List.class);
        return cars;
    }

    public List<Bike>getBikes(Long userId) {
        List<Bike> bikes = restTemplate.getForObject("http://microservicio-bike/bikes/byUser/" + userId, List.class);
        return bikes;
    }

    public  Car saveCar(Long userId, Car car){
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike saveBike(Long userId, Bike bike){
        bike.setUserId(userId);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserAndVehicles(Long userId){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            result.put("Mensaje", "No existe el usuario");
            return result;
        }
        result.put("User", user);
        List<Car> cars = carFeignClient.getCars(userId);
        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if(cars.isEmpty()){
            result.put("Cars","Este usuario no tiene autos");
        }
        else
            result.put("Cars",cars);
        if(bikes.isEmpty()){
            result.put("Bikes","Este usuario no tiene motos");
        }
        else
            result.put("Bikes",bikes);
        return result;
    }

}
