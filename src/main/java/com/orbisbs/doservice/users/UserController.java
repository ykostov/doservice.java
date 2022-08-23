package com.orbisbs.doservice.users;


import com.orbisbs.doservice.cars.CarDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor

public class UserController {


    private final UserService userService;
    private final ModelMapper modelMapper;

    @RequestMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        // convert entity to DTO
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok().body(userResponse);
    }

    @RequestMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
        User userRequest = modelMapper.map(userDto, User.class);
        User user = userService.addUser(userRequest);
        // convert entity to DTO
        UserDto userResponse = modelMapper.map(user, UserDto.class);

        return new ResponseEntity<UserDto>(userResponse, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {

        // convert DTO to Entity
        User userRequest = modelMapper.map(userDto, User.class);
        User user = userService.updateUser(id, userRequest);
        // Entity to DTO
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok().body(userResponse);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}/car/{carId}")
    public User enrollCarToUser(@PathVariable Long userId,
                                 @PathVariable Long carId) {
        return userService.enrollCarToUser(userId, carId);
    }



}
