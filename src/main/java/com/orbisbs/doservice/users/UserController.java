package com.orbisbs.doservice.users;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="User CRUD", description = "Create / Remove / Update / Delete User")
@RequestMapping("/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {


    private final UserService userService;
    private final ModelMapper modelMapper;


    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found a user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        // convert entity to DTO
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok().body(userResponse);
    }


    @Operation(summary = "Get all users")
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Operation(summary = "Add a user")
    @PostMapping
    public ResponseEntity<UserDto2> addUser(@Valid @RequestBody UserDto2 userDto) {
        String password = userDto.getPassword();
        User userRequest = modelMapper.map(userDto, User.class);
        User user = userService.addUser(userRequest, password);
        // convert entity to DTO
        UserDto2 userResponse = modelMapper.map(user, UserDto2.class);

        return new ResponseEntity<UserDto2>(userResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a User")
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto2> updateUser(@RequestBody UserDto2 userDto, @PathVariable Long id) {

        // convert DTO to Entity
        User userRequest = modelMapper.map(userDto, User.class);
        User user = userService.updateUser(id, userRequest);
        // Entity to DTO
        UserDto2 userResponse = modelMapper.map(user, UserDto2.class);
        return ResponseEntity.ok().body(userResponse);
    }

    @Operation(summary = "Delete a User")
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }





}
