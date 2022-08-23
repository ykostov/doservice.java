package com.orbisbs.doservice.cars;

import com.orbisbs.doservice.oil.OilDto;
import com.orbisbs.doservice.users.User;
import com.orbisbs.doservice.users.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class CarController {


    private final CarService carService;
    private final ModelMapper modelMapper;

    @RequestMapping("/cars/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable Long id) {
        Car car = carService.getCar(id);
        // convert entity to DTO
        CarDto carResponse = modelMapper.map(car, CarDto.class);

        return ResponseEntity.ok().body(carResponse);
    }

    @RequestMapping("/cars")
    public List<CarDto> getAllCars() {
        return carService.getAllCars().stream().map(car -> modelMapper.map(car, CarDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cars")
    public ResponseEntity<CarDto> addCar(@Valid @RequestBody CarDto carDto) {

        Car carRequest = modelMapper.map(carDto, Car.class);
        Car car = carService.addCar(carRequest);
        // convert entity to DTO
        CarDto carResponse = modelMapper.map(car, CarDto.class);
        return new ResponseEntity<CarDto>(carResponse, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cars/{id}")
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto, @PathVariable Long id) {

        // convert DTO to Entity
        Car carRequest = modelMapper.map(carDto, Car.class);
        Car car = carService.updateCar(id, carRequest);
        // entity to DTO
        CarDto carResponse = modelMapper.map(car, CarDto.class);
        return ResponseEntity.ok().body(carResponse);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cars/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/car/{carId}/oil/{oilId}")
    public void enrollOilChangeToCar(@PathVariable Long carId,
                                @PathVariable Long oilId) {
        carService.enrollOilChangeToCar(carId, oilId);
    }

}
