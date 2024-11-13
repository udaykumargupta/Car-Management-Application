package com.Uday.service;

import com.Uday.domain.CarRequest;
import com.Uday.model.Car;
import com.Uday.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public Car addCar(CarRequest carRequest) throws Exception {
        if (carRequest.getImages().size() > 10) {
            throw new Exception("Cannot upload more than 10 images.");
        }

        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : carRequest.getImages()) {
            String imageUrl = uploadImage(image);  // Custom method to handle image upload
            imageUrls.add(imageUrl);
        }

        Car car = new Car();
        car.setTitle(carRequest.getTitle());
        car.setDescription(carRequest.getDescription());
        car.setTags(carRequest.getTags());
        car.setImageUrls(imageUrls);

        return carRepository.save(car);
    }

    private String uploadImage(MultipartFile file) {
        // Placeholder for uploading the file to a storage (e.g., AWS S3, local file system) and returning the URL
        // For now, assume this function uploads the image and returns the URL where it’s stored
        return "uploaded_image_url";
    }
}
