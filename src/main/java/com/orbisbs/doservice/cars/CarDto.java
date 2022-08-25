package com.orbisbs.doservice.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orbisbs.doservice.oil.Oil;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private String gen;
    private String drivetrain;
    private String oilFrequency;
    private String mileage;
    @JsonIgnore
    private Set<Oil> oil = new HashSet<>();
}
