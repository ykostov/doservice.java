package com.orbisbs.doservice.cars;

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

        return carRepository.save(Objects.requireNonNull(carRepository.findById(id).orElse(null)));
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
