package com.orbisbs.doservice.page;

import com.orbisbs.doservice.cars.CarService;
import com.orbisbs.doservice.models.AuthenticationRequest;
import com.orbisbs.doservice.models.AuthenticationResponse;
import com.orbisbs.doservice.security.MyUserDetailsService;
import com.orbisbs.doservice.users.User;
import com.orbisbs.doservice.users.UserService;
import com.orbisbs.doservice.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "Page Controller", description = "Controller serving for static pages")
public class PageController {

  private final AuthenticationManager authenticationManager;
  private final MyUserDetailsService myUserDetailsService;
  private final UserService userService;
  private final CarService carService;
  private final JwtUtil jwtTokenUtil;
  private final PageService pageService;

  @Operation(summary = "Just a nice welcome message")
  @GetMapping("/")
  public String callMe() {
    return "hello there!.. General Kenobi!";
  }

  @Operation(summary = "Assign an Oil change to a Car")
  @SecurityRequirement(name = "bearerAuth")
  @PutMapping("/car/{carId}/oil/{oilId}")
  public void enrollOilChangeToCar(@PathVariable Long carId,
                                   @PathVariable Long oilId) {
    carService.enrollOilChangeToCar(carId, oilId);
  }

  @Operation(summary = "Assign a Car to a User (Many-to-one)")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok!",
              content = {@Content(mediaType = "application/json",
                      schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied",
              content = @Content),
      @ApiResponse(responseCode = "404", description = "User/Car not found",
              content = @Content)})
  @PutMapping("/users/{userId}/car/{carId}")
  @SecurityRequirement(name = "bearerAuth")
  public void enrollCarToUser(@PathVariable Long userId,
                              @PathVariable Long carId) {
    userService.enrollCarToUser(userId, carId);
  }

  @Operation(summary = "create Authentication Token")
  @PostMapping("/auth")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

    try {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
      );
    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect passwd or username", e);
    }

    final UserDetails userDetails = myUserDetailsService
            .loadUserByUsername(authenticationRequest.getUsername());
    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }


  @Operation(summary = "Check if oil needs to be changed for a car")
  @GetMapping("/oil_change/car/{carId}")
  public String shouldOilBeChanged(@PathVariable Long carId) {

    return pageService.shouldOilBeChanged(carId);

  }

  public Long asdf(Long a, Long b) {
    return a / b;
  }
}
