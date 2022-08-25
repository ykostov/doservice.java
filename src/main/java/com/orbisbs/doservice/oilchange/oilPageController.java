package com.orbisbs.doservice.oilchange;

import com.orbisbs.doservice.cars.Car;
import com.orbisbs.doservice.cars.CarService;
import com.orbisbs.doservice.oil.Oil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@AllArgsConstructor
public class oilPageController {

    private final CarService carService;
    private HashMap<String, Integer> carsOil;
    private LinkedHashMap<String, Integer> sortedMap;


    @RequestMapping("/oil_change/car/{carId}")
    public String getUser(@PathVariable Long carId) {
        this.carsOil.clear();
        this.sortedMap.clear();
        Car car = carService.getCar(carId);

        getCarsMileageAndOilChanges(car);
        String carCurrentMileage = car.getMileage();
        String carOilFrequency = car.getOilFrequency();
        return calculateStatusOfCarOilChange(carCurrentMileage, carOilFrequency, car.getModel());

    }

    private void getCarsMileageAndOilChanges(Car car) {
            for(Oil o : car.getOil()) {
                carsOil.put(o.getDate(), Integer.parseInt(o.getMileage()));
            }

    }

    private String calculateStatusOfCarOilChange(String mileage, String frequency, String model) {

        carsOil.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        for (var entry : sortedMap.entrySet()) {

            if ((Integer.parseInt(mileage) - entry.getValue()) >  Integer.parseInt(frequency))
            {
                return "you need to change the oil! Last oil changed at: " + entry.getValue() +
                "km." + "\r\n" + "You have passed the line of oil frequency with: " + ((Integer.parseInt(mileage) - entry.getValue()) - Integer.parseInt(frequency)) + "km! Change the oil as soon as possible!";

            }
            return "Everything is in order";

        }
        return "No data";
    }


}
