package com.orbisbs.doservice.users;

import com.orbisbs.doservice.cars.Car;
import com.orbisbs.doservice.cars.CarRepository;
import com.orbisbs.doservice.oil.OilRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final OilRepository oilRepository;

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User updateUser(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User enrollCarToUser(Long userId, Long carId) {
        User user = userRepository.findById(userId).get();
        Car car = carRepository.findById(carId).get();
        car.enrollUserForCar(user);
        carRepository.save(car);
        return userRepository.save(user);
    }

}
