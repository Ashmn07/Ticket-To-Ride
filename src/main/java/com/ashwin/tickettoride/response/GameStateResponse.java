package com.ashwin.tickettoride.response;

import com.ashwin.tickettoride.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameStateResponse {
    private String id;
    private GameStatus status;
    private List<PlayerSummary> players;
    private String currentPlayerId;
    private int trainCardDeckCount;
    private int routeCardDeckCount;
    private List<String> faceUpCards;
    private List<RouteSummary> routes;
}