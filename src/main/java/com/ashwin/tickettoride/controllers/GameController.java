package com.ashwin.tickettoride.controllers;

import com.ashwin.tickettoride.models.Game;
import com.ashwin.tickettoride.models.Player;
import com.ashwin.tickettoride.response.GameStateResponse;
import com.ashwin.tickettoride.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createGame(@RequestBody Map<String,String> request){
        String playerName = request.get("playerName");
        Game game = gameService.createGame(playerName);
        Map<String, String> response = new HashMap<>();
        response.put("gameId", game.getId());
        response.put("playerId", game.getCurrentPlayer().getId());
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{gameId}/join")
    public ResponseEntity<Map<String, String>> joinGame(
            @PathVariable String gameId,
            @RequestBody Map<String, String> request
    ) {
        String playerName = request.get("playerName");
        Player player = gameService.joinGame(gameId, playerName);
        Map<String, String> response = new HashMap<>();
        response.put("playerId", player.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{gameId}/start")
    public ResponseEntity<Void> startGame(@PathVariable String gameId) {
        gameService.startGame(gameId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameStateResponse> getGameState(@PathVariable String gameId) {
        GameStateResponse gameState = gameService.getGameState(gameId);
        return ResponseEntity.ok(gameState);
    }
}
