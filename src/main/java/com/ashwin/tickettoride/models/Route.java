package com.ashwin.tickettoride.models;

import com.ashwin.tickettoride.enums.City;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="routes")
public class Route {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private City cityFrom;

    @Enumerated(EnumType.STRING)
    private City cityTo;

    private int length;
   // private boolean requiresLocomotive;

    @ManyToOne
    @JoinColumn(name="claimed_by_id")
    private Player claimedBy;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    private LocalDateTime claimedAt;
}
