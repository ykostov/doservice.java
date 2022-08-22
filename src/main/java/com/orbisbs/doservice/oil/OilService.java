package com.orbisbs.doservice.oil;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
public class OilService {


    private final OilRepository oilRepository;


    public Oil getOil(Long id) {
        return oilRepository.findById(id).orElse(null);
    }

    public List<Oil> getAllOil() {
        List<Oil> oil = new ArrayList<>();
        oilRepository.findAll().forEach(oil::add);
        return oil;
    }

    public void addOil(Oil oil) {
        oilRepository.save(oil);
    }

    public Oil updateOil(Long id, Oil oil) {

        return oilRepository.save(Objects.requireNonNull(oilRepository.findById(id).orElse(null)));
    }

    public void deleteOil(Long id) {
        oilRepository.deleteById(id);
    }



}



