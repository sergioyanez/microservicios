package com.example.microserviciouser.feignClients;

import com.example.microserviciouser.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="microservicio-car", url="http://localhost:8002/cars")
public interface CarFeignClient {

    @PostMapping
    Car save(@RequestBody Car car);
    @GetMapping("/byUser/{userId}")
    List<Car> getCars(@PathVariable("userId") Long userId);


}
