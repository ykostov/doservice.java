package com.orbisbs.doservice.oil;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

  public Oil addOil(Oil oil) {
    return oilRepository.save(oil);
  }

  public Oil updateOil(Long id, Oil oil) {
    oil.setId(id);
    return oilRepository.save(oil);
  }

  public void deleteOil(Long id) {
    oilRepository.deleteById(id);
  }


}



