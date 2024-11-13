package com.Uday.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CarRequest {
    private String title;
    private String description;
    private List<String> tags;
    private List<MultipartFile> images;
}
