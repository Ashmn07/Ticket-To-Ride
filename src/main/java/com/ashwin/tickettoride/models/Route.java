package com.ashwin.tickettoride.models;

import com.ashwin.tickettoride.enums.CardColor;
import com.ashwin.tickettoride.enums.City;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="routes")
public class Route {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private City cityFrom;

    @Enumerated(EnumType.STRING)
    private City cityTo;

    @Enumerated(EnumType.STRING)
    private CardColor color;

    private int length;

    private int trackNumber;
   // private boolean requiresLocomotive;

    @ManyToOne
    @JoinColumn(name="claimed_by_id")
    private Player claimedBy;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    private LocalDateTime claimedAt;
}
