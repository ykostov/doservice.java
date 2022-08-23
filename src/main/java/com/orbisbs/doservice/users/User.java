package com.orbisbs.doservice.users;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orbisbs.doservice.cars.Car;
import com.orbisbs.doservice.oil.Oil;
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


//    @ManyToMany
//    @JoinTable(
//            name="user_car",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "car_id")
//    )
//    private Set<Car> enrolledCars = new HashSet<>();


//    @ManyToMany
//    @JoinTable(
//            name="user_oil",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "oil_id")
//    )
//    private Set<Oil> enrolledOilChanges = new HashSet<>();


    @OneToMany(mappedBy = "user")
    private Set<Car> cars = new HashSet<>();



    public void enrollCars(Car car) {
//        enrolledCars.add(car);
    }
    public void enrollOil(Oil oil) {
//        enrolledOilChanges.add(oil);
    }

}
