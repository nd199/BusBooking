package com.naren.busbookingsystem.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity(name = "Bus")
@Getter
@Setter
@Table(name = "bus")
@NoArgsConstructor
public class Bus {

    @Id
    @SequenceGenerator(name = "bus_id_seq",
            sequenceName = "bus_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bus_id_seq")
    private Long id;

    @Column(
            name = "name"
    )
    private String name;

    @Column(
            name = "number"
    )
    private String number;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "route_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "bus_route_fk"
            )
    )
    private Route route;

    public Bus(Long id, String name, String number, Route route) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.route = route;
    }

    public Bus(String name, String number, Route route) {
        this.name = name;
        this.number = number;
        this.route = route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return Objects.equals(id, bus.id) && Objects.equals(name, bus.name) && Objects.equals(number, bus.number) && Objects.equals(route, bus.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number, route);
    }

    @Override
    public String toString() {
        return "Bus{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", number='" + number + '\'' +
               ", route=" + route +
               '}';
    }
}
