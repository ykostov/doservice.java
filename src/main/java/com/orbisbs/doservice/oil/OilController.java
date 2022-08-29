package com.orbisbs.doservice.oil;


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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Tag(name="Oil change CRUD", description = "Create / Remove / Update / Delete Oil (Logic - A car has many oil (changes) with info at what mileage they were made)")
@RequestMapping("/oil")
@SecurityRequirement(name = "bearerAuth")
public class OilController {

    private final OilService oilService;
    private final ModelMapper modelMapper;


    @Operation(summary = "Get an oil by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found a oil",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Oil not found",
                    content = @Content) })

    @GetMapping("/{id}")
    public ResponseEntity<OilDto> getOil(@PathVariable Long id) {

        Oil oil = oilService.getOil(id);
        // convert entity to DTO
        OilDto oilResponse = modelMapper.map(oil, OilDto.class);

        return ResponseEntity.ok().body(oilResponse);
    }

    @Operation(summary = "Get all oil (changes)")
    @GetMapping
    public List<OilDto> getAllOil() {

        return oilService.getAllOil().stream().map(oil -> modelMapper.map(oil, OilDto.class)).collect(Collectors.toList());

    }


    @Operation(summary = "Add an Oil")
    @PostMapping
    public ResponseEntity<OilDto> addOil(@Valid @RequestBody OilDto oilDto) {


        Oil oilRequest = modelMapper.map(oilDto, Oil.class);
        Oil oil = oilService.addOil(oilRequest);
        // convert entity to DTO
        OilDto oilResponse = modelMapper.map(oil, OilDto.class);
        return new ResponseEntity<OilDto>(oilResponse, HttpStatus.CREATED);

    }

    @Operation(summary = "update an Oil by its id")
    @PutMapping("/{id}")
    public ResponseEntity<OilDto> updateOil(@RequestBody OilDto oilDto, @PathVariable Long id) {

        // convert DTO to Entity
        Oil oilRequest = modelMapper.map(oilDto, Oil.class);
        Oil oil = oilService.updateOil(id, oilRequest);
        // entity to DTO
        OilDto oilResponse = modelMapper.map(oil, OilDto.class);

        return ResponseEntity.ok().body(oilResponse);

    }

    @Operation(summary = "delete an Oil by its id")
    @DeleteMapping("/{id}")
    public void deleteOil(@PathVariable Long id) {
        oilService.deleteOil(id);
    }

}
