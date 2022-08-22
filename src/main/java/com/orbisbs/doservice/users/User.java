package com.orbisbs.doservice.users;


import com.orbisbs.doservice.cars.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = 2, message = "name must be >= 2")
    private String name;


    @ManyToMany
    @JoinTable(
            name="user_car",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private Set<Car> enrolledCars = new HashSet<>();


    public void enrollCars(Car car) {
        enrolledCars.add(car);
    }
}
