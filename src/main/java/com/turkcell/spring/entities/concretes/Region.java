package com.turkcell.spring.entities.concretes;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="region")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private short regionId;

    @Column(name = "region_description")
    private String regionDescription;

    @OneToMany(mappedBy = "region")
    private List<Territory> territories;
}