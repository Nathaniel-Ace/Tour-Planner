package com.tourplanner.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TOUR")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long tour_id;

    private String name;
    private String description;
    private String from_location;
    private String to_location;
    private String transport_type;
    private float distance;
    private int time;
//    private String route_info;

}
