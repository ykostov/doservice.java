package com.orbisbs.doservice.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orbisbs.doservice.oil.Oil;
import com.orbisbs.doservice.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
  private String mileage;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  public void enrollUserForCar(User user) {
    this.user = user;
  }

  @OneToMany(mappedBy = "car")
  private Set<Oil> oil = new HashSet<>();

}
