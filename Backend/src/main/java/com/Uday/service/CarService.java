package com.Uday.service;

import com.Uday.domain.CarRequest;
import com.Uday.model.Car;
import com.Uday.model.User;
import com.Uday.repository.CarRepository;
import com.Uday.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Value("${project.image}")
    private String path;


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
            String imageUrl = uploadImage(path,image);
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
    private String uploadImage(String path, MultipartFile file) throws IOException {

        //file name
        String name=file.getOriginalFilename();

        //fullPath
        String filePath=path+File.separator+name;

        //creating folder if not created
        File f=new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //file copy
        Files.copy(file.getInputStream(),Paths.get(filePath));

        return name;

    }

    public List<Car> getCarsByUser() throws Exception {

        String username = ((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());


        User user = userService.findUserByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return carRepository.findByUser(user);
    }

    public void deleteCar(Long carId) throws Exception {

        String username = ((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }


        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new Exception("Car not found"));


        if (!car.getUser().equals(user)) {
            throw new Exception("Car does not belong to the current user");
        }


        carRepository.delete(car);
    }

    public Car updateCar(Long carId, CarRequest carRequest) throws Exception {

        String username = ((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }


        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new Exception("Car not found"));


        if (!car.getUser().equals(user)) {
            throw new Exception("Car does not belong to the current user");
        }


        if (carRequest.getTitle() != null && !carRequest.getTitle().isEmpty()) {
            car.setTitle(carRequest.getTitle());
        }
        if (carRequest.getDescription() != null && !carRequest.getDescription().isEmpty()) {
            car.setDescription(carRequest.getDescription());
        }
        if (carRequest.getTags() != null && !carRequest.getTags().isEmpty()) {
            car.setTags(carRequest.getTags());
        }


        if (carRequest.getImages() != null && !carRequest.getImages().isEmpty()) {
            if (carRequest.getImages().size() > 10) {
                throw new Exception("Cannot upload more than 10 images.");
            }
            List<String> imageUrls = new ArrayList<>();
            for (MultipartFile image : carRequest.getImages()) {
                String imageUrl = uploadImage(path,image);  // Custom method to handle image upload
                imageUrls.add(imageUrl);
            }
            car.setImageUrls(imageUrls);
        }


        return carRepository.save(car);
    }

    public Car getCarById(Long carId) {
        return carRepository.findById(carId).orElse(null);
    }

    public List<Car> searchCars(String keyword) throws Exception {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return carRepository.searchUserCars(keyword, user);
    }
}
