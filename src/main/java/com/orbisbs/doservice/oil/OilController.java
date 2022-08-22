package com.orbisbs.doservice.oil;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
public class OilController {

    private final OilService oilService;

    @RequestMapping("/oil/{id}")
    public Oil getOil(@PathVariable Long id) {
        return oilService.getOil(id);
    }

    @RequestMapping("/oil")
    public List<Oil> getAllOil() {
        return oilService.getAllOil();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/oil")
    public void addOil(@Valid @RequestBody Oil oil) {
        oilService.addOil(oil);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/oil/{id}")
    public Oil updateOil(@RequestBody Oil oil, @PathVariable Long id) {
        return oilService.updateOil(id, oil);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/oil/{id}")
    public void deleteOil(@PathVariable Long id) {
        oilService.deleteOil(id);
    }

}
