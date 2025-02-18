package com.ashwin.tickettoride.controllers;

import com.ashwin.tickettoride.config.RouteCardConfig;
import com.ashwin.tickettoride.enums.CardColor;
import com.ashwin.tickettoride.response.PlayerStateResponse;
import com.ashwin.tickettoride.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/games/{gameId}/players/{playerId}")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @PostMapping("/draw-train-cards")
    public ResponseEntity<Void> drawTrainCards(
            @PathVariable String gameId,
            @PathVariable String playerId,
            @RequestBody Map<String, List<Integer>> request
    ) {
        List<Integer> cardIndices = request.get("cardIndices");
        playerService.drawTrainCards(gameId, playerId, cardIndices);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/draw-route-cards")
    public ResponseEntity<Map<String, List<RouteCardConfig>>> drawRouteCards(
            @PathVariable String gameId,
            @PathVariable String playerId
    ) {
        List<RouteCardConfig> routeCards = playerService.drawRouteCards(gameId, playerId);
        Map<String, List<RouteCardConfig>> response = new HashMap<>();
        response.put("routeCards", routeCards);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/keep-route-cards")
    public ResponseEntity<Void> keepRouteCards(
            @PathVariable String gameId,
            @PathVariable String playerId,
            @RequestBody Map<String, List<RouteCardConfig>> request
    ) {
        List<RouteCardConfig> routeCards = request.get("routeCards");
        playerService.keepRouteCards(gameId, playerId, routeCards);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/claim-route")
    public ResponseEntity<Void> claimRoute(
            @PathVariable String gameId,
            @PathVariable String playerId,
            @RequestBody Map<String, Object> request
    ) {
        String routeId = (String) request.get("routeId");
        List<CardColor> cardsUsed = (List<CardColor>) request.get("cardsUsed");
        playerService.claimRoute(gameId, playerId, routeId, cardsUsed);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/end-turn")
    public ResponseEntity<Void> endTurn(
            @PathVariable String gameId,
            @PathVariable String playerId
    ) {
        playerService.endTurn(gameId, playerId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PlayerStateResponse> getPlayerState(
            @PathVariable String gameId,
            @PathVariable String playerId
    ) {
        PlayerStateResponse playerState = playerService.getPlayerState(gameId, playerId);
        return ResponseEntity.ok(playerState);
    }

    @PostMapping("/ready")
    public ResponseEntity<Void> markPlayerReady(
            @PathVariable String gameId,
            @PathVariable String playerId
    ) {
        playerService.markPlayerReady(gameId, playerId);
        return ResponseEntity.ok().build();
    }
}