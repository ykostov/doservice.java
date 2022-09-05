package com.orbisbs.doservice.cars;

import com.orbisbs.doservice.users.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    /**
     * Get single car by id
     *
     * @param id
     * @return Response of ok (200) and body of Car
     */

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

    /**
     * Get all cars
     * @param model
     * @return html rendered page (thymeleaf) in /cars (cars.html)
     */

    @Operation(summary = "Get all cars")
    @GetMapping
    public String getAllCars(Model model) {
        List<CarDto> list = carService.getAllCars().stream().map(car -> modelMapper.map(car, CarDto.class)).collect(Collectors.toList());
        model.addAttribute("cars", list);
        return "cars";
    }

    /**
     * Creation of Car
     * @param carDto
     * @return redirects to /cars (thymeleaf)
     */

    @Operation(summary = "Add an Car")
    @PostMapping
    public String addCar(@ModelAttribute("car") CarDto carDto) {

        Car carRequest = modelMapper.map(carDto, Car.class);
        Car car = carService.addCar(carRequest);

        return "redirect:/cars";
    }

    /**
     * Update car by given id, CarDto class and a model from thymeleaf
     * @param id
     * @param carDto
     * @param model
     * @return redirects to /cars
     */

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

    /**
     * Delete a car by given id
     * @param id
     * @return redirects to /cars (thymeleaf)
     */

    @Operation(summary = "delete a Car by its id")
    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }


    /**
     * Method for car creation form, form in it then redirects to addCar(/1)
     * @param model
     * @return opens edit car form
     */

    @GetMapping("/new")
    public String createCarForm(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "create_car";
    }


    /**
     * Method for car edit form, form in it then redirects to updateCar(/3)
     * @param id
     * @param model
     * @return opens edit car form
     */

    @GetMapping("/edit/{id}")
    public String editCarForm(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.getCar(id));
        return "edit_car";
    }


}
