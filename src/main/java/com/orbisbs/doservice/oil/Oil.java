package com.orbisbs.doservice.oil;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orbisbs.doservice.cars.Car;
import com.orbisbs.doservice.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "oil_changes", schema = "public")
public class Oil {

    // id

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String mileage;
    private String date;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;


    public void enrollCarToOilChange(Car car) {
        this.car = car;
    }
    // user-oil manyToMany

//    @JsonIgnore
//    @ManyToMany(mappedBy = "enrolledOilChanges")
//    private Set<User> users = new HashSet<>();

}
