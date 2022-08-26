package com.orbisbs.doservice.users;

import com.orbisbs.doservice.cars.Car;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto2 {
    private String userName;
    private String password;
    private String roles;
}
