package com.ashwin.tickettoride.services;

import com.ashwin.tickettoride.config.RouteCardConfig;
import com.ashwin.tickettoride.enums.CardColor;
import com.ashwin.tickettoride.models.Game;
import com.ashwin.tickettoride.models.Player;
import com.ashwin.tickettoride.models.Route;
import com.ashwin.tickettoride.response.PlayerStateResponse;
import com.ashwin.tickettoride.respository.GameRepository;
import com.ashwin.tickettoride.respository.PlayerRepository;
import com.ashwin.tickettoride.respository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private final GameRepository gameRepository;

    private final PlayerRepository playerRepository;

    private final RouteRepository routeRepository;

    @Autowired
    public PlayerService(GameRepository gameRepository, PlayerRepository playerRepository, RouteRepository routeRepository){
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.routeRepository = routeRepository;
    }

    public void drawTrainCards(String gameId, String playerId, List<Integer> cardIndices) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found"));
        List<CardColor> drawnCards = new ArrayList<>();
        for (int index : cardIndices) {
            if (index == -1) {
                drawnCards.add(game.getTrainCardDeck().remove(0));
            } else {
                drawnCards.add(game.getFaceUpCards().remove(index));
            }
        }
        player.getTrainCards().addAll(drawnCards);
        playerRepository.save(player);
    }

    public List<RouteCardConfig> drawRouteCards(String gameId, String playerId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found"));
        List<RouteCardConfig> drawnCards = game.getRouteCardDeck().subList(0, 3);
        game.getRouteCardDeck().subList(0, 3).clear();
        gameRepository.save(game);
        return drawnCards;
    }

    public void keepRouteCards(String gameId, String playerId, List<RouteCardConfig> routeCards) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found"));
        player.getRouteCards().addAll(routeCards);
        playerRepository.save(player);
    }

    public void claimRoute(String gameId, String playerId, String routeId, List<CardColor> cardsUsed) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found"));
        Route route = routeRepository.findById(routeId).orElseThrow(() -> new RuntimeException("Route not found"));
        if (route.getClaimedBy() != null) {
            throw new RuntimeException("Route already claimed");
        }
        route.setClaimedBy(player);
        route.setClaimedAt(LocalDateTime.now());
        player.getTrainCards().removeAll(cardsUsed);
        player.setScore(player.getScore() + route.getLength());
        routeRepository.save(route);
        playerRepository.save(player);
    }

    public void endTurn(String gameId, String playerId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        Player currentPlayer = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found"));
        int currentPlayerIndex = game.getPlayers().indexOf(currentPlayer);
        int nextPlayerIndex = (currentPlayerIndex + 1) % game.getPlayers().size();
        game.setCurrentPlayer(game.getPlayers().get(nextPlayerIndex));
        gameRepository.save(game);
    }

    public PlayerStateResponse getPlayerState(String gameId, String playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found"));
        return mapToPlayerStateResponse(player);
    }

    public void markPlayerReady(String gameId, String playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found"));
        player.setReady(true);
        playerRepository.save(player);
    }

    private PlayerStateResponse mapToPlayerStateResponse(Player player) {
        PlayerStateResponse response = new PlayerStateResponse();
        response.setId(player.getId());
        response.setName(player.getName());
        response.setTrainCars(player.getTrainCars());
        response.setTrainCards(player.getTrainCards());
        response.setRouteCards(player.getRouteCards());
        response.setScore(player.getScore());
        response.setReady(player.isReady());
        return response;
    }

}
