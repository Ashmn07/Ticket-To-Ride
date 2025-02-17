package com.ashwin.tickettoride.response;

import com.ashwin.tickettoride.enums.CardColor;
import com.ashwin.tickettoride.enums.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteSummary {
    private String id;
    private City cityFrom;
    private City cityTo;
    private int length;
    private CardColor color;
    private boolean isClaimed;
    private String claimedByPlayerId;
}
