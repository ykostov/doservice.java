package com.orbisbs.doservice.oil;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orbisbs.doservice.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "oil_changes", schema = "public")
public class Oil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;



    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledOilChanges")
    private Set<User> users = new HashSet<>();
 
}
