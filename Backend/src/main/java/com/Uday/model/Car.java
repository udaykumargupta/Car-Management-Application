
package com.Uday.model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ElementCollection
    private List<String> tags;

    @ElementCollection
    private List<String> imageUrls;


}
