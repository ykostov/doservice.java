package com.orbisbs.doservice.oilchange;

import com.orbisbs.doservice.cars.Car;
import com.orbisbs.doservice.oil.Oil;
import com.orbisbs.doservice.users.User;
import com.orbisbs.doservice.users.UserDto;
import com.orbisbs.doservice.users.UserRepository;
import com.orbisbs.doservice.users.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@AllArgsConstructor
public class oilPageController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    HashMap<Long, String> cars = new HashMap<>();
    HashMap<Long, String> carsOilFrequency = new HashMap<>();
    HashMap<Long, String> carsOil = new HashMap<>();


    @RequestMapping("/user/{id}/cars")
    public String getUser(@PathVariable Long id) {
        getCarsMileageAndOilChanges(id);
        return "Cars for user: " + cars.toString() +
                " Oil for cars: " + carsOil.toString() +
                " Oil Frequency for cars: " + carsOilFrequency.toString();

    }

    private void getCarsMileageAndOilChanges(Long id) {
        User user = userService.getUser(id);
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        for (Car c : userResponse.getCars() ) {
            cars.put(c.getId(), c.getMileage());
            carsOilFrequency.put(c.getId(), c.getOilFrequency());
            for(Oil o : c.getOil()) {
                carsOil.put(c.getId(), o.getMileage());
            }
        }
    }

    private void calculateStatusOfCarOilChange() {
        for (int i = 0; i < cars.size(); i++) {
            // do something funny
        }
    }


}
