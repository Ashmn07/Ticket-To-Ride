package com.ashwin.tickettoride.models;

import com.ashwin.tickettoride.config.RouteCardConfig;
import com.ashwin.tickettoride.converters.RouteCardConverter;
import com.ashwin.tickettoride.converters.TrainCardConverter;
import com.ashwin.tickettoride.enums.CardColor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private int trainCars = 45;

    @Convert(converter = TrainCardConverter.class)
    private List<CardColor> trainCards = new ArrayList<>();

    @Convert(converter = RouteCardConverter.class)
    private List<RouteCardConfig> routeCards = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    private int score = 0;
    private boolean isReady = false;
}
