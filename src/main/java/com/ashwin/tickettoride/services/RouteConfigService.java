package com.ashwin.tickettoride.services;

import com.ashwin.tickettoride.config.RouteConfig;
import com.ashwin.tickettoride.enums.CardColor;
import com.ashwin.tickettoride.enums.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteConfigService {

    public List<RouteConfig> loadRoutes() {

        return List.of(
                new RouteConfig("SEA-POR", City.SEATTLE, City.PORTLAND, 1, CardColor.GRAY, 2),
                new RouteConfig("LA-LV", City.LOS_ANGELES, City.LAS_VEGAS, 2, CardColor.RED, 1)
        );
    }
}