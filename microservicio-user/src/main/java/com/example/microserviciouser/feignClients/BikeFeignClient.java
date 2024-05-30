package com.example.microserviciouser.feignClients;


import com.example.microserviciouser.models.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="microservicio-bike")
public interface BikeFeignClient {

    @PostMapping
    Bike save(@RequestBody Bike bike);

    @GetMapping("/byUser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") Long userId);

}
