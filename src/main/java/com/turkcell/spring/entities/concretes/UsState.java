package com.turkcell.spring.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="us_states")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsState {
    @Id
    @Column(name = "state_id")
    private short stateId;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "state_abbr")
    private String stateAbbr;

    @Column(name = "state_region")
    private String stateRegion;
}
