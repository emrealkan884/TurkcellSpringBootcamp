package com.turkcell.spring.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee_territories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTerritory {
    @Id
    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "territory_id")
    private Territory territory;
}
