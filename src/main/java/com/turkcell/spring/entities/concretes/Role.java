package com.turkcell.spring.entities.concretes;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name="roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"users"})
public class Role {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
