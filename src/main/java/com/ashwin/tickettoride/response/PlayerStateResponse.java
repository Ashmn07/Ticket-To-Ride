package com.ashwin.tickettoride.response;

import com.ashwin.tickettoride.config.RouteCardConfig;
import com.ashwin.tickettoride.enums.CardColor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStateResponse {
    private String id;
    private String name;
    private int trainCars;
    private List<CardColor> trainCards;
    private List<RouteCardConfig> routeCards;
    private int score;
    private boolean isReady;
}