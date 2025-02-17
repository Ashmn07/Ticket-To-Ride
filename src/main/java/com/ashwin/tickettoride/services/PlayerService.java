package com.ashwin.tickettoride.services;

import com.ashwin.tickettoride.models.Player;
import com.ashwin.tickettoride.response.PlayerStateResponse;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

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
