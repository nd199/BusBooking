package com.naren.busbookingsystem.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Route")
@Table(name = "route")
@Getter
@Setter
@NoArgsConstructor
public class Route {

    @Id
    @SequenceGenerator(name = "route_id_seq",
            sequenceName = "route_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "route_id_seq")
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "start"
    )
    private String start;

    @Column(
            name = "destination"
    )
    private String destination;

    @OneToMany(
            mappedBy = "route",
            cascade = CascadeType.ALL
    )
    private List<Bus> buses = new ArrayList<>();

}
