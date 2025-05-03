package org.nurgisa.mapachu.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "zones")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "center_lat", nullable = false)
    private double centerLat;

    @Column(name = "center_lon", nullable = false)
    private double centerLon;

    @Column(name = "width", nullable = false)
    private double width;

    @Column(name = "height", nullable = false)
    private double height;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
