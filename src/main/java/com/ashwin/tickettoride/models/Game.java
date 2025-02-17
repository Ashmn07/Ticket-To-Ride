package com.ashwin.tickettoride.models;

import com.ashwin.tickettoride.config.RouteCardConfig;
import com.ashwin.tickettoride.converters.RouteCardConverter;
import com.ashwin.tickettoride.converters.TrainCardConverter;
import com.ashwin.tickettoride.enums.CardColor;
import com.ashwin.tickettoride.enums.GameStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Lombok;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    @Convert(converter = TrainCardConverter.class)
    private List<CardColor> trainCardDeck = new ArrayList<>();

    @Convert(converter = RouteCardConverter.class)
    private List<RouteCardConfig> routeCardDeck = new ArrayList<>();

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private List<Route> routes = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private GameStatus status = GameStatus.WAITING;

    private int turnOrderIndex=0;
    private int actionsTakenThisTurn = 0;

    @Version
    private Long version;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime lastUpdatedAt = LocalDateTime.now();
}
