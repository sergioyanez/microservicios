package com.example.microserviciouser.feignClients;

import com.example.microserviciouser.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="microservicio-car")
public interface CarFeignClient {

    @PostMapping("cars")
    Car save(@RequestBody Car car);
    @GetMapping("cars/byUser/{userId}")
    List<Car> getCars(@PathVariable("userId") Long userId);


}
