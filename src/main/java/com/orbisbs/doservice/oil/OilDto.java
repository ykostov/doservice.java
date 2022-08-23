package com.orbisbs.doservice.oil;

import com.orbisbs.doservice.cars.Car;
import lombok.Data;

@Data
public class OilDto {
    private Long id;
    private String mileage;
    private String date;
    private Car car;
}
