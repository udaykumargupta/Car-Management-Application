package com.Uday.controller;

import com.Uday.domain.CarRequest;
import com.Uday.model.Car;
import com.Uday.repository.UserRepository;
import com.Uday.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PutMapping("/{carId}")
    public ResponseEntity<Car> updateCar(@PathVariable Long carId,
                                         @RequestBody CarRequest carRequest
                                         ) {
        try {

            Car updatedCar = carService.updateCar(carId, carRequest);
            return ResponseEntity.ok(updatedCar);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable Long carId) {
        Car car = carService.getCarById(carId);
        if (car == null) {
            return ResponseEntity.notFound().build();  // Return 404 if car is not found
        }
        return ResponseEntity.ok(car);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Car>> searchCars(@RequestParam("keyword") String keyword) {
        try {
            if (keyword == null || keyword.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            List<Car> cars = carService.searchCars(keyword);
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
