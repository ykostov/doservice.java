package com.orbisbs.doservice.users;

import com.orbisbs.doservice.cars.Car;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String name;
    private Set<Car> cars = new HashSet<>();
}
