package com.orbisbs.doservice.cars;

import com.orbisbs.doservice.oil.Oil;
import com.orbisbs.doservice.oil.OilRepository;
import com.orbisbs.doservice.users.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final OilRepository oilRepository;

    public Car getCar(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(cars::add);
        return cars;
    }

    public void addCar(Car car) {
        carRepository.save(car);
    }

    public Car updateCar(Long id, Car car) {
        car.setId(id);
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car enrollOilChangeToCar(Long carId, Long oilId) {
        Car car = carRepository.findById(carId).get();
        Oil oil = oilRepository.findById(oilId).get();
        oil.enrollCarToOilChange(car);
        oilRepository.save(oil);
        return carRepository.save(car);
    }
}
