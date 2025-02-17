package com.ashwin.tickettoride.config;

import com.ashwin.tickettoride.enums.CardColor;
import com.ashwin.tickettoride.enums.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteConfig {
    private String id; // e.g., "SEA-POR"
    private City cityFrom;
    private City cityTo;
    private int length;
    private CardColor color;
    private int tracks; // Number of tracks between cities (1 or 2)
}