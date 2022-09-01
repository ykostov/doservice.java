package com.orbisbs.doservice.users;


import com.orbisbs.doservice.cars.Car;
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
import javax.persistence.OneToMany;
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
  private String userName;
  private String password;
  //    private boolean active;
  private String roles;

  @OneToMany(mappedBy = "user")
  private Set<Car> cars = new HashSet<>();


}
