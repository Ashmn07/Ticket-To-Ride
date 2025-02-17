package com.ashwin.tickettoride.services;

import com.ashwin.tickettoride.config.RouteConfig;
import com.ashwin.tickettoride.models.Game;
import com.ashwin.tickettoride.models.Player;
import com.ashwin.tickettoride.models.Route;
import com.ashwin.tickettoride.response.GameStateResponse;
import com.ashwin.tickettoride.response.GameSummaryResponse;
import com.ashwin.tickettoride.response.PlayerSummary;
import com.ashwin.tickettoride.response.RouteSummary;
import com.ashwin.tickettoride.respository.GameRepository;
import com.ashwin.tickettoride.respository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;

    private final RouteConfigService routeConfigService;

    private final RouteRepository routeRepository;

    @Autowired
    public GameService(GameRepository gameRepository, RouteConfigService routeConfigService, RouteRepository routeRepository){
        this.gameRepository = gameRepository;
        this.routeConfigService = routeConfigService;
        this.routeRepository = routeRepository;
    }

    public Game createGame(String playerName) {
        Game game = new Game();
        Player player = new Player();
        player.setName(playerName);
        player.setGame(game);
        game.getPlayers().add(player);
        initializeGameRoutes(game);
        return gameRepository.save(game);
    }

    private void initializeGameRoutes(Game game){
        List<RouteConfig> routeConfigs = routeConfigService.loadRoutes();
        for (RouteConfig config : routeConfigs) {
            for (int i = 1; i <= config.getTracks(); i++) {
                Route route = new Route();
                route.setCityFrom(config.getCityFrom());
                route.setCityTo(config.getCityTo());
                route.setLength(config.getLength());
                route.setColor(config.getColor());
                route.setTrackNumber(i);
                route.setGame(game);
                routeRepository.save(route);
            }
        }
    }

    public GameStateResponse getGameState(String gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        return mapToGameStateResponse(game);
    }

    public List<GameSummaryResponse> getAllGames() {
        return gameRepository.findAll().stream()
                .map(this::mapToGameSummaryResponse)
                .collect(Collectors.toList());
    }

    private PlayerSummary mapToPlayerSummary(Player player) {
        return new PlayerSummary(
                player.getId(),
                player.getName(),
                player.getScore(),
                player.isReady()
        );
    }

    private RouteSummary mapToRouteSummary(Route route) {
        return new RouteSummary(
                route.getId(),
                route.getCityFrom(),
                route.getCityTo(),
                route.getLength(),
                route.getColor(),
                route.getClaimedBy() != null,
                route.getClaimedBy() != null ? route.getClaimedBy().getId() : null
        );
    }

    private GameStateResponse mapToGameStateResponse(Game game) {
        GameStateResponse response = new GameStateResponse();
        response.setId(game.getId());
        response.setStatus(game.getStatus());
        response.setPlayers(game.getPlayers().stream()
                .map(this::mapToPlayerSummary)
                .collect(Collectors.toList()));
        response.setCurrentPlayerId(game.getCurrentPlayer().getId());
        response.setTrainCardDeckCount(game.getTrainCardDeck().size());
        response.setRouteCardDeckCount(game.getRouteCardDeck().size());
        response.setFaceUpCards(game.getFaceUpCards());
        response.setRoutes(game.getRoutes().stream()
                .map(this::mapToRouteSummary)
                .collect(Collectors.toList()));
        return response;
    }

    private GameSummaryResponse mapToGameSummaryResponse(Game game) {
        GameSummaryResponse response = new GameSummaryResponse();
        response.setId(game.getId());
        response.setStatus(game.getStatus());
        response.setPlayerCount(game.getPlayers().size());
        response.setCreatedAt(game.getCreatedAt());
        return response;
    }

}
