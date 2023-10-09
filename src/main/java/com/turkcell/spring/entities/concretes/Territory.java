package com.turkcell.spring.entities.concretes;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="territories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Territory {
    @Id
    @Column(name = "territory_id")
    private String territoryId;

    @Column(name = "territory_description")
    private String territoryDescription;

    @OneToMany(mappedBy = "territory")
    private List<EmployeeTerritory> EmployeeTerritories;

    @ManyToOne()
    @JoinColumn(name = "region_id")
    private Region region;
}
