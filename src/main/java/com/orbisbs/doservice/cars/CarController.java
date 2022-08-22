package com.orbisbs.doservice.cars;

import com.orbisbs.doservice.users.User;
import com.orbisbs.doservice.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class CarController {


    private final CarService carService;

    @RequestMapping("/cars/{id}")
    public Car getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @RequestMapping("/cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cars")
    public void addCar(@Valid @RequestBody Car car) {
        carService.addCar(car);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cars/{id}")
    public Car updateCar(@RequestBody Car car, @PathVariable Long id) {
        return carService.updateCar(id, car);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cars/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

}
