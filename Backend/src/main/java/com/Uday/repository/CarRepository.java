package com.Uday.repository;

import com.Uday.model.Car;
import com.Uday.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByUser(User user);

    @Query("SELECT DISTINCT c FROM Car c LEFT JOIN c.tags t " +
            "WHERE (LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR (t IS NOT NULL AND LOWER(t) LIKE LOWER(CONCAT('%', :keyword, '%')))) " +
            "AND c.user = :user")
    List<Car> searchUserCars(@Param("keyword") String keyword, @Param("user") User user);


}
