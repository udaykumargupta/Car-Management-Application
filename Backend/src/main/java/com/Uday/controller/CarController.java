package com.Uday.controller;

import com.Uday.domain.CarRequest;
import com.Uday.model.Car;
import com.Uday.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@ModelAttribute CarRequest carRequest) {
        try {
            Car car = carService.addCar(carRequest);
            return ResponseEntity.ok(car);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
