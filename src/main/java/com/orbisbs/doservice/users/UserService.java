package com.orbisbs.doservice.users;

import com.orbisbs.doservice.cars.Car;
import com.orbisbs.doservice.cars.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User updateUser(Long id, User user) {

        return userRepository.save(Objects.requireNonNull(userRepository.findById(id).orElse(null)));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User enrollCarToUser(Long userId, Long carId) {
        User user = userRepository.findById(userId).get();
        Car car = carRepository.findById(carId).get();
        user.enrollCars(car);
        return userRepository.save(user);
    }

}
