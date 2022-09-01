package com.orbisbs.doservice.oil;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orbisbs.doservice.cars.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

}
