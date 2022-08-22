package com.orbisbs.doservice.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orbisbs.doservice.users.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars", schema = "public")

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    private String brand;
    @NotEmpty
    private String model;
    @NotEmpty
    private String gen;
    private String drivetrain;
    @NotEmpty
    private String oilFrequency;


    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledCars")
    private Set<User> users = new HashSet<>();

}
