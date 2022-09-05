package com.orbisbs.doservice.cars;

import com.orbisbs.doservice.oil.OilDto;
import com.orbisbs.doservice.users.User;
import com.orbisbs.doservice.users.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@Tag(name="Car CRUD", description = "Create / Remove / Update / Delete Car")
@RequestMapping("/cars")

@SecurityRequirement(name = "basicAuth")
public class CarController {


    private final CarService carService;
    private final ModelMapper modelMapper;

    @Operation(summary = "Get a car by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found a car",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Car not found",
                    content = @Content) })

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable Long id) {
        Car car = carService.getCar(id);
        // convert entity to DTO
        CarDto carResponse = modelMapper.map(car, CarDto.class);

        return ResponseEntity.ok().body(carResponse);
    }

    @Operation(summary = "Get all cars")
    @GetMapping
    public String getAllCars(Model model) {
        List<CarDto> list = carService.getAllCars().stream().map(car -> modelMapper.map(car, CarDto.class)).collect(Collectors.toList());
        model.addAttribute("cars", list);
        return "cars";
    }

    @GetMapping("/new")
    public String createCarForm(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "create_car";
    }

    @Operation(summary = "Add an Car")
    @PostMapping
    public String addCar(@ModelAttribute("car") CarDto carDto) {

        Car carRequest = modelMapper.map(carDto, Car.class);
        Car car = carService.addCar(carRequest);

        return "redirect:/cars";
    }

    @Operation(summary = "update a Car by its id")
    @PostMapping("/{id}")
    public String updateCar(@PathVariable Long id, @ModelAttribute("car") CarDto carDto, Model model) {

        // convert DTO to Entity
        Car carRequest = modelMapper.map(carDto, Car.class);
        Car car = carService.updateCar(id, carRequest);
        // entity to DTO
        CarDto carResponse = modelMapper.map(car, CarDto.class);
        return "redirect:/cars";
    }

    @GetMapping("/edit/{id}")
    public String editCarForm(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.getCar(id));
        return "edit_car";
    }

    @Operation(summary = "delete a Car by its id")
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/car/{carId}/oil/{oilId}")
    public void enrollOilChangeToCar(@PathVariable Long carId,
                                @PathVariable Long oilId) {
        carService.enrollOilChangeToCar(carId, oilId);
    }

}
