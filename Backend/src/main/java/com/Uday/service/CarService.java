package com.Uday.service;

import com.Uday.domain.CarRequest;
import com.Uday.model.Car;
import com.Uday.model.User;
import com.Uday.repository.CarRepository;
import com.Uday.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public Car addCar(CarRequest carRequest) throws Exception {
        if (carRequest.getImages().size() > 10) {
            throw new Exception("Cannot upload more than 10 images.");
        }


        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }


        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : carRequest.getImages()) {
            String imageUrl = uploadImage(image);
            imageUrls.add(imageUrl);
        }


        Car car = new Car();
        car.setTitle(carRequest.getTitle());
        car.setDescription(carRequest.getDescription());
        car.setTags(carRequest.getTags());
        car.setImageUrls(imageUrls);


        car.setUser(user);


        return carRepository.save(car);
    }
    private String uploadImage(MultipartFile file) {
        // Placeholder for uploading the file to a storage (e.g., AWS S3, local file system) and returning the URL
        // For now, assume this function uploads the image and returns the URL where it’s stored
        return "uploaded_image_url";
    }

    public List<Car> getCarsByUser() throws Exception {

        String username = ((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());


        User user = userService.findUserByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return carRepository.findByUser(user);
    }
}
