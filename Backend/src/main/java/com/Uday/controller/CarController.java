package com.Uday.controller;

import com.Uday.domain.CarRequest;
import com.Uday.model.Car;
import com.Uday.repository.UserRepository;
import com.Uday.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@ModelAttribute CarRequest carRequest) {
        try {
            Car car = carService.addCar(carRequest);
            return ResponseEntity.ok(car);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/my-cars")
    public ResponseEntity<List<Car>> getAllUserCars() throws Exception {
        List<Car> cars = carService.getCarsByUser();
        return ResponseEntity.ok(cars);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable Long carId) {
        try {

            carService.deleteCar(carId);
            return ResponseEntity.ok("Car deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
}
