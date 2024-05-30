package com.example.microserviciouser.feignClients;


import com.example.microserviciouser.models.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="microservicio-bike")
public interface BikeFeignClient {

    @PostMapping("bikes")
    Bike save(@RequestBody Bike bike);

    @GetMapping("bikes/byUser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") Long userId);

}
