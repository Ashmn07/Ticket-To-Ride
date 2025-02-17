package com.ashwin.tickettoride.controllers;

import com.ashwin.tickettoride.models.Game;
import com.ashwin.tickettoride.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createGame(@RequestBody Map<String,String> request){
        String playerName = request.get("playerName");
        Game game = gameService.createGame(playerName);
        Map<String, String> response = new HashMap<>();
        response.put("gameId", game.getId());
        response.put("playerId", game.getPlayers().get(0).getId());
        return ResponseEntity.ok(response);
    }
}
