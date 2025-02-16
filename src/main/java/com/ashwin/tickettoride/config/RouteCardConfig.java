package com.ashwin.tickettoride.config;

import com.ashwin.tickettoride.enums.City;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class RouteCardConfig {
    @Enumerated(EnumType.STRING)
    private City cityFrom;

    @Enumerated(EnumType.STRING)
    private City cityTo;

    private int points;
}