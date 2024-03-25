package com.example.jpademo.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TOURLOG")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tour_log_id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_tourID")
    private TourEntity tour;
//WSas
    private LocalDateTime dateTime;
    private String comment;
    private String difficulty;
    private float totalDistance;
    private String totalTime;
    private float rating;

}