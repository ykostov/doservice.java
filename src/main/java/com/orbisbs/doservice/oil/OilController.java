package com.orbisbs.doservice.oil;


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
public class OilController {

    private final OilService oilService;
    private final ModelMapper modelMapper;

    @RequestMapping("/oil/{id}")
    public ResponseEntity<OilDto> getOil(@PathVariable Long id) {

        Oil oil = oilService.getOil(id);
        // convert entity to DTO
        OilDto oilResponse = modelMapper.map(oil, OilDto.class);

        return ResponseEntity.ok().body(oilResponse);
    }

    @RequestMapping("/oil")
    public List<OilDto> getAllOil() {

        return oilService.getAllOil().stream().map(oil -> modelMapper.map(oil, OilDto.class)).collect(Collectors.toList());

    }

    @RequestMapping(method = RequestMethod.POST, value = "/oil")
    public ResponseEntity<OilDto> addOil(@Valid @RequestBody OilDto oilDto) {


        Oil oilRequest = modelMapper.map(oilDto, Oil.class);
        Oil oil = oilService.addOil(oilRequest);
        // convert entity to DTO
        OilDto oilResponse = modelMapper.map(oil, OilDto.class);
        return new ResponseEntity<OilDto>(oilResponse, HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/oil/{id}")
    public ResponseEntity<OilDto> updateOil(@RequestBody OilDto oilDto, @PathVariable Long id) {

        // convert DTO to Entity
        Oil oilRequest = modelMapper.map(oilDto, Oil.class);
        Oil oil = oilService.updateOil(id, oilRequest);
        // entity to DTO
        OilDto oilResponse = modelMapper.map(oil, OilDto.class);

        return ResponseEntity.ok().body(oilResponse);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/oil/{id}")
    public void deleteOil(@PathVariable Long id) {
        oilService.deleteOil(id);
    }

}
